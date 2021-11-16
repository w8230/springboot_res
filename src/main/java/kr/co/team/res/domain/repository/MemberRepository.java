package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.QAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<QAccount , Long> {
    QAccount findAllByLoginIdAndDelAt(String loginId , String delAt);

    Optional<QAccount> findByLoginId(String loginId);

    Optional<QAccount> findByEmail(String email);
}
