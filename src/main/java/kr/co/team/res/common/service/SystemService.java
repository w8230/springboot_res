package kr.co.team.res.common.service;

import kr.co.team.res.domain.entity.Account;

import javax.servlet.http.HttpServletRequest;

public interface SystemService {

    /**
     * 시스템관리자(tickgo) 에게 알림.
     * @param message
     */
    void notifySysTickgoAsync(String message);


    /**
     * 시스템관리자(sanovice) 에게 알림.
     * @param message
     */
    void notifySysAdminAsync(String message);

    /**
     * 개발환경여부를 리턴함.  spring.profiles.active=dev
     * @return
     */
    boolean isDevMode();

    /**
     * 로그인 성공했을경우 후처리를 async 형식으로 처리함.
     * @param account
     * @param request
     */
    void loginSuccessAsync(Account account, HttpServletRequest request);

    /**
     * 로그인 실패관련 기록을 async 방식으로 남김.
     * @param userId
     * @param msg
     * @param request
     */
    void loginFailAsync(String userId, String msg, HttpServletRequest request);
}
