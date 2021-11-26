package kr.co.team.res.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_partners")
@DynamicUpdate
public class Partners implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="partners_pid")
    private Long id;

    @Column(name="mber_pid")
    private Long mberPid;

    @Column(name = "bnm")
    private String bnm;

    @Column(name = "bno")
    private String bno;

    @Column(name = "thumnail")
    private String thumnail;

    @Column(name = "sector_dv_ty")
    private String sectorDvTy;

    @Column(name = "tel")
    private String tel;

    @Column(name = "zip")
    private String zip;

    @Column(name = "adres")
    private String adres;

    @Column(name = "dtl_adres")
    private String DtlAdres;

    @Column(name = "reg_dtm")
    private LocalDateTime RegDtm;

    @Column(name = "upd_dtm")
    private String UpdDtm;

    @Column(name = "del_at")
    private String DelAt;

    @Column(name = "approval")
    private String approval;

}
