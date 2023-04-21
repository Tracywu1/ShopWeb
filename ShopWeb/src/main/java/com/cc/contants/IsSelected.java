package com.cc.contants;

public enum IsSelected {
    /**
     *
     */
    YES(1,"已勾选"),
    /**
     *
     */
    NO(2,"未勾选");

    private int num;

    private String judge;

    IsSelected(int num, String judge) {
        this.num = num;
        this.judge = judge;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

}
