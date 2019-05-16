package com.alibaba.application.controller;

import com.alibaba.application.entity.SelectModel;
import com.alibaba.application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统核心代码*********：数据分析
 */
@Controller
public class AnalysisController {

    @Autowired
    private FileService fileService;

    /**
     * 数据分析
     * @return
     */
    @RequestMapping("/analysis/{id}")
    public String analysis(@PathVariable(name = "id")Integer id, HttpServletRequest request) {
        System.out.println("id " + id);
        //查询出的问卷所有选项和答案
//        List<Survey> surveys = fileService.selectSurveyByXlsId(id);
        Map<String,Map<String,Integer>> analysisMap = new HashMap<>();

        //查询出所有的问题
        List<String> questions = fileService.selectQuestionCountByXlsId(id);
        SelectModel sm = new SelectModel();
        SelectModel sem = new SelectModel();
        sem.setId(id);
        sm.setId(id);
        for (String q : questions) {
            sm.setQuestion(q);
            List<String> answers = fileService.selectAnswerByQuestion(sm);
            Map<String,Integer> map = new HashMap<>();
            for(String a : answers) {
                sem.setQuestion(q);
                sem.setAnswer(a);
                int count = fileService.selectAnswerCountByAnswer(sem);
                map.put(a,count);
            }
            analysisMap.put(q,map);

        }
        System.out.println("开始统计答案了：");
        Iterator<Map.Entry<String, Map<String, Integer>>> it = analysisMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, Integer>> next = it.next();
            String key = next.getKey();
            System.out.println(key);
            Map<String, Integer> value = next.getValue();
            Iterator<Map.Entry<String, Integer>> it2 = value.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, Integer> entry = it2.next();
                String key1 = entry.getKey();
                Integer count = entry.getValue();
                System.out.println(key1 + "," +count);
            }
        }
        request.setAttribute("map",analysisMap);
        return "/pages/questionnaire/analysis.html";
    }
}
