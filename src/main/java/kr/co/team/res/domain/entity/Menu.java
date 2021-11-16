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
@Table(name="tbl_menu")
@DynamicUpdate
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_pid")
    private Long id;


    @Column(name="menu_nm")
    private String menuNm;

    @Column(name="menu_url")
    private String menuUrl;

    @Column(name="newwin_at")
    private String newwinAt;

    @Column(name="menu_group_cd_pid")
    private Long menuGroupCdPid;

    @Column(name="menu_sn")
    private int menuSn;


    @Transient
    private String menuGroupNm;

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
