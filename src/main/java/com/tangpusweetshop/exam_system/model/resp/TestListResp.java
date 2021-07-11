package com.tangpusweetshop.exam_system.model.resp;

import java.util.List;

public class TestListResp extends Resp{
    int offset;
    int limit;
    List<String> list;

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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
