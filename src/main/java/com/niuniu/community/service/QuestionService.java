package com.niuniu.community.service;


import com.niuniu.community.dto.PageDTO;
import com.niuniu.community.dto.QuestionDTO;
import com.niuniu.community.mapper.QuestionMapper;
import com.niuniu.community.mapper.UserMapper;
import com.niuniu.community.model.Question;
import com.niuniu.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageDTO list(Integer page, Integer size) {

        Integer offset=size*(page-1);
        List<Question> questions= questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS =new ArrayList<QuestionDTO>();
        PageDTO pageDTO =new PageDTO();
        for(Question question:questions){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);

        Integer totalCount= questionMapper.count();
        pageDTO.setPagination(totalCount,page,size);
        return pageDTO ;
    }

    public PageDTO listByUserId(Integer userId, Integer page, Integer size) {

        Integer offset=size*(page-1);
        if(offset<=0){
            offset=1;
        }
        List<Question> questions= questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOS =new ArrayList<QuestionDTO>();
        PageDTO pageDTO =new PageDTO();
        for(Question question:questions){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);

        Integer totalCount= questionMapper.count();
        pageDTO.setPagination(totalCount,page,size);
        return pageDTO ;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user =userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
