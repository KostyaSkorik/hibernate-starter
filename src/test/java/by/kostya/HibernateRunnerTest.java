package by.kostya;

import by.kostya.entity.*;
import by.kostya.utils.HibernateUtil;


import com.querydsl.jpa.impl.JPAQuery;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.kostya.entity.QUser.*;

/**
 * Unit test for simple App.
 */
public class HibernateRunnerTest {

    @Test
    public List<User> findAllWithQueryDSL(){
        return new JPAQuery<User>().select(user).from(user).fetch();
    }


    @Test
    public void checkTestDB() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("SkyEng")
                .build();

        session.persist(company);
        session.getTransaction().commit();
    }

    @Test
    public void checkHQL(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        /*
        String name = "Kostya";
        var users = session.createNamedQuery("findU serByName")
                .setParameter("firstname",name)
                .list();
                System.out.println(users);
        */
        session.getTransaction().commit();
    }

//    @Test
//    public void checkInheritance() {
//        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        var company = Company.builder()
//                .name("Yandex")
//                .build();
//        session.persist(company);
//
//        Programmer programmer = Programmer.builder()
//                .username("kostya@mail.ru")
//                .language(Language.JAVA)
//                .company(company)
//                .build();
//        session.persist(programmer);
//
//        Manager manager = Manager.builder()
//                .username("anna@mail.ru")
//                .project("Java Enterprise")
//                .company(company)
//                .build();
//        session.persist(manager);
//
//        session.flush();
//        session.clear();
//
//        var programmer1 = session.get(Programmer.class, 1L);
//        var manager1 = session.get(User.class, 2L);
//        session.getTransaction().commit();
//    }


    //Тесты до h2
    /*
    При optional = false вызывается inner join
     */
    /*@Test
    public void optionalTest() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        sessionFactory.inTransaction(session -> session.createSelectionQuery("from User", User.class)
                .getResultList().forEach(System.out::println));
    }*/

    @Test
    public void checkOneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        var company = session.get(Company.class, 2);
        System.out.println(company.getUsers());
        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

//        Company company = Company.builder()
//                .name("Yandex")
//                .build();
//        User user = User.builder()
//                .username("kostya@mail.ru")
//                .build();
//        company.addUser(user);

//        session.persist(company);
        session.getTransaction().commit();
    }

    @Test
    public void checkOrphanRemoval() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 11);

        company.getUsers().removeIf(user -> user.getId().equals(9));
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .username("kostya@mail.ru")
                .build();
        Profile profile = Profile.builder()
                .language("RU")
                .street("Pobedy 1")
                .build();
        session.persist(user);
        profile.setUser(user);
        session.persist(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
//        Chat chat = session.get(Chat.class, 1L);
//        User user = session.get(User.class, 7L);

//        UserChat userChat = UserChat.builder()
//                .createdAt(Instant.now())
//                .createdBy("Kostya")
//                .build();

//        UserChat userChat = new UserChat();
//        userChat.setCreatedAt(Instant.now());
//        userChat.setCreatedBy("Kostya");

//        userChat.setChat(chat);
//        userChat.setUser(user);
//        session.persist(userChat);
        session.getTransaction().commit();
    }

    @Test
    public void addToCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = Course.builder()
                .name("Java Enterprise")
                .build();

        Student student1 = Student.builder()
                .firstName("Kostya")
                .lastName("Skorik")
                .build();
        Student student2 = Student.builder()
                .firstName("Anna")
                .lastName("Gombalevskaya")
                .build();

        course.setStudent(student1);
        course.setStudent(student2);

        session.persist(course);
        session.persist(student1);
        session.persist(student2);
        session.getTransaction().commit();
    }

    @Test
    public void writeStudentByCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 2L);
        System.out.println(course.getStudents());
        session.getTransaction().commit();
    }

    @Test
    public void delStudent() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 5L);

        course.getStudents().removeIf(student -> student.getStudentProfile().getAverageScore() < 6);
        session.getTransaction().commit();
    }

    @Test
    public void addStudentToEnterprise() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 2L);

        Student student = Student.builder()
                .firstName("Kostya")
                .lastName("Skorik")
                .build();
        course.setStudent(student);
        session.persist(student);
        session.getTransaction().commit();
    }

    @Test
    public void delCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 5L);
        session.remove(course);
        session.getTransaction().commit();
    }

    @Test
    public void addStudentProfile() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = session.get(Student.class, 6L);
        StudentProfile studentProfile = StudentProfile.builder()
                .averageScore(5.22F)
                .build();
        studentProfile.setStudent(student);
        session.persist(studentProfile);
        session.getTransaction().commit();
    }

    @Test
    public void addTrainerCourse() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 4L);
        Trainer trainer = Trainer.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();
        session.persist(trainer);
        TrainerCourse trainerCourse = TrainerCourse.builder().build();
        trainerCourse.setTrainer(trainer);
        trainerCourse.setCourse(course);
        session.persist(trainerCourse);
        session.getTransaction().commit();
    }

/*
    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        var user = User.builder()
                .username("ivan1@mail.ru")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(LocalDate.of(2000,1,1))
                .age(25)
                .build();
        var sql = """
            insert into
            %s
            (%s)
            values
            (%s)
            """;
        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());
        Field[] fields = user.getClass().getDeclaredFields();
        var columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())).collect(Collectors.joining(", "));
        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","root");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName,columnNames,columnValues));

        for (int i = 0; i < fields.length; i++) {
            //доступ к private полям
            fields[i].setAccessible(true);
            preparedStatement.setObject(i+1,fields[i].get(user));
        }
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();



    }
    */
}
