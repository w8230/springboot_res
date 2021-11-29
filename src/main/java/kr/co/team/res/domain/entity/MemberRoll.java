package kr.co.team.res.domain.entity;

import kr.co.team.res.domain.enums.UserRollType;
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
@Table(name = "tbl_member_roll")
@DynamicUpdate
public class MemberRoll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_pid")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mber_dv_ty")
    private UserRollType mberDvTy;

    @Column(name = "reg_ps_id")
    private String regPsId;
    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "mber_pid")
    private Long mberPid;

}
