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
import java.util.List;

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
        SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddHHmmss");
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
        List<Question> list= questionMapper.listQuestionAll(limit,limit*(offset-1));
        resp.setOffset(offset);
        resp.setLimit(limit);
//        for(Question question:list){
//            question.setAnswer("");
//        }
        resp.setList(list);
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp questionGet(String questionId) {
        Question question=questionMapper.getQuestionByQuestionId(questionId);
        return questionToResp(question);
    }

    @Override
    public Resp questionDisableList(int offset, int limit) {
        QuestionListResp resp=new QuestionListResp();
        List<Question> list= questionMapper.listQuestionDisable(limit,limit*(offset-1));
        for(Question question:list){
            question.setAnswer("");
        }
        resp.setList(list);
        resp.setOffset(offset);
        resp.setLimit(limit);
        resp.setStatus("Success");
        return resp;
    }

    @Override
    public Resp questionDeleteVerify(String questionId, int submit) {
        QuestionResp resp=new QuestionResp();
        if(submit==0){
            questionMapper.enableQuestionByQuestionId(questionId);
            resp.setStatus("Success");
            resp.setQuestionId(questionId);
        }else if (submit==1){
            questionMapper.deleteQuestionByQuestionId(questionId);
            resp.setStatus("Success");
            resp.setQuestionId(questionId);
        }else {
            resp.setStatus("Error");
            resp.setError("Invalid submit value");
        }
        return resp;
    }

    @Override
    public Resp questionAnswer(String questionId) {
        Question question=questionMapper.getQuestionByQuestionId(questionId);
        QuestionResp resp=new QuestionResp();
        resp.setAnswer(question.getAnswer());
        resp.setStatus("Success");
        return resp;
    }

    private QuestionResp questionToResp(Question question){
        QuestionResp resp=new QuestionResp();
        resp.setQuestionId(question.getQuestionId());
        resp.setQuestion(question.getQuestion());
        resp.setChapterId(question.getChapterId());
        resp.setChapterTitle(question.getChapterTitle());
        resp.setChapterKey(question.getChapterKey());
        resp.setLevel(question.getLevel());
        resp.setKeyword(question.getKeyword());
        return resp;
    }

    private Question reqToQuestion(QuestionCreateReq req){
        Question question=new Question();
        question.setQuestion(req.getQuestion());
        question.setAnswer(req.getAnswer());
        question.setChapterId(req.getChapterId());
        question.setChapterTitle(req.getChapterTitle());
        question.setChapterKey(req.getChapterKey());
        question.setLevel(Long.parseLong(req.getLevel()));
        question.setKeyword(req.getKeyword());
        return question;
    }
}
