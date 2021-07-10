package com.tangpusweetshop.exam_system.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tangpusweetshop.exam_system.ExamSystemApplication;
import com.tangpusweetshop.exam_system.mapper.UserMapper;
import com.tangpusweetshop.exam_system.model.User;
import com.tangpusweetshop.exam_system.model.req.UserCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import com.tangpusweetshop.exam_system.model.resp.UserListResp;
import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;
import com.tangpusweetshop.exam_system.model.resp.UserUpdateResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    final private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public UserLoginResp userLogin(String userId, String password) {
        UserLoginResp resp=new UserLoginResp();
        try{
            User user=userMapper.getUserByUserIdAndPassword(userId,password);
            resp.setStatus("Success");
            resp.setType(user.getType());
            String token= JWT.create().withAudience(user.getUserId()).sign(Algorithm.HMAC256(ExamSystemApplication.secret));
            resp.setAuthorize(token);
        }catch (Exception e){
            resp.setStatus("Error");
            resp.setError("wrong user or wrong password");
        }
        return resp;
    }

    @Override
    public Resp userCreate(UserCreateReq req) {
        Resp resp=new Resp();
        if (!(req.getType().equals("Principal")||req.getType().equals("Student"))){
            resp.setStatus("Error");
            resp.setError("Invalid user type");
            return resp;
        }
        User user=new User();
        user.setUserId(req.getUserID());
        user.setPassword(req.getPassword());
        user.setType(req.getType());
        user.setName(req.getName());
        user.setMajor(req.getMajor());
        user.setCode(req.getCode());
        user.setGrade(req.getGrade());
        try{
            userMapper.createUser(user);
        }catch (Exception e){
            resp.setStatus("Error");
            resp.setError("Sql error");
        }
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp userDelete(String userId) {
        userMapper.deleteUserByUserId(userId);
        Resp resp=new Resp();
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp userList(int limit, int offset) {
        UserListResp resp=new UserListResp();
        resp.setLimit(limit);
        resp.setOffset(offset);
        resp.setList(userMapper.getUserList(limit,limit*(offset-1)));
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp userUpdate(UserCreateReq req) {
        UserUpdateResp resp=new UserUpdateResp();
        if (!(req.getType().equals("Principal")||req.getType().equals("Student"))){
            resp.setStatus("Error");
            resp.setError("Invalid user type");
            return resp;
        }
        User user=new User();
        user.setUserId(req.getUserID());
        user.setPassword(req.getPassword());
        user.setType(req.getType());
        user.setName(req.getName());
        user.setMajor(req.getMajor());
        user.setCode(req.getCode());
        user.setGrade(req.getGrade());
        userMapper.updateUser(user);
        resp.setStatus("Success");
        resp.setUser(user);
        return resp;
    }

}
