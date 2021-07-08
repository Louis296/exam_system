package com.tangpusweetshop.exam_system.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tangpusweetshop.exam_system.model.req.UserLoginReq;
import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;
import com.tangpusweetshop.exam_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/login")
    UserLoginResp userLogin(@RequestBody UserLoginReq userLoginReq){
        return userService.userLogin(userLoginReq.getUserId(),userLoginReq.getPassword());
    }

    @PostMapping("/create")
    String userCreate(){
        return "success";
    }

}
