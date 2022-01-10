package kr.co.team.res.service.web.admin.operation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.enums.CateDvTy;
import kr.co.team.res.domain.repository.CategoryRepository;
import kr.co.team.res.domain.repository.SubCategoryRepository;
import kr.co.team.res.domain.vo.admin.CategoryVO;
import kr.co.team.res.domain.vo.admin.SubCategoryVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService extends _BaseService {
    private final JPAQueryFactory queryFactory;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ModelMapper modelMapper;

    //MainCategory List
    public List<Category> mclist() {
        QCategory qCategory = QCategory.category;
        OrderSpecifier<Long> orderSpecifier = qCategory.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.delAt.eq("N"));

        List<Category> list = queryFactory
                .select(Projections.fields(Category.class,
                        qCategory.id,
                        qCategory.categoryNm,
                        qCategory.categoryDsc,
                        qCategory.updDtm,
                        qCategory.updPsId,
                        qCategory.delAt,
                        qCategory.regDtm,
                        qCategory.regPsid
                ))
                .from(qCategory)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();
        return list;
    }

    public Page<Category> list(Pageable pageable, SearchVO searchVO , CategoryVO categoryVO) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page , searchVO.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize());
        //네이티브 쿼리로 유니온 사용하여 시도 중
        /*Page<Category> list = categoryRepository.allcategoryList(pageable);*/

        QCategory qCategory = QCategory.category;
        OrderSpecifier<Long> orderSpecifier  = qCategory.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.delAt.eq("N"));
        QueryResults<Category> categoryList = queryFactory
                .select(Projections.fields(Category.class,
                        qCategory.id,
                        qCategory.categoryNm,
                        qCategory.cateDvTy,
                        qCategory.categoryDsc,
                        qCategory.updDtm,
                        qCategory.updPsId,
                        qCategory.delAt,
                        qCategory.regDtm,
                        qCategory.regPsid
                ))
                .from(qCategory)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        System.out.println(categoryList.getTotal());

        /*System.out.println(categoryList.getResults().get(1).getSubcategoryNm());
        System.out.println(categoryList.getResults().get(2).getSubcategoryNm());*/

        return new PageImpl<>(categoryList.getResults() , pageable , categoryList.getTotal());
    }
    public Category load(Long id){
        QCategory qCategory = QCategory.category;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.id.eq(id));

        Category category = queryFactory
                .select(Projections.fields(Category.class,
                qCategory.id,
                qCategory.categoryNm,
                qCategory.cateDvTy,
                qCategory.categoryDsc,
                qCategory.updDtm,
                qCategory.updPsId,
                qCategory.delAt,
                qCategory.regDtm,
                qCategory.regPsid))
                .from(qCategory)
                .where(builder)
                .fetchFirst();

        return category;
    }
    public List<SubCategory> subcategoryList(Long id) {
        QSubCategory qSubCategory = QSubCategory.subCategory;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSubCategory.categoryPid.eq(id));
        builder.and(qSubCategory.delAt.eq("N"));

        List<SubCategory> list = queryFactory
                .select(Projections.fields(SubCategory.class,
                        qSubCategory.id,
                        qSubCategory.subcategoryNm,
                        qSubCategory.cateDvTy,
                        qSubCategory.regPsId,
                        qSubCategory.regDtm,
                        qSubCategory.updDtm,
                        qSubCategory.updPsId))
                .from(qSubCategory)
                .where(builder)
                .fetch();
        return list;
    }
    /*public Page<Category> sublist(Pageable pageable, SearchVO searchVO , SubCategoryVO SubCategoryVO) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page , searchVO.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize());


        QSubCategory qSubCategory = QSubCategory.subCategory;
        OrderSpecifier<Long> orderSpecifier  = qSubCategory.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSubCategory.delAt.eq("N"));

        QueryResults<Category> categoryList = queryFactory
                .select(Projections.fields(Category.class,
                        qSubCategory.id,
                        qSubCategory.subcategoryNm,
                        qSubCategory.cateDvTy,
                        qSubCategory.updDtm,
                        qSubCategory.updPsId,
                        qSubCategory.delAt,
                        qSubCategory.regDtm,
                        qSubCategory.regPsId))
                .from(qSubCategory)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        return new PageImpl<>(categoryList.getResults() , pageable , categoryList.getTotal());
    }*/

    public boolean register(CategoryVO categoryVO , MemberVO memberVO , SubCategoryVO subCategoryVO)  throws ValidCustomException {

        try{
            if(categoryVO.getCateDvTy().equals(CateDvTy.MAIN)){
                verifyDuplicateCategoryNm(categoryVO.getCategoryNm());
                Category category = modelMapper.map(categoryVO , Category.class);
                category.setRegDtm(LocalDateTime.now());
                category.setRegPsid(memberVO.getLoginId());
                category.setCategoryNm(categoryVO.getCategoryNm());
                category.setDelAt("N");
                category.setCateDvTy("MAIN");
                log.info("메인카테고리 네임 값 확인 :: " + categoryVO.getCategoryNm());
                /*categoryRepository.save(category);*/
            } else if(categoryVO.getCateDvTy().equals(CateDvTy.SUB)) {
                verifyDuplicateSubCategoryNm(categoryVO.getCategoryNm());
                SubCategory subCategory = modelMapper.map(subCategoryVO , SubCategory.class);
                subCategory.setRegDtm(LocalDateTime.now());
                subCategory.setCategoryPid(subCategoryVO.getCategoryPid());
                subCategory.setRegPsId(memberVO.getLoginId());
                subCategory.setSubcategoryNm(categoryVO.getCategoryNm());
                subCategory.setDelAt("N");
                subCategory.setCateDvTy("SUB");
                /*subCategoryRepository.save(subCategory);*/
                log.info("서브카테고리 네임 값 확인 :: " + subCategoryVO.getSubcategoryNm());
                log.info("서브카테고리 pid 값 확인 :: " + subCategoryVO.getCategoryPid());
            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean verifyDuplicateCategoryNm(String categoryNm) {
        boolean result = false;
        //isPresent가 true 라면 result는 false;
        if(categoryRepository.findByCategoryNm(categoryNm).isPresent()) {
            result = false;
        } else if(!categoryRepository.findByCategoryNm(categoryNm).isPresent()) {
            result = true;
        }
        return result;
    }

    public boolean verifyDuplicateSubCategoryNm(String subCategoryNm) {
        boolean result = false;
        if(subCategoryRepository.findBySubcategoryNm(subCategoryNm).isPresent()){
            result = false;
        } else if(!subCategoryRepository.findBySubcategoryNm(subCategoryNm).isPresent()){
            result =  true;
        }
        return result;
    }
}
