package com.niuniu.community.service;


import com.niuniu.community.dto.PageDTO;
import com.niuniu.community.dto.QuestionDTO;
import com.niuniu.community.exception.CustomizeErrorCode;
import com.niuniu.community.exception.CustomizeException;
import com.niuniu.community.mapper.QuestionExtMapper;
import com.niuniu.community.mapper.QuestionMapper;
import com.niuniu.community.mapper.UserMapper;
import com.niuniu.community.model.Question;
import com.niuniu.community.model.QuestionExample;
import com.niuniu.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PageDTO list(Integer page, Integer size) {

        Integer offset=size*(page-1);
        List<Question> questions= questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS =new ArrayList<QuestionDTO>();
        PageDTO pageDTO =new PageDTO();
        for(Question question:questions){
            User user= userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);
        Integer totalCount= (int)questionMapper.countByExample(new QuestionExample());
        pageDTO.setPagination(totalCount,page,size);
        return pageDTO ;
    }

    public PageDTO listByUserId(Integer userId, Integer page, Integer size) {

        Integer offset=size*(page-1);
        if(offset<=0){
            offset=1;
        }
//        List<Question> questions= questionMapper.listByUserId(userId,offset,size);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS =new ArrayList<QuestionDTO>();
        PageDTO pageDTO =new PageDTO();
        for(Question question:questions){
            User user= userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO =new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOS);

        Integer totalCount= (int)questionMapper.countByExample(new QuestionExample());
        pageDTO.setPagination(totalCount,page,size);
        return pageDTO ;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user =userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.insertSelective(question);
        }else{
            Question updateQuestion =new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample =new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion,questionExample);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(1);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
