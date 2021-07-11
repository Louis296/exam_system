package com.tangpusweetshop.exam_system.model.resp;

import com.tangpusweetshop.exam_system.model.Question;

import java.util.List;

public class TestQuestionResp extends Resp{

    List<Question> list;

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }


}
