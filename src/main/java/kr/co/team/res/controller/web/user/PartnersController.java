package kr.co.team.res.controller.web.user;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.entity.Menu;
import kr.co.team.res.domain.vo.admin.MenuVO;
import kr.co.team.res.service.web.admin.system.CommonCodeService;
import kr.co.team.res.service.web.admin.system.MenuService;
import kr.co.team.res.service.web.user.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PartnersController extends BaseCont {
    private final CommonCodeService commonCodeService;
    private final PartnersService partnersService;
    private final MenuService menuService;

    @RequestMapping("/partners/register")
    public String partnersregisterpage() { return "pages/partners/partners_register"; }

    @RequestMapping("/partners/management")
    public String index(){ return "/pages/partners/management/index"; }

    @ResponseBody
    @PostMapping("/api/partners/menu/lnb")
    public List<Map<String, Object>> lnb(Model model,
                                         @CurrentUser Account account,
                                         HttpServletResponse response,
                                         RedirectAttributes redirect,
                                         @Value("${common.code.mnGbnCdPid}") Long mnGbnCdPid) throws Exception {

        MenuVO form = new MenuVO();
        form.setUserPid(account.getId());
        List<Menu> lnbs = menuService.lnbList(form);




        List<Map<String, Object>> lnbMaps = new ArrayList<>();
        Map<String, Object> lnbMap = null;

        List<CommonCode> menuGroupCodes = partnersService.menuListForUppCdPid(mnGbnCdPid);

        for(int i=0; i<menuGroupCodes.size(); i++) {
            log.info(menuGroupCodes.get(i).getCodeNm());
        }

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
