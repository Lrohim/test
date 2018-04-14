package com.example.toshiba.zhuce;

/**
 * Created by qiu on 2017/9/11.
 */

public class MerchantData {
    private String merchant_name;
    private int merchant_image;
    private String merchant_sale;
    private String merchant_base;
    private String merchant_time;

    private String goods_id;
    private String goods_name;
    private String goods_sale;
    private int goods_price;
    private int goods_image;
    private int goods_stock;
    private String goods_resource;

    public  MerchantData(){

    }

    public void setGoods_resource(String resource){this.goods_resource=resource;}
    public String getGoods_resource(){return goods_resource;}
    public void setMerchant_image(int image){
        this.merchant_image = image;
    }
    public int getMerchant_image(){
        return merchant_image;
    }
    public void setMerchant_name(String name){
        this.merchant_name = name;
    }
    public String getMerchant_name(){
        return merchant_name;
    }
    public void setMerchant_sale(String sale){
        this.merchant_sale = sale;
    }
    public String getMerchant_sale(){
        return merchant_sale;
    }
    public void setMerchant_base(String base){
        this.merchant_base = base;
    }
    public String getMerchant_base(){
        return merchant_base;
    }
    public void setMerchant_time(String time){
        this.merchant_time = time;
    }
    public String getMerchant_time(){
        return merchant_time;
    }

    public void setGoods_id(String id){
        this.goods_id=id;
    }
    public String getGoods_id(){
        return goods_id;
    }
    public void setGoods_name(String name){
        this.goods_name=name;
    }
    public String getGoods_name(){
        return goods_name;
    }
    public void setGoods_sale(String num){
        this.goods_sale=num;
    }
    public String getGoods_sale(){
        return goods_sale;
    }
    public void setGoods_price(int price){
        this.goods_price=price;
    }
    public int getGoods_price(){return goods_price;}
    public void setGoods_image(int image){
        this.goods_image=image;
    }
    public int getGoods_image(){
        return goods_image;
    }
    public void setGoods_stock(int goodsstock){this.goods_stock=goodsstock;}
    public int getGoods_stock(){return goods_stock;}
}
