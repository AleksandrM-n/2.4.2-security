package dao;

import model.*;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    void updateUser(User updatedUser);
    void deleteUser(long id);

    // пробный вариант
    User getUserByName(String name);
}
