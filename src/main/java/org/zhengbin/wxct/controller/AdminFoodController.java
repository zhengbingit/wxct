package org.zhengbin.wxct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.wxct.model.Food;
import org.zhengbin.wxct.model.Status;
import org.zhengbin.wxct.service.AdminService;

/**
 * 管理员端：菜品管理
 * Created by zhengbinMac on 2017/5/18.
 */
@Controller
public class AdminFoodController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminFoodController.class);

    @Inject
    private AdminService adminService;

    /**
     * 获得所有的菜品分类信息
     * @return
     */
    @Action("post:/admin/getAllFoodGroup")
    public Data getAllFoodGroup() {
        return new Data(adminService.getAllFoodGroup());
    }

    /**
     * 更新菜品分类名
     * @return
     */
    @Action("post:/admin/updateFoodGroup")
    public Data updateFoodGroup(Param param) {
        int groupId = param.getInt("groupId");
        String newGroupName = param.getString("newGroupName");
        LOGGER.debug("groupId = {}, newGroupName = {}", groupId, newGroupName);
        return new Data(adminService.updateFoodGroupName(groupId, newGroupName));
    }

    /**
     * 删除菜品分类
     * @param param
     * @return
     */
    @Action("post:/admin/deleteFoodGroup")
    public Data deleteFoodGroup(Param param) {
        String groupIds = param.getString("groupIds");
        LOGGER.debug("groupIds = {}", groupIds);
        return new Data(adminService.deleteFoodGroups(groupIds));
    }

    /**
     * 添加菜品分类
     * @param param
     * @return
     */
    @Action("post:/admin/addFoodGroup")
    public Data addFoodGroup(Param param) {
        String groupName = param.getString("groupName");
        return new Data(adminService.addFoodGroup(groupName));
    }

    /**
     * 获得所有菜品信息，按菜品分类进行分组
     * @return
     */
    @Action("post:/admin/getAllFoodInfo")
    public Data getAllFoodInfo() {
        return new Data(adminService.getAllFoodInfo());
    }

    /**
     * 更新菜品信息
     * @param param
     * @return
     */
    @Action("post:/admin/updateFoodInfo")
    public Data updateFoodInfo(Param param) {
        int id = param.getInt("id");
        String name = param.getString("name");
        int sellnum = param.getInt("sellnum");
        double price = param.getDouble("price");
        String detail = param.getString("detail");
        String offstok = param.getString("offstok");
        int offstokv;
        if (offstok.equals("否")) {
            offstokv = 0;
        }else {
            offstokv = 1;
        }
        String unit = param.getString("unit");
        String spell = param.getString("spell");
        int groupId = param.getInt("groupid");
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        food.setSell_num(sellnum);
        food.setPrice(price);
        food.setDetail(detail);
        food.setOff_stock(offstokv);
        food.setUnit(unit);
        food.setSpell(spell);
        food.setGroup_id(groupId);
        LOGGER.debug("FOOD = {}" + food);
        Status status = new Status();
        status.setStatus(adminService.updateFoodInfo(food));
        return new Data(status);
    }

    /**
     * 删除菜品信息
     * @param param
     * @return
     */
    @Action("post:/admin/deleteFoodInfo")
    public Data deleteFoodInfo(Param param) {
        int id = param.getInt("id");
        LOGGER.debug("id = {}", id);
        Status status = new Status();
        status.setStatus(adminService.deleteFoodInfo(id));
        return new Data(status);
    }

    /**
     * 添加菜品信息
     * @param param
     * @return
     */
    @Action("post:/admin/addFoodInfo")
    public Data addFoodInfo(Param param) {
        String name = param.getString("name");
        int sellnum = param.getInt("sellnum");
        double price = param.getDouble("price");
        String detail = param.getString("detail");
        String offstok = param.getString("offstok");
        int offstokv;
        if (offstok.equals("否")) {
            offstokv = 0;
        }else {
            offstokv = 1;
        }
        String unit = param.getString("unit");
        String spell = param.getString("spell");
        int groupId = param.getInt("groupid");
        Food food = new Food();
        food.setName(name);
        food.setSell_num(sellnum);
        food.setPrice(price);
        food.setDetail(detail);
        food.setOff_stock(offstokv);
        food.setUnit(unit);
        food.setSpell(spell);
        food.setGroup_id(groupId);
        LOGGER.debug("FOOD = {}" + food);
        Status status = new Status();
        status.setStatus(adminService.addFoodInfo(food));
        return new Data(status);
    }

}
