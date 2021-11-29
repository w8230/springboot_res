package kr.co.team.res.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * https://github.com/sketchout/GroupNotifyLINE
 * https://engineering.linecorp.com/ko/blog/using-line-notify-to-send-messages-to-line-from-the-command-line/
 * @author user
 *
 */
public class LineMessageUtils {
	
	private static final Logger log = LoggerFactory.getLogger(LineMessageUtils.class); 
	
	private static final String _NOTIFY_API = "https://notify-api.line.me/api/notify";
	
	private static final String _SYSTEM_MANAGER_TOEKN = "oG0yLwe4yfrE2i4bXL0QGsmqSLHdAdaeGhWItGcOrAH";   //line tickGo(sanovice) token 

	private static final String _SYSTEM_MANAGER_TOEKN_TICKGO = "A4NhozGwgvqsSuJzgFG3hh6lM5c3k1ckNvkMFey2rnO";  //line tickgo.coex@gmail.com

	
	/**
	 * 시스템관리자에게 메세지를 전송함. 
	 * @param message
	 * @throws Exception 
	 */
	public static void notifyToSystemManager(String message) {
		
		try {
			LineMessageUtils.notify(_SYSTEM_MANAGER_TOEKN, message);
		} catch (Exception e) {
			//e.printStackTrace();
			log.warn("라인메세지 전송오류:{}", e.getMessage());
		}
		
	}
	
	/**
	 * 시스템관리자(tickgo.coex@gmail.com)으로 메세지전송
	 * @param message
	 */
	public static void notifyToSystemManagerTickgo(String message) {
		
		try {
			LineMessageUtils.notify(_SYSTEM_MANAGER_TOEKN_TICKGO, message);
		} catch (Exception e) {
			//e.printStackTrace();
			log.warn("라인메세지 전송오류:{}", e.getMessage());
		}
		
	}
	
	/**
	 * 토큰 소유자에게 메세지를 전송함. 
	 * @param token
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static int notify(String token, String message) throws Exception 
	{
		int result = 0;	// can't get the result
   		try {
			HttpURLConnection connection = getConnection(token, new URL(_NOTIFY_API));
			String parameterString = new String("message="  + message);

			connection.getOutputStream().write(parameterString.getBytes("UTF-8"));
			connection.getInputStream();
            
			// Gets the status code from an HTTP response message.
			int statusCode = connection.getResponseCode();
        	result = statusCode;
        	if ( statusCode != 200 ) {
        		// Error occurs, before here
        		printHeaderFileds(connection);
        	}
	        connection.disconnect();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	private static HttpURLConnection getConnection(String token, URL url) throws IOException 
	{
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput( true );
		connection.setRequestMethod( "POST" );
		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
		connection.addRequestProperty("Authorization",  "Bearer " + token);
		
		return connection;
	}

	private static void printHeaderFileds(HttpURLConnection connection) {
		Map<String,List<String>> headerFields = connection.getHeaderFields();
		Set<String> keys = headerFields.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String key = it.next();
			List<String> values = headerFields.get(key);
			StringBuffer sb = new StringBuffer();
			sb.append( key ).append( ":" );
			Iterator<String> it2 = values.iterator();
			while( it2.hasNext() ) sb.append( " " ).append( it2.next() );
			System.out.println(sb);
		}
	}

}