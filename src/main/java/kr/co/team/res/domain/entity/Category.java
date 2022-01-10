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
@Table(name = "tbl_category")
@DynamicUpdate
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_pid")
    private Long id;

    @Column(name = "cate_dv_ty")
    private String cateDvTy;

    @Column(name = "category_nm")
    private String categoryNm;

    @Column(name = "category_dsc")
    private String categoryDsc;

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

    @Transient
    private Long categoryPid;

    @Transient
    private Long subcategoryPid;

    @Transient
    private String subcategoryNm;


}
