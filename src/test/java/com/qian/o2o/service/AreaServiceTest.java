package com.qian.o2o.service;

import com.qian.o2o.BaseTest;
import com.qian.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qiuqian on 9/8/19.
 */
public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        Assert.assertEquals(2, areaList.get(0).getPriority());
    }
}
