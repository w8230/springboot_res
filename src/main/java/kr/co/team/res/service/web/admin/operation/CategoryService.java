package kr.co.team.res.service.web.admin.operation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.entity.QCategory;
import kr.co.team.res.domain.repository.CategoryRepository;
import kr.co.team.res.domain.vo.admin.CategoryVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService extends _BaseService {
    private final JPAQueryFactory queryFactory;
    private final CategoryRepository categoryRepository;

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
}
