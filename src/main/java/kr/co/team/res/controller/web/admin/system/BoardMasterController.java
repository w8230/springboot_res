package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.BoardMaster;
import kr.co.team.res.domain.vo.admin.BoardMasterVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.admin.system.BoardMasterService;
import kr.co.team.res.service.web.admin.system.CommonCodeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardMasterController extends BaseCont {

    private final BoardMasterService boardMasterService;
    private final CommonCodeService commonCodeService;

    @RequestMapping("/admin/system/boardList")
    public String list(Model model,
                       @PageableDefault Pageable pageable,
                       @ModelAttribute SearchVO searchForm) {

        model.addAttribute("form", searchForm);
        Page<BoardMaster> boardMasters = boardMasterService.list(pageable, searchForm);
        model.addAttribute("boardMasters", boardMasters);

        model.addAttribute("mc", "boardMaster");
        return "/pages/admin/system/boardMaster/list";
    }

    @GetMapping("/admin/system/detail/{id}")
    public String detail(Model model,
                         @PathVariable(name = "id") Long id) {

        BoardMaster boardMaster = boardMasterService.load(id);
        String a = boardMaster.getSntncHead();
        model.addAttribute("form", boardMaster);

        return "/pages/admin/system/boardMaster/detail";
    }

    @GetMapping("/admin/system/boardRegister")
    public String register(Model model) {

        BoardMaster boardMaster = new BoardMaster();
        model.addAttribute("form", boardMaster);

        return "/pages/admin/system/boardMaster/register";
    }

    @PostMapping("/admin/boardMaster/register")
    public String registerProc(Model model,
                               @ModelAttribute BoardMasterVO boardMasterForm,
                               @CurrentUser Account account,
                               HttpServletResponse response,
                               RedirectAttributes redirect) throws Exception {

        boardMasterForm.setRegDtm(LocalDateTime.now());
        boardMasterForm.setRegPsId(account.getLoginId());
        boardMasterForm.setUpdDtm(LocalDateTime.now());
        boardMasterForm.setUpdPsId(account.getLoginId());
        boardMasterForm.setDelAt("N");

        BoardMaster boardMaster = boardMasterService.insert(boardMasterForm);

        return "redirect:/admin/system/boardList";
    }

    @GetMapping("/admin/boardMaster/modify/{id}")
    public String modify(Model model,
                         @PathVariable(name = "id") Long id) {

        BoardMaster boardMaster = boardMasterService.load(id);
        model.addAttribute("form", boardMaster);

        return "/pages/admin/system/boardMaster/modify";
    }

    @PostMapping("/admin/boardMaster/modify")
    public String modifyProc(Model model,
                             @ModelAttribute BoardMasterVO boardMasterForm,
                             @CurrentUser Account account,
                             HttpServletResponse response,
                             RedirectAttributes redirect) {

        boardMasterForm.setUpdDtm(LocalDateTime.now());
        boardMasterForm.setUpdPsId(account.getLoginId());

        boolean result = boardMasterService.update(boardMasterForm);
        return "redirect:/admin/system/detail/" + boardMasterForm.getId();
    }

    @PostMapping("/admin/boardMaster/delete")
    public String delete(Model model,
                         @ModelAttribute BoardMasterVO boardMasterForm,
                         @CurrentUser Account account) throws Exception {

        boardMasterForm.setUpdDtm(LocalDateTime.now());
        boardMasterForm.setUpdPsId(account.getLoginId());
        boardMasterForm.setDelAt("Y");
        boardMasterService.delete(boardMasterForm);

        return "redirect:/admin/system/boardList";
    }

}
