package com.tangpusweetshop.exam_system.mapper;

import com.tangpusweetshop.exam_system.model.Question;
import com.tangpusweetshop.exam_system.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    void createTestWithList(List<Test> test);
    List<Question> getTestWithTestId(String id);
    List<String> listTests(int limit,int offset);
}
