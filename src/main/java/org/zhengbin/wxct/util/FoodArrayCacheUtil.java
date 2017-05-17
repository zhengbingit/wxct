package org.zhengbin.wxct.util;

import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.model.FoodArray;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengbinMac on 2017/5/8.
 */
public class FoodArrayCacheUtil {
    private static Map<Integer, List<FoodArray>> foodArrayCacheMap;

    static {
        foodArrayCacheMap = new ConcurrentHashMap<Integer, List<FoodArray>>();
    }

    public static List<FoodArray> getFoodArrayListByKey(int tableId) {
        return foodArrayCacheMap.get(tableId);
    }

    public static void putFoodArrayList(int tableId, String foodArrayListJson, Class type) {
        List<FoodArray> foodArrayList = JsonUtil.fromJson2List(foodArrayListJson, type);
        foodArrayCacheMap.put(tableId, foodArrayList);
    }

    public static void putFoodArrayList(int tableId, List<FoodArray> foodArrayList) {
        foodArrayCacheMap.put(tableId, foodArrayList);
    }

    public static boolean deleteFoodArrayListByKey(int tableId) {
        return foodArrayCacheMap.remove(tableId) != null;
    }
}
