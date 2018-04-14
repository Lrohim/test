package com.example.toshiba.zhuce;

import java.io.Serializable;

/**
 * Created by Toshiba on 2017/9/1.
 */

public class UserAttri implements Serializable {
    private String username;
    private String password;
    private String phoneNum;
    private String address;
    private int remainSum;
    private int credit;
    private int logcondition;

    public UserAttri(){

    }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getPhoneNum(){return this.phoneNum;}
    public String getAddress(){return this.address;}
    public int getRemainSum(){return this.remainSum;}
    public int getCredit(){return this.credit;}
    public int getLogcondition(){return logcondition;}

    public void setUsername(String U){
        this.username=U;
    }
    public void setPassword(String P){
        this.password=P;
    }
    public void setPhoneNum(String P){this.phoneNum=P;}
    public void setAddress(String A){
        this.address=A;
    }
    public void setRemainSum(int R){
        this.remainSum=R;
    }
    public void setCredit(int C){
        this.credit=C;
    }
    public void setLogcondition(int L){this.logcondition=L;}
}
