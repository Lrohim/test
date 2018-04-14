package com.example.toshiba.zhuce;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by qiu on 2017/9/6.
 */

interface ViewControl {
    void setView(View view, int type);
    void setRAdapter(ViewPager view, List<MerchantData> list,List<View> list2);
}
