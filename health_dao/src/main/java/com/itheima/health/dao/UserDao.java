package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserDao {
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
    void addUser(User user);

    /**
     * 建立用户和角色间的联系
     * @param userId
     * @param roleId
     */
    void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();


    /**
     * 用户的分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 根据会员id查询会员信息
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 修改会员
     * @param user
     * @return
     */
    void update(User user);

    /**
     * 修改会员与角色的关系
     * @param roleId
     */
    void updateUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 会员删除(逻辑删除)
     * @param id
     * @return
     */
    void delete(Integer id);
}
