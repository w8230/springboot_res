package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web.admin.operation.AdminMemberService;
import kr.co.team.res.service.web.admin.system.UserService;
import kr.co.team.res.service.web.user.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/operation/member")
public class AdminMemberController extends BaseCont {
    private final AdminMemberService adminMemberService;
    private final UserService userService;
    private final PartnersService partnersService;


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

    @RequestMapping("/detail/{id}")
    public String detail(Model model ,
                         @PathVariable(name = "id") Long id) {

        //Id 값 가져오기 문제 없음.
        log.info("id :: " + id);
        Account account = adminMemberService.load(id);
        model.addAttribute("form", account);

        if(account.getMberDvTy().equals(UserRollType.PARTNERS)) {
            PartnersVO partnersVO = new PartnersVO();
            partnersVO.setMberPid(account.getId());
            Partners partnersList = partnersService.list(partnersVO);
            model.addAttribute("pt" , partnersList);
            //개인정보 form / 사업장 정보 partners
        }

        return "/pages/admin/operation/member/detail";
    }

    @RequestMapping("/modify")
    public String modify(){ return null; }

    @RequestMapping("/api/approval")
    public String approval() { return null; }
}
