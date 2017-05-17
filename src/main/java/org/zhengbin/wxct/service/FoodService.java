package org.zhengbin.wxct.service;

import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.wxct.dao.FoodDao;
import org.zhengbin.wxct.dao.FoodGroupDao;
import org.zhengbin.wxct.model.Food;
import org.zhengbin.wxct.model.FoodGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品 Service
 * Created by zhengbinMac on 2017/5/6.
 */
@Service
public class FoodService {
    @Inject
    private FoodDao foodDao;
    @Inject
    private FoodGroupDao foodGroupDao;

//    public List<FoodGroup> getAllFoodGroupByFoodGroup() {
//        List<FoodGroup> allFoodGroupByFoodGroupList = new ArrayList<FoodGroup>();
//
//        List<FoodGroup> foodGroupList = foodGroupDao.getAllFoodGroups();
//        List<Food> foodList = foodDao.getAllFoods();
//
//        for (FoodGroup foodGroup : foodGroupList) {
//            List<Food> tempList = new ArrayList<Food>();
//            for (Food food : foodList) {
//                if (food.getGroup_id() == foodGroup.getId()) {
//                    tempList.add(food);
//                }
//            }
//            foodGroup.setFoodList(tempList);
//            allFoodGroupByFoodGroupList.add(foodGroup);
//        }
//
//        return allFoodGroupByFoodGroupList;
//    }


    public List<Food> getAllFood() {
        return foodDao.getAllFoods();
    }
}
