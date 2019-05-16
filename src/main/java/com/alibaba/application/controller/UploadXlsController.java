package com.alibaba.application.controller;

import com.alibaba.application.entity.FileInfo;
import com.alibaba.application.entity.User;
import com.alibaba.application.entity.Xls;
import com.alibaba.application.service.FileService;
import com.alibaba.application.utils.ExcelUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadXlsController {
    
    @Autowired
    private FileService fileService;

    private final static String DIR = "/upload/file/";
    @ResponseBody
    @RequestMapping("/uploadXls")
    public Object saveImg(MultipartFile file, HttpServletRequest request) {
        String name = "";
        FileInfo fileInfo = new FileInfo();
        if (null != file) {
            //得到文件原始名字
            String name1 = file.getOriginalFilename();
            name = name1.substring(0,name1.lastIndexOf("."));
            //设置文件新名字
            //获取当前日期+时分秒
            SimpleDateFormat format = new SimpleDateFormat("ddhhmmss");
            Date date = new Date();
            String dateNow = format.format(date);
            //随机名字
            //打印下后缀
            String aa = name1.substring(name1.lastIndexOf("."));
            System.out.println( aa);

            String fileName = name + dateNow + RandomStringUtils.randomAlphanumeric(4)
                    + name1.substring(name1.lastIndexOf("."));
            //获取当前路径
            String path = new ApplicationHome(UploadXlsController.class).getSource().getPath() + DIR;
//            String path = request.getSession().getServletContext().getRealPath("/") + "upload/file/";
            //创建路径
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File fileNow = new File(path + fileName);
            try {
                //存一下文件
                file.transferTo(fileNow);
                fileInfo.setName(fileName);
                System.out.println(fileNow.getAbsolutePath());
                //保存excel信息
                Xls xls = new Xls();
                xls.setXlsName(fileName);
                xls.setDate(new Date());
                User user = (User)request.getSession().getAttribute("user");
                xls.setUserId(user.getId());
                try {
                    fileService.insertToXls(xls);
                    int id = fileService.selectXlsByName(xls);
                    //进行读取excel操作
                    ExcelUtils.exportExcel(path + fileName,id,fileService);
                    fileInfo.setCode(FileInfo.SUCCESS_CODE);
                    Object o = JSON.toJSON(fileInfo);
                    return o;
                }catch (Exception e) {
                    System.out.println("操作数据库出现异常");
                    e.printStackTrace();
                    fileInfo.setCode(FileInfo.ERROR_CODE);
                    Object o = JSON.toJSON(fileInfo);
                    return o;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        fileInfo.setCode(FileInfo.FAIL_CODE);
        Object o = JSON.toJSON(fileInfo);
        return o;
    }

}
