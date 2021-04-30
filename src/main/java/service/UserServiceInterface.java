package service;

import model.User;

import java.util.List;

public interface UserServiceInterface {
    void add(User user);
    List<User> listUsers();
}
