package com.example.toshiba.zhuce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 2017/9/9.
 */

public class GoodsOP {

    private List<Goodslist> goodslist;
    public GoodsOP(){
        goodslist=new ArrayList<Goodslist>();
    }

    public boolean CheckInList(String goodsid) {

        for (int i = 0; i < goodslist.size(); i++) {
            if (goodsid.equals(goodslist.get(i).getGoodsId())) {
                return true;
            }
        }
        return false;
    }

    public void InsertToList(String goodsid, int goodsnum, int goodsImage, int goodsprice, String goodsname,int goodsStock) {

            if (CheckInList(goodsid)) {
                UpdateById(goodsid, goodsnum);
            } else {
                Goodslist l = new Goodslist();

                l.setGoodsId(goodsid);
                l.setGoodsNum(goodsnum);
                l.setGoodsImage(goodsImage);
                l.setGoodsprice(goodsprice);
                l.setGoodsName(goodsname);
                l.setGoodsStock(goodsStock);

                goodslist.add(l);
            }
        }


    public void DeleteById(String goodid){

        for(int i=0;i<goodslist.size();i++){

            Goodslist a=(Goodslist) goodslist.get(i);

            if(a.getGoodsId().equals(goodid)){
                goodslist.remove(i);
                //InsertToList(a.getGoodsId(),a.getgoodsNum(),a.getGoodsImage(),a.getGoodsprice(),a.getGoodsName());
            }

        }

    }
    public void UpdateById(String goodsid,int goodsNum){

        for(int i=0;i<goodslist.size();i++){
            Goodslist a=(Goodslist) goodslist.get(i);
            if(a.getGoodsId().equals(goodsid)){
                a.setGoodsNum(goodsNum+a.getgoodsNum());
            }
        }
    }

    public List<Goodslist> getGoodsList(){
        return this.goodslist;
    }
    public void setGoodslist(ArrayList<Goodslist> a){
        this.goodslist=a;
    }

}
