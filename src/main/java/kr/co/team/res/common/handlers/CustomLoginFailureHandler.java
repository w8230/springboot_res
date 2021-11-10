package kr.co.team.res.common.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass()); 

    @Autowired
    MessageSource messageSource;

    private String loginidname;
    private String loginpwdname;
    private String errormsgname;
    private String defaultFailureUrl;

    public CustomLoginFailureHandler(String defaultFailureUrl, String userId, String userPw) {
        this.defaultFailureUrl = defaultFailureUrl;
        this.loginidname = userId;
        this.loginpwdname = userPw;
        this.errormsgname = "ERRORMSG";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter(loginidname);
        String password = request.getParameter(loginpwdname);
        String errormsg = null;
        
        log.debug("로그인실패 후처리  username:{}", username);
        
        /*
        BadCredentialException : 비밀번호가 일치하지 않을 때 던지는 예외
        InternalAuthenticationServiceException : 존재하지 않는 아이디일 때 던지는 예외
        AuthenticationCredentialNotFoundException : 인증 요구가 거부됐을 때 던지는 예외
        LockedException : 인증 거부 - 잠긴 계정
        DisabledException : 인증 거부 - 계정 비활성화
        AccountExpiredException : 인증 거부 - 계정 유효기간 만료
        CredentialExpiredException : 인증 거부 - 비밀번호 유효기간 만료
        DualAuthenticationServiceException : 이메일 또는 핸드폰중복
        */
        /*if(exception instanceof BadCredentialsException) {
            errormsg = messageSource.getMessage("error.BadCredentials", null, LocaleContextHolder.getLocale());
        } else if(exception instanceof InternalAuthenticationServiceException) {
            errormsg = messageSource.getMessage("error.BadCredentials", null, LocaleContextHolder.getLocale());
        } else if(exception instanceof DisabledException) {
            errormsg = messageSource.getMessage("error.Disabled", null, LocaleContextHolder.getLocale());
        } else if(exception instanceof CredentialsExpiredException) {
            errormsg = messageSource.getMessage("error.CredentialsExpired", null, LocaleContextHolder.getLocale());
        } else if(exception instanceof DualAuthenticationServiceException){
            errormsg = messageSource.getMessage("error.DualCredentials", null, LocaleContextHolder.getLocale());
        }*/

        if(exception instanceof BadCredentialsException) {
            errormsg = messageSource.getMessage("error.BadCredentials", null, LocaleContextHolder.getLocale());
        } else {
            errormsg = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
            request.setAttribute("mailAuthBool", false);
        }

        request.setAttribute(loginidname, username);
        request.setAttribute(loginpwdname, password);
        request.setAttribute(errormsgname, errormsg);

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);

    }

}
