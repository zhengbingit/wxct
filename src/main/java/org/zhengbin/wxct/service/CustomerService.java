package org.zhengbin.wxct.service;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.snowflake.framework.annotation.Transaction;
import org.zhengbin.snowflake.framework.bean.FileParam;
import org.zhengbin.snowflake.framework.helper.UploadHelper;
import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.constants.OrderStatusConstant;
import org.zhengbin.wxct.constants.TableStatusConstant;
import org.zhengbin.wxct.dao.*;
import org.zhengbin.wxct.model.*;
import org.zhengbin.wxct.util.FoodArrayCacheUtil;
import org.zhengbin.wxct.util.GeTuiPushUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/4/30.
 */
@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    // 折扣
    private static final double DISCOUNT = 1d;
    @Inject
    private OrderDao orderDao;
    @Inject
    private OrderInfoDao orderInfoDao;
    @Inject
    private TableDao tableDao;
    @Inject
    private FoodDao foodDao;
    @Inject
    private FoodGroupDao foodGroupDao;

    /**
     * 一键催单
     * @return
     */
    public Status urgeOrder(String tableName) {
        Map<String, Object> pushMap = new HashMap<String, Object>();
        pushMap.put("title", "催单！");
        pushMap.put("content", tableName + "，请尽快出餐！");
        Status resultStatus = new Status();
        resultStatus.setStatus(GeTuiPushUtil.pushMsg(pushMap));
        return resultStatus;
    }

    /**
     * 查看菜单进行点餐
     * 1. 获取所有菜品信息
     * 2. 修改桌台状态为点餐中
     * 3. 推送消息给服务员端
     * @return
     */
    public List<FoodGroup> menu(int tableId) {
        // 获取所有菜品信息
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
        // 修改桌台状态为，点餐中
        tableDao.updateTableStatusById(tableId, TableStatusConstant.DIAN_CAN_ZHONG);
        // 推送消息给服务员端
        String title = "来客人啦！";
        String tableName = getTableName(tableId);
        String content = tableName + "，正在点餐...";
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", title);    // 消息标题
        jsonMap.put("content", content);// 消息内容
        jsonMap.put("tableId", tableId);// 桌台id
        jsonMap.put("tableStatus", TableStatusConstant.DIAN_CAN_ZHONG);// 桌台状态
        LOGGER.debug("推送, ret = {}", GeTuiPushUtil.pushMsg(jsonMap));
        return allFoodGroupByFoodGroupList;
    }

    /**
     * 查看我的订单
     * 按桌台id 获取该卓台没有取消的订单
     * @return
     */
    public Orders myOrder(int tableId) {
        return orderDao.getOrderByTableId(tableId);
    }

    /**
     * 添加订单
     * 1. 落库
     *      增加订单
     *      增加订单详情
     * 2. 修改桌台状态为 已下单
     * 3. 删除缓存中的数据
     * 4. 给服务员端推送消息
     *      已下单
     */
    public Status makeOrder(int tableId, String foodArrayListJson, String remark) {
        List<FoodArray> foodArrayList = JsonUtil.fromJson2List(foodArrayListJson, FoodArray.class);
        double price = 0d;
        for (FoodArray foodArray : foodArrayList) {
            price += foodArray.getRealPrice();
        }
        double realPrice = price * DISCOUNT; // 应收 = 总价 * 折扣
        String time = String.valueOf(System.currentTimeMillis()/1000);
        int status = OrderStatusConstant.UNDONE; // 默认未完成
        int adminId = 1;
        int userId = 1;
        // 订单
        Orders orders = new Orders();
        orders.setTime(time);
        orders.setStatus(status);
        orders.setPrice(price);
        orders.setDiscount(DISCOUNT);
        orders.setReal_price(realPrice);
        orders.setAdmin_id(adminId);
        orders.setUser_id(userId);
        orders.setUser_name("test");
        orders.setAdmin_name("test");
        orders.setTable_id(tableId);
        String tableName = getTableName(tableId);
        orders.setTable_name(tableName);
        orders.setRemark(remark);
        // 插入新订单，并获得订单 id
        int orderId = Integer.parseInt(String.valueOf(orderDao.insertOrder(orders)));
        // 订单详情
        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        for (FoodArray foodArray : foodArrayList) {
            OrderInfo tempOrderInfo = new OrderInfo();
            tempOrderInfo.setOrder_id(orderId);
            tempOrderInfo.setFood_id(foodArray.getFoodId());
            tempOrderInfo.setPrice(foodArray.getDishesPrice());
            tempOrderInfo.setNum(foodArray.getDishesNum());
            tempOrderInfo.setTotal_price(foodArray.getRealPrice());
            tempOrderInfo.setFood_name(foodArray.getDishesName());
            tempOrderInfo.setRemark(foodArray.getDishesRemarks());
            orderInfoList.add(tempOrderInfo);
        }
        orderInfoDao.addOrderInfo(orderInfoList);

        // 修改桌台状态为 —— 已下单
        tableDao.updateTableStatusById(tableId, TableStatusConstant.YI_XIA_DAN);

        // 删除缓存中数据
        FoodArrayCacheUtil.deleteFoodArrayListByKey(tableId);
        Status resultStatus = new Status();
        resultStatus.setStatus(true);
        Map<String, Object> resultMap = new HashedMap();
        resultMap.put("orderId", orderId);
        resultStatus.setValues(resultMap);

        // 推送消息给服务员端
        String title = "新订单提醒";
        String content = tableName + "，已下单！";
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", title);    // 消息标题
        jsonMap.put("content", content);// 消息内容
        jsonMap.put("tableId", tableId);// 桌台id
        jsonMap.put("tableStatus", TableStatusConstant.YI_XIA_DAN);// 桌台状态
        LOGGER.debug("推送, ret = {}", GeTuiPushUtil.pushMsg(jsonMap));
        return resultStatus;
    }

    /**
     * 欢迎页面
     * 1. 返回餐厅名称、桌台 Id
     * @param tableId
     * @param diningName
     * @return
     */
    public Status hello(int tableId, String diningName) {
        Status resultStatus = new Status();
        Map<String, Object> map = new HashedMap();
        map.put("tableId", tableId);
        map.put("diningName", diningName);
        map.put("tableName", getTableName(tableId));
        resultStatus.setValues(map);
        resultStatus.setStatus(true);
        return resultStatus;
    }

    @Transaction
    public boolean createCustomer(FileParam fileParam) {
        UploadHelper.uploadFile("/tmp/upload/", fileParam);
        return true;
    }

    public AdvanceOrder showOrder(int tableId, String foodArrayJson) {
        // 加入缓存中 桌台 id 和 订单详情
        FoodArrayCacheUtil.putFoodArrayList(tableId, foodArrayJson, FoodArray.class);
        // 从缓存中取出，并返回
        List<FoodArray> foodArrayList = FoodArrayCacheUtil.getFoodArrayListByKey(tableId);
        // 返回至前端
        AdvanceOrder advanceOrder = new AdvanceOrder();
        double realMoney = 0D;
        // 计算总价格
        for (FoodArray foodArray : foodArrayList) {
            realMoney += foodArray.getRealPrice();
        }
        // 共计价格 = 应付价格
        advanceOrder.setEndMoney(realMoney);
        advanceOrder.setRealMoney(realMoney);
        advanceOrder.setFoodArrayList(foodArrayList);

        return advanceOrder;
    }

    /**
     * 获取桌台名
     * 桌台分类名 + 桌台名 + '号桌'，如 大厅1号桌
     * @return
     */
    private String getTableName(int tableId) {
        Table tableInfo = tableDao.getTableInfo(tableId);
        String tableGroupName = tableInfo.getTableGroupName();
        String tableName = tableInfo.getName();
        String resultStr = tableGroupName + tableName + "号桌";
        return resultStr;
    }

}
