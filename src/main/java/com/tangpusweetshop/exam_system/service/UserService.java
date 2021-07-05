package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.model.resp.UserLoginResp;


public interface UserService {
     UserLoginResp userLogin(String userId, String password);

}
