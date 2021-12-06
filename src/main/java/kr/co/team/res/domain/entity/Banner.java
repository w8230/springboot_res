package kr.co.team.res.domain.entity;

import kr.co.team.res.domain.enums.BanDvTy;
import kr.co.team.res.domain.enums.LinkTargetType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_banner")
@DynamicUpdate
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_pid")
    private Long id;

    @Column(name="ban_nm")
    private String banNm;

    @Column(name="dsc")
    private String dsc;

    @Enumerated(EnumType.STRING)
    @Column(name="ban_dv_ty")
    private BanDvTy banDvTy;

    @Column(name="ban_link")
    private String banLink;

    @Enumerated(EnumType.STRING)
    @Column(name="link_trgt_ty")
    private LinkTargetType linkTrgtTy;

    @Column(name="st_ymd")
    private LocalDate stYmd;

    @Column(name="ed_ymd")
    private LocalDate edYmd;

    @Column(name = "reg_ps_id")
    private String regPsId;

    @Column(name="reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "upd_ps_id")
    private String updPsId;

    @Column(name = "upd_dtm")
    private LocalDateTime updDtm;

    @Column(name = "del_at")
    private String delAt;

    @Column(name = "use_at")
    private String useAt;

    @Transient
    private String regPsNm;
    @Transient
    private String pcFlNm;
    @Transient
    private String mobileFlNm;

}
