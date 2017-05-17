package org.zhengbin.wxct.test.cache;

import org.zhengbin.snowflake.framework.util.JsonUtil;
import org.zhengbin.wxct.test.model.FoodArray;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhengbinMac on 2017/5/8.
 */
public class FoodArrayCacheUtil {
    private static Map<String, List<FoodArray>> foodArrayCacheMap;

    static {
        foodArrayCacheMap = new ConcurrentHashMap<String, List<FoodArray>>();
    }

    public static List<FoodArray> getFoodArrayListByKey(String key) {
        return foodArrayCacheMap.get(key);
    }

    public static void putFoodArrayList(String key, String foodArrayListJson, Class type) {
        List<FoodArray> foodArrayList = JsonUtil.fromJson2List(foodArrayListJson, type);
        foodArrayCacheMap.put(key, foodArrayList);
    }

    public static void putFoodArrayList(String key, List<FoodArray> foodArrayList) {
        foodArrayCacheMap.put(key, foodArrayList);
    }
}
