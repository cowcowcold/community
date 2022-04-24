package com.niuniu.community.Controller;

import com.niuniu.community.dto.CommentDTO;
import com.niuniu.community.dto.ResultDTO;
import com.niuniu.community.mapper.CommentMapper;
import com.niuniu.community.model.Comment;
import com.niuniu.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(2002,"未登录时不能进行评论，请先登录！");
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentMapper.insertSelective(comment);
        Map<Object,Object> ObjectHashMap = new HashMap<Object,Object>();
        ObjectHashMap.put("message","成功  ");

        return ObjectHashMap;
    }

}
