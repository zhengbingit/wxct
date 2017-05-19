package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.dao.FoodGroupDao;

/**
 * Created by zhengbinMac on 2017/5/18.
 */
public class FoodGroupDaoTest {
    private static FoodGroupDao foodGroupDao;
    static {
        foodGroupDao = new FoodGroupDao();
    }
    @Test
    public void testGetAllFoodGroup() {
        System.out.println(JsonUtil.toJson(foodGroupDao.getAllFoodGroups()));
    }

    @Test
    public void testUpdateFoodGroupInfo() {
        int groupId = 1;
        String newFoodGroupName = "精品小炒1";
        System.out.println(foodGroupDao.updateFoodGroup(groupId, newFoodGroupName));
    }

    @Test
    public void testAddFoodGroup() {
        String groupName = "沙县小吃";
        System.out.println(foodGroupDao.addFoodGroup(groupName));
    }

    @Test
    public void getFoodGroupInfo() {
        System.out.println(foodGroupDao.getFoodGroup(1));
    }
}
