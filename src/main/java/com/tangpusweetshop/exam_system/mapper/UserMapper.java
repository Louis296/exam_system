package com.tangpusweetshop.exam_system.mapper;

import com.tangpusweetshop.exam_system.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByUserIdAndPassword(String id,String pd);
    User getUserByUserId(String id);
    void createUser(User user);
    void deleteUserByUserId(String id);
    void updateUser(User user);
    List<User> getUserList(int limit,int offset);
}
