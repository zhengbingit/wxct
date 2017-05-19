package org.zhengbin.wxct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.wxct.model.*;
import org.zhengbin.wxct.service.APICloudService;
import org.zhengbin.wxct.service.FoodService;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务员端：安卓
 * Created by zhengbinMac on 2017/5/9.
 */
@Controller
public class APICloudController {
    private static final Logger LOGGER = LoggerFactory.getLogger(APICloudController.class);

    @Inject
    private FoodService foodService;
    @Inject
    private APICloudService apiCloudService;


    /**
     * 管理员登录
     * @param param
     * @return
     */
    @Action("post:/apicloud/login")
    public Data adminLogin(Param param) {
        String openid = param.getString("openid");
        String password = param.getString("password");
        LOGGER.debug("openid = {}, password = {}", openid, password);
        Status resultStatus = apiCloudService.adminLogin(openid, password);
        return new Data(resultStatus);
    }

    /**
     * 获取所有桌台信息
     */
    @Action("get:/apicloud/getAllTableInfo")
    public Data getAllTableInfo() {
        List<TableGroup> tableGroupList = apiCloudService.getTableGroupList();
        LOGGER.debug("tableGroupList = {}", tableGroupList);
        return new Data(tableGroupList);
    }

    /**
     * 修改桌台状态
     */
    @Action("post:/apicloud/updateTableStatus")
    public Data updateTableStatus(Param param) {
        int id = param.getInt("table_id");
        int status = param.getInt("table_status");
        LOGGER.debug("id = {}, status = {}", id, status);
        Status resultStatus = new Status();
        resultStatus.setStatus(apiCloudService.updateTableStatus(id, status));
        return new Data(resultStatus);
    }

    /**
     * 查看某桌台的当前订单详情
     */
    @Action("get:/apicloud/getTableOrderByTableId")
    public Data getTableOrderByTableId(Param param) {
        int tableId = param.getInt("table_id");
        Orders orders = apiCloudService.getTableOrderInfoList(tableId);
        LOGGER.debug("orders = {}", orders);
        return new Data(orders);
    }

    /**
     * 删除某个订单中的一个商品
     */
    @Action("get:/apicloud/deleteOrderInfoById")
    public Data deleteOrderInfoById(Param param) {
        int orderId = param.getInt("order_id");
        int orderInfoId = param.getInt("orderinfo_id");
        LOGGER.debug("orderId = {}, orderInfoId = {}", orderId, orderInfoId);
        boolean status = apiCloudService.deleteOrderInfoById(orderId, orderInfoId);
        Status resultStatus = new Status();
        resultStatus.setStatus(status);
        LOGGER.debug("deleteOrderInfoById, orderId = {}, orderInfoId = {}, resultStatus = {}", orderId, orderInfoId, resultStatus);
        return new Data(resultStatus);
    }

}
