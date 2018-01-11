package com.yijiupi.login.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置动态数据源：
 * 配置的是一主，两从。
 * 注意问题：
 *      1.多个用户公用一个数据源，存在线程问题。因此使用并发编程解决，见类（DynamicDataSourceHolder）
 * @author fengqigui
 * @Date 2018/1/11 19:34
 */
public class DynamicDataSource extends AbstractRoutingDataSource{



    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }


}
