package com.example.toshiba.face2;

/**
 * Created by Toshiba on 2017/12/26.
 */

public class Myface {


    public String getPitch() {
        return pitch;
    }

    public String getRoll() {
        return roll;
    }

    public String getYaw() {
        return yaw;
    }


    public String getGender() {
        return gender;
    }

    public String getGlass() {
        return glass;
    }

    public String getExpression() {
        return expression;
    }

    public String getBeauty() {
        return beauty;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setYaw(String yaw) {
        this.yaw = yaw;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setBeauty(String beauty) {
        this.beauty = beauty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getAge() {
        return age;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //    "face_id": "2376861190432292863",
//                                "x": 631,
//                                "y": 135,
//                                "height": 627,
//                                "width": 627,
//                                "pitch": 7,
//                                "roll": 1,
//                                "yaw": 0,
//                                "age": 35,
//                                "gender": 21,
//                                "glass": false,
//                                "expression": 100,
//                                "beauty": 72
    int x;
    int y;
    int height;
    int width;
    int age;
    String gender;
    String glass;
    String expression;
    String beauty;
    String pitch;
    String roll;
    String yaw;
}
