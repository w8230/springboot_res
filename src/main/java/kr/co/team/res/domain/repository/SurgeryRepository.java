package kr.co.team.res.domain.repository;


import kr.co.team.res.domain.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

}
