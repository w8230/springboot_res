package kr.co.team.res.service.web.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.MemberRollRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.domain.vo.user.PartnersVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
                qAccount.pwd,
                qAccount.nm,
                qAccount.sexPrTy,
                qAccount.mberDvTy,
                qAccount.moblphon,
                qAccount.email,
                qAccount.zip,
                qAccount.adres,
                qAccount.dtlAdres,
                qAccount.ncnm,
                qAccount.brthdy,
                qAccount.regDtm,
                qAccount.delAt))
                .from(qAccount)
                .where(builder)
                .fetchFirst();
        return account;
    }

    @Transactional
    public boolean infoModify(MemberVO memberVO) {
        try {
            Account account = memberRepository.findById(memberVO.getId()).orElse(new Account());

            account.setUpdDtm(LocalDateTime.now());
            account.setUpdPsId(memberVO.getLoginId());
            account.setSexPrTy(memberVO.getSexPrTy());
            account.setAdres(memberVO.getAdres());
            account.setDtlAdres(memberVO.getDtlAdres());
            account.setZip(memberVO.getZip());
            account.setNcnm(memberVO.getNcnm());
            account.setNm(memberVO.getNm());
            account.setMoblphon(memberVO.getMoblphon());
            account.setEmail(memberVO.getEmail());

            if (memberVO.getPwd() != "" || !memberVO.getPwd().equals("")){
                account.setPwd(passwordEncoder.encode(memberVO.getPwd()));
            } else {
                System.out.println("패스워드 수정 안함");
            }

            if(memberVO.getBrthdy() != "" || !memberVO.getBrthdy().equals("")){
                account.setBrthdy(memberVO.getBrthdy());
            } else {
                System.out.println("생년월일 수정 안함");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*@Transactional
    public boolean checkPwd(Long id , String pwd) {

        try {
            Account load = memberRepository.findById(id).orElseGet(Account::new);
            if(!passwordEncoder.matches(pwd , load.getPwd())){
                return false;
            }
            return true;

        }catch (Exception e) {
            return false;
        }
    }*/
}
