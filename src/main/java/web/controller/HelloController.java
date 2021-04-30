package web.controller;

import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import service.UserServiceInterface;
import web.config.*;

@Controller
public class HelloController {

    @GetMapping(value = "/hello")
    public String printWelcome(ModelMap model) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DbConfig.class);

        UserServiceInterface userService = context.getBean(UserServiceInterface.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));

        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add(userService.listUsers().toString());
        model.addAttribute("messages", messages);
        return "index";
    }

}
