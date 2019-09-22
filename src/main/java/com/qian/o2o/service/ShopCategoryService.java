package com.qian.o2o.service;

import com.qian.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Created by qiuqian on 9/22/19.
 */
public interface ShopCategoryService {
    public static final String SCLISTKEY = "shopcategorylist";
    /**
     * Get a list of ShopCategory
     *
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
