package kr.co.team.res.domain.repository;

import kr.co.team.res.domain.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    @Modifying
    @Query("delete from FileInfo f where f.dataPid=:dataPid and f.tableNm=:tableNm and f.dvTy=:dvTy")
    void deleteByData(@Param("dataPid") Long dataPid
            , @Param("tableNm") String tableNm
            , @Param("dvTy") String dvTy);

    FileInfo findByFlPthAndChgFlNm(String fl_pth, String chg_fl_nm);
}
