package com.tangpusweetshop.exam_system.mapper;

import com.tangpusweetshop.exam_system.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    void createQuestion(Question question);
    void disableQuestionByQuestionId(String id);
    void enableQuestionByQuestionId(String id);
    void deleteQuestionByQuestionId(String id);
    void updateQuestion(Question question);
    List<Question> listQuestionAll(int limit,int offset);
    Question getQuestionByQuestionId(String id);
    List<Question> listQuestionDisable(int limit,int offset);
    List<Question> getQuestionsWithTestCondition(int number,int level,String chapterTitle,String chapterKey);
}
