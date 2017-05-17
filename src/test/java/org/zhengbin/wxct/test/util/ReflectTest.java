package org.zhengbin.wxct.test.util;

import org.zhengbin.snowflake.framework.util.CollectionUtil;
import org.zhengbin.wxct.model.OrderInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/14.
 */
public class ReflectTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
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

        orderInfos.add(orderInfo1);
        orderInfos.add(orderInfo2);

        insertObject(orderInfos);
    }

    private static void insertObject(List<?> objectList) throws IllegalAccessException {
        /**
         * 比如：
         * SQL 语句：insert into person(name, age, info) values ('...', '...', '...');
         * 其中 values 改为占位符：
         * SQL 语句：insert into person(name, age, info) values (?, ?, ?);
         * 再获取所有的数据值 Object 数组
         * [zhengbin, 21, 要毕业了]
         */
        // 确定占位符的个数（即对象中不为 null 的字段个数）
        int columnNum = 0;
        // 插入数据的列名
        StringBuilder columns = new StringBuilder("(");
        // 填充占位符的值（即对象中不为null的字段的值）
        List<Object> valuesList = new ArrayList<Object>();
        // 如果为空则不执行
        if (CollectionUtil.isEmpty(objectList)) {
            return;
        }
        // 通过 List 中的第一个 Object，确定插入对象的字段
        Object object = objectList.get(0);
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(object) != null) {
                columnNum++;
                columns.append(field.getName()).append(", ");
                valuesList.add(field.get(object));
            }
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");

        // 获取所有的值
        for (int i = 1; i < objectList.size(); i++) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(objectList.get(i)) != null) {
                    valuesList.add(field.get(objectList.get(i)));
                }
            }
        }
        // 确定一个 Object 的占位符 '?'
        StringBuilder zhanweifuColumn = new StringBuilder("(");
        for (int i = 0; i < columnNum; i++) {
            zhanweifuColumn.append("?, ");
        }
        zhanweifuColumn.replace(zhanweifuColumn.lastIndexOf(", "), zhanweifuColumn.length(), ")");

        // 确定所有的占位符
        int objectNum = objectList.size();
        StringBuilder zhanweifu = new StringBuilder();
        for (int j = 0; j < objectNum; j++) {
            zhanweifu.append(zhanweifuColumn.toString()).append(", ");
        }
        zhanweifu.replace(zhanweifu.lastIndexOf(", "), zhanweifu.length(), "");

        // 生成最终 SQL
        String sql = "INSERT INTO " + object.getClass().getSimpleName().toLowerCase() + " " + columns + " VALUES " + zhanweifu.toString();
        System.out.println(sql);
        System.out.println(valuesList);
    }
}
