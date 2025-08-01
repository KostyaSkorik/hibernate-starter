package by.kostya.utils;

import by.kostya.converter.BirthdayConverter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(BirthdayConverter.class,true);
        return configuration.buildSessionFactory();
    }
}
