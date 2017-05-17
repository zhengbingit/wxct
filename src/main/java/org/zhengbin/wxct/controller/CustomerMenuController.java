package org.zhengbin.wxct.controller;

import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.View;
import org.zhengbin.wxct.model.AdvanceOrder;
import org.zhengbin.wxct.model.FoodGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.wxct.model.Orders;
import org.zhengbin.wxct.model.Status;
import org.zhengbin.wxct.service.CustomerService;
import org.zhengbin.wxct.service.FoodService;

import java.util.List;

/**
 * 消费者端
 * Created by zhengbinMac on 2017/4/25.
 */
@Controller
public class CustomerMenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMenuController.class);

    @Inject
    private CustomerService customerService;
    @Inject
    private FoodService foodService;

    /**
     * 一键催单
     * @param param
     * @return
     */
    @Action("post:/urgeOrder")
    public Data urgeOrder(Param param) {
        String tableName = param.getString("tableName");
        LOGGER.debug("一键催单，tableName = {}", tableName);
        return new Data(customerService.urgeOrder(tableName));
    }

    /**
     * 取消订单
     * @param param
     * @return
     */
    @Action("post:/cancelOrder")
    public Data cancelOrder(Param param) {
        int orderId = param.getInt("orderId");
        LOGGER.debug("取消订单，orderId = {}", orderId);
        return null;
    }

    /**
     * 获得我的订单
     * 根据桌台id，获得非
     * @param param
     * @return
     */
    @Action("post:/myOrder")
    public Data myOrder(Param param) {
        LOGGER.debug("controller myOrder");
        int tableId = param.getInt("tableId");
        Orders resultOrder = customerService.myOrder(tableId);
        LOGGER.debug("tableId = {}, resultOrder = {}", tableId, resultOrder);
        return new Data(resultOrder);
    }

    /**
     * 二维码跳转至欢迎页面
     * @param param
     * @return
     */
    @Action("get:/hello")
    public View hello(Param param) {
        LOGGER.debug("controller hello");
        int tableId = param.getInt("tableId");
        String restaurantName = "微信餐厅";
        Status resultStatus = customerService.hello(tableId, restaurantName);
        LOGGER.debug("hello result = {}", resultStatus);
        return new View("home.jsp").addModel("status", resultStatus);
    }

    /**
     * 确定下单
     * @param param
     * @return
     */
    @Action("post:/showOrder")
    public Data showOrder(Param param) {
        LOGGER.debug("controller showOrder");
        String foodArray = param.getString("foodArray");
        int tableId = param.getInt("tableId");
        LOGGER.debug("foodArray = {}, \ntableNo = {}", foodArray, tableId);
        AdvanceOrder resultAdvanceOrder = customerService.showOrder(tableId, foodArray);
        LOGGER.debug("showOrder(), resultAdvanceOrder = {}", resultAdvanceOrder);
        return new Data(resultAdvanceOrder);
    }

    /**
     * 下单
     * @param param
     * @return
     */
    @Action("post:/makeOrder")
    public Data makeOrder(Param param) {
        LOGGER.debug("controller makeOrder");
        int tableId = param.getInt("tableId");
        String foodArrayJson = param.getString("foodArray");
        String remark = param.getString("remark");
        LOGGER.debug("tableId = {}, \nfoodArrayJson = {}, \nremark = {}", tableId, foodArrayJson, remark);
        Status resultStatus = customerService.makeOrder(tableId, foodArrayJson, remark);
        return new Data(resultStatus);
    }

    /**
     * 菜单
     * @return
     */
    @Action("post:/home")
    public Data home(Param param) {
        LOGGER.debug("controller home");
        int tableId = param.getInt("tableId");
        List<FoodGroup> foodGroupList = customerService.menu(tableId);
        LOGGER.debug("foodGroupList = {}", foodGroupList);
        return new Data(foodGroupList);
    }

}
