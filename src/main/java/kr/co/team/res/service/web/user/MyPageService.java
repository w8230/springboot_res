package kr.co.team.res.service.web.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.MemberRollRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPageService extends _BaseService {

    private final MemberRepository memberRepository;
    private final PartnersRepository partnersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory queryFactory;

    public Account MemberInfo(long id){
        QAccount qAccount = QAccount.account;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAccount.id.eq(id));

        Account account = queryFactory.select(Projections.fields(Account.class,
                qAccount.loginId,
                qAccount.adres,
                qAccount.nm,
                qAccount.ncnm,
                qAccount.moblphon,
                qAccount.email,
                qAccount.brthdy,
                qAccount.sexPrTy))
                .from(qAccount)
                .where(builder)
                .fetchFirst();

        return account;
    }
}
