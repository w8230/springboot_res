package kr.co.team.res.controller.web.user;

import kr.co.team.res.controller.web.BaseCont;
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

    @RequestMapping("/pages/choiceregister")
    public String registerchoice(){ return "pages/choice_register"; }

    @RequestMapping("/member/register")
    public String memberjoinpage(Model model){ return "pages/member/member_register"; }

    //회원가입 컨트롤러
    @PostMapping("/api/member/insert")
    public ResponseEntity insert(MemberVO memberVO ,
                                 Errors errors ,
                                 BindingResult bindingResult) {

        boolean result = false;
        String msg = "";
        //스크립트 조합값 확인.
        log.info(memberVO.getBrthdy());
        //hidden 값 잘 받아냄.

        if(memberVO.getAuthMobileChk() == 2) {
            if(memberVO.getAuthMobileChk() == 2){
                log.info("val moble 2");
                memberVO.setMobileAttcAt("Y");
                memberVO.setEmailAttcAt("N");
                memberVO.setMobileAttcDtm(LocalDateTime.now());
                System.out.println(memberVO.getZip());
                System.out.println(memberVO.getBzip());
                memberService.insert(memberVO);

            } else if (memberVO.getAuthEmailChk() == 2) {
                log.info("val email 2");
                memberVO.setEmailAttcAt("Y");
                memberVO.setMobileAttcAt("N");
                memberVO.setEmailAttcDtm(LocalDateTime.now());
                memberService.insert(memberVO);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        if(result){
            msg = "회원가입이 완료되었습니다.";
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
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
