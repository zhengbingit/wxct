package org.zhengbin.wxct.service;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.wxct.dao.FoodGroupDao;
import org.zhengbin.wxct.dao.OrderDao;
import org.zhengbin.wxct.dao.OrderInfoDao;
import org.zhengbin.wxct.dao.TableGroupDao;
import org.zhengbin.wxct.model.*;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/5/17.
 */
@Service
public class AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    @Inject
    private OrderDao orderDao;
    @Inject
    private OrderInfoDao orderInfoDao;
    @Inject
    private FoodGroupDao foodGroupDao;
    @Inject
    private TableGroupDao tableGroupDao;

    /**
     * 获得所有的订单
     * @return
     */
    public List<Orders> getOrders() {
        return orderDao.getAllOrderNoInfo();
    }

    /**
     * 根据订单 Id 获得对应的订单详情
     * @param orderId
     * @return
     */
    public List<OrderInfo> getOrderInfo(int orderId) {
        return orderInfoDao.getOrderInfoByOrderId2Admin(orderId);
    }

    /**
     * 删除多个订单
     * 1. 删订单详情
     * 2. 删订单
     * @param orderIds
     * @return
     */
    public Status deleteOrders(String orderIds) {
        LOGGER.debug("orderIds = {}", orderIds);
        Status resultStatus = new Status();
        int orderNum = orderDao.deleteOrders(orderIds);
        resultStatus.setStatus(true);
        Map<String, Object> mapValue = new HashMap<String, Object>();
        mapValue.put("orderNum", orderNum);
        resultStatus.setValues(mapValue);
        return resultStatus;
    }

    /**
     * 获得所有的菜品分类信息
     * @return
     */
    public List<FoodGroup> getAllFoodGroup() {
        return foodGroupDao.getAllFoodGroups();
    }

    /**
     * 修改菜品分类名
     * @param groupId
     * @param groupName
     * @return
     */
    public Status updateFoodGroupName(int groupId, String groupName) {
        Status status = new Status();
        status.setStatus(foodGroupDao.updateFoodGroup(groupId, groupName));
        return status;
    }

    /**
     * 删除菜品分类（多个）
     * 返回删除个数
     * @param ids
     * @return
     */
    public Status deleteFoodGroups(String ids) {
        LOGGER.debug("groupIds = {}", ids);
        int groupNum = foodGroupDao.deleteFoodGroups(ids);
        Status status = new Status();
        status.setStatus(true);
        Map<String, Object> mapValue = new HashMap<String, Object>();
        mapValue.put("groupNum", groupNum);
        status.setValues(mapValue);
        return status;
    }

    /**
     * 添加菜品分类
     * @param groupName
     * @return
     */
    public Status addFoodGroup(String groupName) {
        Status resultStatus = new Status();
        resultStatus.setStatus(true);
        Map<String, Object> mapValue = new HashMap<String, Object>();
        mapValue.put("newGroupId", foodGroupDao.addFoodGroup(groupName));
        resultStatus.setValues(mapValue);
        return resultStatus;
    }

    /**
     * 获得所有桌台分类
     */
    public List<TableGroup> getAllTableGroup() {
        return tableGroupDao.getAllTableGroups();
    }

    /**
     * 修改桌台分类
     */
    public Status updateTableGroup(int groupId, String groupName) {
        Status status = new Status();
        status.setStatus(tableGroupDao.updateTableGroup(groupId, groupName));
        return status;
    }
    /**
     * 删除桌台分类
     */
    public Status deleteTableGroup(String ids) {
        Status status = new Status();
        status.setStatus(true);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupNum", tableGroupDao.deleteTableGroup(ids));
        status.setValues(map);
        return status;
    }

    /**
     * 增加桌台分类
     */
    public Status addTableGroup(String groupName) {
        Status status = new Status();
        status.setStatus(true);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupNum", tableGroupDao.addTableGroup(groupName));
        status.setValues(map);
        return status;
    }
}
