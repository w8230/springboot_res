package kr.co.team.res.service.web.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.exceptions.ValidCustomException;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.repository.PartnersRepository;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartnersService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final PartnersRepository partnersRepository;
    private final PasswordEncoder passwordEncoder;


}

