package org.zhengbin.wxct.model;

/**
 * 前端 菜品对象
 * Created by zhengbinMac on 2017/5/8.
 */
public class FoodArray {
    private int foodId;         // 菜品 id
    private String dishesName;  // 菜品名
    private double dishesPrice; // 菜品单价
    private double realPrice;   // 菜品总价 = 菜品单价 * 菜品数量
    private int dishesNum;      // 菜品数量
    private String unit;        // 菜品单位
    private String dishesRemarks;// 菜品备注

    @Override
    public String toString() {
        return "FoodArray{" +
                "foodId=" + foodId +
                ", dishesName='" + dishesName + '\'' +
                ", dishesPrice=" + dishesPrice +
                ", realPrice=" + realPrice +
                ", dishesNum=" + dishesNum +
                ", unit='" + unit + '\'' +
                ", dishesRemarks='" + dishesRemarks + '\'' +
                '}';
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDishesRemarks() {
        return dishesRemarks;
    }

    public void setDishesRemarks(String dishesRemarks) {
        this.dishesRemarks = dishesRemarks;
    }
}
