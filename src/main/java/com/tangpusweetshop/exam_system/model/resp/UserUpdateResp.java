package com.tangpusweetshop.exam_system.model.resp;

import com.tangpusweetshop.exam_system.model.User;

public class UserUpdateResp extends Resp{
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
