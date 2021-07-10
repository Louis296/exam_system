package com.tangpusweetshop.exam_system.model.resp;

import com.tangpusweetshop.exam_system.model.User;

import java.util.List;

public class UserListResp extends Resp {
    int limit;
    int offset;
    List<User> list;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
