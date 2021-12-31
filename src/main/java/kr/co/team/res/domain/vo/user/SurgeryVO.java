package kr.co.team.res.domain.vo.user;

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
public class SurgeryVO extends SearchVO {

    private Long id;
    private String partnersPid;
    private String surgeryNm;
    private String surgeryDsc;
    private Long surgeryTime;
    private Integer surgery_sn;
    private String regPsid;
    private LocalDateTime regDtm;
    private String updPsId;
    private LocalDateTime updDtm;
    private String delAt;
}
