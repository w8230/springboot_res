package kr.co.team.res.common.utils;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class HttpUtil {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(HttpUtil.class);
	
	private String ACCESS_TOKEN = "epart_onlinedirectory_2019070120201231";
	private String BASE_URL = "http://api.coex.co.kr:7777/api/cybercoex";
//	private String BASE_URL = "http://localhost:7777/api/cybercoex";
	
	public Object connect(String subUrl, Map<String, String> queryParam) {
		
		// 인증토큰 생성
		String bearerToken = "Bearer " + ACCESS_TOKEN;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", bearerToken);
		HttpEntity<Object> request = new HttpEntity<>(headers);

		URI url = getURL(buildURL(subUrl, queryParam));
		
		ResponseEntity<Map> result = null;
		
		try {
			result = restTemplate.postForEntity(url, request, Map.class);
			logger.info("getStatusCode : " + result.getStatusCodeValue());
		} catch (Exception e) {
			logger.error("API 호출시 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
//		List<String> list = (List<String>) result.getBody().get("data");
		Object obj = result.getBody().get("data");
		
		return obj;
	}
	
	private URI getURL(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (Exception e) {
			logger.error("getURL ERROR {} : ", e.getMessage());
		}
		
		return uri;
	}
	
	private String buildURL(String subUrl, Map<String, String> queryParam) {
		
		String url = BASE_URL + subUrl;
		
		if(queryParam == null) return url;
		
		StringBuffer sb = new StringBuffer();
		try {
			for(Entry<String, String> p : queryParam.entrySet()) {
				if(sb.length() > 0) {
					sb.append("&");
				}
				sb.append(p.getKey()).append("=").append(p.getValue());
			}
			url += "?" + sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		logger.debug("URL : {} ",  url);
		
		return url;
	}

	/**
	 * url 접속결과를 리턴함. 
	 * @param url
	 * @param header
	 * @return
	 */
	public static Map<?, ?> getMap(String url, String bearerToken) {
		
		RestTemplate restTemplate = new RestTemplate();
		

		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		//ContentRequest contentRequest = new ContentRequest();
		//contentRequest.setContent("----");
		 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "bearer " + bearerToken);
		headers.setContentType(MediaType.APPLICATION_JSON); 

		HttpEntity<Map<String,?>> entity = new HttpEntity<Map<String,?>>(headers);
		
		ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
		
		logger.info("result:{}", result); 
		
		if ( result.getStatusCode().isError() ) {
			logger.warn("웹접속결과오류 url:{}, result:{}", result);
			return null;
		}
		
		return result.getBody();
		
	}
		
}
 