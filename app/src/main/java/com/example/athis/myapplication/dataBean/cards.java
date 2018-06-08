package com.example.athis.myapplication.dataBean;

import java.io.Serializable;

/**
 * Created by This on 2018/4/23.
 */

public class cards implements Serializable{

    public cards(String name){
        this.name = name;
    }

    public cards(String name, int goTo){
        this.name = name;
        this.goTo = goTo;
    }
    public String name;
    public int goTo;
}
