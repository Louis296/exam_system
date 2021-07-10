package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.mapper.QuestionMapper;
import com.tangpusweetshop.exam_system.model.Question;
import com.tangpusweetshop.exam_system.model.req.QuestionCreateReq;
import com.tangpusweetshop.exam_system.model.resp.QuestionListResp;
import com.tangpusweetshop.exam_system.model.resp.QuestionResp;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper){
        this.questionMapper=questionMapper;
    }

    @Override
    public Resp questionCreate(QuestionCreateReq req) {
        Question question=reqToQuestion(req);
        Date data=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("yyyymmddhhmmss");
        question.setQuestionId("Q"+ft.format(data));
        questionMapper.createQuestion(question);
        QuestionResp resp=new QuestionResp();
        resp.setStatus("Success");
        resp.setQuestionId(question.getQuestionId());
        return resp;
    }

    @Override
    public Resp questionDelete(String questionId) {
        questionMapper.disableQuestionByQuestionId(questionId);
        QuestionResp resp=new QuestionResp();
        resp.setStatus("Success");
        resp.setQuestionId(questionId);
        return resp;
    }

    @Override
    public Resp questionUpdate(QuestionCreateReq req,String questionId) {
        Question question=reqToQuestion(req);
        question.setQuestionId(questionId);
        questionMapper.updateQuestion(question);
        QuestionResp resp=new QuestionResp();
        resp.setStatus("Success");
        resp.setQuestionId(questionId);
        return resp;
    }

    @Override
    public Resp questionList(int offset, int limit) {
        QuestionListResp resp=new QuestionListResp();
        resp.setStatus("Success");
        resp.setOffset(offset);
        resp.setLimit(limit);
        resp.setList(questionMapper.listQuestionAll(limit,limit*(offset-1)));
        return resp;
    }

    @Override
    public Resp questionGet(String questionId) {
        QuestionResp resp=new QuestionResp();
        questionMapper.getQuestionByQuestionId(questionId);
        return null;
    }

    @Override
    public Resp questionDisableList(int offset, int limit) {
        return null;
    }

    @Override
    public Resp questionDeleteVerify(String questionId, int submit) {
        return null;
    }

    private Question reqToQuestion(QuestionCreateReq req){
        Question question=new Question();
        question.setQuestion(req.getQuestion());
        question.setAnswer(req.getAnswer());
        question.setChapterId(req.getChapterId());
        question.setChapterTitle(req.getChapterTitle());
        question.setChapterKey(req.getChapterKey());
        question.setLevel(Long.parseLong(req.getLevel()));
        question.setKeyword(req.getKeyWord());
        return question;
    }
}
