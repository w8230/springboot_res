package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.LoginCnntLogs;
import kr.co.team.res.domain.entity.LoginCnntLogsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCnntLogsRepository extends JpaRepository<LoginCnntLogs, LoginCnntLogsKey> {
    void deleteByCnctId (String cnctId);
}
