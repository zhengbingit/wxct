package org.zhengbin.wxct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.snowflake.framework.helper.ServletHelper;
import org.zhengbin.snowflake.framework.util.QrCodeUtil;
import org.zhengbin.wxct.constants.TableStatusConstant;
import org.zhengbin.wxct.model.Status;
import org.zhengbin.wxct.model.Table;
import org.zhengbin.wxct.service.AdminService;

/**
 * 管理员端：桌台管理
 * Created by zhengbinMac on 2017/5/18.
 */
@Controller
public class AdminTableController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTableController.class);
    @Inject
    private AdminService adminService;

    /**
     * 获得所有的桌台分类
     *
     * @return
     */
    @Action("post:/admin/getAllTableGroup")
    public Data getAllTableGroup() {
        return new Data(adminService.getAllTableGroup());
    }

    /**
     * 更新桌台分类
     *
     * @return
     */
    @Action("post:/admin/updateTableGroup")
    public Data updateTableGroup(Param param) {
        String groupName = param.getString("groupName");
        int groupId = param.getInt("groupId");
        LOGGER.info("groupName = {}, groupId = {}", groupName, groupId);
        return new Data(adminService.updateTableGroup(groupId, groupName));
    }

    /**
     * 添加桌台分类
     *
     * @return
     */
    @Action("post:/admin/addTableGroup")
    public Data addTableGroup(Param param) {
        String groupName = param.getString("groupName");
        LOGGER.debug("groupName = {}", groupName);
        return new Data(adminService.addTableGroup(groupName));
    }

    /**
     * 删除桌台分类
     *
     * @return
     */
    @Action("post:/admin/deleteTableGroup")
    public Data deleteTableGroup(Param param) {
        String ids = param.getString("groupIds");
        LOGGER.debug("ids = {}", ids);
        return new Data(adminService.deleteTableGroup(ids));
    }

    /**
     * 获得所有桌台信息
     *
     * @return
     */
    @Action("post:/admin/getAllTableInfo")
    public Data getAllTableInfo() {
        return new Data(adminService.getAllTableInfo());
    }

    /**
     * 添加桌台信息
     *
     * @param param
     * @return
     */
    @Action("post:/admin/addTableInfo")
    public Data addTableInfo(Param param) {
        //"tableId="+tableId+"&tableName="+tableName+"&tableNum"+tableNum+"&groupId="+groupId;
        int tableId = param.getInt("tableId");
        String tableName = param.getString("tableName");
        int tableNum = param.getInt("tableNum");
        int groupId = param.getInt("groupId");
        LOGGER.debug("tableId = {}, tableName = {}, tableNum = {}, groupId = {}", tableId, tableName, tableNum, groupId);
        Table table = new Table();
        table.setTable_id(tableId);
        table.setName(tableName);
        table.setNum(tableNum);
        table.setGroup_id(groupId);
        // 默认空闲中
        table.setStatus(TableStatusConstant.KONG_XIAN_ZHONG);
        Status status = new Status();
        status.setStatus(adminService.addTableInfo(table)!=0);
        return new Data(status);
    }

    /**
     * 更新桌台信息
     *
     * @param param
     * @return
     */
    @Action("post:/admin/updateTableInfo")
    public Data updateTableInfo(Param param) {
        int id = param.getInt("id");
        int groupId = param.getInt("groupId");
        int num = param.getInt("num");
        Table table = new Table();
        table.setId(id);
        table.setGroup_id(groupId);
        table.setNum(num);
        return new Data(adminService.updateTableInfo(table));
    }

    /**
     * 删除桌台信息
     *
     * @param param
     * @return
     */
    @Action("post:/admin/deleteTableInfo")
    public Data deleteTableInfo(Param param) {
        int id = param.getInt("id");
        return new Data(adminService.deleteTableInfo(id));
    }
}
