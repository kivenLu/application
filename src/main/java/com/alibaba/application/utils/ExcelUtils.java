package com.alibaba.application.utils;

import com.alibaba.application.entity.Survey;
import com.alibaba.application.service.FileService;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * excel导入工具类
 */
public class ExcelUtils {

    public static void exportExcel(String path, Integer id, FileService fileService) {
        File f=new File(path);
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ExcelLogs logs =new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

        for(Map m : importExcel){
            Iterator it = m.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                if (key .equals( "序号" ))
                    continue;
                Survey survey = new Survey();
                survey.setAnswer(value);
                survey.setQuestion(key);
                survey.setXlsId(id);
                fileService.insertToSurvey(survey);
                System.out.println("Key = " + key + ", Value = " + value);
            }
        }
    }
}
