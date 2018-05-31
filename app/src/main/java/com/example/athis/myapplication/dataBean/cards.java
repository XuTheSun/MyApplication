package com.example.athis.myapplication.dataBean;

/**
 * Created by This on 2018/4/23.
 */

public class cards {

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
