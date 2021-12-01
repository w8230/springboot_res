package kr.co.team.res.domain.vo.user;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginCnntLogsVO extends SearchVO {


    private LocalDateTime cnctDtm;
    private String cnctId;
    private String succesAt;
    private Long cnctIp;
    private int failCnt;
    private String rsn;
    private String[] yyyyMMddArr;
}
