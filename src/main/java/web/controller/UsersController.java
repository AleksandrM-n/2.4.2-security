package web.controller;

import model.Role;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import service.UserServiceInterface;
import web.config.*;

@Controller
public class UsersController {
    static UserServiceInterface getUserService() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DbConfig.class);
        return context.getBean(UserServiceInterface.class);
    }

    @GetMapping("/admin")
    public String asAdminStart() {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String printUsers(ModelMap model) {

        List<User> users = new ArrayList<>(getUserService().listUsers());

        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String printUserById(@PathVariable ("id") long id, ModelMap model) {
        User user = getUserService().getUserById(id);
        model.addAttribute("user", user.toString());
        return "user";
    }

    @GetMapping("/admin/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleSet", getUserService().getRoles());
        return "newForm";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute ("user") User user) {
        getUserService().add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/update/{id}")
    public String updateUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", getUserService().getUserById(id));
        model.addAttribute("roleSet", getUserService().getRoles());
        return "update";
    }

    @PostMapping("admin/update/{id}")
    public String update(@ModelAttribute("user") User user) {
        getUserService().updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        getUserService().deleteUser(id);
        return "redirect:/admin/users";
    }
}
