package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.Menu;
import kr.co.team.res.domain.entity.MenuAuth;
import kr.co.team.res.domain.vo.admin.MenuAuthVO;
import kr.co.team.res.domain.vo.admin.MenuVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.admin.system.CommonCodeService;
import kr.co.team.res.service.web.admin.system.MenuAuthService;
import kr.co.team.res.service.web.admin.system.MenuService;
import kr.co.team.res.service.web.user.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuAuthController extends BaseCont {

    private final MenuService menuService;
    private final MenuAuthService menuAuthService;
    private final MemberService memberService;
    private final CommonCodeService commonCodeService;

    @RequestMapping("/admin/system/menuAuth")
    public String list(Model model,
                       @PageableDefault Pageable pageable,
                       @ModelAttribute SearchVO form,
                       @ModelAttribute MenuVO vo,
                       @Value("${common.code.mnGbnCdPid}") Long mnGbnCdPid) {

        model.addAttribute("form", vo);

        List<Account> members = memberService.listByAdminUser(form);
        model.addAttribute("members", members);

        List<Menu> mngMenus = menuService.list(vo);
        model.addAttribute("mngMenus", mngMenus);

        List<CommonCode> menuGroupCodes = commonCodeService.menuListForUppCdPid(mnGbnCdPid);
        model.addAttribute("menuGroupCodes", menuGroupCodes);

        model.addAttribute("mc", "menu");
        return "/pages/admin/system/menuAuth";
    }


    @ResponseBody
    @PostMapping("/api/menu/authList")
    public List<MenuAuth> authList(Model model,
                                   @RequestBody MenuAuthVO vo) {

        List<MenuAuth> list = menuAuthService.list(vo);

        return list;
    }

    @ResponseBody
    @RequestMapping("/api/menu/authSave")
    public String authSave(Model model,
                           HttpServletResponse response,
                           @RequestBody MenuAuthVO vo,
                           @CurrentUser Account account) {

        boolean result = false;
        if (vo.getConfmAt().equalsIgnoreCase("Y")) {
            result = menuAuthService.insert(vo);
        } else {
            result = menuAuthService.delete(vo);
        }

        String msg = "";
        if (result) {
            msg = "ok";
            response.setStatus(200);
        } else {
            msg = "fail";
            response.setStatus(401);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        return msg;
    }

    @ResponseBody
    @RequestMapping("/api/menu/authAllSave")
    public String authAllSave(Model model,
                                   @RequestBody MenuAuthVO vo,
                                   HttpServletResponse response,
                                   @CurrentUser Account account) {

        boolean result = false;

        result = menuAuthService.InsertToAll(vo);

        String msg = "fail";
        if (result) {
            msg = "ok";
            response.setStatus(200);
        } else {
            msg = "fail";
            response.setStatus(401);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //model.addAttribute("msg", msg);

        return msg;
    }
    @ResponseBody
    @RequestMapping("/api/menu/authAllDelete")
    public String authAllDelete(Model model,
                                   @RequestBody MenuAuthVO vo,
                                   HttpServletResponse response,
                                   @CurrentUser Account account) {

        boolean result = false;

        result = menuAuthService.deleteAll(vo);

        String msg = "fail";
        if (result) {
            msg = "ok";
            response.setStatus(200);
        } else {
            msg = "fail";
            response.setStatus(401);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //model.addAttribute("msg", msg);

        return msg;
    }
}
