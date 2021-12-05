package kr.co.team.res.controller.web.user;

import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web.user.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController extends BaseCont {
    private final MemberService memberService;

    @RequestMapping("/pages/register")
    public String registerchoice(){ return "pages/choice_register"; }

    @RequestMapping("/member/register")
    public String memberjoinpage(Model model){ return "pages/member/member_register"; }

    //회원가입 컨트롤러
    @PostMapping(value = "/api/member/insert")
    public String insert(MemberVO memberVO ,
                         Model model) {
        boolean result = false;

        if(memberVO.getAuthMobileChk() == 2) {
            if(memberVO.getAuthMobileChk() == 2){
                log.info("나이스 인증 임시처리 됨");
                memberVO.setMobileAttcAt("Y");
                memberVO.setEmailAttcAt("N");
                memberVO.setMobileAttcDtm(LocalDateTime.now());
                memberService.insert(memberVO);

            } else if (memberVO.getAuthEmailChk() == 2) {
                log.info("나이스 인증 임시처리 됨");
                memberVO.setEmailAttcAt("Y");
                memberVO.setMobileAttcAt("N");
                memberVO.setEmailAttcDtm(LocalDateTime.now());
                memberService.insert(memberVO);
            }
        } else if(memberVO.getAuthMobileChk() != 2 || memberVO.getAuthEmailChk() != 2){
            if(memberVO.getMberDvTy().equals(UserRollType.NORMAL)){
                model.addAttribute("altmsg" , "본인인증에 실패했습니다. RES에 문의 해주세요.");
                model.addAttribute("locurl" , "/pages/member/member_register");
                return "/message";
            } else if(memberVO.getMberDvTy().equals(UserRollType.PARTNERS)) {
                model.addAttribute("altmsg" , "본인인증에 실패했습니다. RES에 문의 해주세요.");
                model.addAttribute("locurl" , "/pages/partners/partners_register");
                return "/message";
            }
        }

        if(result){
            model.addAttribute("altmsg" , "회원가입이 완료되었습니다.");
            model.addAttribute("locurl" , "/pages/login");
            return "/message";
        } else {
            model.addAttribute("altmsg" , "회원가입에 실패하였습니다. RES에 문의해주세요.");
            model.addAttribute("locurl" , "/");
            return "/message";
        }
    }

    //계정 중복확인 컨트롤러
    @ResponseBody
    @PostMapping("/api/member/isExistsByLoginId")
    public ResponseEntity isExistsByLoginId(Model model , @ModelAttribute MemberVO memberVO , BindingResult bindingResult) {
        if (memberService.existsByLoginId(memberVO.getLoginId())) {
            bindingResult.rejectValue("loginId", "invalid ID", new Object[]{memberVO.getLoginId()}, "이미 사용중인 아이디입니다.");
        }
        if (memberService.existsSpace(memberVO.getLoginId())) {
            bindingResult.rejectValue("loginId", "invalid ID", new Object[]{memberVO.getLoginId()}, "아이디에는 공백을 사용 할 수 없습니다.");
        }
        //memberFormValidator.validate(memberForm, bindingResult);
        if (bindingResult.hasFieldErrors("loginId")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError("loginId").getDefaultMessage());
        } else {
            return ResponseEntity.ok(memberVO);
        }
    }
}
