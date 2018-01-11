package com.yijiupi.login.util;

import com.yijiupi.login.controller.UserController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Cookie工具类：
 *      将模型中的数据存放在cookie中，产生的cookie的个数由传进来字段的属性个数决定。
 *      saveCookie方法中：cookie中的name就是传进来的map的key，cookie的value就是传进来的map的value。
 *      getCookieToObject方法：将cookie的中的name的值映射到对象对应的属性中。
 * @author fengqigui
 * @Date 2017/11/9 11:31
 */
public class CookieUtil {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserController.class);

    /**
     * 将用户存进cookie，放在客户端
     * @param response  : HttpServletResponse
     * @param mapCookie : 用于存放待存放到Cookie的数据。使用pojo类的属性作为key.例如：User类的属性作为key:userId、userName、userPassword、userSex、userSalary、userFriend、userPhoto
     * @return  是否正确存储
     */
    public static boolean saveCookie(HttpServletResponse response, Map<String, String> mapCookie){

        if (null != mapCookie) {

            Set<String> keySet = mapCookie.keySet();

            Iterator<String> iterator = keySet.iterator();

            Cookie cookie1;

            String tempValue;

            try {
                while (iterator.hasNext()) {

                    String key = iterator.next();

                    tempValue = URLEncoder.encode(mapCookie.get(key), "UTF-8");

                    cookie1 = new Cookie(key, tempValue);

                    cookie1.setMaxAge(7* 24* 60* 60);

                    response.addCookie(cookie1);

                }

            } catch (UnsupportedEncodingException e) {

                LOGGER.info("Cookie的设值异常" + e.getMessage());

                e.printStackTrace();

                return false;
            }

            return true;

        }

        return false;
    }


    /**
     * 将cookie中对应的属性进行映射到对象中
     * @param request   ：HttpServletRequest
     * @param clazz     ：希望进行映射的类属性
     * @return
     */
    public static Object getCookieToObject(HttpServletRequest request,  Class clazz){

        Cookie[] cookies = request.getCookies();

        if (null != cookies) {

            try {
                // 获得实例，类的加载机制。
                Object bean = clazz.newInstance();

                // 获得所有的私有字段
                Field[] fields = clazz.getDeclaredFields();

                if (null != fields) {

                    for (int indexCookie = 0; indexCookie < cookies.length; indexCookie++) {

                        for (int indexField = 0; indexField < fields.length; indexField ++) {

                            Field field = fields[indexField];

                            //  虚拟机的安全机制：免检查。从而设值
                            field.setAccessible(true);

                            // 获得属性名
                            String fieldName = field.getName();

                            //获得类的属性的类型
                            String fieldTypeTemp = field.getType().toString();

                            String fieldType = fieldTypeTemp.substring(fieldTypeTemp.lastIndexOf(".")+1, fieldTypeTemp.length());

                            Cookie cookie = cookies[indexCookie];

                            // 获得cookie中的属性名
                            String cookieName = cookie.getName();

                            if (cookieName.equals(fieldName)) {

                                String cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");

                                Object fieldValue = cookieOfValueToObject(fieldType, cookieValue);

                                field.set(bean, fieldValue);

                            }
                        }
                    }

                    return bean;
                }

            } catch (Exception e) {

                LOGGER.error("反射创建实例异常：" + e.getMessage());

                e.printStackTrace();

            }

        }

        return null;
    }

    /**
     * 将cookie中的String类型数据转化为pojo中的具体属性类型的数据
     * @param cookieValue   :   cookie中value的数据
     * @param fieldType     :   类字段的类型
     * @return
     */
    private static Object cookieOfValueToObject(String fieldType, String cookieValue){

        Object object = null;

        if(null != cookieValue && "" != cookieValue.trim()) {

            if("Byte".equals(fieldType)){

                object=Byte.parseByte(cookieValue);

            }

            if("Short".equals(fieldType)){

                object=Short.parseShort(cookieValue);

            }

            if("Integer".equals(fieldType)){

                object=Integer.parseInt(cookieValue);

            }

            if("Long".equals(fieldType)){

                object=Long.parseLong(cookieValue);

            }


            if("Float".equals(fieldType)){

                object=Float.parseFloat(cookieValue);

            }

            if("Double".equals(fieldType)){

                object=Double.parseDouble(cookieValue);

            }

            if("Boolean".equals(fieldType)){

                object=Boolean.parseBoolean(cookieValue);

            }

            if("String".equals(fieldType)){

                object=String.valueOf(cookieValue);

            }

            if("Character".equals(fieldType)){

                object=cookieValue.toCharArray()[0];

            }
        }

        if("Date".equals(fieldType)){

            String[] dateArr={"yy-MM-dd","yy-MM-dd HH-mm-ss","yy/MM/dd","yy/MM/dd HH/mm/ss", "yy-MM-dd HH:mm:ss"};

            for(int y=0;y<dateArr.length;y++){

                try{

                    SimpleDateFormat format=new SimpleDateFormat(dateArr[y]);

                    Date date = format.parse(cookieValue);

                    object=date;

                } catch (Exception e){

                    LOGGER.error("cookie的value转field的属性失败：" + e.getMessage());

                    e.printStackTrace();

                }

            }
        }

        return object;

    }


}
