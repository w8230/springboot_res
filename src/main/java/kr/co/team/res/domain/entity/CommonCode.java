package kr.co.team.res.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_common_code")
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_pid")
    private Long id;

    @Column(name = "code_sno")
    private int codeSno;

    @Column(name = "code_nm")
    private String codeNm;

    @Column(name = "code_dsc")
    private String codeDsc;

    @Column(name = "code_value")
    private String codeValue;

    @Column(name = "reg_ps_id")
    private String regPsId;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "upd_ps_id")
    private String updPsId;

    @Column(name = "upd_dtm")
    private LocalDateTime updDtm;

    @Column(name = "del_at")
    private String delAt;
}
