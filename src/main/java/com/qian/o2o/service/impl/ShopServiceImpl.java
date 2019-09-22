package com.qian.o2o.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qian.o2o.dao.ShopDao;
import com.qian.o2o.dto.ImageHolder;
import com.qian.o2o.dto.ShopExecution;
import com.qian.o2o.entity.Shop;
import com.qian.o2o.enums.ShopStateEnum;
import com.qian.o2o.exceptions.ShopOperationException;
import com.qian.o2o.service.ShopService;
import com.qian.o2o.utils.ImageUtil;
import com.qian.o2o.utils.PageCalculator;
import com.qian.o2o.utils.PathUtil;

/**
 * Created by qiuqian on 9/21/19.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        return new ShopExecution(ShopStateEnum.NULL_SHOP);
    }

    @Override
    @Transactional
    public Shop getByShopId(long shopId) {
        return new Shop();
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {

            return new ShopExecution(ShopStateEnum.NULL_SHOP);
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("Fail to insert shop");
            } else {
                if (thumbnail.getImage() != null) {

                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("Fail to update image");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }
}

