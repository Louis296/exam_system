package com.tangpusweetshop.exam_system.mapper;

import com.tangpusweetshop.exam_system.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUserIdAndPassword(String id,String pd);
}
