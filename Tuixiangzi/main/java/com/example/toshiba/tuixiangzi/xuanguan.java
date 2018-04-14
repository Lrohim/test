package com.example.toshiba.tuixiangzi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Toshiba on 2017/11/16.
 */

public class xuanguan extends AppCompatActivity implements View.OnClickListener{

//    int [][] one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuanguan);

//        int[][] temp={
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,3,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,0,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,1,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2}
//        };
//        one=temp;

        findViewById(R.id.Round_one).setOnClickListener(this);
        findViewById(R.id.Round_two).setOnClickListener(this);
        findViewById(R.id.Round_three).setOnClickListener(this);
        findViewById(R.id.Round_four).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Round_one:
                Intent it1=new Intent(xuanguan.this,play.class);
                it1.putExtra("mapArray","one");
               // new youxizhujiemian(xuanguan.this,"one");
                startActivity(it1);
                break;
            case R.id.Round_two:
                Intent it2=new Intent(xuanguan.this,play.class);
                it2.putExtra("mapArray","two");
                startActivity(it2);
                break;
            case R.id.Round_three:
                Intent it3=new Intent(xuanguan.this,play.class);
                it3.putExtra("mapArray","three");
                startActivity(it3);
                break;
            case R.id.Round_four:
                Intent it4=new Intent(xuanguan.this,play.class);
                it4.putExtra("mapArray","four");
                startActivity(it4);
                break;
        }
    }
}
