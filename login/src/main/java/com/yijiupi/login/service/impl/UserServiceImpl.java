package com.yijiupi.login.service.impl;

import com.yijiupi.login.common.DataSourceType;
import com.yijiupi.login.common.anno.DataSource;
import com.yijiupi.login.mapper.UserMapper;
import com.yijiupi.login.pojo.User;
import com.yijiupi.login.pojo.UserExample;
import com.yijiupi.login.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现UserService接口
 * @author fengqigui
 * @Date 2017/11/6 12:52
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    public int insertUser(User user) {

        int influenceRow = userMapper.insertSelective(user);

        return influenceRow;
    }

    public int updateUser(User user) {

        return 0;
    }

    public int removeUser(int userId) {

        int affectRow = userMapper.deleteByPrimaryKey(userId);

        return affectRow;
    }

    @DataSource(DataSourceType.SLAVE)// 注解：解决切换数据源
    public User getUserById(int id) {
        return null;
    }

    public User getUserByNameAndPassword(String userName, String userPassword) {

        Map<String, String> map = new HashMap<String, String>();

        map.put("userName", userName);

        map.put("userPassword", userPassword);

        User user = userMapper.getUserByNameAndPassword(map);

        return user;
    }

    @Override
    public List<User> listUser(int pageSize, int pageCurrent) {

        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();

        int countUsers = userMapper.countByExample(userExample);

        double page = (1.0 * countUsers) / pageSize;

        int basePage = (int) Math.ceil(page);

        // 如果当前页超出数据库分页的上限，直接赋值为1。
        if  (basePage < pageCurrent)  {

            pageCurrent = 1;

        }

        // 如果当前页小于1，就将其赋值数据库分页的上限
        if (pageCurrent < 1) {

            pageCurrent = basePage;

        }

        // 起始行
        int pageRowStart = (pageCurrent-1) * pageSize;

        // 终止行
        int pageRowEnd = pageSize * pageCurrent;

        // sql语句的参数
        Map<String, Integer> map = new HashMap<>();

        map.put("pageRowStart", pageRowStart);

        map.put("pageRowEnd", pageRowEnd);

        List<User> userList = userMapper.listUserOfPaging(map);

        return userList;

    }

    public List<User> listUserByExample(User user) {
        return null;
    }


    public int countUserName(String userName) {

        // 添加条件
        UserExample example = new UserExample();

        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUserNameEqualTo(userName);

        //查询数据库的相同名字的行数
        int count = userMapper.countByExample(example);

        return count;
    }
}
