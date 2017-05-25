package org.zhengbin.wxct.dao;

import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.wxct.model.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.model.FoodGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜品数据 DAO
 * Created by zhengbinMac on 2017/4/18.
 */
@Repository
public class FoodDao {
    @Inject
    private FoodGroupDao foodGroupDao;

    private static final String COLUMN_TABLE = "id, `name`, sell_num, price, detail," +
                                                " group_id, big_img, small_img," +
                                                " group_name, unit, off_stock," +
                                                " trade_time, spell";
    private static final String COLUMN_TABLE_FOODGROUP = "id, name";
    /**
     * 链表查询，获取菜品详情
     */
    public List<Map<String, Object>> getFood(int id) {
        String sql = "select " +
                "food.id as food_id," +
                "food.`name` as food_name," +
                "food.rest_num," +
                "food.sell_num," +
                "food.price," +
                "food.detail as food_detail," +
                "foodgroup.`name` as fg_name," +
                "foodnature.`name` as fn_name," +
                "foodnature.detail as fn_detail " +
                "FROM food,foodgroup,foodnature " +
                "WHERE food.id=? " +
                "AND food.group_id=foodgroup.id " +
                "AND food.nature_id=foodnature.id;";
        return DatabaseHelper.executeQuery(sql, id);
    }

    /**
     * 获取菜单
     * 前台调用
     */
    public List<Food> getAllFoods() {
        String sql = "select " + COLUMN_TABLE + " from food";
        return DatabaseHelper.queryEntityList(Food.class, sql);
    }

    /**
     * 修改菜品信息
     * 后天调用
     */
    public boolean updateFood(Food food) {
        return DatabaseHelper.updateEntity(food.getId(), food);
    }

    /**
     * 调整菜品销量和库存
     * 前天，出售菜品时调用
     */
    public boolean updateFoodNum(int num, int id) {
        String sql = "update food SET rest_num=rest_num-?,sell_num=sell_num+? where id = ?;";
        Object[] params = {num, num, id};
        return DatabaseHelper.executeUpdate(sql, params) == 1;
    }

    /**
     * 调整菜品库存
     * 后台调用
     */
    public boolean updateFoodRestNum(int num, int id) {
        String sql = "UPDATE food SET rest_num = ? where id = ?";
        Object[] params = {num, id};
        return DatabaseHelper.executeUpdate(sql, params) == 1;
    }

    /**
     * 获取所有菜品详情-管理员端调用
     * @return
     */
    public List<FoodGroup> getAllFoodInfo() {
        // 获取所有菜品信息
        List<FoodGroup> allFoodGroupByFoodGroupList = new ArrayList<FoodGroup>();
        String sql = "select " + COLUMN_TABLE_FOODGROUP + " from foodgroup";
        List<FoodGroup> foodGroupList = DatabaseHelper.queryEntityList(FoodGroup.class, sql);
        List<Food> foodList = getAllFoods();
        for (FoodGroup foodGroup : foodGroupList) {
            List<Food> tempList = new ArrayList<Food>();
            for (Food food : foodList) {
                if (food.getGroup_id()!=null && food.getGroup_id()==foodGroup.getId()) {
                    tempList.add(food);
                }
            }
            foodGroup.setFoodList(tempList);
            allFoodGroupByFoodGroupList.add(foodGroup);
        }
        return allFoodGroupByFoodGroupList;
    }

    /**
     * 添加菜品信息
     * @param food
     * @return
     */
    public boolean addFoodInfo(Food food) {
        return DatabaseHelper.insertEntity(food) == 1;
    }

    /**
     * 删除菜品信息
     * @param id
     * @return
     */
    public boolean deleteFoodInfo(int id) {
        return DatabaseHelper.deleteEntityById(Food.class, id);
    }

}
