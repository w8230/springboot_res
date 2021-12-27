package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web.admin.operation.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/operation/member")
public class AdminMemberController extends BaseCont {

    @Autowired
    private AdminMemberService adminMemberService;

    @RequestMapping("/list")
    public String list(Model model,
                       @PageableDefault Pageable pageable ,
                       @ModelAttribute SearchVO searchVO ,
                       @ModelAttribute MemberVO memberVO) {
        model.addAttribute("form" , searchVO);
        Page<Account> memberList = adminMemberService.list(pageable , searchVO , memberVO);
        model.addAttribute("list" , memberList);

        return "/pages/admin/operation/member/memberList";
    }
}
