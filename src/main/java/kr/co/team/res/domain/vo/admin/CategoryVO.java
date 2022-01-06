package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.enums.CateDvTy;
import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryVO extends SearchVO {

    private Long id;
    private CateDvTy cateDvTy;
    private String categoryNm = "";
    private String categoryDsc;
    //SearchVO를 익스텐드 하여서 관계가 모호해 진 변수
    //private String regPsid;
    private LocalDateTime regDtm;
    private String updPsId;
    private LocalDateTime updDtm;
    private String delAt;

}
