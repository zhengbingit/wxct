package org.zhengbin.wxct.test.model;

/**
 * Created by zhengbinMac on 2017/5/8.
 */
public class FoodArray {
    private int id;
    private String dishesName;
    private double dishesPrice;
    private double realPrice;
    private int dishesNum;

    @Override
    public String toString() {
        return "FoodArray{" +
                "id=" + id +
                ", dishesName='" + dishesName + '\'' +
                ", dishesPrice=" + dishesPrice +
                ", realPrice=" + realPrice +
                ", dishesNum=" + dishesNum +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public double getDishesPrice() {
        return dishesPrice;
    }

    public void setDishesPrice(double dishesPrice) {
        this.dishesPrice = dishesPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public int getDishesNum() {
        return dishesNum;
    }

    public void setDishesNum(int dishesNum) {
        this.dishesNum = dishesNum;
    }
}
