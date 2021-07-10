package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.model.req.QuestionCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;

public interface QuestionService {
    Resp questionCreate(QuestionCreateReq req);
    Resp questionDelete(String questionId);
    Resp questionUpdate(QuestionCreateReq req,String questionId);
    Resp questionList(int offset,int limit);
    Resp questionGet(String questionId);
    Resp questionDisableList(int offset,int limit);
    Resp questionDeleteVerify(String questionId,int submit);
}
