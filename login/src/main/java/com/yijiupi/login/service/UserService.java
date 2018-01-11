package com.yijiupi.login.service;

import com.yijiupi.login.pojo.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author fengqigui
 */
public interface UserService {

    /**
     * 向数据库中插入增加单个用户
     * @param user 待增加用户实例
     * @return 数据库影响的行数
     */
    int insertUser(User user);

    /**
     * 更新用户数据
     * @param user
     * @return 返回数据库影响的行数
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param userId
     * @return 返回数据库影响的行数
     */
    int removeUser(int userId);

    /**
     * 更根据用户的ID获得用户
     * @param id 用户的ID
     * @return 返回单个的用户
     */
    User getUserById(int id);

    /**
     * 根据用户名和密码获得单个用户
     * @param userName 用户名
     * @param userPassword 用户密码
     * @return 返回单个的用户
     */
    User getUserByNameAndPassword(String userName, String userPassword);

    /**
     *
     * @param pageSize  : 页面展示条数
     * @param pageCurrent  : 当前的页数
     * @return ：用户的list集合
     */
    List<User> listUser(int pageSize, int pageCurrent);

    /**
     *根据传入的用户条件进行用户查询
     * @param user 用户条件
     * @return 返回用户列表
     */
    List<User> listUserByExample(User user);

    /**
     * 统计目前的用户名的数量
     * @param UserName ：带统计的用户名
     * @return ： 统计的数量
     */
    int countUserName(String UserName);

}
