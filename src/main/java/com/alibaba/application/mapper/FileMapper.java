package com.alibaba.application.mapper;

import com.alibaba.application.entity.SelectModel;
import com.alibaba.application.entity.Survey;
import com.alibaba.application.entity.Xls;
import com.alibaba.application.entity.XlsInfo;

import java.util.List;

/**
 * xls和survey数据库操作层
 */
public interface FileMapper {
    int insertToXls(Xls xls);
    void insertToSurvey(Survey survey);
    int selectXlsByName(Xls xls);
    List<Xls> selectXlsById(XlsInfo  user);
    int selectCountById(XlsInfo user);
    List<Survey> selectSurveyByXlsId(Integer id);
    List<String> selectQuestionCountByXlsId(Integer id);
    List<String> selectAnswerByQuestion(SelectModel selectModel);
    int selectAnswerCountByAnswer(SelectModel selectModel);
    void delXlsById(Integer id);
    void delSurveyById(Integer id);
}
