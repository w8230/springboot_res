package kr.co.team.res.controller.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @RequestMapping("/login")
    public String login(Model model) {
        String redirect = "/";
        return "redirect:" + redirect;
    }
    @RequestMapping("/pages/login")
    public String loginPage(Model model){
        return "pages/login";
    }
}
