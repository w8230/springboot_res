package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Long> {

}
