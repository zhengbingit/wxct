package org.zhengbin.wxct.test.dao;

import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.dao.OrderDao;
import org.zhengbin.wxct.model.Orders;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengbinMac on 2017/4/19.
 */
public class OrderDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoTest.class);
    private static OrderDao orderDao;
    public OrderDaoTest() {
        orderDao = new OrderDao();
    }

    @Test
    public void testUpdateOrderPrice() {
        int orderId = 1;
        double price = 62d;
        LOGGER.debug("resultStatus = {}", orderDao.updateOrderPrice(orderId, price));
    }

    @Test
    public void testGetOrderByTableId() {
        int tableId = 3;
        Orders orders = orderDao.getOrderByTableId(tableId);
        LOGGER.debug("order = {}", orders);
        LOGGER.debug("JSON = {}", JsonUtil.toJson(orders));
    }

    @Test
    public void testGetAllOrders() {
        LOGGER.debug("orders : {}", orderDao.getAllOrders());
    }

    @Test
    public void testGetOrderById() {
        int id = 1;
        LOGGER.debug("order = {}", orderDao.getOrderById(id));
    }

    @Test
    public void testInsertOrder() {
        int admin_id = 1;
        int user_id = 1;
        int table_id = 2;
        String admin_name = "管理员1";
        String table_name = "大厅2";
        String remark = "肉菜别放肉，素菜别放菜";

        Orders order = new Orders();
        order.setTime(String.valueOf(System.currentTimeMillis()/1000));
        order.setStatus(1);
        order.setPrice(18d);
        order.setDiscount(1d);
        order.setReal_price(18d);
        order.setUser_name("郑斌");
        order.setAdmin_name(admin_name);
        order.setTable_name(table_name);
        order.setTable_id(table_id);
        order.setUser_id(user_id);
        order.setAdmin_id(admin_id);
        order.setRemark(remark);

        LOGGER.debug("插入 ：{}", orderDao.insertOrder(order));
    }

    @Test
    public void testCancelOrder() {
        int id = 7;
        LOGGER.debug("更新 ：{}", orderDao.cancelOrder(id));
    }

    @Test
    public void testDeleteOrder() {
        int[] ids = {7};
        for (int i : ids) {
            LOGGER.debug("删除 ：{}", orderDao.deleteOrder(i));
        }
    }

    @Test
    public void testQueryAll() {
        System.out.println(orderDao.getAllOrders());
    }
}
