package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.BoardMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMasterRepository extends JpaRepository<BoardMaster, Long> {

}
