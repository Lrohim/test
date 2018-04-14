package com.example.toshiba.jiudian;

/**
 * Created by qiu on 2017/12/26.
 */

public class hotelData {
    String hotel_name,name,info,sale,price,promotion;
    int img;

    public hotelData(int img,String hotel_name, String name, String info, String sale, String price, String promotion){
        this.hotel_name = hotel_name;
        this.img = img;
        this.name = name;
        this.info = info;
        this.sale = sale;
        this.price = price;
        this.promotion = promotion;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }


    public String getInfo() {
        return info;
    }


    public String getPrice() {
        return price;
    }


    public String getSale() {
        return sale;
    }

    public String getPromotion() {
        return promotion;
    }
}
