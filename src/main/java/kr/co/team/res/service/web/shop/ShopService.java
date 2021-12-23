package kr.co.team.res.service.web.shop;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.domain.entity.Menu;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.entity.QPartners;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService extends _BaseService {
    private final JPAQueryFactory queryFactory;
    private final PartnersRepository partnersRepository;


    // 샵 검색
    /*public Page<Partners> srchShoplist(Pageable pageable , SearchVO searchVO) {

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
    }*/

    public Page<Partners> searchShoplist(String srchWord ,
                                         Pageable pageable,
                                         SearchVO searchVO){

        int page = (pageable.getPageNumber() == 0 ) ? 0 : (pageable.getPageNumber() -1);
        //pageable = PageRequest.of(page , Constants.DEFAULT_PAGESIZE);
        pageable = PageRequest.of(page , (searchVO.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize()));

        QPartners qPartners = QPartners.partners;
        BooleanBuilder builder = new BooleanBuilder();
        //where
        builder.and(qPartners.DelAt.eq("N"));
        builder.and(qPartners.approval.eq("Y"));
        builder.and(qPartners.bnm.contains(srchWord));

        OrderSpecifier<Long> orderSpecifier = qPartners.id.desc();
        QueryResults<Partners> shoplist = queryFactory
                .select(Projections.fields(Partners.class,
                        qPartners.id,
                        qPartners.bnm,
                        qPartners.bno,
                        qPartners.thumnail,
                        qPartners.adres,
                        qPartners.DtlAdres))
                .from(qPartners)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        return new PageImpl<>(shoplist.getResults(), pageable, shoplist.getTotal());
    }
    /*public Page<Partners> list(Pageable pageable, SearchVO searchVO) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, (searchVO.getPageSize() == null
                ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize()));

        QPartners qPartners = QPartners.partners;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPartners.DelAt.eq("N"));
        builder.and(qPartners.approval.eq("Y"));

        if(searchVO.getSrchWord() != null ||
                !searchVO.getSrchWord().equals("")) {
            builder.and(qPartners.bnm.like("%" + searchVO.getSrchWord() + "%"));
        }

        OrderSpecifier<Long> orderSpecifier = qPartners.id.desc();
        JPAQuery<Partners> list = queryFactory
                .select(Projections.fields(Partners.class,
                        qPartners.id,
                        qPartners.bnm,
                        qPartners.bno,
                        qPartners.thumnail,
                        qPartners.adres,
                        qPartners.DtlAdres))
                .from(qPartners)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier);
        QueryResults<Partners> mngList = list.fetchResults();

        return new PageImpl<>(mngList.getResults(), pageable, mngList.getTotal());
    }*/

    // 샵 상세정보
    public List<Partners> findShopData(long id){

        QPartners qPartners = QPartners.partners;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPartners.id.eq(id));

        List<Partners> shopData = queryFactory
                .select(Projections.fields(Partners.class,
                        qPartners.id,
                        qPartners.bnm,
                        qPartners.tel,
                        qPartners.thumnail,
                        qPartners.adres,
                        qPartners.DtlAdres))
                .from(qPartners)
                .where(builder)
                .fetch();

        log.debug("Shop Data ===="+shopData);

        return shopData;
    }
}
