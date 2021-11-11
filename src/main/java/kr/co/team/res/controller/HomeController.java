package kr.co.team.res.controller;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController extends Base {

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
    @RequestMapping("/12")
    public String index1(Model model){
        return "index";
    }
}
