package org.zhengbin.wxct.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.annotation.Transaction;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.model.OrderInfo;

import java.util.List;

/**
 * 订单详情，数据操作
 * Created by zhengbinMac on 2017/5/12.
 */
@Repository
public class OrderInfoDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInfoDao.class);
    private static final String COLUMNS = "id, order_id, food_id, price, num, total_price, food_name, remark";
    private static final String COLUMNS_ADMIN = "id, order_id, price, num, total_price, food_name";

    public List<OrderInfo> getOrderInfoByOrderId2Admin(int orderId) {
        String sql = "select "+COLUMNS_ADMIN+" from orderinfo where order_id = ?";
        return DatabaseHelper.queryEntityList(OrderInfo.class, sql, orderId);
    }

    /**
     * 按多个订单id 删除对应的所有订单详情
     * @param ids
     * @return
     */
    public int deleteOrderInfosByOrderIds(String ids) {
        return DatabaseHelper.deleteEntitysByCloumn(OrderInfo.class, "order_id", ids);
    }

    /**
     * 根据订单详情 id，删除该条订单详情记录
     * @param orderInfoId
     * @return
     */
    @Transaction
    public boolean deleteOrderInfoByOrderInfoId(int orderInfoId) {
        return DatabaseHelper.deleteEntityById(OrderInfo.class, orderInfoId);
    }

    /**
     * 计算某笔订单的总金额（即所有订单详情的总和）
     * @param orderId
     * @return
     */
    @Transaction
    public double sumOrderPriceByOrderId(int orderId) {
        String sql = "select SUM(total_price) as total from orderinfo where order_id = ?";
        double total = 0d;
        try {
            total = DatabaseHelper.queryColumn("total", sql, orderId);
        }catch (NullPointerException e) {
            LOGGER.error("订单的订单详情为空, orderId = {}, e : {}", orderId,e);
        }
        LOGGER.debug("total = {}", total);
        return total;
    }

    /**
     * 添加新的订单详情
     * @param orderInfoList
     * @return
     */
    public boolean addOrderInfo(List<OrderInfo> orderInfoList) {
        try {
            return DatabaseHelper.insertListEntity(orderInfoList);
        } catch (IllegalAccessException e) {
            LOGGER.error("e : {}", e);
            return false;
        }
    }

}
