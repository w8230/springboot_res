package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.MemberRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRollRepository extends JpaRepository<MemberRoll, Long> {

    List<MemberRoll> findAllByMberPid(Long mberPid);
}
