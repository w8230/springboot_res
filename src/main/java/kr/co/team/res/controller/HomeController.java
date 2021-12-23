package kr.co.team.res.controller;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController extends Base {

    @RequestMapping("/")
    public String index(Model model ,
                        SearchVO searchVO){
        model.addAttribute("form" , searchVO);
        return "index";
    }
}
