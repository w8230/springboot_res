package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubCategoryVO extends SearchVO {

    private Long id;
    private Long categoryPid;
    private String cateDvTy;
    private String subcategoryNm;
    private LocalDateTime regDtm;
    private String updPsId;
    private LocalDateTime updDtm;
    private String delAt;

}
