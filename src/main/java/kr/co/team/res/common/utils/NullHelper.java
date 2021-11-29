package kr.co.team.res.common.utils;

import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class NullHelper {

	//private static final Logger log = LoggerFactory.getLogger(NullHelper.class);

	/**
	 * 문자열이 null 또는 공백스트링이면 true
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		if ( isNull(text) ) return true; 
		
		return text.isEmpty();
	} 

	/**
	 * list 공백여부 확인, null 또는 사이즈가 0이면 공백 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		if ( list == null ) return true; 
		
		return list.size() < 1 ? true : false;
	}
	
	// null check
	public static boolean isNull(Object obj ) {
		return null == obj ? true : false; 
	}

	/**
	 * 문자열로 변경함. 단 null 인경우 기본값 ""으로 리턴. 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		String defaultString = ""; 
		
		if (obj == null ) return defaultString; 
		
		return obj.toString();
	}

	/**
	 * map에 해당하는 키값을 리턴함. null인경우 공백 리턴.
	 * @param m
	 * @param mapKey
	 * @return
	 */
	public static String toString(Map<String, Object> m, String mapKey) {
		
		if ( m == null )  return ""; 
		
		return toString(m.get(mapKey));
	}

	/**
	 * null 인경우 false를 리턴함. 
	 * @param nullAbleValue
	 * @return
	 */
	public static boolean safe(Boolean nullAbleValue) {
		
		if ( nullAbleValue == null ) return false; 
		return nullAbleValue;
	}

	public static <T extends Enum<T>> String safeName(Class<T> b) {
		
		if ( b == null ) return "";
		
		return b.getName();
	}

}
