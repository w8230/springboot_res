package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PartnersController extends Base {

    @RequestMapping("/partners/register")
    public String partnersregisterpage() { return "pages/partners/partners_register"; }
}
