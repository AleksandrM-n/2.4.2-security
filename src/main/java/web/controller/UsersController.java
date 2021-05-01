package web.controller;

import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import service.UserServiceInterface;
import web.config.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    static UserServiceInterface getUserService() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DbConfig.class);
        return context.getBean(UserServiceInterface.class);
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        List<String> users = new ArrayList<>();

        for (User user : getUserService().listUsers()) {
            users.add(user.toString());
        }

        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String printUserById(@PathVariable ("id") long id, ModelMap model) {
        User user = getUserService().getUserById(id);
        model.addAttribute("user", user.toString());
        return "user";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "newForm";
    }

    @PostMapping
    public String addUser(@ModelAttribute ("user") User user) {
        getUserService().add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String updateUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", getUserService().getUserById(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        getUserService().updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        getUserService().deleteUser(id);
        return "redirect:/users";
    }
}
