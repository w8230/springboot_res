package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonCodeVO extends SearchVO {
    private Long id;
    private Long prntCodePid;
    private int codeSno;
    private String codeNm;
    private String codeDsc;
    private String codeValue;
}
