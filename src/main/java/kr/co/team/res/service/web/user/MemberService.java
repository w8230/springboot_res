package kr.co.team.res.service.web.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.entity.QAccount;
import kr.co.team.res.domain.entity.QCommonCode;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.MemberVO;
import kr.co.team.res.domain.vo.PartnersVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PartnersRepository partnersRepository;


    public boolean insert(MemberVO memberVO) throws ValidCustomException {

        try {
            verifyDuplicateLoginId(memberVO.getLoginId());

            Account account = new Account();

            //Controller에서 인증로직 수행 후 Service에서 인증로직 검토 -> insert 수행

            account.setDelAt("N");
            account.setApproval("Y");
            account.setRegDtm(LocalDateTime.now());
            account.setLoginId(memberVO.getLoginId());
            account.setPwd(passwordEncoder.encode(memberVO.getPwd()));
            account.setNm(memberVO.getNm());
            account.setNcnm(memberVO.getNcnm());
            account.setMoblphon(memberVO.getMoblphon());
            account.setEmail(memberVO.getEmail());
            account.setBrthdy(memberVO.getBrthdy());
            account.setAdres(memberVO.getAdres());
            account.setDtlAdres(memberVO.getDtlAdres());
            account.setSexPrTy(memberVO.getSexPrTy());

            //RollType 구분 vo SET
            if(memberVO.getMberDvTy().equals(UserRollType.NORMAL)){
                account.setId(account.getId());
                account.setMberDvTy(UserRollType.NORMAL);
                memberRepository.save(account);

            } else if(memberVO.getMberDvTy().equals(UserRollType.PARTNERS)){



                account.setMberDvTy(UserRollType.PARTNERS);
                memberRepository.save(account);
            } else {
                log.info("== insert logic error ==");
                return false;
            }

            return true;
        } catch (ValidCustomException ve) {
            return false;
        } catch (Exception e){
            return false;
        }
    }
    //DvTy Chk
    public boolean chkDvTy(UserRollType dv){
        if(!UserRollType.NORMAL.equals(dv) && !UserRollType.PARTNERS.equals(dv) && !UserRollType.ADMIN.equals(dv)) {
            return true;
        } else {
            return false;
        }
    }
    //Verify Id
    public void verifyDuplicateLoginId(String loginId) {
        if(memberRepository.findByLoginId(loginId).isPresent()) {
            throw new ValidCustomException("이미 사용 중인 아이디입니다." , "loginId");
        }
    }

    //Verify Email
    public void verifyDuplicateEmail(String email) {
        if(memberRepository.findByEmail(email).isPresent()) {
            throw new ValidCustomException("이미 사용 중인 이메일입니다." , "email");
        }
    }

    public boolean existsByLoginId(String loginId) {
        Account account = memberRepository.findByLoginId(loginId).orElseGet(Account::new);
        return (account != null && account.getId() != null);
    }
    public boolean existsByEmail(String email) {
        Account account = memberRepository.findByEmail(email).orElseGet(Account::new);
        return (account != null && account.getId() != null);
    }
    public boolean existsSpace(String text) {
        if (text == null) return true;
        return text.contains(" ");
    }

    public List<Account> listByAdminUser(SearchVO vo) {

        QAccount qAccount = QAccount.account;
        QCommonCode qCommonCode = QCommonCode.commonCode;

        OrderSpecifier<Long> orderSpecifier = qAccount.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAccount.delAt.eq("N"));
        builder.and(qAccount.mberDvTy.eq(UserRollType.MASTER));
        builder.or(qAccount.mberDvTy.eq(UserRollType.ADMIN));

        List<Account> mngList = queryFactory
                .select(Projections.fields(Account.class,
                        qAccount.id,
                        qAccount.loginId, qAccount.nm, qAccount.mberDvTy,
                        qAccount.sexPrTy, qAccount.moblphon, qAccount.email, qAccount.adres,
                        qAccount.regDtm, qAccount.updDtm
                ))
                .from(qAccount)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();

        return mngList;
    }

}