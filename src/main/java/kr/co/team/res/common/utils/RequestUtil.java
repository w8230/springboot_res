package kr.co.team.res.common.utils;

import kr.co.team.res.common.handlers.AppWebResult;
import kr.co.team.res.domain.enums.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * RequestUtil [agent 확인 class]
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/08/09
**/
public class RequestUtil {
	private static Logger log = LoggerFactory.getLogger(RequestUtil.class); 
	
	private static String APP_WEB_RESULT_KEY = "_app_web_result_"; 

	/**
	 * 접속 클라이언트의 브라우져 정보(user-agent) 리턴함. 
	 * @param request
	 */
	public static String getUserAgent(HttpServletRequest request) {
		
		if ( null == request )  return "";
		
		String userAgent = "";
		userAgent = request.getHeader("user-agent"); 
		
		/*
		Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36 : chrome
		Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko : ie
		*/
		return userAgent;
	}

	/**
	 * 카카오 agent 로 접속하였는지 확인함. 
	 * @param request
	 * @return
	 */
	public static boolean isKakaoAgent(HttpServletRequest request) {
		String checkAgent = "KAKAO"; 
		String userAgent = getUserAgent(request);
		
		return StringHelper.isMatchesIgnore(userAgent, checkAgent);
	}

	/**
	 * client(thymeleaf)에 전달할 메세지를 request 기록함. 
	 * @param request
	 * @param message
	 */
	public static void setAppWebResult(HttpServletRequest request, String message) {
		AppWebResult result = new AppWebResult(message);
		request.setAttribute(APP_WEB_RESULT_KEY, result);
	}

	/**
	 * 리다이렉트 처리시 전달메세지를 기록함. 
	 * @param redirectAttr
	 * @param message
	 */
	public static void setAppWebResult(RedirectAttributes redirectAttr, String message) {
		AppWebResult result = new AppWebResult(message); 
		redirectAttr.addFlashAttribute(APP_WEB_RESULT_KEY, result);
	}
	

	public static void setAppWebResultForRedirect(String message) {
		AppWebResult result = new AppWebResult(message); 
		
		FlashMap flashMap = SpringContenxtUtil.getFlashMap(); 
		
		flashMap.put(APP_WEB_RESULT_KEY, result); 
		
		log.info("리다이렉트를 위해 flashMap을 저장할 필요가 있을까?:{}", flashMap); 
	}
	
	/**
	 * request에 존재하는 전달메세지를 리턴하고 클리어함. 
	 * @param request
	 * @return
	 */
	public static AppWebResult getAppResultMessageAndClear(HttpServletRequest request) {
		Object result = request.getAttribute(APP_WEB_RESULT_KEY); 
		if ( result == null ) return null; 
		
		return (AppWebResult) result;
	}
	
	
	public static String getAppWebResultKey() {
		return APP_WEB_RESULT_KEY; 
	}

	/**
	 * 개발등 전달된 내용을 심플하게 문자열로 변환.
	 * @param request
	 * @return
	 */
	public static String simpleString(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder(); 
		
		
		Enumeration<String> att = request.getAttributeNames();
		sb.append("==Attribute==\n");
		while(att.hasMoreElements()) {
			String key = att.nextElement();
			sb.append(key).append(":").append(request.getAttribute(key)).append(",");
		}
		
		Enumeration<String> p = request.getParameterNames(); 
		sb.append("\n==Parameter==\n");
		while(p.hasMoreElements()) {
			String key = p.nextElement();
			sb.append(key).append(":").append(request.getParameter(key)).append(",");
		}
		
		return sb.toString();
	}

	/**
	 * 사용자 IP를 리턴함. 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		if ( request == null )  return ""; 
		
		return request.getRemoteAddr();
	}


	public static boolean isMobile(HttpServletRequest request) {
		String ua = getUserAgent(request).toLowerCase();

		if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*") || ua.substring(0, 4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getBrowser(HttpServletRequest request) {

		String userAgent = getUserAgent(request);
		BrowserType browser = null;

		if (userAgent.indexOf("Trident") > -1 || userAgent.indexOf("MSIE") > -1) { //IE

			if (userAgent.indexOf("Trident/7") > -1) {
				//browser = "IE 11";
				browser = BrowserType.IE;
			} else if (userAgent.indexOf("Trident/6") > -1) {
				//browser = "IE 10";
				browser = BrowserType.IE;
			} else if (userAgent.indexOf("Trident/5") > -1) {
				//browser = "IE 9";
				browser = BrowserType.IE;
			} else if (userAgent.indexOf("Trident/4") > -1) {
				//browser = "IE 8";
				browser = BrowserType.IE;
			} else if (userAgent.indexOf("edge") > -1) {
				//browser = "IE edge";
				browser = BrowserType.EDGE;
			}

		} else if (userAgent.indexOf("Whale") > -1) { //네이버 WHALE
			//browser = "WHALE " + userAgent.split("Whale/")[1].toString().split(" ")[0].toString();
			browser = BrowserType.WHALE;
		} else if (userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) { //오페라
			if (userAgent.indexOf("Opera") > -1) {
				//browser = "OPERA " + userAgent.split("Opera/")[1].toString().split(" ")[0].toString();
				browser = BrowserType.OPERA;
			} else if (userAgent.indexOf("OPR") > -1) {
				//browser = "OPERA " + userAgent.split("OPR/")[1].toString().split(" ")[0].toString();
				browser = BrowserType.OPERA;
			}
		} else if (userAgent.indexOf("Firefox") > -1) { //파이어폭스
			//browser = "FIREFOX " + userAgent.split("Firefox/")[1].toString().split(" ")[0].toString();
			browser = BrowserType.FIREFOX;
		} else if (userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1) { //사파리
			//browser = "SAFARI " + userAgent.split("Safari/")[1].toString().split(" ")[0].toString();
			browser = BrowserType.SAFARI;
		} else if (userAgent.indexOf("Chrome") > -1) { //크롬
			//browser = "CHROME " + userAgent.split("Chrome/")[1].toString().split(" ")[0].toString();
			browser = BrowserType.CHROME;
		}

		return (browser == null ? "" : browser.name());
	}

}
