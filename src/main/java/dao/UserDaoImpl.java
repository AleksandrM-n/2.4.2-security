package dao;

import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;


    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("FROM " + User.class.getSimpleName());
        return query.getResultList();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        Query query = entityManager.createQuery("FROM " + User.class.getSimpleName()
                + " WHERE id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        User user = getUserById(updatedUser.getId());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
