package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.MenuRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController extends BaseCont {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final PartnersRepository partnersRepository;
    private final MenuRepository menuRepository;

    //12.05 만들거 기본정보 뽑기 , 페이지 이동(예외처리까지) , 업데이트 로직

    @RequestMapping("/pages/mypage/info")
    public String Mypage(Model model ,
                         @CurrentUser Account account){
        boolean result = false;

        if(account == null) {
            model.addAttribute("altmsg" , "로그인 후 이용 가능 합니다.");
            model.addAttribute("locurl" , "/pages/login");
            return "/message";
        }

        model.addAttribute("mc" , "mypage");
        return "pages/member/mypage/my_info_modify";
    }

    @RequestMapping("/api/mypage/update")
    public Account update(Model model,
                         @CurrentUser Account account) {

        return account;
    }

}
