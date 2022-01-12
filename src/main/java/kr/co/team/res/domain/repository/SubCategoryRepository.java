package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

    Optional<SubCategory> findBySubcategoryNm(String subcategoryNm);

    @Modifying
    @Query("UPDATE SubCategory sc " +
            "SET sc.delAt = :yn , sc.updPsId = :updPsId , sc.updDtm= :updDtm , sc.categoryPid = 0L " +
            " WHERE  sc.id = :id")
    int setDelAt(Long id , String yn , String updPsId , LocalDateTime updDtm );

    int countByCategoryPid(Long id);

}
