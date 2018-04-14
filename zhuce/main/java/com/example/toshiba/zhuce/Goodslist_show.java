package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Toshiba on 2017/9/9.
 */

public class Goodslist_show extends AppCompatActivity {

    List<Goodslist> goodslist;
//    GoodsShowList v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);

        goodslist=new ArrayList<>();
        //GoodsOP op = new GoodsOP();

//        插入数据时注意
//        op.InsertToList("12345",10,R.drawable.man34,3,"可乐",12);
//        op.InsertToList("56455",100,R.drawable.e,14,"雪碧",133);
        //op.InsertToList("asdas",123,12,10,"球星与");

        List<Goodslist> temp =MerchantAdapter.gp.getGoodsList();

        for(int i=0;i<temp.size();i++) {
            if (temp.get(i).getgoodsNum()>0) {
                goodslist.add(temp.get(i));
            }
        }

        //Toast.makeText(Goodslist_show.this,new DBOption().getusername(),Toast.LENGTH_SHORT).show();

        ((Button) findViewById(R.id.list_buy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传参给Recond
//                Intent go=new Intent(Goodslist_show.this,Recond.class);
//                for(int i=0;i<goodslist.size();i++) {
//                    go.putExtra("goodslist"+i,goodslist.get(i));
//                }
//                startActivity(go);
                //UserAttri a=(UserAttri) getIntent().getSerializableExtra("UserInf");

                if (new DBOption().UserIsLog(Goodslist_show.this)) {
                    String AllgoodsId = "";
                    String recordId = "" +((int)Math.floor(Math.random()*990000));
                    String Allgoodsname = "";
                    String recordDate = "" + new Date(System.currentTimeMillis());
                    int sumprice = 0;

                    DBOption db = new DBOption();
                    for (int i = 0; i < goodslist.size(); i++) {
                        AllgoodsId += goodslist.get(i).getGoodsId() + "\n";
                        Allgoodsname +="\n"+goodslist.get(i).getGoodsId()+"\t\t\t\t\t\t\t\t\t\t\t"+goodslist.get(i).getGoodsName()+"×"+goodslist.get(i).getgoodsNum();
                        sumprice += goodslist.get(i).getGoodsprice()*goodslist.get(i).getgoodsNum();

                    }
                    db.InsertRecond(Goodslist_show.this, AllgoodsId, recordId, Allgoodsname, recordDate, sumprice);
                    Intent a1 = new Intent(Goodslist_show.this, Recond.class);
                    startActivity(a1);
                } else {
                    Intent b=new Intent(Goodslist_show.this,unlogin.class);
                    startActivity(b);
                }
            }
        });



        if( goodslist!=null && !goodslist.isEmpty()){
            ListView listView = (ListView) this.findViewById(R.id.showlist);
            listView.setAdapter(new GoodsShowList(Goodslist_show.this,R.layout.showlist_part, goodslist));
        }
        else {
            Intent a=new Intent(Goodslist_show.this,showlist_null.class);
            startActivity(a);
        }
    }
}
