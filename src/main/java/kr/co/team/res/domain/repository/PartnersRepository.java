package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Long> {

    Optional<Partners> findByMberPid(Long id);

    @Modifying
    @Query("UPDATE Partners pt SET pt.approval = :yn WHERE  pt.mberPid = :id")
    int setApproval(Long id , String yn);


}
