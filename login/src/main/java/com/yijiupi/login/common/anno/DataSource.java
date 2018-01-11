package com.yijiupi.login.common.anno;

import com.yijiupi.login.common.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义DataSource注解，解决，在业务代码中显示的写DynamicDataSourceHolder.set()方法切换数据库
 * @author fengqigui
 * @Date 2018/1/11 20:14
 */
// 运行策略：运行时运行
@Retention(RetentionPolicy.RUNTIME)
// 方法级别,其作用
@Target({ElementType.METHOD})
public @interface DataSource {

    /**
     * 默认给主库的数据源
     * @return
     */
    DataSourceType value() default  DataSourceType.MASTER;




}
