package com.example.toshiba.jiudian;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Toshiba on 2017/12/30.
 */

public class MyPagerAdapt extends PagerAdapter {

    List<ImageView> imageViews;

    public MyPagerAdapt(List<ImageView> imageViews) {
        this.imageViews=imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
