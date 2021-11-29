package kr.co.team.res.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatetimeHelper {

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";	//기본문자열 

    public static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    public static Period getPeriod(LocalDate dob, LocalDate now) {
        return Period.between(dob, now);
    }

    public static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }

    /**
     * 2개의 날자를 심플하게 텍스트로 변환함.  yyyy.MM.dd~MM.dd 
     * @param dateStart
     * @param dateEnd
     * @return
     */
	public static String simpleFormat(LocalDate dateStart, LocalDate dateEnd) {
		if ( dateStart == null ) return ""; 
		
		String sday = format(dateStart);
		//종료일이 없으면 시작일자만. 
		if ( dateEnd == null )  return sday; 
		
		String eday = format(dateEnd, "MM.dd"); 
		
		return sday + "~" + eday;
	}

	
	public static String format(LocalDate date) {
		
		String dayFormat = "yy.MM.dd"; 
		
		return format(date, dayFormat);
	}
	/**
	 * 날자를 format 형식의 문자열로 변환 
	 * @param date
	 * @param dayFormat
	 * @return
	 */
	public static String format(LocalDate date, String dayFormat) {
		
		return date.format(DateTimeFormatter.ofPattern(dayFormat));
	}

	/**
	 * 새해부터 오늘까지 일수 (오늘 - 1월1일)
	 * @return
	 */
	public static int getDaysFromNewYear() {
		
		LocalDate today = LocalDate.now(); 
		int curYear = today.getYear();
		
		
		LocalDate newYear = LocalDate.of(curYear, 1, 1);
		
		
		Period period = newYear.until(today);
		
		return period.getDays();
	}

	/**
	 * 날자를 기본문자열로 리턴함. 
	 * @param localDate
	 * @return
	 */
	public static String toString(LocalDate localDate) {
		
		if ( localDate == null )  return ""; 
		
		return format(localDate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 지금시간이 두날자 사이인지 확인 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkInToday(LocalDate startDate, LocalDate endDate) {
		if ( startDate == null ) return false; 
		if ( endDate == null ) return false; 
		
		LocalDate now = LocalDate.now();	//현재일자
		
		//종료일 보다 이전이거나 같고 시작일 이후이거나 같은경우 참. 
		if ( now.isBefore(endDate) || now.isEqual(endDate)) {
			if ( now.isAfter(startDate) || now.isEqual(startDate)) {
				//조건을 만족했을 경우 
				return true; 
			}
		}
		
		return false;
	}

	/**
	 * 지금시간이 두날자(시간포함) 사이인지 확인 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkInToday(LocalDateTime startDate, LocalDateTime endDate) {
		if ( startDate == null ) return false; 
		if ( endDate == null ) return false; 
		
		LocalDateTime now = LocalDateTime.now();	//현재일자
		
		//종료일 보다 이전이거나 같고 시작일 이후이거나 같은경우 참. 
		if ( now.isBefore(endDate) || now.isEqual(endDate)) {
			if ( now.isAfter(startDate) || now.isEqual(startDate)) {
				//조건을 만족했을 경우 
				return true; 
			}
		}
		
		return false;
	}

	/**
	 * 현재시간을 리턴함. 
	 * @return
	 */
	public static LocalDateTime getTodaytime() {
		
		return  LocalDateTime.now();
	}
}
