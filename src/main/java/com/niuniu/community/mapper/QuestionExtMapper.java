package com.niuniu.community.mapper;

import com.niuniu.community.model.Question;
import com.niuniu.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView (Question row);
}