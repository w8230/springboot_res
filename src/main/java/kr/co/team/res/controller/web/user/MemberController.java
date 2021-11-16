package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.service.web.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController extends Base{
    private final MemberService memberService;

    @RequestMapping("/pages/choiceregister")
    public String registerchoice(){ return "pages/choice_register"; }

    @RequestMapping("/member/register")
    public String memberjoinpage(Model model){ return "pages/member/member_register"; }

    @PostMapping("/api/member/insert")
    public ResponseEntity insert(MemberVO memberVO ,
                                 Errors errors ,
                                 BindingResult bindingResult) {

        log.info("controller run");

        log.info(memberVO.getLoginId());

        log.info(memberVO.getEmail());

        log.info(memberVO.getBrthdy());

        log.info(memberVO.getAdres());

        log.info(memberVO.getPwd());

        log.info(memberVO.getNm());

        log.info(memberVO.getNcnm());

        log.info(memberVO.getMoblphon());

        boolean result = false;
        String msg = "";


        /*//기본정보 SET 인증검수 로직 작성 후 service로 이동.
        memberVO.setDelAt("N");
        memberVO.setApproval("Y");
        memberVO.setRegDtm(LocalDateTime.now());
        memberVO.setLoginId(memberVO.getLoginId());
        memberVO.setPwd(passwordEncoder.encode(memberVO.getPwd()));
        memberVO.setNm(memberVO.getNm());
        memberVO.setNcnm(memberVO.getNcnm());
        memberVO.setMoblphon(memberVO.getMoblphon());
        memberVO.setEmail(memberVO.getEmail());
        memberVO.setBrthdy(memberVO.getBrthdy());
        memberVO.setAdres(memberVO.getAdres());
        memberVO.setDtlAdres(memberVO.getDtlAdres());
        memberVO.setSexPrTy(memberVO.getSexPrTy());
        //RollType 구분 vo SET
        if(memberVO.getMberDvTy().equals(UserRollType.NORMAL)){
            memberVO.setMberDvTy(UserRollType.NORMAL);
        } else if(memberVO.getMberDvTy().equals(UserRollType.PARTNERS)){
            memberVO.setMberDvTy(UserRollType.PARTNERS);
        } else if(memberVO.getMberDvTy().equals(UserRollType.ADMIN)){
            memberVO.setMberDvTy(UserRollType.ADMIN);
        }*/

        if(memberVO.getAuthMobileChk() == 2 && memberVO.getAuthMobileChk() == 2) {
            if(memberVO.getAuthMobileChk() == 2){
                memberVO.setMobileAttcAt("Y");
                memberVO.setEmailAttcAt("N");
                memberVO.setMobileAttcDtm(LocalDateTime.now());
                //memberService.insert(memberVO);
            } else if (memberVO.getAuthEmailChk() == 2) {
                memberVO.setEmailAttcAt("Y");
                memberVO.setMobileAttcAt("N");
                memberVO.setEmailAttcDtm(LocalDateTime.now());
                //memberService.insert(memberVO);
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
    /*@ResponseBody
    @PostMapping("/api/member/isExistsByLoginId")
    public ResponseEntity isExistsByLoginId(@ModelAttribute MemberVO memberVO ,
                                            BindingResult bindingResult) {
        if(memberService.)
    }*/
}
