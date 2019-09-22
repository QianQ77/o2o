package com.qian.o2o.utils;

/**
 * Created by qiuqian on 9/21/19.
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
