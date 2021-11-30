package kr.co.team.res.domain.vo.user;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MobileAuthLogVO extends SearchVO {

    private Long id;
    private String decrHr;
    private String dmnNo;
    private String rspNo;
    private String attcMns;
    private String nm;
    private String dupSbscrbCfmVal;
    private String cnecInfoCfmVal;
    private String brthdy;
    private String sexdstn;
    private String iseFrerInfo;
    private String mbtlnum;
    private String telecom;
    private String failrCode;
    private String mssage;
    private String encrData;
    private LocalDateTime regDtm;

}
