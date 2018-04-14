package com.example.toshiba.zhuce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Toshiba on 2017/9/1.
 */

public class db_con extends SQLiteOpenHelper {

    Context c;
    public db_con(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        c=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table t_user (" +
                "username varchar unique," +
                "password varchar," +
                "phoneNum varchar," +
                "address varchar," +
                "remainSum integer," +
                "credit integer," +
                "LogCondition integer)");
        //Toast.makeText(c,"create succeed",Toast.LENGTH_SHORT).show();
        db.execSQL("create table t_record ("+
                "goodsID nchar(8)," +
                "recordId nchar(8)," +
                "goodsName varchar," +
                "recordDate varchar," +
                "username varchar," +
                "sumPrice integer)");

        db.execSQL("create table t_goods(" +
                "goodsId nchar(8)," +
                "goodsName varchar," +
                "goodsPrice integer," +
                "goodsStock integer," +
                "goodsResource varchar," +
                "goodsImage varchar" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
