package com.alibaba.application.service.impl;

import com.alibaba.application.entity.*;
import com.alibaba.application.mapper.FileMapper;
import com.alibaba.application.mapper.UserMapper;
import com.alibaba.application.service.FileService;
import com.alibaba.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    public int insertToXls(Xls xls) {
        return fileMapper.insertToXls(xls);
    }

    @Override
    public void insertToSurvey(Survey survey) {
        fileMapper.insertToSurvey(survey);
    }

    @Override
    public int selectXlsByName(Xls xls) {
        return fileMapper.selectXlsByName(xls);
    }

    @Override
    public List<Xls> selectXlsById(XlsInfo user) {
        return fileMapper.selectXlsById(user);
    }

    @Override
    public int selectCountById(XlsInfo user) {
        return fileMapper.selectCountById(user);
    }

    @Override
    public List<Survey> selectSurveyByXlsId(Integer id) {

        return fileMapper.selectSurveyByXlsId(id);
    }

    @Override
    public List<String> selectQuestionCountByXlsId(Integer id) {
        return fileMapper.selectQuestionCountByXlsId(id);
    }

    @Override
    public List<String> selectAnswerByQuestion(SelectModel selectModel) {
        return fileMapper.selectAnswerByQuestion(selectModel);
    }

    @Override
    public int selectAnswerCountByAnswer(SelectModel selectModel) {
        return fileMapper.selectAnswerCountByAnswer(selectModel);
    }

    @Override
    public void delXlsById(Integer id) {
        fileMapper.delXlsById(id);
    }

    @Override
    public void delSurveyById(Integer id) {
        fileMapper.delSurveyById(id);
    }
}
