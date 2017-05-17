package org.zhengbin.wxct.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.annotation.Service;
import org.zhengbin.snowflake.framework.annotation.Transaction;
import org.zhengbin.wxct.dao.*;
import org.zhengbin.wxct.model.Orders;
import org.zhengbin.wxct.model.Status;
import org.zhengbin.wxct.model.TableGroup;

import java.util.List;

/**
 * 服务员端接口服务层
 * Created by zhengbinMac on 2017/5/12.
 */
@Service
public class APICloudService {
    private static final Logger LOGGER = LoggerFactory.getLogger(APICloudService.class);
    @Inject
    private TableDao tableDao;
    @Inject
    private TableGroupDao tableGroupDao;
    @Inject
    private OrderInfoDao orderInfoDao;
    @Inject
    private OrderDao orderDao;
    @Inject
    private AdminDao adminDao;

    /**
     * 管理员登录
     */
    public Status adminLogin(String openid, String password) {
        String bdPa = adminDao.getAdminPasswordByOpenId(openid);
        Status resultStatus = new Status();
        if (bdPa.equals(password)) {
            resultStatus.setStatus(true);
        }else {
            resultStatus.setStatus(false);
        }
        return resultStatus;
    }

    /**
     * 获取所有桌台信息
     */
    public List<TableGroup> getTableGroupList() {
        return tableGroupDao.getAllTableGroup();
    }

    /**
     * 修改桌台状态
     */
    public boolean updateTableStatus(int id, int status) {
        return tableDao.updateTableStatusById(id, status);
    }

    /**
     * 查看某桌台的当前订单详情
     */
    public Orders getTableOrderInfoList(int tableId) {
        return orderDao.getOrderByTableId(tableId);
    }

    /**
     * 删除某个订单中的一个商品
     * 1.根据订单详情id，删除该条商品详情
     * 2.更新订单的总金额（重新计算）
     */
    public boolean deleteOrderInfoById(int orderId, int orderInfoId) {
        if (!orderInfoDao.deleteOrderInfoByOrderInfoId(orderInfoId)) {
            return false;
        }
        double orderTotalPrice = orderInfoDao.sumOrderPriceByOrderId(orderId);
        return orderDao.updateOrderPrice(orderId, orderTotalPrice);
    }
}
