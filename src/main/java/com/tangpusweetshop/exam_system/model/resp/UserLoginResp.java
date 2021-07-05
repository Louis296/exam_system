package com.tangpusweetshop.exam_system.model.resp;

public class UserLoginResp extends Resp{
    String authorize;
    String type;

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
