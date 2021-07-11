package com.tangpusweetshop.exam_system.service;

import com.tangpusweetshop.exam_system.model.req.TestCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;

public interface TestService {
    Resp createTest(TestCreateReq req);
    Resp getTest(String testId);
    Resp getTestAnswer(String testId);
    Resp listTests(int offset,int limit);
}
