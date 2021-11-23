package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.vo.admin.MenuAuthVO;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class MenuAuthRepository {

    @PersistenceContext
    private EntityManager em;

    public void list(Long mberPid) {

        String qry = "select * from tbl_menu_auth where mber_pid = " + mberPid;
        Query query = em.createNativeQuery(qry);
        query.executeUpdate();

        //em.persist(mngMenuAuth);
    }
    public void save(MenuAuthVO vo) {

        String qry = "insert into tbl_menu_auth values (" + vo.getMenuPid()+ "," + vo.getMberPid() + ",'Y')";
        Query query = em.createNativeQuery(qry);
        query.executeUpdate();

    }
    public void delete(MenuAuthVO vo) {

        String qry = "delete from tbl_menu_auth where menu_pid = " + vo.getMenuPid()+ " and mber_pid=" + vo.getMberPid();
        Query query = em.createNativeQuery(qry);
        query.executeUpdate();

    }

    public void deleteByMberPid(MenuAuthVO vo) {

        String qry = "delete from tbl_menu_auth where mber_pid=" + vo.getMberPid();
        Query query = em.createNativeQuery(qry);
        query.executeUpdate();

    }

    public void saveToAll(MenuAuthVO vo) {
        String qry = "insert into tbl_menu_auth select menu_pid, " + vo.getMberPid() + ",'Y' from tbl_menu ";
        Query query = em.createNativeQuery(qry);
        query.executeUpdate();
    }
}
