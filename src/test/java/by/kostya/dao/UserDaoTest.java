package by.kostya.dao;

import by.kostya.entity.Birthday;
import by.kostya.entity.PersonInfo;
import by.kostya.entity.User;
import by.kostya.utils.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;



class UserDaoTest {
    private static final UserDao userDao = new UserDao();

    @Test
    void findAll() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        System.out.println(userDao.findAll(session));
    }

    @Test
    void findAllByFirstName() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        System.out.println(userDao.findAllByFirstName(session, "Kostya"));
    }

    @Test
    void addUser() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        User user = User.builder()
                .username("ivan3@mail.ru")
                .personInfo(PersonInfo.builder()
                        .birthDate(new Birthday(LocalDate.of(2017,7,17)))
                        .build())
                .build();
        userDao.addUser(session,user);
    }
    @Test
    void findLimitedUsersOrderedByBirthday(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        System.out.println(userDao.findLimitedUsersOrderedByBirthday(session,5));
    }
    @Test
    void findAllByCompanyName(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        System.out.println(userDao.findAllByCompanyName(session,"Yandex"));
    }
}