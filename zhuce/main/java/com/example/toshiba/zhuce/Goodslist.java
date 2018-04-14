package com.example.toshiba.zhuce;

import java.io.Serializable;

/**
 * Created by Toshiba on 2017/9/9.
 */

public class Goodslist implements Serializable {

    private String goodsID;
    private int goodsImage;
    private int goodsNum;
    private int goodsStock;
    private int goodsprice;
    private String goodsName;

    public String getGoodsId(){
        return goodsID;
    }
    public int getgoodsNum(){
        return goodsNum;
    }
    public int getGoodsStock(){return goodsStock;}
    public  int getGoodsImage(){
        return goodsImage;
    }
    public int getGoodsprice(){return goodsprice;}
    public  String getGoodsName(){
        return goodsName;
    }
    public void setGoodsId(String goodsId){
        this.goodsID=goodsId;
    }
    public void setGoodsNum(int goodsNum){this.goodsNum=goodsNum;}
    public void setGoodsStock(int goodsStock){this.goodsStock=goodsStock;}
    public void setGoodsprice(int goodsprice){this.goodsprice=goodsprice;}
    public void setGoodsImage(int goodsImage){this.goodsImage=goodsImage;}
    public void setGoodsName(String goodsName){
        this.goodsName=goodsName;
    }
}
