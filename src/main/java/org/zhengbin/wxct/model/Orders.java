package org.zhengbin.wxct.model;

import java.util.List;

/**
 * 订单
 * Created by zhengbinMac on 2017/4/19.
 */
public class Orders {
    private Integer id;                 // 订单id
    private String time;                // 下单时间
    private Integer status;             // 订单状态
    private String user_name;           // 顾客姓名
    private String table_name;          // 桌台名
    private String admin_name;          // 管理员名
    private Double price;               // 应收金额
    private Double discount;            // 折扣
    private Double real_price;          // 实收金额
    private Integer table_id;           // 桌台 id
    private Integer user_id;            // 用户 id
    private Integer admin_id;           // 管理员 id
    private String remark;              // 订单备注
    private List<OrderInfo> order_infos; // 订单详情

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getReal_price() {
        return real_price;
    }

    public void setReal_price(Double real_price) {
        this.real_price = real_price;
    }

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderInfo> getOrder_infos() {
        return order_infos;
    }

    public void setOrder_infos(List<OrderInfo> order_infos) {
        this.order_infos = order_infos;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", user_name='" + user_name + '\'' +
                ", table_name='" + table_name + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", real_price=" + real_price +
                ", table_id=" + table_id +
                ", user_id=" + user_id +
                ", admin_id=" + admin_id +
                ", remark='" + remark + '\'' +
                ", order_infos=" + order_infos +
                '}';
    }
}
