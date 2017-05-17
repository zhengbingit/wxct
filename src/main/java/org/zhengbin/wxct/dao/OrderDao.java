package org.zhengbin.wxct.dao;

import org.zhengbin.snowflake.framework.annotation.Repository;
import org.zhengbin.snowflake.framework.annotation.Transaction;
import org.zhengbin.wxct.constants.OrderStatusConstant;
import org.zhengbin.wxct.model.Orders;
import org.zhengbin.wxct.model.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import java.util.List;

/**
 * Order 订单，数据操作
 * Created by zhengbinMac on 2017/4/19.
 */
@Repository
public class OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);
    private static final String COLUMN_ORDERS = "id, time, status, price, discount, real_price, admin_id, user_id, table_id, user_name, table_name, admin_name, remark";
    private static final String COLUMN_ORDERINFO = "id, order_id, food_id, price, num, total_price, food_name, remark";

    /**
     * 更新订单的总金额
     * @return
     */
    @Transaction
    public boolean updateOrderPrice(int orderId, double price) {
        String sql = "UPDATE orders SET price = ?, real_price = discount*? WHERE id = ?";
        return DatabaseHelper.executeUpdate(sql, price, price, orderId) == 1;
    }

    /**
     * 获取某桌台的当前订单（非取消的订单）
     * @param tableId
     * @return
     */
    public Orders getOrderByTableId(int tableId) {
        String sql = "select "+ COLUMN_ORDERS +" from orders where table_id = ? and status != " + OrderStatusConstant.CANCEL;
        Orders resultOrder = DatabaseHelper.queryEntity(Orders.class, sql, tableId);
        if (resultOrder!=null) {
            String orderInfoSql = "select " + COLUMN_ORDERINFO + " from orderinfo where order_id = ?";
            List<OrderInfo> orderInfoList = DatabaseHelper.queryEntityList(OrderInfo.class, orderInfoSql, resultOrder.getId());
            resultOrder.setOrder_infos(orderInfoList);
        }
        return resultOrder;
    }

    /**
     * 获取所有的订单
     * @return
     */
    public List<Orders> getAllOrders() {
        String orderSql = "select " + COLUMN_ORDERS + " from orders";
        List<Orders> ordersList = DatabaseHelper.queryEntityList(Orders.class, orderSql);
        for (Orders order : ordersList) {
            String orderInfoSql = "select " + COLUMN_ORDERINFO + " from orderinfo where order_id = ?";
            List<OrderInfo> orderInfoList = DatabaseHelper.queryEntityList(OrderInfo.class, orderInfoSql, order.getId());
            order.setOrder_infos(orderInfoList);
        }
        return ordersList;
    }

    /**
     * 按订单id 获取 订单信息
     * @param id
     * @return
     */
    public Orders getOrderById(int id) {
        String orderSql = "select " + COLUMN_ORDERS + " from `orders` where id = ?";
        Orders order = DatabaseHelper.queryEntity(Orders.class, orderSql, id);
        String orderInfoSql = "select " + COLUMN_ORDERINFO + " from orderinfo where order_id = ?";
        List<OrderInfo> orderInfoList = DatabaseHelper.queryEntityList(OrderInfo.class, orderInfoSql, id);
        order.setOrder_infos(orderInfoList);
        return order;
    }

    /**
     * 添加新订单
     * @param order
     * @return
     */
    public Long insertOrder(Orders order) {
        return DatabaseHelper.insertEntity(order);
    }

    /**
     * 根据订单 id 取消订单
     * @return
     */
    public boolean cancelOrder(int id) {
        Orders order = new Orders();
        order.setStatus(-1);
        return DatabaseHelper.updateEntity(id, order);
    }

    /**
     * 根据订单 id 删除订单
     * @param id
     * @return
     */
    public boolean deleteOrder(int id) {
        return DatabaseHelper.deleteEntity(Orders.class, id);
    }
}
