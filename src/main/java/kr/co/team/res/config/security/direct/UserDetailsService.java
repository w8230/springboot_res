package kr.co.team.res.config.security.direct;

import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.MemberRoll;
import kr.co.team.res.domain.repository.CommonCodeRepository;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.MemberRollRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserDetailsService [유저 디테일 서비스]
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/08/17
**/
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberRollRepository memberRollRepository;

    private Logger log = LoggerFactory.getLogger(getClass());


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String loginId) {

        //boolean isEmail = loginId.indexOf("@") > 0 ? true : false;
        //boolean isChk = false;
        String msg = "error.BadCredentials";
        String unAuthMsg = "error.login.email.unAuth";
        String unParentAuthMsg = "error.login.parentEmail.unAuth";

        log.debug("**스프링시큐리터 회원정보조회 loadUserByUsername login_id:{}", loginId);

        Account user = memberRepository.findByLoginIdAndDelAt(loginId, "N");

        if(user == null){
            // InternalAuthenticationServiceException UsernameNotFoundException BadCredentialsException AuthenticationException
            //throw new InternalAuthenticationServiceException(msg);
            //불필요한 Exception 로그가 발생되어 로그인실패에 대한 오류로 변경처리함. 2020.02.20,
            throw new UsernameNotFoundException(msg);
        }

        List<MemberRoll> allByMberPid = memberRollRepository.findAllByMberPid(user.getId());

        /*
         * 이메일 인증은 이제 선택임으로 비활성화
         * 보호자 인증여부 임시 비활성화 -- 김재일 08.24
         * */
        // Account byLoginIdAndEmail = memberRepository.findByLoginIdAndEmail(user.getLoginId(), user.getEmail());

//        if(byLoginIdAndEmail == null || !byLoginIdAndEmail.getEmailAttcAt().equals("Y")){
//            throw new LockedException(unAuthMsg);
//        }
//        if(byLoginIdAndEmail == null){
//            throw new LockedException(unAuthMsg);
//        }

//        if(!byLoginIdAndEmail.getPrtctorAttcAt().equals("Y")){
//            throw new LockedException(unAuthMsg);
//        }

        Account account = Account.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .nm(user.getNm())
                .pwd(user.getPwd())
                .ncnm(user.getNcnm() == null || "".equals(user.getNcnm()) ? user.getNm() : user.getNcnm())
                .mberDvTy(allByMberPid.get(0).getMberDvTy())
                .authorites(allByMberPid)
                .moblphon(user.getMoblphon())
                .approval(user.getApproval())
                .build();

        return new UserDetails(account);
    }

}
