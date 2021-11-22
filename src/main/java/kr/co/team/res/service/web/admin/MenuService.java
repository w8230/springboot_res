package kr.co.team.res.service.web.admin;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.repository.CommonCodeRepository;
import kr.co.team.res.domain.repository.MenuRepository;
import kr.co.team.res.domain.vo.admin.CommonCodeVO;
import kr.co.team.res.domain.vo.admin.MenuVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final ModelMapper modelMapper;
    private final MenuRepository menuRepository;

    public List<Menu> list(SearchVO form) {
        QMenu qMenu = QMenu.menu;
        QCommonCode qcommonCode = QCommonCode.commonCode;

        OrderSpecifier<Long> orderSpecifier = qMenu.menuGroupCdPid.asc();
        OrderSpecifier<Integer> orderSpecifier2 = qMenu.menuSn.asc();

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qMenu.delAt.eq("N"));
        if (form.getSrchMnGbnCdPid() != null){
            builder.and(qMenu.menuGroupCdPid.eq(form.getSrchMnGbnCdPid()));
        }
        if (form.getSrchWord() != null){
            builder.and(qMenu.menuNm.eq(form.getSrchWord()));
        }

        List<Menu> list = queryFactory
                .select(Projections.fields(Menu.class,
                        qMenu.id,
                        qMenu.menuNm,
                        qMenu.menuUrl,
                        qMenu.newwinAt,
                        qMenu.menuGroupCdPid,
                        qMenu.menuSn,
                        qMenu.regDtm,
                        qMenu.updPsId,
                        qMenu.updDtm,
                        qMenu.delAt,
                        ExpressionUtils.as(
                                JPAExpressions.select(qcommonCode.codeNm)
                                        .from(qcommonCode)
                                        .where(qcommonCode.id.eq(qMenu.menuGroupCdPid)
                                                .and(qcommonCode.delAt.eq("N"))),
                                "menuGroupNm")
                ))
                .from(qMenu)
                .where(builder)
                .orderBy(orderSpecifier)
                .orderBy(orderSpecifier2)
                .fetch();

        return list;
    }


    public Menu load(MenuVO form) {
        return menuRepository.findById(form.getId()).get();
    }

    @Transactional
    public boolean insert(MenuVO form) {
        try{
            Menu menu = modelMapper.map(form, Menu.class);
            menuRepository.save(menu);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean update(MenuVO form) {
        try{
            Menu Menu = menuRepository.findById(form.getId()).get();
            Menu.setMenuGroupCdPid(form.getMenuGroupCdPid());
            Menu.setMenuNm(form.getMenuNm());
            Menu.setMenuUrl(form.getMenuUrl());
            Menu.setNewwinAt(form.getNewwinAt());
            Menu.setMenuSn(form.getMenuSn());
            Menu.setUpdPsId(form.getUpdPsId());
            Menu.setUpdDtm(LocalDateTime.now());
            return true;
        }catch(Exception e){
            return false;
        }

    }

    @Transactional
    public boolean delete(List<MenuVO> menuFormList) {
        try{
            for(MenuVO form : menuFormList){
                Menu Menu = menuRepository.findById(form.getId()).get();
                Menu.setUpdPsId(form.getUpdPsId());
                Menu.setUpdDtm(LocalDateTime.now());
                Menu.setDelAt("Y");
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Menu> gbnList(MenuVO form) {
//        QMenu qMenu = QMenu.Menu;
        QCommonCode qcommonCode = QCommonCode.commonCode;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qcommonCode.delAt.eq("N"));
        builder.and(qcommonCode.prntCodePid.eq(form.getMenuGroupCdPid()));
        builder.and(qcommonCode.delAt.eq("N"));

        List<CommonCode> list = queryFactory
                .select(Projections.fields(CommonCode.class,
                        qcommonCode.id,
                        qcommonCode.codeNm,
                        qcommonCode.codeSno
                ))
                .from(qcommonCode)
                .where(builder)
                .fetch();

        List<Menu> MenuList = new ArrayList<>();

        for(int i = 0; i<list.size(); i++){
            Menu vo = new Menu();
            vo.setId(list.get(i).getId());
            vo.setMenuNm(list.get(i).getCodeNm());
            vo.setMenuSn(list.get(i).getCodeSno());
            MenuList.add(vo);
        }

        return MenuList;
    }

    public List<Menu> lnbList(MenuVO form) {
        QMenu qMenu = QMenu.menu;
        QMenuAuth qMenuAuth = QMenuAuth.menuAuth;
        QCommonCode qcommonCode = QCommonCode.commonCode;

        OrderSpecifier<Integer> orderSpecifier = qMenu.menuSn.asc();

        BooleanBuilder builder = new BooleanBuilder();

        if(form.getUserPid() != null){
            builder.and(qMenuAuth.mberPid.eq(form.getUserPid()));
        }
        builder.and(qMenu.delAt.eq("N"));

        List<Menu> list = queryFactory
                .select(Projections.fields(Menu.class,
                        qMenu.id,
                        qMenu.menuGroupCdPid,
                        qMenu.menuNm,
                        qMenu.menuUrl,
                        qMenu.newwinAt,
                        qMenu.menuSn,
                        qMenu.regPsId,
                        qMenu.regDtm,
                        qMenu.updPsId,
                        qMenu.updDtm,
                        qMenu.delAt,
                        qMenuAuth.mberPid,
                        ExpressionUtils.as(
                                JPAExpressions.select(qcommonCode.codeNm)
                                        .from(qcommonCode)
                                        .where(qcommonCode.id.eq(qMenu.menuGroupCdPid)
                                                .and(qcommonCode.delAt.eq("N"))),
                                "menuGroupNm")
                ))
                .from(qMenu)
                .leftJoin(qMenuAuth).on(qMenuAuth.menuPid.eq(qMenu.id))
                .where(builder)
                .orderBy(orderSpecifier)
                .fetch();

        return list;
    }

}
