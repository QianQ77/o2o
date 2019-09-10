package com.qian.o2o.service.impl;

import com.qian.o2o.dao.AreaDao;
import com.qian.o2o.entity.Area;
import com.qian.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiuqian on 9/8/19.
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
