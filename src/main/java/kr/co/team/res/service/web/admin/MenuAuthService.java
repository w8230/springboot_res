package kr.co.team.res.service.web.admin;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.team.res.domain.entity.MenuAuth;
import kr.co.team.res.domain.entity.QMenuAuth;
import kr.co.team.res.domain.repository.MenuAuthRepository;
import kr.co.team.res.domain.vo.admin.MenuAuthVO;
import kr.co.team.res.service.web._BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuAuthService extends _BaseService {

    private final JPAQueryFactory queryFactory;
    private final ModelMapper modelMapper;
    private final MenuAuthRepository menuAuthRepository;

    public List<MenuAuth> list(MenuAuthVO vo) {
        QMenuAuth qMenuAuth = QMenuAuth.menuAuth;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMenuAuth.mberPid.eq(vo.getMberPid()));

        List<MenuAuth> list = queryFactory
                .select(Projections.fields(MenuAuth.class,
                        qMenuAuth.menuPid,
                        qMenuAuth.mberPid,
                        qMenuAuth.confmAt
                ))
                .from(qMenuAuth)
                .where(builder)
                .fetch();

        return list;
    }

    @Transactional
    public boolean insert(MenuAuthVO vo) {
        try{
            // MenuAuth menuAuth = modelMapper.map(vo, MenuAuth.class);
            menuAuthRepository.save(vo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean delete(MenuAuthVO vo) {
        try{
            menuAuthRepository.delete(vo);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Transactional
    public boolean deleteAll(MenuAuthVO vo) {
        try{
            menuAuthRepository.deleteByMberPid(vo);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Transactional
    public boolean InsertToAll(MenuAuthVO vo) {
        try{
            //MngMenuAuth mngMenuAuth = modelMapper.map(mngMenuAuthForm, MngMenuAuth.class);
            menuAuthRepository.saveToAll(vo);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
