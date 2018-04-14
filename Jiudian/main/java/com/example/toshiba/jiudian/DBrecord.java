package com.example.toshiba.jiudian;

import org.litepal.crud.DataSupport;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class DBrecord  extends DataSupport {
    public String getHotel_name() {
        return hotel_name;
    }

    public int getRoom_spent() {
        return room_spent;
    }

    public String getRoom_kind() {
        return room_kind;
    }

    public String getDate_in() {
        return date_in;
    }

    public int getRoom_account() {
        return room_account;
    }

    public String getDate_out() {
        return date_out;
    }

    public String getPay_condition() {
        return pay_condition;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setRoom_spent(int room_spent) {
        this.room_spent = room_spent;
    }

    public void setRoom_kind(String room_kind) {
        this.room_kind = room_kind;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public void setRoom_account(int room_account) {
        this.room_account = room_account;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public void setPay_condition(String pay_condition) {
        this.pay_condition = pay_condition;
    }

    String hotel_name;
    int room_spent;
    String room_kind;
    String date_in;
    int room_account;
    String date_out;
    String pay_condition;
}
