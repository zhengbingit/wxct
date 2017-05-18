package org.zhengbin.wxct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.wxct.model.Status;
import org.zhengbin.wxct.service.AdminService;

/**
 * 管理员端——PC端
 * Created by zhengbinMac on 2017/5/17.
 */
@Controller
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private AdminService adminService;

    /**
     * 获得所有订单，只返回订单表，不返回订单详情
     * @return
     */
    @Action("post:/admin/getOrderList")
    public Data orderList() {
        return new Data(adminService.getOrders());
    }

    /**
     * 根据订单id，获得其订单详情
     * @param param
     * @return
     */
    @Action("post:/admin/getOrderInfo")
    public Data orderInfo(Param param) {
        int orderId = param.getInt("orderId");
        return new Data(adminService.getOrderInfo(orderId));
    }

    /**
     * 删除多个订单，同时删除对应的订单详情
     * @param param
     * @return
     */
    @Action("post:/admin/deleteOrder")
    public Data deleteOrder(Param param) {
        String orders = param.getString("orderIds");
        Status resultStatus = adminService.deleteOrders(orders);
        return new Data(resultStatus);
    }

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
     * 获得所有的桌台分类
     * @return
     */
    @Action("post:/admin/getAllTableGroup")
    public Data getAllTableGroup() {
        return new Data(adminService.getAllTableGroup());
    }

    /**
     * 更新桌台分类
     * @return
     */
    @Action("post:/admin/updateTableGroup")
    public Data updateTableGroup(Param param) {
        String groupName = param.getString("groupName");
        int groupId = param.getInt("groupId");
        LOGGER.info("groupName = {}, groupId = {}", groupName, groupId);
        return new Data(adminService.updateTableGroup(groupId, groupName));
    }

    /**
     * 添加桌台分类
     * @return
     */
    @Action("post:/admin/addTableGroup")
    public Data addTableGroup(Param param) {
        String groupName = param.getString("groupName");
        LOGGER.debug("groupName = {}", groupName);
        return new Data(adminService.addTableGroup(groupName));
    }

    /**
     * 删除桌台分类
     * @return
     */
    @Action("post:/admin/deleteTableGroup")
    public Data deleteTableGroup(Param param) {
        String ids = param.getString("groupIds");
        LOGGER.debug("ids = {}", ids);
        return new Data(adminService.deleteTableGroup(ids));
    }

    /**
     * 获得所有桌台信息
     * @return
     */
    @Action("post:/admin/getAllTableInfo")
    public Data getAllTableInfo() {
        return new Data(adminService.getAllTableInfo());
    }
}
