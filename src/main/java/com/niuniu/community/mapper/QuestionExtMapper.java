package com.niuniu.community.mapper;

import com.niuniu.community.model.Question;
import com.niuniu.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {

    int incView (Question row);
    int incCommentCount(Question record);
}