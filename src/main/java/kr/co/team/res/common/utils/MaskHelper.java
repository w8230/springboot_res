package kr.co.team.res.common.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskHelper {

	/**
	 * 핸드폰번호 마스킹처리.
	 * @param str
	 * @return
	 */
    public static String phoneNum(String str) {
        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            Matcher matcher = Pattern.compile("^(\\d{3})-?(\\d{3,4})-?(\\d{4})$").matcher(str);

            if (matcher.matches()) {
                replaceString = "";

                boolean isHyphen = false;
                if (str.indexOf("-") > -1) {
                    isHyphen = true;
                }

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 2) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }

                    if (isHyphen && i < matcher.groupCount()) {
                        replaceString = replaceString + "-";
                    }
                }
            }
        }
        return replaceString;
    }

    /**
     * 핸드폰번호 뒷자리 마스킹처리.
     * @param str
     * @return
     */
    public static String phoneNumBack4(String str) {
        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            Matcher matcher = Pattern.compile("^(\\d{3})-?(\\d{3,4})-?(\\d{4})$").matcher(str);

            if (matcher.matches()) {
                replaceString = "";

                boolean isHyphen = false;
                if (str.indexOf("-") > -1) {
                    isHyphen = true;
                }

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 3) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }

                    if (isHyphen && i < matcher.groupCount()) {
                        replaceString = replaceString + "-";
                    }
                }
            }
        }
        return replaceString;
    }

    /**
     * 핸드폰번호 2자리만 마스킹 처리함. 
     * @param str
     * @return
     */
    public static String phoneNum2(String str) {
    	
    	if ( str == null || str.isEmpty() ) return str; 

    	//7자리보다 작으면 그대로 리턴. 
    	int minLength = 7 ; 
    	if ( str.length() < minLength ) return str;
    	
    	//7자리이상이면 6,7 자리는 마스킹, 나머지는 그대로. 
    	String phone = str.substring(0, minLength-2); 
    	phone += "**"; //마스킹 2자리
    	
    	if ( str.length() > minLength) {
    		phone += str.substring(minLength);
    	}
    	
    	return phone;
    }

    /**
     * 이메일주소 마스킹처리.
     * @param str
     * @return
     */
    public static String email(String str) {
        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            Matcher matcher = Pattern.compile("^(..)(.*)([@]{1})(.*)$").matcher(str);

            if (matcher.matches()) {
                replaceString = "";

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 2) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }
                }

            }
        }
        return replaceString;
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


    public static String name(String str) {

        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            String pattern = "";
            if (str.length() == 2) {
                pattern = "^(.)(.+)$";
            } else {
                pattern = "^(.)(.+)(.)$";
            }

            Matcher matcher = Pattern.compile(pattern).matcher(str);

            if (matcher.matches()) {
                replaceString = "";

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 2) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }

                }
            }
        }
        return replaceString;
    }
    
    /**
     * ip주소 마스킹.
     * @param str
     * @return
     */
    public static String ip(String str) {
        String replaceString = "";
        if (str != null && !str.equalsIgnoreCase("")) {
            replaceString = str;

            Matcher matcher = Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$").matcher(str);

            if (matcher.matches()) {
                replaceString = "";

                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String replaceTarget = matcher.group(i);
                    if (i == 3) {
                        char[] c = new char[replaceTarget.length()];
                        Arrays.fill(c, '*');

                        replaceString = replaceString + String.valueOf(c);
                    } else {
                        replaceString = replaceString + replaceTarget;
                    }
                    if (i < matcher.groupCount()) {
                        replaceString = replaceString + ".";
                    }
                }
            }
        }
        return replaceString;
    }

    /**
     * email 함수는 마스킹처리가 너무 많아 끝자리 2자리만 마스킹 하는 함수 추가. 2020.02.19
     * @param mail
     * @return
     */
	public static String email2(String mail) {
		if ( mail == null ) return "";
		if ( mail.isEmpty() ) return mail;
		
		String[] sp = mail.split("@"); 
		if ( sp.length < 2 )  return mail; 
		
		String first = sp[0]; 
		if ( first.length() < 2 ) first = "*"; 
		else if ( first.length() == 2 ) first = first.substring(0, 1) + "*";
		else if ( first.length() == 3 ) first = first.substring(0, 2) + "*";
		else first = first.substring(0, first.length()-2) + "**";
		
		String email = first + "@";
		//첫번째는 마스킹 했으니 2번째 부터 붙이기. 
		for(int i=1; i< sp.length; i++ ) {
			email += sp[i];
		}
		
		return email;
	}


}
