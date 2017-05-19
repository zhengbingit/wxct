package org.zhengbin.wxct.dao;

import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.model.FoodGroup;

import java.util.List;

/**
 * 菜品分类数据 DAO
 * Created by zhengbinMac on 2017/5/6.
 */
@Repository
public class FoodGroupDao {
    private static final String COLUMN_TABLE = "id, name";

    /**
     * 增加一个菜品分类
     * @return
     */
    public int addFoodGroup(String groupName) {
        FoodGroup foodGroup = new FoodGroup();
        foodGroup.setName(groupName);
        return Integer.parseInt(String.valueOf(DatabaseHelper.insertEntity(foodGroup)));
    }

    /**
     * 获取所有菜品分类
     */
    public List<FoodGroup> getAllFoodGroups() {
        String sql = "select " + COLUMN_TABLE + " from foodgroup";
        return DatabaseHelper.queryEntityList(FoodGroup.class, sql);
    }

    /**
     * 需改菜品分类名
     * @param groupId
     * @param groupName
     * @return
     */
    public boolean updateFoodGroup(int groupId, String groupName) {
        FoodGroup foodGroup = new FoodGroup();
        foodGroup.setId(groupId);
        foodGroup.setName(groupName);
        return DatabaseHelper.updateEntity(groupId, foodGroup);
    }

    /**
     * 删除多个菜品分类
     * @param ids
     * @return
     */
    public int deleteFoodGroups(String ids) {
        return DatabaseHelper.deleteEntitysByIds(FoodGroup.class, ids);
    }

    /**
     * 获取单个菜品分类信息
     * @param groupId
     * @return
     */
    public String getFoodGroup(int groupId) {
        String sql = "select * from foodgroup where id = ?";
        return DatabaseHelper.queryColumn("name", sql, groupId);
    }
}
