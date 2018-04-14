package com.example.toshiba.jiudian;

import org.litepal.crud.DataSupport;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class DBhotel extends DataSupport{
    public String getHotel_name() {
        return hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public int getHotel_core() {
        return hotel_core;
    }

    public String getHotel_distance() {
        return hotel_distance;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public void setHotel_core(int hotel_core) {
        this.hotel_core = hotel_core;
    }

    public void setHotel_distance(String hotel_distance) {
        this.hotel_distance = hotel_distance;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    String hotel_name;
    String hotel_location;
    int hotel_core;
    String hotel_distance;
    String hotel_phone;
}
