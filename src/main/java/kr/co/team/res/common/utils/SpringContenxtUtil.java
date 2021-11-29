package kr.co.team.res.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SpringContenxtUtil {

	/**
	 * Spring RequestContextHolder를 이용하여 request 객체를 리턴함. 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return req; 
	}
	
	/**
	 * Spring RequestContextHolder를 이용해서 response 를 리턴함. 미확인. 2020.02.27
	 * @return
	 */
	public static HttpServletResponse getResponse() {

		HttpServletResponse res = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
		
		return res; 
	}
	
	
	/**
	 * Spring RequestContextHolder를 이용해서 HttpSession 객체를 리턴함. 
	 * @return
	 */
	public static HttpSession  getHttpHttpSession() {
		
		HttpServletRequest req = getHttpServletRequest(); 
		
		
		return req.getSession(); 
	}
	
	/**
	 * 리다이렉트 처리시 사용될 FlashMap 객체를 리턴함.  사용은 flashMap.put("key", value);  
	 * https://www.programcreek.com/java-api-examples/?api=org.springframework.web.servlet.FlashMap
	 * https://m.blog.naver.com/479lgs/220349314506
	 * @return
	 */
	public static FlashMap getFlashMap() {
		HttpServletRequest request = getHttpServletRequest();
		
		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
		
		/*
		// create a flashmapMapManger with `request`
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);

		// save the flash map data in session with falshMapManager
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		*/
		
		return  flashMap; 
	}

}
