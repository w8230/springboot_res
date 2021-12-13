package kr.co.team.res.service.web.shop;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.domain.entity.Menu;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.entity.QPartners;
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

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService extends _BaseService {
    private final JPAQueryFactory queryFactory;

    // 샵 검색
    public Page<Partners> srchShoplist(Pageable pageable , SearchVO searchVO) {

        if(searchVO.getSrchWord() == null || searchVO.getSrchWord().equals("")){
            searchVO.setSrchWord("");
        }
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page, (searchVO.getPageSize() == null) ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize());

        QPartners qPartners = QPartners.partners;
        OrderSpecifier<Long> orderSpecifier = qPartners.id.desc();
        JPAQuery<Partners> list = queryFactory
                .select(Projections.fields(Partners.class,
                        qPartners.id, qPartners.mberPid, qPartners.bnm, qPartners.bno,
                        qPartners.adres, qPartners.DtlAdres, qPartners.zip, qPartners.tel,
                        qPartners.thumnail, qPartners.approval, qPartners.DelAt ,qPartners.thumnail,
                        qPartners.RegDtm, qPartners.UpdDtm, qPartners.sectorDvTy))
                .from(qPartners)
                .where(qPartners.DelAt.eq("N"))
                .limit(pageable.getPageNumber())
                .offset(pageable.getOffset());
        list.orderBy(orderSpecifier);
        QueryResults<Partners> mngList = list.fetchResults();
        return new PageImpl<>(mngList.getResults() , pageable , mngList.getTotal());
    }

    public Page<Partners> searchShoplist(String srchWord){

        return null;
    }
}
