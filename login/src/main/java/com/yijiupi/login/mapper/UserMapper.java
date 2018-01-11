package com.yijiupi.login.mapper;

import com.yijiupi.login.pojo.User;
import com.yijiupi.login.pojo.UserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author fengqigui
 *User模型对应DAO层的CURD操作
 */
@Repository
public interface UserMapper {
    /**
     *根据Example条件进行统计
     * @param example 条件
     * @return 统计的数量
     */
    int countByExample(UserExample example);

    /**
     * 根据筛选条件删除User用户
     * @param example
     * @return
     */
    int deleteByExample(UserExample example);

    /**
     *  根据主键删除用户
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入用户，要求每个属性都必须有值
     * @param record 用户实例，要求每个属性非空
     * @return 数据库影响行数
     */
    int insert(User record);

    /**
     * 插入用户信息，使用动态SQL。
     * @param record 用户实例
     * @return 返回数据库影响的行数
     */
    int insertSelective(User record);

    /**
     * 按条件查询用户
     * @param example 筛选的条件
     * @return 用户的List集合
     */
    List<User> listByExample(UserExample example);

    List<User> listUserOfPaging(Map<String, Integer> map);


    /**
     *根据主键进行查询
     * @param userId 主键
     * @return 单个的用户实例
     */
    User getByPrimaryKey(Integer userId);

    /**
     * 按照条件更新用户,动态SQL允许部分属性为空。
     * @param record 待更新的用户实例
     * @param example 被更新用户的条件
     * @return 返回数据库影响的行数
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 按照条件更新用户，必须每个都属性都不为空。
     * @param record 用户实例
     * @param example 条件
     * @return 数据库影响的行数
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     *根据主键更新用户，要求主键必须有值，不为默认值(null),其余的属性根据动态SQL进行判断可以为空。
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *根据主键更新用户，要求所有的属性都不为空
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名和密码查找对应的用户
     * @param map 存放用户名和密码
     * @return 对应的用户
     */
    User getUserByNameAndPassword(Map<String,String> map);


}