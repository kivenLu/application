package com.alibaba.application.controller;

import com.alibaba.application.entity.*;
import com.alibaba.application.service.FileService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.XLSBUnsupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 查询问卷接口控制
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private FileService fileService;

    /**
     * 获取xls列表
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public XlsVo getList(Integer page
            , Integer limit, HttpServletRequest request,String xlsName) {
        System.out.println("当前页面情况：" + page + ",  " + limit + "," +xlsName);
        User user1 = (User)request.getSession().getAttribute("user");
        XlsInfo user = new XlsInfo();
        user.setXlsName(xlsName);
        user.setAnswer(user1.getAnswer());
        user.setId(user1.getId());
        user.setPower(user1.getPower());
        user.setLimit(limit);
        user.setPage(((page) - 1) * limit);
        List<Xls> surveys = fileService.selectXlsById(user);
        System.out.println("go:" + surveys);
        XlsVo vo = new XlsVo();
        vo.setCode(0);
        vo.setCount(fileService.selectCountById(user));
        vo.setData(surveys);
        vo.setMsg("查询成功");
        String s = JSON.toJSONString(vo);
        System.out.println(s);
        return vo;
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping("/del-survey/{id}")
    public String delSurvey(@PathVariable(name = "id")Integer id) {
        if (null != id) {
            try{
                fileService.delXlsById(id);
                fileService.delSurveyById(id);
                return ResponseBody.SUCCESS;
            }catch (Exception e) {
                System.out.println("当前id为空！");
                e.printStackTrace();
                return ResponseBody.FAIL;
            }
        }else {
            return ResponseBody.FAIL;
        }
    }

}
