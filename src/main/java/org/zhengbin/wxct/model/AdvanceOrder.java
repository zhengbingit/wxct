package org.zhengbin.wxct.model;

import java.util.List;

/**
 * 前端 预订单对象
 * Created by zhengbinMac on 2017/5/8.
 */
public class AdvanceOrder {
    private Double realMoney;   // 共计价格
    private Double endMoney;    // 应付价格
    private List<FoodArray> foodArrayList;

    public Double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Double realMoney) {
        this.realMoney = realMoney;
    }

    public Double getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(Double endMoney) {
        this.endMoney = endMoney;
    }

    public List<FoodArray> getFoodArrayList() {
        return foodArrayList;
    }

    public void setFoodArrayList(List<FoodArray> foodArrayList) {
        this.foodArrayList = foodArrayList;
    }

    @Override
    public String toString() {
        return "AdvanceOrder{" +
                "realMoney=" + realMoney +
                ", endMoney=" + endMoney +
                ", foodArrayList=" + foodArrayList +
                '}';
    }
}
