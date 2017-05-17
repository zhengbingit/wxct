package org.zhengbin.wxct.model;

import java.util.List;

/**
 * 菜品分类
 * Created by zhengbinMac on 2017/4/18.
 */
public class FoodGroup {
    private int id;         // 菜品分类 id
    private String name;    // 菜品分类名称
    private List<Food> foodList;    // 该分类的所有菜品

    @Override
    public String toString() {
        return "FoodGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foodList=" + foodList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
