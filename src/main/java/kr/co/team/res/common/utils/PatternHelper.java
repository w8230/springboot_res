package kr.co.team.res.common.utils;

import java.util.regex.Pattern;

public class PatternHelper {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PatternHelper.class);

	/**
	 * 전시회 상세페이지 형식인지 확인 (/pm/exhibition/전시번호) 형식인지 확인
	 * @return
	 */
	public static boolean isPmExhibitionExPid(String url) {
		if ( url == null || url.isEmpty() ) return false; 
		//log.debug("====패터체크 url:{}", url);
		String pattern = "^/pm/exhibition/[0-9]*|^/pm/exhibition/[0-9]*/|^/pm/exhibition/[0-9]*/.*"; 
		
		
		boolean re = Pattern.matches(pattern, url);
		//log.trace("일치여부확인. {}", re);
		
		return re;
	}

	/**
	 * /pm/exhibition/전시회번호  형식에서 전시회번호를 리턴함. 
	 * 3번째 전시호번호를 리턴
	 * @param u
	 * @return
	 */
	public static Long getPmExhibitionExPid(String u) {
		if ( isPmExhibitionExPid(u) == false ) return null; 
		
		String[] sp = u.split("/"); 
		if ( sp.length < 4 ) return null; 
		String exPid = sp[3];
		
		Long exno = null;
		try {
			exno = Long.valueOf(exPid);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			log.debug("3번째 숫자변환오류:{}", u);
		} 
		
		return exno;
	}
	
		
}
 