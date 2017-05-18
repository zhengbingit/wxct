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
}
