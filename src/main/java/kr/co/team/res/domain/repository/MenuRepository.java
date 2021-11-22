package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByDelAt(String delAt);
}
