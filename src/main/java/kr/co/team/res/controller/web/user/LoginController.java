package kr.co.team.res.controller.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(Model model) {
        String redirect = "/";
        return "redirect:" + redirect;
    }
}
