package kr.co.team.res.controller.web.admin;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminController extends Base {
    @RequestMapping("/admin/dashboard")
    public String dashboard(){
        log.info("test");
        return "/pages/admin/index";
    }
}
