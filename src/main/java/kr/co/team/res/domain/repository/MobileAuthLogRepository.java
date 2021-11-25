package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.MobileAuthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAuthLogRepository extends JpaRepository<MobileAuthLog, Long> {

    MobileAuthLog findByDmnNoAndRspNoAndMbtlnum(String dmnNo, String RspNo, String mbtlnum);
}
