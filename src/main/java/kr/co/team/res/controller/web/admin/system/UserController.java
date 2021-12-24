package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web.admin.system.UserService;
import kr.co.team.res.service.web.user.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.processor.SpringValueTagProcessor;

@Controller
@RequiredArgsConstructor
public class UserController extends BaseCont {
    private final MemberService memberService;
    private final UserService userService;


    @RequestMapping("/admin/user/userList")
    public String list(Model model,
                       @PageableDefault Pageable pageable ,
                       @ModelAttribute SearchVO searchVO ,
                       @ModelAttribute MemberVO memberVO) {
        model.addAttribute("form" , searchVO);
        Page<Account> memberList = userService.list(pageable , searchVO , memberVO);
        model.addAttribute("list" , memberList);

        return "/pages/admin/operation/member/memberList";
    }

    @RequestMapping("/admin/user/detail/{id}")
    public String detail() {

        return null;
    }
    @RequestMapping("/user/modify")
    public String modify() {

        return null;
    }
}
