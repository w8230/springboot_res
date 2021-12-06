package kr.co.team.res.domain.vo.admin;

import kr.co.team.res.domain.enums.BanDvTy;
import kr.co.team.res.domain.enums.LinkTargetType;
import kr.co.team.res.domain.vo.common.SearchVO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class BannerVO extends SearchVO {

    private Long id;
    private String banNm;
    private String dsc;
    @Enumerated(EnumType.STRING)
    private BanDvTy banDvTy;
    private String banLink;

    @Enumerated(EnumType.STRING)
    private LinkTargetType linkTrgtTy;
    private String stYmd;
    private String edYmd;
    private String regPsId;
    private LocalDateTime regDtm;
    private String updPsId;
    private LocalDateTime updDtm;
    private String delAt;
    private String useAt;

}
