package com.yijiupi.login.common.aop;

import com.yijiupi.login.common.DynamicDataSourceHolder;
import com.yijiupi.login.common.anno.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用切面编程，解决在每一个业务代码前切换数据源
 * @author fengqigui
 * @Date 2018/1/11 20:20
 */
@Aspect
@Component
public class DataSourceAspect {



    public void before(JoinPoint point) throws NoSuchMethodException {
        // 获得方法
        Object target = point.getTarget();
        // 获得方法的签名      获得方法名
        String method = point.getSignature().getName();
        Class clazz = target.getClass();
        // 获得方法里面的参数
        Class<?>[] parameterType = ((MethodSignature)point.getSignature()).getParameterTypes();

        Method method1 = clazz.getMethod(method, parameterType);
        // 判断方法上面是否存在DataSource注解
        if(method1 != null && method1.isAnnotationPresent(DataSource.class)) {
            // 或的注解
            DataSource dataSource = method1.getAnnotation(DataSource.class);
            // 切换数据源
            DynamicDataSourceHolder.setDataSource(dataSource.value());

        }


    }











}
