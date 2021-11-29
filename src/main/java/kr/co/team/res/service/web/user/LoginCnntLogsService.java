package kr.co.team.res.service.web.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.LoginCnntLogs;
import kr.co.team.res.domain.entity.QLoginCnntLogs;
import kr.co.team.res.domain.repository.LoginCnntLogsRepository;
import kr.co.team.res.domain.vo.user.LoginCnntLogsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginCnntLogsService {

    private final JPAQueryFactory queryFactory;
    private final LoginCnntLogsRepository loginCnntLogsRepository;

    public LoginCnntLogs loginFailCnt(LoginCnntLogs loginCnntLogs) {

        QLoginCnntLogs qLoginCnntLogs = QLoginCnntLogs.loginCnntLogs;

        OrderSpecifier<LocalDateTime> orderSpecifier = qLoginCnntLogs.cnctDtm.desc();

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qLoginCnntLogs.cnctId.eq(loginCnntLogs.getCnctId()));

        LoginCnntLogs cntLoad = queryFactory
                .select(Projections.fields(LoginCnntLogs.class,
                                qLoginCnntLogs.cnctId,
                                qLoginCnntLogs.cnctIp,
                                qLoginCnntLogs.failCnt
                ))
                .from(qLoginCnntLogs)
                .where(builder)
                .orderBy(orderSpecifier)
                .limit(1L)
                .fetchOne();

        return cntLoad;
    }

    @Transactional
    public boolean insert(LoginCnntLogs loginCnntLogs) {
        try{
            loginCnntLogsRepository.save(loginCnntLogs);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean delete(LoginCnntLogsVO form) {
        try {
            loginCnntLogsRepository.deleteByCnctId(form.getCnctId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Long count(LoginCnntLogsVO LoginCnntLogsVO) {
        QLoginCnntLogs qLoginCnntLogs = QLoginCnntLogs.loginCnntLogs;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qLoginCnntLogs.cnctId.eq(LoginCnntLogsVO.getCnctId()));
        builder.and(qLoginCnntLogs.succesAt.eq("Y"));

        List<LoginCnntLogs> fetch = queryFactory
                .select(Projections.fields(LoginCnntLogs.class,
                        qLoginCnntLogs.cnctId
                ))
                .from(qLoginCnntLogs)
                .where(builder)
                .groupBy(Expressions.stringTemplate("date_format({0},{1})", qLoginCnntLogs.cnctDtm, ConstantImpl.create("%Y%m%d")))
                .fetch();

        return fetch == null ? 0 : fetch.stream().count();
    }

    public List<LoginCnntLogs> list(LoginCnntLogsVO LoginCnntLogsVO) {
        QLoginCnntLogs qLoginCnntLogs = QLoginCnntLogs.loginCnntLogs;

        OrderSpecifier<LocalDateTime> orderSpecifier = qLoginCnntLogs.cnctDtm.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qLoginCnntLogs.cnctId.eq(LoginCnntLogsVO.getCnctId()));
        builder.and(qLoginCnntLogs.succesAt.eq(LoginCnntLogsVO.getSuccesAt()));
        builder.and(Expressions.stringTemplate("date_format({0},{1})", qLoginCnntLogs.cnctDtm, ConstantImpl.create("%Y%m%d")).in(LoginCnntLogsVO.getYyyyMMddArr()));

        List<LoginCnntLogs> fetch = queryFactory
                .select(Projections.fields(LoginCnntLogs.class,
                        qLoginCnntLogs.cnctId,
                        qLoginCnntLogs.cnctDtm,
                        qLoginCnntLogs.succesAt,
                        qLoginCnntLogs.cnctIp,
                        qLoginCnntLogs.rsn
                ))
                .from(qLoginCnntLogs)
                .where(builder)
                .groupBy(Expressions.stringTemplate("date_format({0},{1})", qLoginCnntLogs.cnctDtm, ConstantImpl.create("%Y%m%d")))
                .orderBy(orderSpecifier)
                .fetch();

        return fetch;
    }

    public LoginCnntLogs lastLogin(LoginCnntLogsVO LoginCnntLogsVO) {
        QLoginCnntLogs qLoginCnntLogs = QLoginCnntLogs.loginCnntLogs;

        OrderSpecifier<LocalDateTime> orderSpecifier = qLoginCnntLogs.cnctDtm.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qLoginCnntLogs.cnctId.eq(LoginCnntLogsVO.getCnctId()));
        builder.and(qLoginCnntLogs.succesAt.eq(LoginCnntLogsVO.getSuccesAt()));

        LoginCnntLogs fetch = queryFactory
                .select(Projections.fields(LoginCnntLogs.class,
                        qLoginCnntLogs.cnctId,
                        qLoginCnntLogs.cnctDtm,
                        qLoginCnntLogs.succesAt,
                        qLoginCnntLogs.cnctIp,
                        qLoginCnntLogs.rsn
                ))
                .from(qLoginCnntLogs)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetchFirst();

        return fetch;
    }
}
