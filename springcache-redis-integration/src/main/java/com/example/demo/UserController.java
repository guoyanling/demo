package com.example.demo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserDao userDao;

    /**
     * 查询出一条数据并且添加到缓存
     * 以userCache{id}为key ,返回对象为value
     */
    @GetMapping("/getUser/{id}")
    @Cacheable("userCache")
    public User getUser(@PathVariable Integer id) {
        System.out.println("如果没有缓存，就会调用下面方法，如果有缓存，则直接输出，不会输出此段话");
        return userDao.getUser(id);
    }

    /**
     * 删除缓存
     */
    @CacheEvict("userCache")
    @GetMapping(value = "/delUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return "删除成功";
    }

    /**
     * 添加一条保存的数据到缓存，缓存的key是当前user的id
     */
    @GetMapping("/saveUser")
    @CachePut(value = "userCache", key = "#result.id +''")
    public User saveUser(User user) {
        return user;
    }


    /**
     * 返回结果name中含有nocache字符串就不缓存
     */
    @GetMapping("/getUser2/{id}")
    @CachePut(value = "userCache", unless = "#result.name.contains('nocache')")
    public User getUser2(@PathVariable Integer id) {
        System.out.println("这里输出了则说明没有缓存到");
        return new User(id, "name_nocache" + id);
    }


    @GetMapping("/getUser3/{id}")
    @Cacheable(value = "userCache", key = "#root.targetClass.getName() + #root.methodName + #id")
    public User getUser3(@PathVariable int id) {
        System.out.println("如果第二次没有走到这里说明缓存被添加了");
        return userDao.getUser(id);
    }

}
