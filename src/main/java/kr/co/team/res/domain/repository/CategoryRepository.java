package kr.co.team.res.domain.repository;

import com.querydsl.core.QueryResults;
import kr.co.team.res.domain.entity.Category;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Optional<Category> findByCategoryNm(String categoryNm);
    Optional<Category> findById(Long id);

    /*@Query(value =  "SELECT category_pid , category_nm , cate_dv_ty  , reg_ps_id  , reg_dtm  FROM tbl_category " +
            "union all " +
            "select subcategory_pid , subcategory_nm , cate_dv_ty , reg_ps_id, reg_dtm from tbl_subcategory" , nativeQuery = true)
    Page<Category> allcategoryList(Pageable pageable);*/

    @Modifying
    @Query("UPDATE Category ca " +
            "SET ca.delAt = :yn , ca.updPsId = :updpsid , ca.updDtm= :upddtm WHERE  ca.id = :id")
    int setDelAt(Long id , String yn , String updpsid , LocalDateTime upddtm);
}
