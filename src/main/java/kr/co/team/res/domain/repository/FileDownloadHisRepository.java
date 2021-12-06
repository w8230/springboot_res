package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.FileDownloadHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDownloadHisRepository extends JpaRepository<FileDownloadHis, Long> {
    void deleteByFlPid (Long flPid);
}
