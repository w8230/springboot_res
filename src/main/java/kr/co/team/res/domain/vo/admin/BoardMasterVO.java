package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.enums.BoardType;
import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class BoardMasterVO extends SearchVO {

    private Long id;
    private String bbsNm;

    @Enumerated(EnumType.STRING)
    private BoardType bbsTy;

    private String sntncHead;

    private String bbsUpendCn;
    private String pwd;

}
