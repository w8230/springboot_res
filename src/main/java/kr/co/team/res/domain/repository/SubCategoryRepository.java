package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.entity.MobileAuthLog;
import kr.co.team.res.domain.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

    Optional<SubCategory> findBySubcategoryNm(String subcategoryNm);
    Optional<SubCategory> findByCategoryPid(Long categoryPid);


    int countByCategoryPid(Long id);

}
