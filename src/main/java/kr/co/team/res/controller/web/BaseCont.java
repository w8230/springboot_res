package kr.co.team.res.controller.web;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.exceptions.AppCheckException;
import kr.co.team.res.common.handlers.AppWebResult;
import kr.co.team.res.common.service.SystemService;
import kr.co.team.res.common.utils.RequestUtil;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.enums.UserRollType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;

public abstract class BaseCont extends Base {

    @Autowired
    private SystemService systemService;


    protected boolean hasAllRoll(Account account, UserRollType...roll) {
        if (roll == null) return false;
        if (account == null || account.getAuthorites() == null || account.getAuthorites().size() == 0) return false;

        boolean rtnBool = true;
        for (UserRollType userRollType : roll) {
            if (!account.getAuthorites().contains(userRollType)) {
                rtnBool = false;
                break;
            }
        }
        return rtnBool;
    }

    protected boolean hasOneRoll(Account account, UserRollType...roll) {
        if (roll == null) return false;
        if (account == null || account.getAuthorites() == null || account.getAuthorites().size() == 0) return false;

        boolean rtnBool = false;
        for (UserRollType userRollType : roll) {
            if (!account.getAuthorites().contains(userRollType)) {
                rtnBool = true;
                break;
            }
        }
        return rtnBool;
    }


    /**
     * 접속 클라이언트 정보(user-agent) 리턴함.
     * @param request
     * @return
     */
    protected String gerUserAgent(HttpServletRequest request) {

        return RequestUtil.getUserAgent(request);
    }


    /**
     * kakao agent 인지여부를 리턴함.
     * @param request
     * @return
     */
    protected boolean isKakaoAgent(HttpServletRequest request) {

        return RequestUtil.isKakaoAgent(request);
    }


    // 로그인 여부를 리턴함.
    protected boolean isLogined(Account account) {
        if ( account == null )  return false;

        boolean isLogined = account.getId() != null ? true : false;

        return isLogined;
    }
    //로그인안된었는지 확인
    protected boolean isNotLogined(Account account) {
        return !isLogined(account);
    }

    /**
     * client(thymeleaf)에서 사용될 수 있도록 (공통)메세지를 기록함.
     * @param model
     * @param message
     */
    protected void setAppWebResult(Model model, String message) {
        boolean success = true;
        setAppWebResult(model, message, success);
    }

    protected void setAppWebResult(Model model, AppCheckException e) {
        boolean success = false;
        setAppWebResult(model, e.getMessage(), success);
    }

    /**
     * client(thymeleaf)에서 사용될 수 있도록 (공통)메세지를 기록함.
     * @param model
     * @param message
     * @param success
     */
    private void setAppWebResult(Model model, String message, boolean success) {
        AppWebResult result = new AppWebResult(message);

        model.addAttribute(RequestUtil.getAppWebResultKey(), result);   // key  _app_web_result_
    }

    /**
     * client(thymleaf) 에서 사용될수 있도록 model 객체에 설정함
     * @param model
     * @param request
     */
	/*protected void setAppWebResult(Model model, HttpServletRequest request) {
		AppWebResult result = RequestUtil.getAppResultMessageAndClear(request);

		model.addAttribute(RequestUtil.getAppWebResultKey(), result);   // key  _app_web_result_
	}*/

    /**
     * 시스템 관리자에게 메세지 전송함.
     * @param message
     */
    public void notifySysAdmin(String message) {

        if ( systemService != null ) systemService.notifySysAdminAsync(message);
    }

    protected ModelAndView newModelAndView() {

        return new ModelAndView();
    }

    protected ModelAndView newRedirectModelAndView(String url) {
        return new ModelAndView(new RedirectView(url));
    }

}
