package by.kostya;

import by.kostya.dao.CompanyRepository;
import by.kostya.dao.UserRepository;
import by.kostya.dto.UserCreateDto;
import by.kostya.dto.UserUpdateDto;
import by.kostya.entity.*;
import by.kostya.mapper.CompanyReadMapper;
import by.kostya.mapper.UserCreateMapper;
import by.kostya.mapper.UserReadMapper;
import by.kostya.mapper.UserUpdateMapper;
import by.kostya.services.UserService;
import by.kostya.utils.HibernateUtil;

import javax.persistence.Id;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Hello world!
 */


@Slf4j
public class HibernateRunner {
    public static void main(String[] args) throws IllegalAccessException {

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(),args1)));
            session.beginTransaction();

            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            CompanyRepository companyRepository = new CompanyRepository(session);
            UserUpdateMapper userUpdateMapper = new UserUpdateMapper(companyRepository);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);
            UserRepository userRepository = new UserRepository(session);
            UserService userService = new UserService(userRepository,userReadMapper,userCreateMapper,userUpdateMapper);

            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonInfo.builder()
                            .firstname("Anna")
                            .lastname("Skorik")
//                            .birthDate(new Birthday(LocalDate.of(2005,3,2)))
                            .build(),"annaSkor@mail.ru",Role.ADMIN,2
            );
            System.out.println(userService.create(userCreateDto));
            session.getTransaction().commit();

        }

    }
}
