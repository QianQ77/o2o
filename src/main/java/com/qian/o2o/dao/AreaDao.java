package com.qian.o2o.dao;

import com.qian.o2o.entity.Area;

import java.util.List;

/**
 * Created by qiuqian on 9/8/19.
 */
public interface AreaDao {
    /**
     * list area lists
     * @return areaList
     */
    List<Area> queryArea();
}
