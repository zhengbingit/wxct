package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;
import org.zhengbin.wxct.dao.OrderInfoDao;
import org.zhengbin.wxct.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/12.
 */
public class OrderInfoDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInfoDaoTest.class);
    private static OrderInfoDao orderInfoDao;
    public OrderInfoDaoTest() {
        orderInfoDao = new OrderInfoDao();
    }

    @Test
    public void testDeleteOrderInfoByOrderInfoId() {
        int orderInfoId = 3;
        LOGGER.debug("删除订单详情：{}", orderInfoDao.deleteOrderInfoByOrderInfoId(orderInfoId));
    }

    @Test
    public void testSumOrderPriceByOrderId() {
        int orderId = 1;
        LOGGER.debug("sum = {}", orderInfoDao.sumOrderPriceByOrderId(orderId));
    }

    @Test
    public void testSumDatabaseHelper() {
        String sql = "select SUM(total_price) as total from orderinfo where order_id = ?";
        LOGGER.debug("total = {}", DatabaseHelper.queryColumn("total", sql, 1));
    }

    @Test
    public void testAddOrderInfo() {
        /**
         * "id":1,
         "order_id":1,
         "food_id":1,
         "price":18,
         "num":1,
         "total_price":18,
         "food_name":"宫保鸡丁",
         "remark":"少放辣",
         "food":null
         */
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setOrder_id(10);
        orderInfo1.setFood_id(1);
        orderInfo1.setPrice(18d);
        orderInfo1.setNum(1);
        orderInfo1.setTotal_price(18d);
        orderInfo1.setFood_name("宫保鸡丁");
        orderInfo1.setRemark("少放辣椒");

        OrderInfo orderInfo2 = new OrderInfo();
        orderInfo2.setOrder_id(10);
        orderInfo2.setFood_id(1);
        orderInfo2.setPrice(16d);
        orderInfo2.setNum(1);
        orderInfo2.setTotal_price(16d);
        orderInfo2.setFood_name("鱼香茄子");
        orderInfo2.setRemark("少放油");

        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
        orderInfos.add(orderInfo1);
        orderInfos.add(orderInfo2);

        LOGGER.debug("插入是否成功：{}", orderInfoDao.addOrderInfo(orderInfos));
    }
}
