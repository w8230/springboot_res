package kr.co.team.res.controller.web.admin;

import kr.co.team.res.common.Base;
import kr.co.team.res.controller.web.BaseCont;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController extends BaseCont {
    @RequestMapping("/admin/dashboard")
    public String Dashboard(){
        return "/pages/admin/index";
    }
}
