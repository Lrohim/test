package com.example.toshiba.exweather;

import java.util.List;

/**
 * Created by Toshiba on 2017/12/26.
 */

public class Weather {
            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public String getDate() {
                return date;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }

            String cond_txt_d;
            String date;
            String tmp_min;
            String tmp_max;
            String wind_dir;
            String wind_sc;
            String wind_spd;
        }