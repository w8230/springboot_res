package kr.co.team.res.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_menu_auth")
public class MenuAuth implements Serializable {

    @Id
    @Column(name = "menu_pid")
    private Long menuPid;

    @Id
    @Column(name="mber_pid")
    private Long mberPid;

    @Column(name="confm_at")
    private String confmAt;

}
