package kr.co.team.res.common.handlers;

import kr.co.team.res.common.Base;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 웹에서 리턴된 메세지
 * @author user
 *
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class AppWebResult extends Base {

	private String msg = ""; 
	private boolean success = true; 
	
	
	public AppWebResult(String msg) {
		super(); 
		this.msg = msg; 
	}

	public AppWebResult(String msg, boolean success) {
		super(); 
		this.msg = msg; 
		this.success = success;
	}
}
