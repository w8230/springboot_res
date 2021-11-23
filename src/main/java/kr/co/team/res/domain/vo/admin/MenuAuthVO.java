package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuAuthVO extends SearchVO {

    private Long menuPid;
    private Long mberPid;
    private String confmAt;

}
