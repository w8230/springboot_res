package kr.co.team.res.service.web.admin;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.repository.CommonCodeRepository;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonCodeService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final CommonCodeRepository commonCodeRepository;

    public List<CommonCode> findAll() {
        return commonCodeRepository.findAllByDelAtOrderByCodeSno("N");
    }
}
