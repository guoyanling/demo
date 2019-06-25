package com.example.demo.dao;


import com.example.demo.pojo.FactoryUser;
import com.example.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void find() {
        List<User> a = userDao.findUsersByRole(1L);
        System.out.println(a);
    }

    @Test
    public void findByAccount() {
        User user = userDao.findTopByUsername("123");
        System.out.println(user);
    }

    @Test
    public void findAll () {
        Specification<User> spec = new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("username").as(String.class), "123");
//                 设置sql链接
                Join<User, FactoryUser> fuJoin = root.join(root.getModel().getSingularAttribute("fu", FactoryUser.class), JoinType.INNER);
                Predicate p2 = cb.equal(fuJoin.get("factoryId").as(Long.class), 1);
                query.where(cb.and(p1, p2));
//                 添加排序的功能
                query.orderBy(cb.desc(root.get("id").as(Long.class)));
                return query.getRestriction();
            }
        };
        System.out.println(userDao.findAll(spec));
    }

}
