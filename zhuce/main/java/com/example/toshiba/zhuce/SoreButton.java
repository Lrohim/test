package com.example.toshiba.zhuce;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 2017/9/6.
 */

// 自定义控件SoreButton.java
public class SoreButton extends LinearLayout{
    Context mContext;
    private ViewPager viewPager;
    private LinearLayout llIndicator;
    private List<MerchantData> goodsList = new ArrayList<>();
    //选中图片
//    private int RadioSelect;
    //未选中图片
//    private int RadioUnselected;
    //圆点间距
    private int distance;

    List<View> listSoreView = new ArrayList<>();
    View soreView;
    private List<Integer> listView;

    //接口
    private ViewControl viewControl;
    //设置接口
    public void setViewControl(ViewControl viewControl) {
        this.viewControl = viewControl;
    }

    public SoreButton(Context context) {
        super(context);
    }

    public SoreButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        LayoutInflater.from(context).inflate(R.layout.anfq_sore_button, this, true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        llIndicator = (LinearLayout) findViewById(R.id.llIndicator);
/**
 * 原本没注释
 */
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DynamicSoreView);
//        if (typedArray != null) {
//            //选中点
//            RadioSelect = typedArray.getResourceId(R.styleable.DynamicSoreView_SoreRadioSelect, R.drawable.radio_select);
//            //未选中点
//            RadioUnselected = typedArray.getResourceId(R.styleable.DynamicSoreView_SoreRadioUnselected, R.drawable.radio_unselected);
//            //圆点间距
//            distance = typedArray.getInteger(R.styleable.DynamicSoreView_SoreDistance,10);
//            typedArray.recycle();
//        }
//        setDistance(10);   //这里是自己添加的
        //设置空布局     为什么要设置空布局？？？？
        listView = new ArrayList<>();
        listView.add(R.layout.viewpager_default);
    }

    //初始化商品
    private void initGoods(Context context) {
//        GoodsData a1 = new GoodsData("A","月销售100件","¥6.66",R.drawable.icon_1);
//        goodsList.add(a1);
//        GoodsData a2 = new GoodsData("B","月销售10件","¥6.66",R.drawable.icon_2);
//        goodsList.add(a2);
//        GoodsData a3 = new GoodsData("C","月销售1件","¥6.66",R.drawable.icon_3);
//        goodsList.add(a3);
//        GoodsData a4 = new GoodsData("D","月销售5件","¥6.66",R.drawable.icon_4);
//        goodsList.add(a4);
//        GoodsData a5 = new GoodsData("E","月销售40件","¥6.66",R.drawable.icon_5);
//        goodsList.add(a5);
//        GoodsData a6 = new GoodsData("F","月销售4件","¥6.66",R.drawable.icon_8);
//        goodsList.add(a6);
//        GoodsData a7 = new GoodsData("G","月销售62件","¥6.66",R.drawable.icon_7);
//        goodsList.add(a7);
//        GoodsData a8 = new GoodsData("H","月销售62件","¥6.66",R.drawable.icon_9);
//        goodsList.add(a8);
        DBOption db=new DBOption();
        List<MerchantData> a= db.SelectAllGoods(context);

        for(int i=0;i<a.size();i++){
            a.get(i).setGoods_sale(a.get(i).getMerchant_sale());
        }
        goodsList=a;
    }


    //初始化ViewPager
    private void initViewPager(){
        listSoreView = new ArrayList<>();
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//mContext是MainActivity
        int size = listView.size();  //多少个viewpager,目前为3
        for (int i = 0; i < size; i++) {
            //循环拿到传入的View
            soreView = layoutInflater.inflate(listView.get(i), null); //每一个Viewpager，即是一个view
            //通过接口回调的形式返回当前的View,实现接口后开源拿到每个View然后进行操作
            if (viewControl!=null){
                viewControl.setView(soreView,i);          //回调mainActivity中的setView方法
            }
            //将获取到的View添加到List中
            listSoreView.add(soreView);
        }
        //设置viewPager的Adapter
//        viewPager.setAdapter(new ViewPagerAdapter(listSoreView));

        //初始化LinearLayout，加入指示器
//        initLinearLayout(viewPager, size, llIndicator);

        //接口回调 给Recyclerview设置设配器
        viewControl.setRAdapter(viewPager,goodsList,listSoreView);

    }

    /**
     * 设置指示器，设置ViewPager滑动事件监听
     * @param viewPager --ViewPager
     * @param pageSize --View的页数
     * @param linearLayout --LinearLayout
     */
//    private void initLinearLayout(ViewPager viewPager, int pageSize, LinearLayout linearLayout) {
//        linearLayout.removeAllViews();
//        //定义数组用来放置指示器的点，pageSize是View个数
//        final ImageView[] imageViews = new ImageView[pageSize];
//        for (int i = 0; i < pageSize; i++) {
//            //创建ImageView
//            ImageView image = new ImageView(mContext);
//            //将ImageView放入数组
//            imageViews[i] = image;
//            //默认选中第一个
//            if (i == 0) {
//                //选中的点
//                image.setImageResource(R.drawable.radio1);//RadioSelect
//            } else {
//                //未选中的点
//                image.setImageResource(R.drawable.radio2);//RadioUnselected
//            }
//            //设置宽高
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(distance, 0, distance, 0);
//
//            //将点添加到LinearLayout中
//            linearLayout.addView(image, params);
//        }
//
//        //ViewPager的滑动事件
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrollStateChanged(int arg0) {}
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {}
//            @Override
//            public void onPageSelected(int arg0) {   //arg0是当前viewpager的位置，第一页为0，第二页为1，第三页为2
//                //arg0当前ViewPager
//                for (int i = 0; i < imageViews.length; i++) {  //目前imageViews=3
//                    //设置为选中的点
//                    imageViews[arg0].setImageResource(R.drawable.radio1);
//                    //判断当前的点i如果不等于当前页的话就设置为未选中
//                    if (arg0 != i) {
//                        imageViews[i].setImageResource(R.drawable.radio2);
//                    }
//                }
//            }
//        });
//    }

    /**
     * 设置圆点距离
     * @param distance  --距离
     * @return
     */
    @Deprecated
    public SoreButton setDistance(int distance){
        this.distance = distance;
        return this;
    }
//    /**
//     * 设置指示器图片
//     * @param radioSelect       --选中图片
//     * @param radioUnselected   --未选中图片
//     * @return
//     */
//    @Deprecated
//    public SoreButton setIndicator(int radioSelect,int radioUnselected){
//        //选中图片
//        RadioSelect = radioSelect;
//        //未选中图片
//        RadioUnselected = radioUnselected;
//        return this;
//    }
    /**
     * 设置view
     * @param listView   --view
     * @return
     */
    public SoreButton setView(List<Integer> listView){
        this.listView = listView;
        return this;   //this是自定义按钮？？app:id/SoreButton  返回当前对象，对象属于SoreButton
    }
    /**
     * 设置初始化
     */
    public SoreButton init(Context context){
        initGoods(context);
        initViewPager();
        return this;
    }

}
