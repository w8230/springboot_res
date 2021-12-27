package kr.co.team.res.service.web.admin.operation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.QAccount;
import kr.co.team.res.domain.entity.QPartners;
import kr.co.team.res.domain.repository.FileInfoRepository;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
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

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminMemberService extends _BaseService {
    private final JPAQueryFactory queryFactory;
    //private final MemberRepository memberRepository;
    //private final PartnersRepository partnersRepository;
    //private final ModelMapper modelMapper;
    //private final FileInfoRepository fileInfoRepository;

    public Page<Account> list(Pageable pageable , SearchVO searchVO , MemberVO memberVO ) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page, (searchVO.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchVO.getPageSize()));

        QAccount qAccount = QAccount.account;
        QPartners qPartners = QPartners.partners;

        OrderSpecifier<Long> orderSpecifier = qAccount.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAccount.delAt.eq("N"));


        QueryResults<Account> memberList = queryFactory
                .select(Projections.fields(Account.class,
                        qAccount.id,
                        qAccount.loginId,
                        qAccount.pwd,
                        qAccount.nm,
                        qAccount.ncnm,
                        qAccount.sexPrTy,
                        qAccount.mberDvTy,
                        qAccount.moblphon,
                        qAccount.email,
                        qAccount.zip,
                        qAccount.adres,
                        qAccount.dtlAdres,
                        qAccount.regDtm,
                        qAccount.approval))
                .from(qAccount)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        return new PageImpl<>(memberList.getResults() , pageable , memberList.getTotal());
    }
}