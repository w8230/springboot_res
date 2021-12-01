package kr.co.team.res.service.web.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.MobileAuthLog;
import kr.co.team.res.domain.repository.MobileAuthLogRepository;
import kr.co.team.res.domain.vo.user.MobileAuthLogVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MobileAuthLogService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final MobileAuthLogRepository mobileAuthLogRepository;
    private final ModelMapper modelMapper;

    public MobileAuthLog load(Long id) {
        MobileAuthLog actionLog = mobileAuthLogRepository.findById(id).orElseGet(MobileAuthLog::new);

        return actionLog;
    }

    public MobileAuthLog load(MobileAuthLogVO form) {
        MobileAuthLog actionLog = mobileAuthLogRepository.findByDmnNoAndRspNoAndMbtlnum(form.getDmnNo(), form.getRspNo(), form.getMbtlnum());

        return actionLog;
    }

    /**
     * @param form
     * @return
     */
    @Transactional
    public boolean insert(MobileAuthLogVO form) {

        try {
            MobileAuthLog mobileAuthLog = modelMapper.map(form, MobileAuthLog.class);
            mobileAuthLog.setRegDtm(LocalDateTime.now());
            mobileAuthLogRepository.save(mobileAuthLog);

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
