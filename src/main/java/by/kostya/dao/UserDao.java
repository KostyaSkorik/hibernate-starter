package by.kostya.dao;


import by.kostya.entity.QCompany;
import by.kostya.entity.User;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static by.kostya.entity.QCompany.company;
import static by.kostya.entity.QUser.user;

public class UserDao {

    public List<User> findAll(Session session){
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findAllByFirstName(Session session,String firstName){
        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personInfo().firstname.eq(firstName)).fetch();
    }
    public void addUser(Session session, User user){
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }
    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit){
        return new JPAQuery<User>(session).select(user)
                .from(user)
                .orderBy(new OrderSpecifier(Order.ASC,user.personInfo().birthDate))
                .limit(limit)
                .fetch();

    }
    public List<User> findAllByCompanyName(Session session, String companyName){
        int[] arr = new int[3];
        System.out.println(arr.length);
        return new JPAQuery<User>(session).select(user).from(company).join(company.users,user)
                .where(company.name.eq(companyName)).fetch();
    }
}
