package kr.co.team.res.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHelper {
	
	private static Logger log = LoggerFactory.getLogger(TwitterHelper.class);

	
	/**
	 * 트위터 포스팅할때 업체명앞에 #을 추가해서 해쉬태크 처리를 하려고 할때 특수문자 제거처리.
	 * @param companyName
	 * @return
	 */
	public static String convertCompanyName(String companyName) {

		String re = companyName.trim().replace(" ", ""); //공백제거 
		re = re.replace("(주)", "");
		re = re.replace("(유)", "");
		re = re.replace("(사)", "");
		re = re.replace("주)", "");
		re = re.replace("주)", "");
		re = re.replace("㈜", ""); 
		
		log.trace("변경후:{}", re);
		
		return re;
	} 
	
	

}
