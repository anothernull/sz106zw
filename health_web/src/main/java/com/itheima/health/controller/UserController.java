package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 添加会员
     * @param user
     * @param roleId
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user, Integer roleId){
        Set<Role> userRoles = user.getRoles();
        user.setIsShow(1);
        //设置电话号码后6为为密码
        String telephone = user.getTelephone();
        String password = telephone.substring(5);
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        userService.addUser(user, roleId);
        return new Result(true, "新增会员成功");
    }

    /**
     * 查询所有会员
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        List<User> list = userService.findAll();
        return new Result(true, "查询会员成功", list);
    }

    /**
     * 根据会员id查询会员信息和角色
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(Integer id){
        User user = userService.findById(id);
        return new Result(true, "查询会员成功", user);
    }

    /**
     * 会员的分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用服务 分页查询
        PageResult<CheckItem> pageResult = userService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 修改会员
     * @param user
     * @param roleId
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user, Integer roleId){
        userService.update(user, roleId);
        return new Result(true, "修改会员成功");
    }

    /**
     * 会员删除(逻辑删除)
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result delete(Integer id){
        userService.delete(id);
        return new Result(true, "删除会员成功");
    }
}
