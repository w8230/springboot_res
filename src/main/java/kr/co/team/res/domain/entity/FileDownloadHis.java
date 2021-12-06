package kr.co.team.res.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_file_download_his")
public class FileDownloadHis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dwld_his_pid")
    private Long id;

    @Column(name = "cnct_ip")
    private String cnctIp;

    @Column(name = "fl_pid")
    private Long flPid;

    @Column(name = "mber_pid")
    private Long mberPid;

}
