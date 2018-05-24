package com.example.athis.myapplication.dataBean;

import java.util.List;

public class SelectBean {

    /**
     * lid : C01
     * lname : 升学辅导
     * hobbies : [{"lid":"A0001","lname":"幼小衔接","flid":"C01"},{"lid":"A0002","lname":"小学同步","flid":"C01"},{"lid":"A0003","lname":"小升初","flid":"C01"},{"lid":"A0004","lname":"初中同步","flid":"C01"},{"lid":"A0005","lname":"中考冲刺","flid":"C01"},{"lid":"A0006","lname":"高中同步","flid":"C01"},{"lid":"A0007","lname":"高考冲刺","flid":"C01"},{"lid":"A0008","lname":"艺考","flid":"C01"},{"lid":"A0263","lname":"专升本","flid":"C01"},{"lid":"A0009","lname":"考研辅导","flid":"C01"},{"lid":"A0230","lname":"成人学历教育","flid":"C01"}]
     */

    private String lid;
    private String lname;
    private List<HobbiesBean> hobbies;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<HobbiesBean> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbiesBean> hobbies) {
        this.hobbies = hobbies;
    }

    public static class HobbiesBean {
        /**
         * lid : A0001
         * lname : 幼小衔接
         * flid : C01
         */

        private String lid;
        private String lname;
        private String flid;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getFlid() {
            return flid;
        }

        public void setFlid(String flid) {
            this.flid = flid;
        }
    }
}
