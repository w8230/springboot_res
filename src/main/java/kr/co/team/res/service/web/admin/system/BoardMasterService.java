package kr.co.team.res.service.web.admin.system;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.domain.entity.BoardMaster;
import kr.co.team.res.domain.entity.QAccount;
import kr.co.team.res.domain.entity.QBoardMaster;
import kr.co.team.res.domain.enums.BoardType;
import kr.co.team.res.domain.repository.BoardMasterRepository;
import kr.co.team.res.domain.vo.admin.BoardMasterVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardMasterService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final BoardMasterRepository campaignRepository;
    private final ModelMapper modelMapper;

    public Page<BoardMaster> list(Pageable pageable, SearchVO searchForm) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, Constants.DEFAULT_PAGESIZE); // <- Sort 추가

        QBoardMaster qBoardMaster = QBoardMaster.boardMaster;
        QAccount qAccount = QAccount.account;

        OrderSpecifier<Long> orderSpecifier = qBoardMaster.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBoardMaster.delAt.eq("N"));
        if (searchForm.getSrchGbn() != null && !searchForm.getSrchGbn().isEmpty()) {
            builder.and(qBoardMaster.bbsTy.eq(BoardType.valueOf(searchForm.getSrchGbn())));
        }
        if (searchForm.getSrchWord() != null && !searchForm.getSrchWord().isEmpty()) {
            builder.and(qBoardMaster.bbsNm.like("%" + searchForm.getSrchWord() + "%"));
        }

        QueryResults<BoardMaster> mngList = queryFactory
                .select(Projections.fields(BoardMaster.class,
                        qBoardMaster.id,
                        qBoardMaster.bbsNm,
                        qBoardMaster.bbsTy,
                        qBoardMaster.bbsUpendCn,
                        qBoardMaster.pwd,
                        qBoardMaster.regPsId,
                        qBoardMaster.regDtm,
                        ExpressionUtils.as(
                                JPAExpressions.select(qAccount.nm)
                                        .from(qAccount)
                                        .where(qAccount.loginId.eq(qBoardMaster.regPsId)),
                                "regPsNm")
                ))
                .from(qBoardMaster)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        return new PageImpl<>(mngList.getResults(), pageable, mngList.getTotal());
    }

    public BoardMaster load(Long id) {
        BoardMaster boardMaster = campaignRepository.findById(id).orElseGet(BoardMaster::new);

        return boardMaster;
    }

    @Transactional
    public void delete(BoardMasterVO boardMasterForm) {
        BoardMaster mng = this.load(boardMasterForm.getId());

        mng.setUpdDtm(LocalDateTime.now());
        mng.setUpdPsId(boardMasterForm.getUpdPsId());
        mng.setDelAt(boardMasterForm.getDelAt());
    }

    /**
     * @param boardMasterForm
     * @return
     */
    @Transactional
    public BoardMaster insert(BoardMasterVO boardMasterForm) throws Exception {

        BoardMaster boardMaster = modelMapper.map(boardMasterForm, BoardMaster.class);
        boardMaster = campaignRepository.save(boardMaster);

        return boardMaster;
    }

    @Transactional
    public boolean update(BoardMasterVO boardMasterForm) {

        try {
            BoardMaster boardMaster = campaignRepository.findById(boardMasterForm.getId()).orElseGet(BoardMaster::new);
            boardMaster.setId(boardMasterForm.getId());
            boardMaster.setBbsNm(boardMasterForm.getBbsNm());
            boardMaster.setBbsTy(boardMasterForm.getBbsTy());
            boardMaster.setBbsUpendCn(boardMasterForm.getBbsUpendCn());
            boardMaster.setSntncHead(boardMasterForm.getSntncHead());

            boardMaster.setUpdPsId(boardMasterForm.getUpdPsId());
            boardMaster.setUpdDtm(LocalDateTime.now());
            //return userRepository.save(account);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
