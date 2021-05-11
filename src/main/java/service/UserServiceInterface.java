package service;

import model.Role;
import model.User;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    void add(User user);
    List<User> listUsers();
    User getUserById(long id);
    void updateUser(User updatedUser);
    void deleteUser(long id);
    Set<Role> getRoles();
}
