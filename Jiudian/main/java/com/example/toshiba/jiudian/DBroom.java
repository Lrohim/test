package com.example.toshiba.jiudian;

import org.litepal.crud.DataSupport;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class DBroom  extends DataSupport {
    String room_kind;
    int room_spent;
    String room_detail;

    public void setRoom_kind(String room_kind) {
        this.room_kind = room_kind;
    }

    public void setRoom_spent(int room_spent) {
        this.room_spent = room_spent;
    }

    public void setRoom_detail(String room_detail) {
        this.room_detail = room_detail;
    }

    public String getRoom_kind() {

        return room_kind;
    }

    public int getRoom_spent() {
        return room_spent;
    }

    public String getRoom_detail() {
        return room_detail;
    }
}
