package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuVO extends SearchVO {

    private Long id;
    private String menuNm;
    private String menuUrl;
    private String newwinAt;
    private Long menuGroupCdPid;
    private int menuSn;

}

