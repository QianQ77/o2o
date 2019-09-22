package com.qian.o2o.service;

/**
 * Created by qiuqian on 9/21/19.
 */
import com.qian.o2o.dto.ImageHolder;
import com.qian.o2o.dto.ShopExecution;
import com.qian.o2o.entity.Shop;
import com.qian.o2o.exceptions.ShopOperationException;

public interface ShopService {
    /**
     * get a list of shop according to shopCondition and page setting
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * get corresponding Shop
     *
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * update shop and its image
     *
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * add shop and its image
     *
     * @param shop
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}