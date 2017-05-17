package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.dao.FoodDao;
import org.zhengbin.wxct.dao.FoodGroupDao;
import org.zhengbin.wxct.model.Food;
import org.zhengbin.wxct.model.FoodGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/6.
 */
public class FoodServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(FoodServiceTest.class);
    private static FoodDao foodDao;
    private static FoodGroupDao foodGroupDao;

    public FoodServiceTest() {
        foodDao = new FoodDao();
        foodGroupDao = new FoodGroupDao();
    }

    @Test
    public void getAllFoodGroupByFoodGroupId() {
        List<FoodGroup> allFoodGroupByFoodGroupList = new ArrayList<FoodGroup>();

        List<FoodGroup> foodGroupList = foodGroupDao.getAllFoodGroups();
        List<Food> foodList = foodDao.getAllFoods();

        for (FoodGroup foodGroup : foodGroupList) {
            List<Food> tempList = new ArrayList<Food>();
            for (Food food : foodList) {
                if (food.getGroup_id() == foodGroup.getId()) {
                    tempList.add(food);
                }
            }
            foodGroup.setFoodList(tempList);
            allFoodGroupByFoodGroupList.add(foodGroup);
        }
        LOGGER.debug("allFoodGroupByFoodGroupList = {}", JsonUtil.toJson(allFoodGroupByFoodGroupList));
    }
}
