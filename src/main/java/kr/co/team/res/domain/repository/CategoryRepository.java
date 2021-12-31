package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Optional<Category> findByCategoryNm(String categoryNm);


}
