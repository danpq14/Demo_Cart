package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.UserService;

import java.awt.*;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public User setUpUser() {
        return new User();
    }

    @RequestMapping("/login")
    public String getLoginForm(){
        return "login";
    }

    @PostMapping("/doLogin")
    public String checkLogin(@ModelAttribute("user") User user, Model model) {
        Iterable<User> users = userService.findAll();
        for (User item : users) {
            if (user.getUserName().equalsIgnoreCase(item.getUserName())
                    && user.getPassword().equalsIgnoreCase(item.getPassword())) {
                user.setName(item.getName());
                return "redirect:/showListProduct";
            }
        }
        model.addAttribute("message", "Login fail!!!");
        return "login";
    }

}
