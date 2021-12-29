package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web.admin.system.UserService;
import kr.co.team.res.service.web.user.MemberService;
import kr.co.team.res.service.web.user.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.processor.SpringValueTagProcessor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController extends BaseCont {
    private final PartnersService partnersService;
    private final UserService userService;


    /*@RequestMapping("/admin/operation/member/list")
    public String list(Model model,
                       @PageableDefault Pageable pageable ,
                       @ModelAttribute SearchVO searchVO ,
                       @ModelAttribute MemberVO memberVO) {
        model.addAttribute("form" , searchVO);
        Page<Account> memberList = userService.list(pageable , searchVO , memberVO);
        model.addAttribute("list" , memberList);

        return "/pages/admin/operation/member/memberList";
    }*/


    /*@RequestMapping("/admin/user/detail/{id}")
    public String detail(Model model ,
                         @PathVariable(name = "id") Long id) {

        //Id 값 가져오기 문제 없음.
        Account account = userService.load(id);
        model.addAttribute("account", account);

        if(account.getMberDvTy() == UserRollType.PARTNERS) {
            PartnersVO partnersVO = new PartnersVO();
            partnersVO.setMberPid(account.getId());
            List<Partners> partnersList = partnersService.list(partnersVO);
            model.addAttribute("partners" , partnersList);
            //개인정보 form / 사업장 정보 list
        }
        return "pages/admin/operation/member/detail";
    }*/


    /*@RequestMapping("/user/modify")
    public String modify() {

        return null;
    }*/
}

