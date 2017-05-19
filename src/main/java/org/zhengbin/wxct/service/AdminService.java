package org.zhengbin.wxct.service;

import javafx.scene.control.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.snowflake.framework.helper.ServletHelper;
import org.zhengbin.snowflake.framework.util.FileUtil;
import org.zhengbin.snowflake.framework.util.QrCodeUtil;
import org.zhengbin.wxct.dao.*;
import org.zhengbin.wxct.model.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
    private FoodDao foodDao;
    @Inject
    private TableGroupDao tableGroupDao;
    @Inject
    private TableDao tableDao;

    /**
     * 获得所有的订单
     *
     * @return
     */
    public List<Orders> getOrders() {
        return orderDao.getAllOrderNoInfo();
    }

    /**
     * 根据订单 Id 获得对应的订单详情
     *
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
     *
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
     *
     * @return
     */
    public List<FoodGroup> getAllFoodGroup() {
        return foodGroupDao.getAllFoodGroups();
    }

    /**
     * 修改菜品分类名
     *
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
     *
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
     *
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

    /**
     * 获得所有桌台信息
     *
     * @return
     */
    public List<TableGroup> getAllTableInfo() {
        return tableGroupDao.getAllTableGroup();
    }

    /**
     * 添加桌台信息
     * 生成二维码
     *
     * @param table
     * @return
     */
    public int addTableInfo(Table table) {
        String realPath = ServletHelper.getSession().getServletContext().getRealPath("");
        realPath = realPath.substring(0, realPath.lastIndexOf("target"));
        // 桌台信息落库
        tableDao.addTable(table);
        // 获得最后一个桌台id
        int id = tableDao.getTableLastInfo();
        String content = "http://192.168.43.26:8080/wxct/hello?tableId="+id;
        String imgPath = realPath+"/src/main/webapp/qrcodeimg/qrcode-"+id+".png";
        LOGGER.debug("content = {}, imgPath = {}", content, imgPath);
        QrCodeUtil.createQrcodeImage(imgPath, content);
        return id;
    }

    /**
     * 修改桌台信息（桌台人数、桌台分类）
     *
     * @return
     */
    public Status updateTableInfo(Table table) {
        Status status = new Status();
        status.setStatus(tableDao.updateTableInfo(table));
        return status;
    }

    /**
     * 删除桌台信息
     * @param id
     * @return
     */
    public Status deleteTableInfo(int id) {
        Status status = new Status();
        status.setStatus(tableDao.deleteTableInfo(id));
        return status;
    }

    /**
     * 获得所有菜品信息，按菜品的分类进行分组
     * @return
     */
    public List<FoodGroup> getAllFoodInfo() {
        return foodDao.getAllFoodInfo();
    }

    /**
     * 更新菜品信息
     * @param food
     * @return
     */
    public boolean updateFoodInfo(Food food) {
        return foodDao.updateFood(food);
    }

    /**
     * 删除菜品信息
     * @param id
     * @return
     */
    public boolean deleteFoodInfo(int id) {
        return foodDao.deleteFoodInfo(id);
    }

    /**
     * 添加菜品信息
     * @param food
     * @return
     */
    public boolean addFoodInfo(Food food) {
        String groupName = foodGroupDao.getFoodGroup(food.getGroup_id());
        food.setGroup_name(groupName);
        return foodDao.addFoodInfo(food);
    }

}
