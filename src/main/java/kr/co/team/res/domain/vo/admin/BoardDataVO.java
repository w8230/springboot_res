package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDataVO extends SearchVO {

    private Long id;
    private String thread;
    private String depth;
    private String ttl;
    private String cn;
    private String wrterNm;
    private String sntncHead;
    private String pwd;
    private Integer readCnt;
    private String wrterIp;
    private String prntPwd;
    private Long mstPid;
    private String prevNext;

    private Long mberPid;
    private String hashTags;
    private String fixingAt;
    private String ntceDtString;
    private LocalDateTime ntceDt;

}
