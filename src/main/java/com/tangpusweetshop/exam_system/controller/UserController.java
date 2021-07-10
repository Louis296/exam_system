package com.tangpusweetshop.exam_system.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tangpusweetshop.exam_system.model.User;
import com.tangpusweetshop.exam_system.model.req.UserCreateReq;
import com.tangpusweetshop.exam_system.model.req.UserLoginReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import com.tangpusweetshop.exam_system.model.resp.UserListResp;
import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;
import com.tangpusweetshop.exam_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    Resp userCreate(User user, @RequestBody UserCreateReq userCreateReq){
        if (!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return userService.userCreate(userCreateReq);
    }

    @GetMapping("/delete")
    Resp userDelete(User user,@RequestParam(value = "UserID") String userId){
        if(!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return userService.userDelete(userId);
    }

    @GetMapping("/list")
    Resp userList(User user,@RequestParam(value = "Limit") int limit,@RequestParam(value = "Offset") int offset){
        if(!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return userService.userList(limit,offset);
    }

    @PostMapping("/update")
    Resp userUpdate(User user,@RequestBody UserCreateReq req){
        if(!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return userService.userUpdate(req);
    }

    Resp getNoPermissionResp(String type){
        Resp resp=new Resp();
        resp.setStatus("Error");
        resp.setError(type+" do not have permission use this api");
        return resp;
    }
}
