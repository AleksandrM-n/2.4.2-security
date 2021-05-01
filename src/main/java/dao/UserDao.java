package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    void updateUser(User updatedUser);
    void deleteUser(long id);
}
