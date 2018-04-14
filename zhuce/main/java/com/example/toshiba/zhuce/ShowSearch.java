package com.example.toshiba.zhuce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Toshiba on 2017/9/14.
 */

public class ShowSearch extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_search);

        String a=getIntent().getStringExtra("goodsName");
        //Toast.makeText(ShowSearch.this,a,Toast.LENGTH_SHORT).show();
        DBOption db=new DBOption();
        MerchantData md=db.SelectByGoodsname(ShowSearch.this,a);

        ((TextView) findViewById(R.id.good_id)).setText("商品ID:"+md.getGoods_id());
        ((TextView) findViewById(R.id.good_name)).setText("商品名:"+md.getGoods_name());
        ((TextView) findViewById(R.id.good_price)).setText("商品价格:"+md.getGoods_price()+"元");
        ((TextView) findViewById(R.id.good_stock)).setText("商品库存:"+md.getGoods_stock()+"瓶");
        ((TextView) findViewById(R.id.good_resource)).setText("商品来源地址:"+md.getGoods_resource());
        ((ImageView) findViewById(R.id.goods_img)).setImageResource(md.getGoods_image());
    }
}
