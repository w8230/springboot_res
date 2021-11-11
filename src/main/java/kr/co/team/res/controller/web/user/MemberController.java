package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController extends Base{

    @RequestMapping("/member/join")
    public String memberjoinpage(Model model){ return "/pages/member/member_join"; }
}
