package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findAllByPrntCodePid(Long prntCodePid);
    List<CommonCode> findAllByDelAtOrderByCodeSno(String delAt);
}
