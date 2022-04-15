package com.niuniu.community.Controller;

import com.niuniu.community.dto.PageDTO;
import com.niuniu.community.mapper.UserMapper;
import com.niuniu.community.model.User;
import com.niuniu.community.model.UserExample;
import com.niuniu.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionServicer;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size ){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length!=0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    if(users.size()!=0){
                        request.getSession().setAttribute("user",users.get(0));
                    }
                    break;
                }
            }
        }
        PageDTO pageDTO=questionServicer.list(page,size);
        model.addAttribute("pageDTO",pageDTO);
        return "index";
    }
}
