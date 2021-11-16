package kr.co.team.res.service.web;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.enums.UserRollType;
import kr.co.team.res.domain.repository.MemberRepository;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService extends BaseService {

    private final JPAQueryFactory queryFactory;
    private final MemberRepository memberRepository;
    private final PartnersRepository partnersRepository;

    /*public boolean insert(MemberVO memberVO ) throws ValidCustomException {
        try {
            verifyDuplicateLoginId(memberVO.getLoginId());
            //Controller에서 인증로직 수행 후 Service에서 인증로직 검토 -> insert 수행
            if()

            return true;
        } catch (ValidCustomException ve) {
            return ve;
        } catch (Exception e){
            return false;
        }
    }*/

    /*//DvTy Chk
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
*/
}
