package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.Menu;
import kr.co.team.res.domain.vo.admin.MenuVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.admin.CommonCodeService;
import kr.co.team.res.service.web.admin.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MenuController extends BaseCont {

    private final MenuService MenuService;
    private final CommonCodeService commonCodeService;

    @RequestMapping("/admin/system/menuList")
    public String list(Model model,
                       @ModelAttribute SearchVO form,
                       @Value("${common.code.mnGbnCdPid}") Long mnGbnCdPid) {

        model.addAttribute("form", form);

        List<CommonCode> menuGroupCodes = commonCodeService.menuListForUppCdPid(mnGbnCdPid);
        model.addAttribute("menuGroupCodes", menuGroupCodes);

        List<Menu> Menus = MenuService.list(form);
        model.addAttribute("mngMenus", Menus);

        model.addAttribute("mc", "menu");
        return "/pages/admin/system/menuList";
    }



    @ResponseBody
    @PostMapping("/api/menu/load")
    public Menu detail(Model model,
                          @RequestBody MenuVO form) {

        Menu load = MenuService.load(form);

        return load;
    }

    @PostMapping("/api/menu/save")
    public String registerProc(Model model,
                               @ModelAttribute MenuVO form,
                               @CurrentUser Account account,
                               HttpServletResponse response,
                               RedirectAttributes redirect) {

        form.setRegDtm(LocalDateTime.now());
        form.setUpdDtm(LocalDateTime.now());
        form.setRegPsId("master");
        form.setUpdPsId("master");
        form.setDelAt("N");

        if (form.getNewwinAt() == null) form.setNewwinAt("N");

        boolean result = false;

        if (form.getId() == null) {
            //form.setId(null);
            result = MenuService.insert(form);
        } else {
            result = MenuService.update(form);
        }

        redirect.addAttribute("srchMnGbnCdPid", form.getSrchMnGbnCdPid());
        return "redirect:/admin/system/menu/list";
    }

    @PostMapping("/api/menu/delete")
    public String delete(Model model,
                         @RequestParam(name = "id") Long[] ids,
                         @CurrentUser Account account,
                         HttpServletResponse response,
                         RedirectAttributes redirect) throws Exception {

        List<MenuVO> menuFormList = new ArrayList<MenuVO>();
        MenuVO form = new MenuVO();
        for (Long id : ids) {
            form.setId(id);
            menuFormList.add(form);
        }
        MenuService.delete(menuFormList);

        //redirect.addAttribute("srch_menu_group_cd_id", menu.getSrch_menu_group_cd_id());
        return "redirect:/admin/system/menu/list";
    }


    @ResponseBody
    @PostMapping("/api/menu/gnb")
    public List<Menu> gnb(Model model,
                             @CurrentUser Account account,
                             HttpServletResponse response,
                             RedirectAttributes redirect,
                             @Value("${common.code.mnGbnCdPid}") Long mnGbnCdPid) throws Exception {

        MenuVO form = new MenuVO();
        form.setMenuGroupCdPid(mnGbnCdPid);
        //form.setRegPsId(account.getId());
        //form.setUpdPsId(account.get());
        //form.setEmpno(account.getEmpno());
        List<Menu> gnbs = MenuService.gbnList(form);

        return gnbs;
    }

    @ResponseBody
    @PostMapping("/api/menu/lnb")
    public List<Map<String, Object>> lnb(Model model,
                                         @CurrentUser Account account,
                                         HttpServletResponse response,
                                         RedirectAttributes redirect,
                                         @Value("${common.code.mnGbnCdPid}") Long mnGbnCdPid) throws Exception {

        MenuVO form = new MenuVO();
        form.setUserPid(account.getId());
        List<Menu> lnbs = MenuService.lnbList(form);


        List<Map<String, Object>> lnbMaps = new ArrayList<>();
        Map<String, Object> lnbMap = null;

        List<CommonCode> menuGroupCodes = commonCodeService.menuListForUppCdPid(mnGbnCdPid);
        for (CommonCode menuGroupCode : menuGroupCodes) {
            lnbMap = new HashMap<>();
            lnbMap.put("menuNm", menuGroupCode.getCodeNm());
            List<Map<String, Object>> lnbSubMaps = new ArrayList<>();
            Map<String, Object> lnbSubMap = null;
            for (Menu lnb : lnbs) {
                if(menuGroupCode.getId().equals(lnb.getMenuGroupCdPid())){
                    lnbSubMap = new HashMap<>();
                    lnbSubMap.put("id", lnb.getId());
                    lnbSubMap.put("menuNm", lnb.getMenuNm());
                    lnbSubMap.put("menuUrl", lnb.getMenuUrl());

                    lnbSubMaps.add(lnbSubMap);
                }
            }

            if (lnbSubMaps.size() != 0) {
                lnbMap.put("lnbs", lnbSubMaps);

                lnbMaps.add(lnbMap);
            }
        }

        return lnbMaps;
    }
}
