package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
     * 通过用户名查询用户角色权限
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user, Integer roleId);


    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 检查项的分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 根据会员id查询会员信息和角色
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 修改会员
     * @param user
     * @param roleId
     * @return
     */
    void update(User user, Integer roleId);

    /**
     * 会员删除(逻辑删除)
     * @param id
     * @return
     */
    void delete(Integer id);
}
