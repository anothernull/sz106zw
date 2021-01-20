package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.UserDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过用户名查询用户角色权限
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user, Integer roleId) {
        //添加用户
        userDao.addUser(user);

         int userId = user.getId();
        //建立用户和角色间的联系
        userDao.addUserRole(userId, roleId);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 用户的分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // pageSize能无限大吗？参数是从前端来的，100W，后台限制大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 条件查询
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString())){
            // 有查询条件， 模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // page extends arrayList
        Page<CheckItem> page = userDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckItem> pageResult = new PageResult<CheckItem>(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 根据会员id查询会员信息和角色
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        //通过id查询会员信息
        User user = userDao.findById(id);

        return user;
    }

    /**
     * 修改会员
     * @param user
     * @param roleId
     * @return
     */
    @Override
    public void update(User user, Integer roleId) {
        //修改会员的信息
        userDao.update(user);
        //修改会员与角色的关系
        userDao.updateUserRole(user.getId(), roleId);
    }

    /**
     * 会员删除(逻辑删除)
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
       userDao.delete(id);
    }
}
