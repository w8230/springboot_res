package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.MemberRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Account, Long> {

    Account findAllByLoginIdAndDelAt(String loginId , String delAt);
    Account findByLoginIdAndDelAt(String loginId, String delAt);

    Optional<Account> findByLoginId(String loginId);

    Optional<Account> findByEmail(String email);
}
