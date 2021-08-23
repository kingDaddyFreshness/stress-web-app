package com.jhilgedick.utils;

import com.jhilgedick.data.MyGlobalVars;

public class MyResponse {
    private MyGlobalVars myGlobalVars = MyGlobalVars.getInstance();
    private String message = "";

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public MyGlobalVars getMyGlobalVars() {
        return this.myGlobalVars;
    }
}
