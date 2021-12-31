package kr.co.team.res.service.web.admin.operation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.entity.QCategory;
import kr.co.team.res.domain.repository.CategoryRepository;
import kr.co.team.res.domain.vo.admin.CategoryVO;
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

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService extends _BaseService {
    private final JPAQueryFactory queryFactory;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public Page<Category> list(Pageable pageable, SearchVO searchVO , CategoryVO categoryVO) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page , searchVO.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize());

        QCategory qCategory = QCategory.category;

        OrderSpecifier<Long> orderSpecifier  = qCategory.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.delAt.eq("N"));

        QueryResults<Category> categoryList = queryFactory
                .select(Projections.fields(Category.class,
                        qCategory.id,
                        qCategory.categoryNm,
                        qCategory.categoryDsc,
                        qCategory.updDtm,
                        qCategory.updPsId,
                        qCategory.delAt,
                        qCategory.regDtm,
                        qCategory.regPsid))
                .from(qCategory)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();


        return new PageImpl<>(categoryList.getResults() , pageable , categoryList.getTotal());
    }
    public boolean register(CategoryVO categoryVO , MemberVO memberVO)  throws ValidCustomException {
        try{

            verifyDuplicateCategoryNm(categoryVO.getCategoryNm());
            Category category = modelMapper.map(categoryVO , Category.class);
            category.setRegDtm(LocalDateTime.now());
            category.setRegPsid(memberVO.getLoginId());
            category.setCategoryNm(categoryVO.getCategoryNm());
            category.setDelAt("N");
            categoryRepository.save(category);

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
            /*throw new ValidCustomException("이미 존재하는 카테고리 입니다." , "categoryNm");*/
            result = false;
            //isPresent가 false라면 result는 true;
        } else if(!categoryRepository.findByCategoryNm(categoryNm).isPresent()) {
            result = true;
        }
        return result;
    }
}
