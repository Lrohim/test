package com.example.toshiba.zhuce;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class MainActivity1 extends AppCompatActivity implements ViewControl,ViewPager.OnPageChangeListener {


    private Context context;
    private SoreButton soreButton;
    private List<Integer> list;
    public RecyclerView rv;
    private LinearLayoutManager lm;
    private BottomNavigationView bn; //底部导航栏
    private ViewPager main_vp;
    //private fragment1 fragment1 = new fragment1();
    //private fragment2 fragment2 = new fragment2();
    private LinearLayout LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        DBOption db=new DBOption();
        db.InsertGoods(MainActivity1.this,"10001","苹果奶茶",10,100,"东软",R.drawable.appletea);
        db.InsertGoods(MainActivity1.this,"10002","哈尔滨啤酒",5,100,"东软",R.drawable.beer);
        db.InsertGoods(MainActivity1.this,"10003","丝滑拿铁",10,90,"东软",R.drawable.coffee);
        db.InsertGoods(MainActivity1.this,"10004","可口可乐",4,70,"华师",R.drawable.cola);
        db.InsertGoods(MainActivity1.this,"10005","水果汁",11,100,"华师",R.drawable.fruit);
        db.InsertGoods(MainActivity1.this,"10006","绿茶",10,50,"广轻",R.drawable.green_tea);
        db.InsertGoods(MainActivity1.this,"10007","圣代",12,10,"广轻",R.drawable.icecream);
        db.InsertGoods(MainActivity1.this,"10008","韩式咖啡",15,15,"石化",R.drawable.koreacoffee);
        db.InsertGoods(MainActivity1.this,"10009","韩式果汁",22,29,"石化",R.drawable.koreajuice);
        db.InsertGoods(MainActivity1.this,"10010","柠檬茶",12,100,"东软",R.drawable.lemon);
        db.InsertGoods(MainActivity1.this,"10011","百香果茶",14,60,"东软",R.drawable.passionfruit);
        db.InsertGoods(MainActivity1.this,"10012","进口果汁",25,60,"广轻",R.drawable.snapple);
        db.InsertGoods(MainActivity1.this,"10013","黄尾袋鼠",30,999,"东软",R.drawable.wine);

        context = this;

        soreButton = (SoreButton) (getLayoutInflater().inflate(R.layout.self_button,null)).findViewById(R.id.soreButton);
//        rv =(RecyclerView)(getLayoutInflater().inflate(R.layout.activity_main,null)).findViewById(R.id.myRecyclerView);
        rv = (RecyclerView)findViewById(R.id.myRecyclerView);


        //设置界面监听    接口
        soreButton.setViewControl(this);
        //添加界面到list
        list = new ArrayList<>();
        list.add(R.layout.viewpager_page);
        list.add(R.layout.viewpager_page);
        list.add(R.layout.viewpager_page_text);

//
//        main_vp = (ViewPager)findViewById(R.id.main_viewpager);
//        //添加事件监听
//        main_vp.addOnPageChangeListener(this);



        bn = (BottomNavigationView) findViewById(R.id.bottom_navigation);


/*


        main_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return fragment1;
                    case 1:
                        return fragment2;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
*/



        //底部导航选中监听
        bn.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.drink:
                                break;
                            case R.id.location:
                                Intent a=new Intent(MainActivity1.this,Merchant_Activity.class);
                                startActivity(a);
                                break;
                            case R.id.shuang:
                                if(new DBOption().UserIsLog(MainActivity1.this)) {
                                    Intent b1=new Intent(MainActivity1.this,Recond.class);
                                    startActivity(b1);
                                    break;
                                }else {
                                    Intent b = new Intent(MainActivity1.this, Recond_null.class);
                                    startActivity(b);
                                    break;
                                }

                            case R.id.mine:
                                if(new DBOption().UserIsLog(MainActivity1.this)){
                                    Intent b1=new Intent(MainActivity1.this,MyInf.class);
                                    startActivity(b1);
                                    break;
                                }else {
                                    Intent c = new Intent(MainActivity1.this, unlogin.class);
                                    startActivity(c);
                                    break;
                                }
                        }
                        return true;
                    }
                }
        );


        //控件相关设置
//        soreButton
//                //设置选中和未选中指示器图标
//                .setIndicator(R.drawable.radio1,R.drawable.radio2)
//                //设置指示器半间距px
//                .setDistance(10)
//                //设置view组
//                .setView(list)
//                .init();

        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        soreButton.setView(list).init(MainActivity1.this);
    }

//
//    private BottomNavigationView.OnNavigationItemSelectedListener NISL =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    //点击BottomNavigationView的Item项，切换ViewPager页面
//                    //menu/navigation.xml里加的android:orderInCategory属性就是下面item.getOrder()取的值
//                    main_vp.setCurrentItem(item.getOrder());
//                    return false;
//                }
//            };
//

//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragment, fragment);
//        transaction.commit();
//    }
    /**
     * 给RecyclerView设置适配器
     * @param viewpager
     * @param list
     */
    public void setRAdapter(ViewPager viewpager, List<MerchantData> list,List<View> listView){
        RecyclerAdapter rAdapter = new RecyclerAdapter(this,viewpager,list,listView);
        rv.setAdapter(rAdapter);
    }

    /**
     * 对每一页tab的ImgaeView和TextView设置数据和点击toast提示
     * @param view
     * @param type
     */
    @Override
    public void setView(View view, final int type) {

        switch (type) {
            case 0://第一个界面
                GridView gridView = (GridView) view.findViewById(R.id.gridView);
                SortButtonAdapter adapter = new SortButtonAdapter(this,setData());  //调用MainActivity中的setDate方法
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context,"第"+type+"页"+position,Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(MainActivity1.this,Merchant_Activity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1://第二个界面
                GridView gridView2 = (GridView) view.findViewById(R.id.gridView);
                SortButtonAdapter adapter2 = new SortButtonAdapter(this,setData2());   //调用MainActivity中的setDate2方法
                gridView2.setAdapter(adapter2);
                gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context,"第"+type+"页"+position,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity1.this,Merchant_Activity.class);
                        startActivity(intent);
                    }
                });
                break;
//            case 2://第三个界面
//                TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//                tvTitle.setText("可高度定制，可设置任意layout,并且在回调中获取该layout内的所有控件");
//                tvTitle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(context,"点击了该文字",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
        }
    }


    private List<ButtonModel> setData(){
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_1);
        buttonModel.setName("美食");
        data.add(buttonModel);

        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_2);
        buttonModel.setName("电影");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_3);
        buttonModel.setName("酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_4);
        buttonModel.setName("休闲娱乐");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_5);
        buttonModel.setName("外卖");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_6);
        buttonModel.setName("机票/火车票");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_7);
        buttonModel.setName("KTV");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_8);
        buttonModel.setName("周边游");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_9);
        buttonModel.setName("丽人");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_10);
        buttonModel.setName("旅游出行");
        data.add(buttonModel);
        return data;
    }

    private List<ButtonModel> setData2(){
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_11);
        buttonModel.setName("品质酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_12);
        buttonModel.setName("生活服务");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_13);
        buttonModel.setName("足疗按摩");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_14);
        buttonModel.setName("母婴亲子");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_15);
        buttonModel.setName("结婚");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_16);
        buttonModel.setName("景点");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_17);
        buttonModel.setName("温泉");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_18);
        buttonModel.setName("学习培训");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_19);
        buttonModel.setName("洗浴/汗蒸");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_20);
        buttonModel.setName("全部分类");
        data.add(buttonModel);
        return data;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bn.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
