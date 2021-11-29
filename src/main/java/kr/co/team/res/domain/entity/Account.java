package kr.co.team.res.domain.entity;

import kr.co.team.res.domain.enums.UserRollType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_member")
@DynamicUpdate
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mber_pid")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "nm")
    private String nm;

    @Column(name = "ncnm")
    private String ncnm;

    @Column(name = "brthdy")
    private String brthdy;

    @Column(name = "sex_pr_ty")
    private String sexPrTy;

    @Enumerated(EnumType.STRING)
    @Column(name = "mber_dv_ty")
    private UserRollType mberDvTy;

    @Column(name = "moblphon")
    private String moblphon;

    @Column(name = "email")
    private String email;

    @Column(name = "zip")
    private String zip;

    @Column(name = "adres")
    private String adres;

    @Column(name = "dtl_adres")
    private String dtlAdres;

    @Column(name = "upd_dtm")
    private LocalDateTime updDtm;

    @Column(name = "del_at")
    private String delAt;

    @Column(name = "email_attc_dtm")
    private LocalDateTime emailAttcDtm;

    @Column(name = "email_attc_at")
    private String emailAttcAt;

    @Column(name = "mobile_attc_dtm")
    private LocalDateTime mobileAttcDtm;

    @Column(name = "mobile_attc_at")
    private String mobileAttcAt;

    @Column(name = "approval")
    private String approval;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Transient
    private List<MemberRoll> authorites;
    @Builder
    public Account(Long id, String loginId, String nm, String pwd, String ncnm, LocalDateTime regDtm, LocalDateTime updDtm, UserRollType mberDvTy, List<MemberRoll> authorites, String moblphon, String approval) {

        this.id = id;
        this.loginId = loginId;
        this.nm = nm;
        this.pwd = pwd;
        this.ncnm = ncnm;
        this.regDtm = regDtm;
        this.updDtm = updDtm;
        this.mberDvTy = mberDvTy;
        this.authorites = authorites;
        this.moblphon = moblphon;
        this.approval = approval;
    }
}
