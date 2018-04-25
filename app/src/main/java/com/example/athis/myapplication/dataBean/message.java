package com.example.athis.myapplication.dataBean;

/**
 * Created by This on 2018/4/24.
 */

public class message {

    public static final int STATUS_SEND = 0;
    public static final int STATUS_RECEIVE = 1;

    public message(String content, int status) {
        this.content = content;
        this.status = status;
    }

    public String content;
    public int status;

}
