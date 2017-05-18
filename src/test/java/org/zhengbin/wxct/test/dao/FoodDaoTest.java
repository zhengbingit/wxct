package org.zhengbin.wxct.test.dao;

import org.zhengbin.wxct.dao.FoodDao;
import org.zhengbin.wxct.model.Food;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhengbinMac on 2017/4/18.
 */
public class FoodDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodDaoTest.class);
    private FoodDao foodDao;

    public FoodDaoTest() {
        this.foodDao = new FoodDao();
    }

    @Test
    public void testGetFood() {
        List<Map<String, Object>> list = foodDao.getFood(1);
        for (Map<String, Object> map : list) {
            Set<String> set = map.keySet();
            for (String s : set) {
                LOGGER.debug("Key = {}, Value = {}", s, map.get(s));
            }
        }
    }

    @Test
    public void testGetFoods() {
        List<Food> foodList = foodDao.getAllFoods();
        for (Food food : foodList) {
            System.out.println(food.getGroup_id()==null);
        }
    }

    @Test
    public void testUpdateFood() {
        Food f = new Food();
        f.setId(1);
        f.setName("宫保鸡丁");
        LOGGER.debug("update : {}", foodDao.updateFood(f));
    }

    @Test
    public void testUpdateFoodNum() {
        int id = 1;
        int num = 2;
        LOGGER.debug("updateFoodNum : {}", foodDao.updateFoodNum(num, id));
    }

    @Test
    public void testUpdateFoodRestNum() {
        int id = 1;
        int num = 1000;
        LOGGER.debug("updateFoodRestNum : {}", foodDao.updateFoodRestNum(num, id));
    }
}
