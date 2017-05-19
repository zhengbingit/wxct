package org.zhengbin.wxct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.annotation.Controller;
import org.zhengbin.snowflake.framework.annotation.Inject;
import org.zhengbin.snowflake.framework.bean.Data;
import org.zhengbin.snowflake.framework.bean.Param;
import org.zhengbin.wxct.service.AdminService;

/**
 * 管理员端：菜品管理
 * Created by zhengbinMac on 2017/5/18.
 */
@Controller
public class AdminFoodController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminFoodController.class);

    @Inject
    private AdminService adminService;

    /**
     * 获得所有的菜品分类信息
     * @return
     */
    @Action("post:/admin/getAllFoodGroup")
    public Data getAllFoodGroup() {
        return new Data(adminService.getAllFoodGroup());
    }

    /**
     * 更新菜品分类名
     * @return
     */
    @Action("post:/admin/updateFoodGroup")
    public Data updateFoodGroup(Param param) {
        int groupId = param.getInt("groupId");
        String newGroupName = param.getString("newGroupName");
        LOGGER.debug("groupId = {}, newGroupName = {}", groupId, newGroupName);
        return new Data(adminService.updateFoodGroupName(groupId, newGroupName));
    }

    /**
     * 删除菜品分类
     * @param param
     * @return
     */
    @Action("post:/admin/deleteFoodGroup")
    public Data deleteFoodGroup(Param param) {
        String groupIds = param.getString("groupIds");
        LOGGER.debug("groupIds = {}", groupIds);
        return new Data(adminService.deleteFoodGroups(groupIds));
    }

    /**
     * 添加菜品分类
     * @param param
     * @return
     */
    @Action("post:/admin/addFoodGroup")
    public Data addFoodGroup(Param param) {
        String groupName = param.getString("groupName");
        return new Data(adminService.addFoodGroup(groupName));
    }

    /**
     * 获得所有菜品信息，按菜品分类进行分组
     * @return
     */
    @Action("post:/admin/getAllFoodInfo")
    public Data getAllFoodInfo() {
        return new Data(adminService.getAllFoodInfo());
    }
}
