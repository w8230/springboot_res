package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.BoardData;
import kr.co.team.res.domain.entity.BoardMaster;
import kr.co.team.res.domain.vo.admin.BoardDataVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.admin.system.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BoardController extends BaseCont {
//
//    private final BoardDataService boardDataService;
//    private final CommonCodeService commonCodeService;
//    private final BoardMasterService boardMasterService;
//    // private final FileInfoService fileInfoService;
//    private final BoardTargetService boardTargetService;
//    // private final CommonCommentService commonCommentService;
//    // private final HashTagService hashTagService;
//
//    @RequestMapping("/soulGod/board/list/{mstPid}")
//    public String list(Model model,
//                       @PageableDefault Pageable pageable,
//                       @PathVariable(name = "mstPid") Long mstPid,
//                       @ModelAttribute SearchVO form,
//                       @Value("${common.code.eduDataCdPid}") Long eduDataCdPid) {
//
//        model.addAttribute("form", form);
//        model.addAttribute("mstPid", mstPid);
//        model.addAttribute("eduData", eduDataCdPid);
//
//        BoardMaster boardMaster = boardMasterService.load(mstPid);
//        model.addAttribute("boardMaster", boardMaster);
//
//        BoardDataVO vo = new BoardDataForm();
//        vo.setMstPid(mstPid);
//        Page<BoardData> boardDatas = boardDataService.list(pageable, form, vo);
//        model.addAttribute("boardDatas", boardDatas);
//
//
//        model.addAttribute("mc", "board");
//
//        return "/soulGod/board/list";
//    }
}
