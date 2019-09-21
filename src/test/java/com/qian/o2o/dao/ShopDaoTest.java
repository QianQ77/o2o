package com.qian.o2o.dao;

import com.qian.o2o.BaseTest;
import com.qian.o2o.entity.Area;
import com.qian.o2o.entity.PersonInfo;
import com.qian.o2o.entity.Shop;
import com.qian.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by qiuqian on 9/13/19.
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1l);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1l);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("test shop");
        shop.setShopDesc("test desc");
        shop.setShopAddr("addr");
        shop.setPhone("test");
        shop.setShopImg("img");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setPriority(1);
        shop.setAdvice("test advice");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("update shop");
        shop.setShopAddr("update addr");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
}
