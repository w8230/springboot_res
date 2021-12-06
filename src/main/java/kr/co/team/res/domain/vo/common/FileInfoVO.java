package kr.co.team.res.domain.vo.common;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class FileInfoVO extends SearchVO{

    private Long id;
    private String flNm;
    private String chgFlNm;
    private Long flSz;
    private String dvTy;
    private int flSn;
    private LocalDateTime regDtm;
    private Long dataPid;
    private String tableNm;
}
