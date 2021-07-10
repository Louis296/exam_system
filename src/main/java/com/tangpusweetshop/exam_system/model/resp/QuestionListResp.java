package com.tangpusweetshop.exam_system.model.resp;

import com.tangpusweetshop.exam_system.model.Question;

import java.util.List;

public class QuestionListResp extends Resp{
    int offset;
    int limit;
    List<Question> list;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }
}
