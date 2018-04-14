package com.example.toshiba.zhuce;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Toshiba on 2017/9/1.
 */

public class DBOption {

    //    public void CreateUserOp(Context context) {
//        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
//
//        db.close();
//    }
//    private String username="";
//
//    public String getusername(){
//        return username;
//    }
//    public void setUsername(String username){
//        this.username=username;
//    }

    public void InsertUserOp(Context context, String username, String password, String phoneNum, String address, int logcondition) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        db.execSQL("insert into t_user (username,password,phoneNum,address,LogCondition) values (?,?,?,?,?)", new Object[]{username, password, phoneNum, address, logcondition});
        //Toast.makeText(context,"insert success",Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void UpdateByUser(Context context, String username, int logcondition) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        db.execSQL("Update t_user set LogCondition=? where username=?", new Object[]{logcondition, username});
        db.close();
    }

    public String FindLogUser(Context context) {
        String temp = "";
        //int i=0;
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username from t_user where LogCondition = 1", null);
        while (cursor.moveToNext()) {
            temp = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return temp;
    }

    public boolean UserIsLog(Context context) {

        String temp = FindLogUser(context);

        if (temp != null && !("".equals(temp))) {

            return true;

        } else {

            return false;
        }
    }

    public UserAttri SelectByUserInf(Context context, String username) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        ArrayList<UserAttri> au = new ArrayList<UserAttri>();
        Cursor c = db.rawQuery("select * from t_user where username=? ", new String[]{username});
        UserAttri u = new UserAttri();
        while (c.moveToNext()) {
            u.setUsername(c.getString(0));
            u.setPassword(c.getString(1));
            u.setPhoneNum(c.getString(2));
            u.setAddress(c.getString(3));
            u.setRemainSum(c.getInt(4));
            u.setCredit(c.getInt(5));
            u.setLogcondition(c.getInt(6));
        }
        c.close();
        db.close();
        return u;
    }

    public Boolean CheckUser(Context context, String username, String password) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        Cursor u = db.rawQuery("select password from t_user where username=?", new String[]{username});
        //Toast.makeText(context,username+password,Toast.LENGTH_SHORT).show();
        while (u.moveToNext()) {
            if ((u.getString(0)).equals(password)) {
                Toast.makeText(context, "验证成功", Toast.LENGTH_SHORT).show();
                UpdateByUser(context, username, 1);
                u.close();
                db.close();
                return true;
            }
        }
        return false;
    }

    public Boolean CheckLiveUser(Context context, String username) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        Cursor u = db.rawQuery("select password from t_user where username=?", new String[]{username});
        while (u.moveToNext()) {
            if (!("".equals((u.getString(0))))) {
                //Toast.makeText(context, "账户已存在", Toast.LENGTH_SHORT).show();
                u.close();
                db.close();
                return true;
            }
        }
        return false;
    }

    public ArrayList<RecondAttri> SelectAllRecond(Context context) {
        if (!UserIsLog(context)) {
            return null;
        } else {
            SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
            Cursor c = db.rawQuery("select * from t_record where username=?", new String[]{FindLogUser(context)});
            ArrayList<RecondAttri> w = new ArrayList<RecondAttri>();
            while (c.moveToNext()) {
                RecondAttri r = new RecondAttri();
                r.setGoodsID(c.getString(0));
                r.setRecordId(c.getString(1));
                r.setGoodsName(c.getString(2));
                r.setRecordDate(c.getString(3));
                r.setUsername(c.getString(4));
                r.setSumPrice(c.getInt(5));
                w.add(r);
            }
            c.close();
            db.close();
            return w;
        }
    }

    public void InsertRecond(Context context, String AllgoodsId, String recordId, String Allgoodsname, String recordDate, int sumprice) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        if (UserIsLog(context)) {
            db.execSQL("insert into t_record values(?,?,?,?,?,?)", new Object[]{AllgoodsId, recordId, Allgoodsname, recordDate, FindLogUser(context), sumprice});
        }
        db.close();
    }

    public void InsertGoods(Context context, String goodsId, String goodsName, int goodsPrice, int goodsStock, String goodsResource, int goodsPicture) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        db.execSQL("insert into t_goods values(?,?,?,?,?,?)", new Object[]{goodsId, goodsName, goodsPrice, goodsStock, goodsResource, goodsPicture});
        db.close();
    }

    public ArrayList<MerchantData> SelectAllGoods(Context context) {
        SQLiteDatabase db = new db_con(context, "AppShop.db", null, 1).getReadableDatabase();
        ArrayList<MerchantData> a = new ArrayList<MerchantData>();
        Cursor cursor = db.rawQuery("select * from t_goods", null);
        while (cursor.moveToNext()) {
            MerchantData g = new MerchantData();
            g.setGoods_id(cursor.getString(0));
            g.setGoods_name(cursor.getString(1));
            g.setGoods_price(cursor.getInt(2));
            g.setGoods_stock(cursor.getInt(3));
            g.setGoods_resource(cursor.getString(4));
            g.setGoods_image(cursor.getInt(5));
            a.add(g);
        }
        cursor.close();
        db.close();
        return a;
    }

    public MerchantData SelectByGoodsname(Context context, String goodsname) {

        ArrayList<MerchantData> mdlist = SelectAllGoods(context);
        MerchantData md = new MerchantData();

        for (int i = 0; i < mdlist.size(); i++) {
            if (goodsname.equals(mdlist.get(i).getGoods_name())) {
                md = mdlist.get(i);
            }
        }
        return md;
    }
}
