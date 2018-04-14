package com.example.toshiba.zhuce;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Toshiba on 2017/9/6.
 */

public class RecondAttri implements Serializable {

    private String goodsID;
    private String recordId;
    private String goodsName;
    private String recordDate;
    private String username;
    private int sumPrice;

    public String getGoodsID(){return goodsID;}
    public String getRecordId(){return recordId;}
    public String getGoodsName(){return goodsName;}
    public String getRecordDate(){return recordDate;}
    public String getUsername(){return username;}
    public int getSumPrice(){return sumPrice;}

    public void setGoodsID(String goodsID){this.goodsID=goodsID;}
    public void setRecordId(String recordId){this.recordId=recordId;}
    public void setGoodsName(String goodsName){this.goodsName=goodsName;}
    public void setRecordDate(String recordDate){this.recordDate=recordDate;}
    public void setUsername(String username){this.username=username;}
    public void setSumPrice(int sumPrice){this.sumPrice=sumPrice;}
}
