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
}
