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
@Table(name = "tbl_mobile_auth_log")
@DynamicUpdate
public class MobileAuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attc_pid")
    private Long id;

    @Column(name = "decr_hr")
    private String decrHr;
    @Column(name = "dmn_no")
    private String dmnNo;
    @Column(name = "rsp_no")
    private String rspNo;
    @Column(name = "attc_mns")
    private String attcMns;
    private String nm;
    @Column(name = "dup_sbscrb_cfm_val")
    private String dupSbscrbCfmVal;
    @Column(name = "cnec_info_cfm_val")
    private String cnecInfoCfmVal;
    private String brthdy;
    private String sexdstn;
    @Column(name = "ise_frer_info")
    private String iseFrerInfo;
    private String mbtlnum;
    private String telecom;
    @Column(name = "failr_code")
    private String failrCode;
    private String mssage;
    @Column(name = "encr_data")
    private String encrData;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;
}
