package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.model.req.UserCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;


public interface UserService {
     UserLoginResp userLogin(String userId, String password);
     Resp userCreate(UserCreateReq req);
     Resp userDelete(String userId);
     Resp userList(int limit,int offset);
     Resp userUpdate(UserCreateReq req);
}
