package com.example.toshiba.zhuce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Toshiba on 2017/9/3.
 */

public class MyInf extends AppCompatActivity {
    UserAttri a1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview_wode);

        //a1=(UserAttri) getIntent().getSerializableExtra("UserInf");//这样如果已经登录，就会跳转报错
          DBOption db=new DBOption();
        String user=db.FindLogUser(MyInf.this);
        a1=(UserAttri) db.SelectByUserInf(MyInf.this,user);
        //new DBOption().setUsername(a1.getUsername());

        ((TextView) findViewById(R.id.myinf_login)).setText("欢迎光临，尊敬的会员"+"\n"+a1.getUsername());//显示用户名

        ((TextView) findViewById(R.id.myinf_ye)).setText(a1.getRemainSum()+"元");
        ((TextView) findViewById(R.id.myinf_sc)).setText("收藏");
        ((TextView) findViewById(R.id.myinf_jf)).setText(a1.getCredit()+"积分");

        //Toast.makeText(MyInf.this,new DBOption().getusername(),Toast.LENGTH_SHORT).show();

        ((Button) findViewById(R.id.test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MyInf.this,MainActivity1.class);
                //a.putExtra("UserInf",a1);
                startActivity(a);
            }
        });

        ((Button) findViewById(R.id.out_log)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onDestory的时候退出登录
                new DBOption().UpdateByUser(MyInf.this,a1.getUsername(),0);
                Intent a1=new Intent(MyInf.this,unlogin.class);
                startActivity(a1);
            }
        });
    }
}
