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
@Table(name = "tbl_subcategory")
@DynamicUpdate
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_pid")
    private Long id;

    @Column(name = "category_pid")
    private Long categoryPid;

    @Column(name = "cate_dv_ty")
    private String cateDvTy;

    @Column(name = "subcategory_nm")
    private String subcategoryNm;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "reg_ps_id")
    private String regPsId;

    @Column(name = "upd_ps_id")
    private String updPsId;

    @Column(name = "upd_dtm")
    private LocalDateTime updDtm;

    @Column(name = "del_at")
    private String delAt;


}
