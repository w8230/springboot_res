package kr.co.team.res.common.service;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.utils.LineMessageUtils;
import kr.co.team.res.common.utils.NullHelper;
import kr.co.team.res.domain.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Service
public class SystemServiceImpl extends Base implements SystemService {

    @Value("${spring.profiles.active}")
    private String springProfilesActive = "real";

    @Async
    @Override
    public void notifySysTickgoAsync(String message) {

        LineMessageUtils.notifyToSystemManagerTickgo(message);
    }

    @Async
    @Override
    public void notifySysAdminAsync(String message) {

        LineMessageUtils.notifyToSystemManager(message);
    }

    @Override
    public boolean isDevMode() {

        if ( NullHelper.isEmpty(springProfilesActive) )  return false;

        // spring.profiles.active 가 dev 이면 개발환경
        return springProfilesActive.toLowerCase().equals("dev") ;
    }

    @Async
    @Override
    public void loginSuccessAsync(Account account, HttpServletRequest request) {

		/*if ( account == null ) {
			log.info("로그인성공시 회원정보 없음. request:{}", request);
			return ;
		}
        String clientIP = RequestUtil.getClientIp(request);	//사용자IP
		loginLogsService.updateSuccessYn(account, clientIP);*/

        //log.info("회원정보에 마지막 로그인 시점 기록필요 userPid:{}", account.getId());
        //userService.updateLastLogin(account.getId());

    }

    @Override
    public void loginFailAsync(String userId, String loginFailMsg, HttpServletRequest request) {

		/*if ( userId == null || loginFailMsg == null ) return ;

        String clientIP = RequestUtil.getClientIp(request);

        LoginLogs loginLogs = new LoginLogs();
        //loginLogs.setTryDtHms(LocalDateTime.now());
        loginLogs.setCnntIp(clientIP);
        loginLogs.setSuccessYn("Y");
        loginLogs.setFailReason(loginFailMsg);
        loginLogs.setUserId(userId);


        try {
            loginLogsService.save(loginLogs);
        } catch (Exception e) {
            log.warn("==로그인기록 남길때오류발생 userId:{}, 오류:{}", userId, e.getMessage());
            e.printStackTrace();
        }*/
    }


}
