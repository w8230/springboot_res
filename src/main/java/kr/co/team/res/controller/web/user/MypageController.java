package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.MenuRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web.user.MemberService;
import kr.co.team.res.service.web.user.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController extends BaseCont {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final PartnersRepository partnersRepository;
    private final MenuRepository menuRepository;
    private final MemberService memberService;
    private final MyPageService myPageService;

    @RequestMapping("/pages/mypage/info")
    public String infoPage(Model model,
                           @CurrentUser Account account) {

        if (account == null) {
            model.addAttribute("altmsg", "로그인 후 이용 가능 합니다.");
            model.addAttribute("locurl", "/pages/login");
            return "/message";
        }

        Account load = myPageService.MemberInfo(account.getId());
        //Account load = memberService.load(account.getId());
        Account form = new Account();

        form.setMberDvTy(load.getMberDvTy());
        form.setLoginId(load.getLoginId());
        form.setNcnm(load.getNcnm());
        form.setNm(load.getNm());
        form.setMoblphon(load.getMoblphon());
        form.setEmail(load.getEmail());
        form.setSexPrTy(load.getSexPrTy());
        form.setAdres(load.getAdres());
        form.setDtlAdres(load.getDtlAdres());
        form.setBrthdy(load.getBrthdy());

        String brthdy = load.getBrthdy();
        String year = brthdy.substring(0,4);
        String month = brthdy.substring(4,6);
        String day = brthdy.substring(6,8);

        form.setYear(year);
        form.setMonth(month);
        form.setDay(day);
        //2004 03 17
        System.out.println("year : " + year + " month : " + month + " day : " + day);

        model.addAttribute("userData", form);
        /*model.addAttribute("brthdylist" , userData);*/
        model.addAttribute("mc", "mypage");
        return "pages/member/mypage/my_info_modify";
    }

    @RequestMapping("/api/mypage/update")
    public String infoModify(Model model,
                             @CurrentUser Account account,
                             @ModelAttribute MemberVO memberVO,
                             @ModelAttribute PartnersVO partnersVO) {
        boolean result = false;

        if (account == null) {
            model.addAttribute("altmsg", "로그인 후 이용 가능 합니다.");
            model.addAttribute("locurl", "/pages/login");
            return "/message";
        }

        memberVO.setId(account.getId());
        result = myPageService.infoModify(memberVO);

        if (result) {
            model.addAttribute("altmsg", "내 정보를 수정하였습니다.");
            model.addAttribute("locurl", "/");
            return "/message";
        } else {
            model.addAttribute("altmsg", "내 정보 수정에 실패하였습니다. 관리자에게 문의하세요");
            model.addAttribute("locurl", "/pages/member/mypage/my_info_modify");
            return "/message";
        }
    }

    /*@ResponseBody
    @PostMapping("/api/mypage/checkPwd")
    public String checkPwd(Model model ,
                           @RequestBody Account inputAccount ,
                           @CurrentUser Account account) {
        boolean result = false;
        result = myPageService.checkPwd(account.getId() , inputAccount.getPwd());
        String msg = "";
        if(result) {
            msg = "ok";
        } else {
            msg = "fail";
        }
        return msg;
    }*/

}
