package kr.co.team.res.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    public static String getClientIP(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Deprecated
    public static String phoneNumDelete(String str) {
    	return MaskHelper.phoneNum(str);
    }

    public static String birthYear(String str) {

        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            Matcher matcher = Pattern.compile("^(\\d{4})$").matcher(str);

            if (matcher.matches()) {
                replaceString = "";

            /*boolean isHyphen = false;
            if(str.indexOf("-") > -1) {
                isHyphen = true;
            }*/

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 2) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }

                /*if(isHyphen && i < matcher.groupCount()) {
                    replaceString = replaceString + "-";
                }*/
                }
            }
        }

        return replaceString;
    }


    @Deprecated
    public static String nameDelete(String str) {
    	return MaskHelper.name(str);
    }

    @Deprecated
    public static String emailDelete(String str) {
        
        return MaskHelper.email(str); 
    }

    @Deprecated
    public static String ipDelete(String str) {
        return MaskHelper.ip(str);
    }

    public static String onlyNumber(String str) {
        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str.replaceAll("\\D", "");
        }
        return replaceString;
    }

    public static String maskingName(String name) {
        if (name == null || name.length() < 2) return name;

        String rtnNm = "";
        for (int i=0; i< name.length(); i++) {
            if ((i == 0) || (i == (name.length()-1))) {
                rtnNm += name.charAt(i);
            } else {
                rtnNm += "*";
            }
        }
        return rtnNm;
    }

    public static String removeTag(String str) {
        if (str == null) return null;
        return str.replaceAll("\\<.*?>","");
    }
    
    //대소문자 구분없이 text문자열에 checkText 존재하는지 리턴함. 
    public static boolean isMatchesIgnore(String text, String checkText) {
		if ( null == text || null == checkText ) return false; 
		
		//대소문자 구분없이 검색 
		String regex = "(?i).*" + checkText + ".*";
		
		return text.matches(regex); 
		
    }

    /**
     * 숫자에 천단위마다 콤마 넣기
     * @param num
     * @return String
     * */
    public static String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public static int birthYearToAge(String birthYear) {
        if (birthYear == null || "".equals(birthYear)) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        int userYear = Integer.parseInt(birthYear);

        return (nowYear - userYear + 1);
    }
}
