package org.zhengbin.wxct.test.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.wxct.test.cache.FoodArrayCacheUtil;
import org.zhengbin.wxct.test.model.FoodArray;

import java.util.List;

/**
 * Created by zhengbinMac on 2017/5/8.
 */
public class CacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTest.class);

    @Test
    public void testCache() {
        String foodArrayJson = "[{\"id\":1,\"dishesName\":\"宫保鸡丁\",\"dishesPrice\":18,\"realPrice\":18,\"dishesNum\":2},{\"id\":2,\"dishesName\":\"鱼香茄子\",\"dishesPrice\":16,\"realPrice\":16,\"dishesNum\":2},{\"id\":3,\"dishesName\":\"油饼\",\"dishesPrice\":2,\"realPrice\":2,\"dishesNum\":1},{\"id\":4,\"dishesName\":\"骨汤鲜肉馄饨\",\"dishesPrice\":6,\"realPrice\":6,\"dishesNum\":1}]";
        FoodArrayCacheUtil.putFoodArrayList("A02", foodArrayJson, FoodArray.class);

        List<FoodArray> foodArrayList = FoodArrayCacheUtil.getFoodArrayListByKey("A02");
        LOGGER.debug("foodArrayList = {}", foodArrayList);
    }
}
