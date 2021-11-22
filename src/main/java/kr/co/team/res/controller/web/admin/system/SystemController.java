package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class SystemController extends Base {
    @RequestMapping("/admin/system/menu/list")
    public String MenuList(){
        return "/pages/admin/system/menuList";
    }

}
