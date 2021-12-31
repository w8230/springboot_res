package kr.co.team.res.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_surgery")
@DynamicUpdate
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surgery_pid")
    private Long id;

    @Column(name = "partners_pid")
    private String partnersPid;

    @Column(name = "surgery_nm")
    private String surgeryNm;

    @Column(name = "surgery_dsc")
    private String surgeryDsc;

    @Column(name = "surgery_time")
    private Long surgeryTime;

    @Column(name = "surgery_sn")
    private Integer surgery_sn;

    @Column(name = "reg_ps_id")
    private String regPsid;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "upd_ps_id")
    private String updPsId;

    @Column(name = "upd_dtm")
    private LocalDateTime updDtm;

    @Column(name = "del_at")
    private String delAt;

}
