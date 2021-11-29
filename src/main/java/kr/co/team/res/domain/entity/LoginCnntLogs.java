package kr.co.team.res.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_login_cnnt_logs")
@IdClass(kr.co.team.res.domain.entity.LoginCnntLogsKey.class)
public class LoginCnntLogs {

    @Id
    @Column(name="cnct_dtm")
    private LocalDateTime cnctDtm;

    @Id
    @Column(name="cnct_id")
    private String cnctId;


    @Column(name="succes_at")
    private String succesAt;

    @Column(name="cnct_ip")
    private String cnctIp;

    @Column(name="fail_cnt")
    private int failCnt;

    @Column(name="rsn")
    private String rsn;

}

