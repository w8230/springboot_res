package kr.co.team.res.service.web.admin.system;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.common.Constants;
import kr.co.team.res.common.utils.FileUtilHelper;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.enums.FileDvType;
import kr.co.team.res.domain.enums.TableNmType;
import kr.co.team.res.domain.repository.BannerRepository;
import kr.co.team.res.domain.repository.FileInfoRepository;
import kr.co.team.res.domain.vo.admin.BannerVO;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final BannerRepository bannerRepository;
    private final FileInfoRepository fileInfoRepository;
    private final ModelMapper modelMapper;

    public Page<Banner> list(Pageable pageable, SearchVO searchForm, BannerVO bannerForm) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, (searchForm.getPageSize() == null ? Constants.DEFAULT_PAGESIZE : searchForm.getPageSize())); // <- Sort 추가

        QBanner qbanner = QBanner.banner;
        QAccount qAccount = QAccount.account;
        QFileInfo qFileInfo = QFileInfo.fileInfo;

        OrderSpecifier<Long> orderSpecifier = qbanner.id.desc();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qbanner.delAt.eq("N"));

        if (bannerForm.getBanDvTy() != null) {
            builder.and(qbanner.banDvTy.eq(bannerForm.getBanDvTy()));
        }

        if (searchForm.getUseAt() != null && !searchForm.getUseAt().isEmpty()) {
            builder.and(qbanner.useAt.eq(searchForm.getUseAt()));
        }

        if (searchForm.getBanDvTy() != null) {
            builder.and(qbanner.banDvTy.eq(searchForm.getBanDvTy()));
        }

        if (searchForm.getSrchWord() != null && !searchForm.getSrchWord().isEmpty()) {
            builder.and(qbanner.banNm.like("%" + searchForm.getSrchWord() + "%"));
        }

        if (searchForm.getSrchDt() != null) {
            builder.and(qbanner.stYmd.loe(searchForm.getSrchDt()));
            builder.and(qbanner.edYmd.goe(searchForm.getSrchDt()));
        }

        QueryResults<Banner> mngList = queryFactory
                .select(Projections.fields(Banner.class,
                        qbanner.id,
                        qbanner.banNm,
                        qbanner.dsc,
                        qbanner.banDvTy,
                        qbanner.banLink,
                        qbanner.linkTrgtTy,
                        qbanner.stYmd,
                        qbanner.edYmd,
                        qbanner.regPsId,
                        qbanner.regDtm,
                        qbanner.updPsId,
                        qbanner.updDtm,
                        qbanner.delAt,
                        qbanner.useAt,
                        qAccount.nm.as("regPsNm"),
                        ExpressionUtils.as(
                                JPAExpressions.select(qFileInfo.chgFlNm)
                                        .from(qFileInfo)
                                        .where(qFileInfo.dataPid.eq(qbanner.id).and(qFileInfo.tableNm.eq(TableNmType.TBL_BANNER.name()).and(qFileInfo.dvTy.eq(FileDvType.PC.name())))),
                                "pcFlNm"),
                        ExpressionUtils.as(
                                JPAExpressions.select(qFileInfo.chgFlNm)
                                        .from(qFileInfo)
                                        .where(qFileInfo.dataPid.eq(qbanner.id).and(qFileInfo.tableNm.eq(TableNmType.TBL_BANNER.name()).and(qFileInfo.dvTy.eq(FileDvType.MOBILE.name())))),
                                "mobileFlNm")
                ))
                .from(qbanner)
                .innerJoin(qAccount).on(qbanner.regPsId.eq(qAccount.loginId))
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(orderSpecifier)
                .fetchResults();

        return new PageImpl<>(mngList.getResults(), pageable, mngList.getTotal());
    }
//
//    public List<Banner> list(SearchVO searchForm, BannerVO bannerForm) {
//
//        QBanner qbanner = QBanner.banner;
//        QAccount qAccount = QAccount.account;
//        QFileInfo qFileInfo = QFileInfo.fileInfo;
//
//        OrderSpecifier<Long> orderSpecifier = qbanner.id.desc();
//
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qbanner.delAt.eq("N"));
//
//        if (bannerForm.getBanDvTy() != null) {
//            builder.and(qbanner.banDvTy.eq(bannerForm.getBanDvTy()));
//        }
//
//        if (searchForm.getUseAt() != null && !searchForm.getUseAt().isEmpty()) {
//            builder.and(qbanner.useAt.eq(searchForm.getUseAt()));
//        }
//
//        if (searchForm.getSrchWord() != null && !searchForm.getSrchWord().isEmpty()) {
//            builder.and(qbanner.banNm.like("%" + searchForm.getSrchWord() + "%"));
//        }
//
//        if (searchForm.getSrchDt() != null) {
//            builder.and(qbanner.stYmd.loe(searchForm.getSrchDt()));
//            builder.and(qbanner.edYmd.goe(searchForm.getSrchDt()));
//        }
//
//        List<Banner> mngList = queryFactory
//                .select(Projections.fields(Banner.class,
//                        qbanner.id,
//                        qbanner.banNm,
//                        qbanner.dsc,
//                        qbanner.banDvTy,
//                        qbanner.banLink,
//                        qbanner.linkTrgtTy,
//                        qbanner.stYmd,
//                        qbanner.edYmd,
//                        qbanner.regPsId,
//                        qbanner.regDtm,
//                        qbanner.updPsId,
//                        qbanner.updDtm,
//                        qbanner.delAt,
//                        qbanner.useAt,
//                        qAccount.nm.as("regPsNm"),
//                        ExpressionUtils.as(
//                                JPAExpressions.select(qFileInfo.chgFlNm)
//                                        .from(qFileInfo)
//                                        .where(qFileInfo.dataPid.eq(qbanner.id).and(qFileInfo.tableNm.eq(TableNmType.TBL_BANNER.name()).and(qFileInfo.dvTy.eq(FileDvType.PC.name())))),
//                                "pcFlNm"),
//                        ExpressionUtils.as(
//                                JPAExpressions.select(qFileInfo.chgFlNm)
//                                        .from(qFileInfo)
//                                        .where(qFileInfo.dataPid.eq(qbanner.id).and(qFileInfo.tableNm.eq(TableNmType.TBL_BANNER.name()).and(qFileInfo.dvTy.eq(FileDvType.MOBILE.name())))),
//                                "mobileFlNm")
//                ))
//                .from(qbanner)
//                .innerJoin(qAccount).on(qbanner.regPsId.eq(qAccount.loginId))
//                .where(builder)
//                .orderBy(orderSpecifier)
//                .fetch();
//
//        return mngList;
//    }

    public Banner load(Long id) {
        Banner banner = bannerRepository.findById(id).orElseGet(Banner::new);

        return banner;
    }

    @Transactional
    public void delete(BannerVO bannerForm) {
        Banner mng = this.load(bannerForm.getId());

        mng.setUpdDtm(LocalDateTime.now());
        mng.setUpdPsId(bannerForm.getUpdPsId());
        mng.setDelAt(bannerForm.getDelAt());
    }

    @Transactional
    public Banner insert(BannerVO bannerForm, MultipartFile attachImgFl, MultipartFile attachMoImgFl) throws Exception {

        Banner banner = modelMapper.map(bannerForm, Banner.class);
        banner.setStYmd(LocalDate.parse(bannerForm.getStYmd(), DateTimeFormatter.ISO_DATE));
        banner.setEdYmd(LocalDate.parse(bannerForm.getEdYmd(), DateTimeFormatter.ISO_DATE));
        banner = bannerRepository.save(banner);

        if (attachImgFl != null) {
            FileInfo fileInfo = FileUtilHelper.writeUploadedFile(attachImgFl, Constants.FOLDERNAME_BANNER, FileUtilHelper.imageExt);
            fileInfo.setDataPid(banner.getId());
            TableNmType tblBoardData = TableNmType.TBL_BANNER;
            fileInfo.setTableNm(tblBoardData.name());
            fileInfo.setDvTy(FileDvType.PC.name());

            fileInfoRepository.save(fileInfo);
        }
        if (attachMoImgFl.isEmpty() == false) {
            FileInfo fileInfo = FileUtilHelper.writeUploadedFile(attachMoImgFl, Constants.FOLDERNAME_BANNER, FileUtilHelper.imageExt);
            fileInfo.setDataPid(banner.getId());
            TableNmType tblBoardData = TableNmType.TBL_BANNER;
            fileInfo.setTableNm(tblBoardData.name());
            fileInfo.setDvTy(FileDvType.MOBILE.name());

            fileInfoRepository.save(fileInfo);
        }

        return banner;
    }

    @Transactional
    public boolean update(BannerVO bannerForm, MultipartFile attachImgFl, MultipartFile attachMoImgFl) {

        Banner form = modelMapper.map(bannerForm, Banner.class);
        form.setStYmd(LocalDate.parse(bannerForm.getStYmd(), DateTimeFormatter.ISO_DATE));
        form.setEdYmd(LocalDate.parse(bannerForm.getEdYmd(), DateTimeFormatter.ISO_DATE));

        try {
            FileInfo fileInfo = new FileInfo();

            Banner banner = bannerRepository.findById(bannerForm.getId()).orElseGet(Banner::new);
            banner.setBanDvTy(form.getBanDvTy());
            banner.setBanNm(form.getBanNm());
            banner.setDsc(form.getDsc());
            banner.setBanLink(form.getBanLink());
            banner.setLinkTrgtTy(form.getLinkTrgtTy());
            banner.setStYmd(form.getStYmd());
            banner.setEdYmd(form.getEdYmd());
            banner.setUpdDtm(form.getUpdDtm());
            banner.setUpdPsId(form.getUpdPsId());
            banner.setUseAt(form.getUseAt());

            if (attachImgFl.isEmpty() == false) {
                fileInfo = FileUtilHelper.writeUploadedFile(attachImgFl, Constants.FOLDERNAME_BANNER, FileUtilHelper.imageExt);

                fileInfo.setDataPid(banner.getId());
                fileInfo.setTableNm(TableNmType.TBL_BANNER.name());
                fileInfo.setDvTy(FileDvType.PC.name());

                fileInfoRepository.deleteByData(fileInfo.getDataPid(), fileInfo.getTableNm(), fileInfo.getDvTy());
                fileInfoRepository.save(fileInfo);
            }


            if (attachMoImgFl.isEmpty() == false) {
                fileInfo = FileUtilHelper.writeUploadedFile(attachMoImgFl, Constants.FOLDERNAME_BANNER, FileUtilHelper.imageExt);
                fileInfo.setDataPid(banner.getId());
                fileInfo.setTableNm(TableNmType.TBL_BANNER.name());
                fileInfo.setDvTy(FileDvType.MOBILE.name());

                fileInfoRepository.deleteByData(fileInfo.getDataPid(), fileInfo.getTableNm(), fileInfo.getDvTy());
                fileInfoRepository.save(fileInfo);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
