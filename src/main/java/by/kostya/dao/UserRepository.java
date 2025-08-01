package by.kostya.dao;

import by.kostya.entity.User;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User>{
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
