package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 2017/9/11.
 */

public class Merchant_Activity extends AppCompatActivity {

    private List<MerchantData> MerchantList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_layout);
        initMerchant();
        ((Button) findViewById(R.id.go_goodlist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Merchant_Activity.this,Goodslist_show.class);
                startActivity(a);
            }
        });
        RecyclerView merchant_rv = (RecyclerView)findViewById(R.id.merchant_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        merchant_rv.setLayoutManager(layoutManager);
        MerchantAdapter adapter = new MerchantAdapter(Merchant_Activity.this,MerchantList);
        merchant_rv.setAdapter(adapter);
    }

    private void initMerchant() {

        DBOption db=new DBOption();
        List<MerchantData> a= db.SelectAllGoods(Merchant_Activity.this);

        for(int i=0;i<a.size();i++){
            a.get(i).setMerchant_image(a.get(i).getGoods_image());
            a.get(i).setMerchant_name(a.get(i).getGoods_name());
            a.get(i).setMerchant_sale("月售"+(i+1)*9+"笔");
            a.get(i).setMerchant_base((i+1)*10+"元起送");
            a.get(i).setMerchant_time((i+1)*15+"分钟");
        }

        MerchantList=a;
//        MerchantData m1 = new MerchantData(R.drawable.icon_11,"一号商铺","月售1512笔","¥10元起送","15分钟");
//        MerchantList.add(m1);
//        MerchantData m2 = new MerchantData(R.drawable.icon_12,"二号商铺","月售15笔","¥0元起送","1分钟");
//        MerchantList.add(m2);
//        MerchantData m3 = new MerchantData(R.drawable.icon_13,"三号商铺","月售112笔","¥15元起送","4分钟");
//        MerchantList.add(m3);
//        MerchantData m4 = new MerchantData(R.drawable.icon_14,"四号商铺","月售1笔","¥20元起送","27分钟");
//        MerchantList.add(m4);
//        MerchantData m5 = new MerchantData(R.drawable.icon_15,"五号商铺","月售123笔","¥100元起送","35分钟");
//        MerchantList.add(m5);
//        MerchantData m6 = new MerchantData(R.drawable.icon_16,"六号商铺","月售1122笔","¥1元起送","7分钟");
//        MerchantList.add(m6);
//        MerchantData m7 = new MerchantData(R.drawable.icon_17,"七号商铺","月售13412笔","¥10元起送","15分钟");
//        MerchantList.add(m7);
    }
}
