package com.tangpusweetshop.exam_system.model.resp;

import com.tangpusweetshop.exam_system.model.Answer;

import java.util.List;

public class TestAnswerResp extends Resp{
    List<Answer> list;

    public List<Answer> getList() {
        return list;
    }

    public void setList(List<Answer> list) {
        this.list = list;
    }
}

