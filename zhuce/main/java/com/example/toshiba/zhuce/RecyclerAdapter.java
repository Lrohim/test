package com.example.toshiba.zhuce;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 2017/9/7.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<MerchantData> mGoodsList;  //商品列表
    private ViewPager mViewpager;   //上面的滑动viewpager
    private ViewPager header;   //用来放Viewpager？？
    private LayoutInflater mif;
    private int size;       //viewpager的页数
    private View view;
    private List<View> listSoreView = new ArrayList<>();
    private static final int type_appname = 0;
    private static final int type_search = 1;
    private static final int type_viewpager = 2;//当需要填viewpager的时候，getViewType方法的position返回为0
    private static final int type_text = 3;//当需要填textview的时候，getViewType方法的position返回为1
    private static final int type_goods = 4;//当需要填商品信息的时候，getViewType方法的position返回为2

    public RecyclerAdapter(Context context, ViewPager header, List<MerchantData> data, List<View> listview) {
        this.header = header;
        mif = LayoutInflater.from(context);
        this.mGoodsList = data;
        this.mContext = context;
        this.size = listview.size();
        this.listSoreView = listview;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton goodsImage;
        TextView goodsname;
        TextView goodssale;
        TextView goodsprice;
        TextView guest;
        TextView appname;
        EditText search_editText;//
        ImageView search_img;//
        //View viewtemp;

        ViewPager vp;
        private LinearLayout point_layout;      //用来放置指示器
        private LinearLayout search_layout;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == type_viewpager) {
                //init viewpager
                point_layout = (LinearLayout) itemView.findViewById(R.id.llIndicator);
                vp = (ViewPager) itemView.findViewById(R.id.viewPager);
            } else if (viewType == type_text) {
                guest = (TextView) itemView.findViewById(R.id.default_text);
            } else if (viewType == type_appname) {
                appname = (TextView) itemView.findViewById(R.id.keleme);
            } else if (viewType == type_search) {
                search_layout = (LinearLayout) itemView.findViewById(R.id.top);
                search_editText = (EditText) itemView.findViewById(R.id.search_edit);//
                search_img = (ImageView) itemView.findViewById(R.id.search);//
            } else {
                goodsImage = (ImageButton) itemView.findViewById(R.id.goods_image);
                goodsname = (TextView) itemView.findViewById(R.id.good_name);
                goodssale = (TextView) itemView.findViewById(R.id.goods_sale);
                goodsprice = (TextView) itemView.findViewById(R.id.good_price);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //用于创建ViewHolder实例,将子项布局加载进来，然后创建一个ViewHolder实例

        if (viewType == type_viewpager) {
            View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.anfq_sore_button, parent, false);
            ViewHolder holder1 = new ViewHolder(view1, viewType);
            return holder1;
//            return new ViewHolder(header,viewType);
        } else if (viewType == type_text) {
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_text, parent, false);
            ViewHolder holder2 = new ViewHolder(view2, viewType);
            return holder2;
        } else if (viewType == type_appname) {
            View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.appname_layout, parent, false);
            ViewHolder holder4 = new ViewHolder(view4, viewType);
            return holder4;
        } else if (viewType == type_search) {
            View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_layout, parent, false);
            //viewtemp=view5;
            ViewHolder holder5 = new ViewHolder(view5, viewType);
            return holder5;
        } else {
            View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
            ViewHolder holder3 = new ViewHolder(view3, viewType);
            return holder3;
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {     // 对RecyclerView的子项进行赋值，每个子项被滚到屏幕内时执行
        if(getItemViewType(position)==type_viewpager){
            //holder.vp_banner.setImageResoure(MyApplication.pics[0]);
            //给VIewpager设置适配器
            holder.vp.setAdapter(new ViewPagerAdapter(listSoreView));
            //设置指示器的点
            holder.point_layout.removeAllViews();
            final ImageView[] imageViews = new ImageView[size];
            for (int i = 0; i < size; i++) {
                //创建ImageView
                ImageView image = new ImageView(mContext);
                //将ImageView放入数组
                imageViews[i] = image;
                //默认选中第一个
                if (i == 0) {
                    //选中的点
                    image.setImageResource(R.drawable.radio1);//RadioSelect
                } else {
                    //未选中的点
                    image.setImageResource(R.drawable.radio2);//RadioUnselected
                }
                //设置宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 0, 10, 0);

                //将点添加到LinearLayout中
                holder.point_layout.addView(image, params);
            }


            //ViewPager的滑动事件
            holder.vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrollStateChanged(int arg0) {}
                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {}
                @Override
                public void onPageSelected(int arg0) {   //arg0是当前viewpager的位置，第一页为0，第二页为1，第三页为2
                    //arg0当前ViewPager
                    for (int i = 0; i < imageViews.length; i++) {  //目前imageViews=3
                        //设置为选中的点
                        imageViews[arg0].setImageResource(R.drawable.radio1);
                        //判断当前的点i如果不等于当前页的话就设置为未选中
                        if (arg0 != i) {
                            imageViews[i].setImageResource(R.drawable.radio2);
                        }
                    }
                }
            });

        }else if(getItemViewType(position)==type_text) {
//                holder.guest.setText("猜你喜欢");
        }else if(getItemViewType(position)==type_search) {
//                holder.search_layout.removeAllViews();
                holder.search_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
               //         View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_layout, parent, false);

                        String inf=((EditText) holder.search_layout.findViewById(R.id.search_edit)).getText().toString();
                        //Toast.makeText(mContext,inf,Toast.LENGTH_SHORT).show();
                        if(inf!=null && !("".equals(inf))) {
                            Intent a = new Intent(mContext, ShowSearch.class);
                            a.putExtra("goodsName", inf);
                            mContext.startActivity(a);
                        }else {
                            Intent b=new Intent(mContext,Search_null.class);
                            mContext.startActivity(b);
                        }
                    }
                });
        }else if(getItemViewType(position)==type_appname) {
//                holder.guest.setText("猜你喜欢");
        }else {
                MerchantData goods = mGoodsList.get(position - 4);
                holder.goodsname.setText("商品名:"+goods.getGoods_name());
                holder.goodsprice.setText("库存:"+goods.getGoods_price());
                holder.goodssale.setText("库存:"+goods.getGoods_stock());
                holder.goodsImage.setImageResource(goods.getGoods_image());

        }
        }

    public int getItemViewType(int position){
        if(position == 0) {
            return type_appname;
        }else if(position == 1){
            return type_search;
        }else if(position == 2){
            return type_viewpager;
        }else if(position == 3) {
            return type_text;
        }else{
            return type_goods;
        }
    }

    @Override
    public int getItemCount() {

        return mGoodsList.size()+4;
    }

}
