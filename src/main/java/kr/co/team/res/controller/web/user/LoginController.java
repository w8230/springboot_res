package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.common.service.SystemService;
import kr.co.team.res.common.utils.RequestUtil;
import kr.co.team.res.config.security.direct.UserDetailsService;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.LoginCnntLogs;
import kr.co.team.res.domain.enums.LoginLogMessage;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.service.web.user.LoginCnntLogsService;
import kr.co.team.res.service.web.user.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * LoginController [로그인 처리 컨트롤러]
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/08/09
 **/
@Controller
@RequiredArgsConstructor
public class LoginController extends BaseCont {

    private final MemberService userService;
    private final UserDetailsService userDetailsService;
    private final SystemService systemService;
    private final PasswordEncoder passwordEncoder;
    private final LoginCnntLogsService loginCnntLogsService;


    //private final ResourceServerTokenServices tokenServices;	//kakao login 2020.03.03  fail
    @RequestMapping("/pages/login")
    public String loginPage() {
        return "/pages/login";
    }

    // 로그인 button submit method @CurrentUser Account account,
    @RequestMapping("/login")
    public String login(Model model,
                        HttpServletRequest request,
                        @CurrentUser Account account,
                        HttpSession session) throws UnsupportedEncodingException {
        String redirect = "/";
        if (super.isLogined(account)) {
            log.debug("이미로그인됨 메인이동, account:{}", account);
            if(UserRollType.ADMIN.name().equals(account.getMberDvTy().name())
                    && UserRollType.MASTER.name().equals(account.getMberDvTy().name())) {

                redirect = "/admin/dashboard";
            }
            return "redirect:" + redirect;
        }

        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        model.addAttribute("mc", "memberJoin");
        return "/pages/login";
    }

    @RequestMapping("/pages/session/duplication")
    public String sessionDuplication(Model model) {
        return "/pages/login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(Model model,
                               @CurrentUser Account account,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {


        //log.debug("==로그인성공 후처리시작");

        String redirect = "/";
        log.info("가나다라마"+account.getApproval().equals("N"));
        if(account.getApproval().equals("N")) {
            model.addAttribute("mc","memberJoin");
            model.addAttribute("rsMsg","미승인 계정입니다.");
            return "/pages/login";
        }
        if(UserRollType.ADMIN.name().equals(account.getMberDvTy().name())
                || UserRollType.MASTER.name().equals(account.getMberDvTy().name())) {
            redirect = "/admin/dashboard";
        }

        String clientIP = RequestUtil.getClientIp(request);

        //로그인 로그 삽입 전 체크 해야함.
//        try {
//            pointService.checkAndAddPoint(account, PointType.ATTENDANCE_1DAY);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        LoginCnntLogs loginCnntLogs = new LoginCnntLogs();
        loginCnntLogs.setCnctDtm(LocalDateTime.now());
        loginCnntLogs.setCnctId(account.getLoginId());
        loginCnntLogs.setCnctIp(clientIP);
        loginCnntLogs.setFailCnt(0);
        loginCnntLogs.setSuccesAt("Y");
        loginCnntLogs.setRsn(LoginLogMessage.LOGINSUCESS.getValue());

        boolean result = loginCnntLogsService.insert(loginCnntLogs);
        //systemService.loginSuccessAsync(account, request);    //비동기방식으로 성공처리

        return "redirect:" + redirect;

    }

    // 로그인 실패처리 메소드
    @RequestMapping("/loginFailure")
    public String loginFailure(Model model,
                               HttpServletRequest request) {

        //log.debug("==로그인실패 후처리시작");
        String userId = request.getAttribute("userId") != null ? request.getAttribute("userId").toString() : "";
        String msg = request.getAttribute("ERRORMSG") != null ? request.getAttribute("ERRORMSG").toString() : "";
        model.addAttribute("errormsg", msg);

        //rttr.addFlashAttribute("message", "fail");

        //로그인기록을 남김.
        //systemService.loginFailAsync(userId, msg, request);

        Integer failCnt = 0;

        LoginCnntLogs loginCnntLogs = new LoginCnntLogs();

        if (userId != null) {
            loginCnntLogs.setCnctId(userId);
            LoginCnntLogs failInfo = loginCnntLogsService.loginFailCnt(loginCnntLogs);

            if (failInfo != null) {
                failCnt = failInfo.getFailCnt();
            }
        }

        String clientIP = RequestUtil.getClientIp(request);

        loginCnntLogs.setCnctDtm(LocalDateTime.now());
        loginCnntLogs.setCnctId(userId);
        loginCnntLogs.setCnctIp(clientIP);
        loginCnntLogs.setFailCnt(failCnt+1);
        loginCnntLogs.setSuccesAt("N");
        loginCnntLogs.setRsn(msg);

        try {
            boolean result = loginCnntLogsService.insert(loginCnntLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("mc", "memberJoin");
        return "/pages/login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        String url = "/";
        HttpSession session = request.getSession(false);
        session.invalidate();
        if(request.getParameter("dv").equals("n")) {
            url = "/pages/login";
        }
        return "redirect:"+url;
    }

    @GetMapping("/member/success")
    public String success(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "/member/success";
    }

    @GetMapping("/mypage/mypage")
    public String mypage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "/front/mypage/mypage";
    }

    private static long getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        return seconds;
    }

}
