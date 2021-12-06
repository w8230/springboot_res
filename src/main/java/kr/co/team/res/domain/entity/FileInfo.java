package kr.co.team.res.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_pid")
    private Long id;

    @Column(name = "dv_ty")
    private String dvTy;

    @Column(name = "fl_nm")
    private String flNm;

    @Column(name = "chg_fl_nm")
    private String chgFlNm;

    @Column(name = "fl_sz")
    private Long flSz;

    @Column(name = "fl_sn")
    private int flSn;

    @Column(name = "reg_dtm")
    private LocalDateTime regDtm;

    @Column(name = "data_pid")
    private Long dataPid;

    @Column(name = "table_nm")
    private String tableNm;

    @Column(name = "fl_extsn")
    private String flExtsn;

    @Column(name = "fl_pth")
    private String flPth;

    @Transient
    private Long downloadCnt;
}
