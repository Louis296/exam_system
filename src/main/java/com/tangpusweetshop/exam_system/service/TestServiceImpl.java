package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.mapper.QuestionMapper;
import com.tangpusweetshop.exam_system.mapper.TestMapper;
import com.tangpusweetshop.exam_system.model.Answer;
import com.tangpusweetshop.exam_system.model.Question;
import com.tangpusweetshop.exam_system.model.Test;
import com.tangpusweetshop.exam_system.model.req.TestCreateReq;
import com.tangpusweetshop.exam_system.model.resp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    private final TestMapper testMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public TestServiceImpl(TestMapper testMapper,QuestionMapper questionMapper){
        this.testMapper=testMapper;
        this.questionMapper=questionMapper;
    }

    @Override
    public Resp createTest(TestCreateReq req) {
        List<Question> questionList=questionMapper.getQuestionsWithTestCondition(
                Integer.parseInt(req.getNumber()),
                Integer.parseInt(req.getLevel()),
                req.getChapterTitle(),
                req.getChapterKey()
        );
        List<Test> testRelations=new ArrayList<>();
        Date data=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddHHmmss");
        String testId="T"+ft.format(data);
        for (Question question:questionList){
            Test test=new Test();
            test.setTestId(testId);
            test.setQuestionId(question.getQuestionId());
            testRelations.add(test);
        }
        testMapper.createTestWithList(testRelations);
        TestResp resp=new TestResp();
        resp.setStatus("Success");
        resp.setTestId(testId);
        return resp;
    }

    @Override
    public Resp getTest(String testId) {
        List<Question> questionList=testMapper.getTestWithTestId(testId);
//        for(Question question:questionList){
//            question.setAnswer("");
//        }
        TestQuestionResp resp=new TestQuestionResp();
        resp.setList(questionList);
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp getTestAnswer(String testId) {
        List<Question> questionList=testMapper.getTestWithTestId(testId);
        List<Answer> list=new ArrayList<>();
        for (Question question:questionList){
            Answer answer=new Answer();
            answer.setQuestionId(question.getQuestionId());
            answer.setAnswer(question.getAnswer());
            list.add(answer);
        }
        TestAnswerResp resp=new TestAnswerResp();
        resp.setList(list);
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp listTests(int offset, int limit) {
        List<String> testIds=testMapper.listTests(limit,limit*(offset-1));
        TestListResp resp=new TestListResp();
        resp.setLimit(limit);
        resp.setOffset(offset);
        resp.setList(testIds);
        resp.setStatus("Success");
        return resp;
    }
}
