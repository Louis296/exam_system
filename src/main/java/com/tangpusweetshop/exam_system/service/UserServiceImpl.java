package com.tangpusweetshop.exam_system.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tangpusweetshop.exam_system.ExamSystemApplication;
import com.tangpusweetshop.exam_system.mapper.UserMapper;
import com.tangpusweetshop.exam_system.model.User;
import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;
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
}
