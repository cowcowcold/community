package com.niuniu.community.mapper;

import com.niuniu.community.model.Comment;
import com.niuniu.community.model.CommentExample;
import com.niuniu.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}