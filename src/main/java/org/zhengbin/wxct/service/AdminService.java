package org.zhengbin.wxct.service;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.wxct.dao.FoodGroupDao;
import org.zhengbin.wxct.dao.OrderDao;
import org.zhengbin.wxct.dao.OrderInfoDao;
import org.zhengbin.wxct.model.FoodGroup;
import org.zhengbin.wxct.model.OrderInfo;
import org.zhengbin.wxct.model.Orders;
import org.zhengbin.wxct.model.Status;

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
//        int orderInfoNum = orderInfoDao.deleteOrderInfosByOrderIds(orderIds);
        int orderNum = orderDao.deleteOrders(orderIds);
        resultStatus.setStatus(true);
        Map<String, Object> mapValue = new HashMap<String, Object>();
//        mapValue.put("orderInfoNum", orderInfoNum);
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

}
