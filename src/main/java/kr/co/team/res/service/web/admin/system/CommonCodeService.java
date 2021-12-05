package kr.co.team.res.service.web.admin.system;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.QCommonCode;
import kr.co.team.res.domain.repository.CommonCodeRepository;
import kr.co.team.res.domain.vo.admin.CommonCodeVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonCodeService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final ModelMapper modelMapper;
    private final CommonCodeRepository commonCodeRepository;

    public List<CommonCode> findAll() {
        return commonCodeRepository.findAllByDelAtOrderByCodeSno("N");
    }
    public CommonCode findById(Long id) {
        return commonCodeRepository.findById(id).orElseGet(CommonCode::new);
    }
    @Transactional
    public boolean insert(CommonCodeVO form) {
        try {
            CommonCode commonCode = modelMapper.map(form, CommonCode.class);
            commonCodeRepository.save(commonCode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean update(CommonCodeVO form) {
        try {
            CommonCode commonCode = commonCodeRepository.findById(form.getId()).get();
            commonCode.setPrntCodePid(form.getPrntCodePid());
            commonCode.setCodeSno(form.getCodeSno());
            commonCode.setCodeNm(form.getCodeNm());
            commonCode.setCodeDsc(form.getCodeDsc());
            commonCode.setCodeValue(form.getCodeValue());
            commonCode.setUpdPsId(form.getUpdPsId());
            commonCode.setUpdDtm(LocalDateTime.now());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean delete(CommonCodeVO form) {
        try {

            CommonCode commonCode = commonCodeRepository.findById(form.getId()).get();
            commonCode.setUpdPsId(form.getUpdPsId());
            commonCode.setUpdDtm(LocalDateTime.now());
            commonCode.setDelAt("Y");

            return true;
        } catch (Exception e) {
            return false;
        }
    }
// push test
    public List<CommonCode> menuListForUppCdPid(Long prntCodePid) {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        OrderSpecifier<Integer> orderSpecifier = qCommonCode.codeSno.asc();

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qCommonCode.prntCodePid.eq(prntCodePid));
        builder.and(qCommonCode.delAt.eq("N"));

        List<CommonCode> results = queryFactory
                .select(Projections.fields(CommonCode.class,
                        qCommonCode.id,
                        qCommonCode.prntCodePid,
                        qCommonCode.codeSno,
                        qCommonCode.codeNm,
                        qCommonCode.codeDsc,
                        qCommonCode.codeValue,
                        qCommonCode.regPsId,
                        qCommonCode.regDtm,
                        qCommonCode.updPsId,
                        qCommonCode.updDtm,
                        qCommonCode.delAt
                ))
                .from(qCommonCode)
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();

        return results;
    }
}
