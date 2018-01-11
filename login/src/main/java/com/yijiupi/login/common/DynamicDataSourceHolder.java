package com.yijiupi.login.common;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决动态数据源的线程问题，
 *  DynamicDataSourceHolder主要是提供数据源。
 *  ThreadLocal：解决数据源切换造成的线程问题;
 *  什么时候切换数据源，怎么切换？
 *  怎么切换：根据lookupkey，的值来切换的。
 *  什么时候切换？在业务层使用自定义注解注解@DataSource(DataSourceType.MASTER)，或者在业务代码中DynamicDataSourceHolder.set();
 * @author fengqigui
 * @Date 2018/1/11 19:46
 */
public class DynamicDataSourceHolder {

    /**
     * 为每个线程独立的创键一个绑定holder，相当于备份。每个请求都会绑定一个threadlocal。
     */
    public static final ThreadLocal<String> holder = new ThreadLocal<>();

    // 累加器，用于轮询，使用AtomicInteger解决并发编程问题
    private static AtomicInteger counter = new AtomicInteger(-1);

    /*对应自己spring xml中配置的三个map的key*/
    public static final String MASTER = "master";
    public static final String SLAVE_1 = "slave_1";
    public static final String SLAVE_2 = "slave_2";

    public static void setDataSource(DataSourceType dataSourceType){

        if(DataSourceType.MASTER == dataSourceType){

            holder.set(MASTER);
        }else if (dataSourceType == DataSourceType.SLAVE){
            // 这里要考虑轮询算法，否则两个从只有一个有用，另外一个相当于备份
            holder.set(roundRobinSlaveKey());
        }
    }

    /**
     * 获取数据源的lookupkey,也就改变了数据源
     * @return
     */
    public static String getDataSource(){
        return holder.get();
    }






    /**
     * 轮询的算法
     * @return
     */
    private static String roundRobinSlaveKey() {
        Integer index = counter.incrementAndGet() % 2;
        if (counter.get()>99999) {
            counter.set(-1);
        }
        if( index == 0 ){
            return SLAVE_1;
        }else {
            return SLAVE_2;
        }
    }


}
