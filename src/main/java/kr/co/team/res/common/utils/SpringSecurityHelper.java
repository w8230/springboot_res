package kr.co.team.res.common.utils;

import kr.co.team.res.config.security.direct.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityHelper {

	private static Logger log = LoggerFactory.getLogger(SpringSecurityHelper.class); 
	
	
	/**
	 * SpringSecurity 로그인된 계정정보를 리턴함. 미로그인이거나 anonymousUser 이면 null
	 * @return
	 */
	public static UserDetails loginedUser() {
		
		UserDetails userDetails = null; 
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		if(principal.toString().equalsIgnoreCase("anonymousUser")) {
			log.debug("로그인되지 않았음. anonymousUser");
			return null;
		} else {
			userDetails = (UserDetails) principal;
		}
		
		return userDetails;
	}

}
