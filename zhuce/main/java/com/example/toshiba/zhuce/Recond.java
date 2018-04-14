package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2017/9/5.
 */

public class Recond extends AppCompatActivity {
    List<RecondAttri> m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recond_main);

        //username,判断是否登陆

        DBOption db=new DBOption();
        //db.InsertRecond(Recond.this,"123456","654321","垃圾","hammond","2017/10/12",100);
        m =db.SelectAllRecond(Recond.this);





if(new DBOption().UserIsLog(Recond.this)){
        if(m!=null && !m.isEmpty()) {
            ListView listView = (ListView) this.findViewById(R.id.listview);//关联?
            listView.setAdapter(new Recond_list(Recond.this, R.layout.recond, m));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    RecondAttri r = (RecondAttri) m.get(position);
                    Intent a = new Intent(Recond.this, Recond_show.class);
                    a.putExtra("Recond", r);
                    startActivity(a);
                }
            });
        } }
        else {
            Intent b=new Intent(Recond.this,Recond_null.class);
            startActivity(b);
        }
    }
}
