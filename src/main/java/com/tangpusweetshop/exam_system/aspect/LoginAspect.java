package com.tangpusweetshop.exam_system.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tangpusweetshop.exam_system.ExamSystemApplication;
import com.tangpusweetshop.exam_system.mapper.UserMapper;
import com.tangpusweetshop.exam_system.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {

    private final UserMapper userMapper;

    @Autowired
    public LoginAspect(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Before("execution(* com.tangpusweetshop.exam_system.controller..*.*(..))  && !execution(* com.tangpusweetshop.exam_system.controller.UserController.userLogin(..)) ")
    public void doLoginCheck(JoinPoint joinPoint){
        try{
            Object[] obj=joinPoint.getArgs();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("Authorize");
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(ExamSystemApplication.secret)).build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            String userId=decodedJWT.getClaim("aud").asString();
            User user=userMapper.getUserByUserId(userId);
            for (Object argParam:obj){
                if (argParam instanceof User){
                    User userParam=(User) argParam;
                    userParam.setType(user.getType());
                    userParam.setName(user.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
