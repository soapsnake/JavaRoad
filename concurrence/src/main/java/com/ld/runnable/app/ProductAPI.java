package com.ld.runnable.app;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 同时有N个商品（用long类型的商品id表示），每个商品都可以被任何一个用户（long类型的用户id）订阅，
 * 每被订阅一次，该商品的订阅数加1，同一个用户对同一个商品只能订阅一次
 */
public class ProductAPI {
    //商品订阅者
    private static final Map<Long, List<Long>> PRODUCTSUBS = new ConcurrentHashMap<>(16);

    /**
     * 为指定的用户id订阅指定的商品
     *
     * @param productId
     * @param userId
     */
    public synchronized boolean subscripProducts(Long productId, Long userId) throws Exception {
        if (productId == null || userId == null) {
            throw new Exception("参数错误");
        }
        List<Long> subscripers = PRODUCTSUBS.get(productId);
        if (CollectionUtils.isEmpty(subscripers)) {
            subscripers = new ArrayList<>(16);
            subscripers.add(userId);
            PRODUCTSUBS.put(productId, subscripers);
        }
        if (subscripers.contains(userId)) {
            throw new Exception("顾客已经订阅该商品");
        }
        return subscripers.add(userId);
    }

    /**
     * 返回所有商品的订阅总数
     *
     * @return
     */
    public synchronized long getAllSubscrips() {
        long count = 0L;
        long totalCount = 0L;
        for (Long productId : PRODUCTSUBS.keySet()) {
            count = this.getSubscripsById(productId);
            totalCount += count;
        }
        return totalCount;
    }

    /**
     * 根据商品ID返回这个商品的订阅数
     *
     * @param productId
     * @return
     */
    public synchronized long getSubscripsById(Long productId) {
        List<Long> subscripers = PRODUCTSUBS.get(productId);
        if (CollectionUtils.isEmpty(subscripers)) {
            return 0L;
        }
        return subscripers.size();
    }


}
