package kr.co.team.res.service.web.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.entity.QCommonCode;
import kr.co.team.res.domain.entity.QPartners;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Projection;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartnersService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final PartnersRepository partnersRepository;
    private final PasswordEncoder passwordEncoder;

    public Partners list(PartnersVO partnersVO) {

        QPartners qPartners = QPartners.partners;
        OrderSpecifier<Long> orderSpecifier = qPartners.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        if(partnersVO.getMberPid() != null){
            builder.and(qPartners.mberPid.eq(partnersVO.getMberPid()));
        }
        if(partnersVO.getBnm() != null){

        }
        if(partnersVO.getAdres() != null){

        }
        Partners partnersList = queryFactory
                .select(Projections.fields(Partners.class,
                        qPartners.id,
                        qPartners.thumnail,
                        qPartners.bnm,
                        qPartners.bno,
                        qPartners.RegDtm,
                        qPartners.UpdDtm,
                        qPartners.approval,
                        qPartners.mberPid,
                        qPartners.DelAt,
                        qPartners.tel,
                        qPartners.adres,
                        qPartners.DtlAdres,
                        qPartners.zip,
                        qPartners.sectorDvTy))
                .from(qPartners)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetchFirst();
        return partnersList;
    }

    public List<CommonCode> menuListForUppCdPid(Long prntCodePid) {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        OrderSpecifier<Integer> orderSpecifier = qCommonCode.codeSno.asc();

        BooleanBuilder builder = new BooleanBuilder();

        for(Long i= 5L; i==8; i++){
            builder.and(qCommonCode.id.eq(i));
        }
        builder.and(qCommonCode.delAt.eq("N"));

        List<CommonCode> results = queryFactory
                .select(Projections.fields(CommonCode.class,
                        qCommonCode.id,
                        qCommonCode.prntCodePid,
                        qCommonCode.codeSno,
                        qCommonCode.codeNm,
                        qCommonCode.codeDsc,
                        qCommonCode.codeValue,
                        qCommonCode.regPsId,
                        qCommonCode.regDtm,
                        qCommonCode.updPsId,
                        qCommonCode.updDtm,
                        qCommonCode.delAt
                ))
                .from(qCommonCode)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();

        return results;
    }
}

