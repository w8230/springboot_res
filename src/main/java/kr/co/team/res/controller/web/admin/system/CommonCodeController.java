package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.domain.vo.admin.CommonCodeVO;
import kr.co.team.res.service.web.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommonCodeController extends Base {

    private final CommonCodeService commonCodeService;

    @RequestMapping("/admin/system/commoncode")
    public String CommonCode(){
        return "/pages/admin/system/commonCode";
    }

    @ResponseBody
    @PostMapping("/api/commonCode/tree")
    public List<Map<String, Object>> tree(Model model,
                                          @ModelAttribute CommonCode commonCode,
                                          @CurrentUser Account account) throws Exception {

        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String, Object> tmp = null;
        List<CommonCode> commonCodeList = commonCodeService.findAll();
        log.info(commonCodeList.get(0).getCodeNm() + "ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴ");
        for (CommonCode commonCodeVo2 : commonCodeList) {
            if (commonCodeVo2.getPrntCodePid() == null) {
                tmp = new HashMap<>();

                tmp.put("text", commonCodeVo2.getCodeNm());
                tmp.put("id", commonCodeVo2.getId());
                tmp.put("items", getMakeTree(commonCodeVo2.getId(), commonCodeList));
                log.info(tmp.toString());
                retList.add(tmp);
            }
        }
        return retList;
    }

    @ResponseBody
    @PostMapping("/api/soulGod/commonCode/load")
    public CommonCode load(Model model,
                           @RequestBody CommonCodeVO vo) {
        CommonCode byId = commonCodeService.findById(vo.getId());
        return byId;
    }

    @ResponseBody
    @PostMapping("/api/soulGod/commonCode/save")
    public String save(Model model,
                       @CurrentUser Account account,
                       @RequestBody CommonCodeVO commonCodeForm,
                       RedirectAttributes redirectAttributes) {

        commonCodeForm.setRegPsId(account.getLoginId());
        commonCodeForm.setRegDtm(LocalDateTime.now());
        commonCodeForm.setUpdPsId(account.getLoginId());
        commonCodeForm.setUpdDtm(LocalDateTime.now());
        commonCodeForm.setDelAt("N");

        boolean result = commonCodeForm.getId() == 0
                ? commonCodeService.insert(commonCodeForm) : commonCodeService.update(commonCodeForm);

        String msg = "fail";
        if (result) {
            msg = "ok";
        } else {
            msg = "fail";
        }
        return msg;
    }

    @ResponseBody
    @PostMapping("/api/soulGod/commonCode/delete")
    public String delete(Model model,
                         @CurrentUser Account account,
                         @RequestBody CommonCodeVO vo,
                         RedirectAttributes redirectAttributes) {

        if (vo.getId() == null) {
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다.");
            return "redirect:" + "/soulGod/systemCode/list";
        }

        vo.setUpdPsId(account.getLoginId());
        vo.setUpdDtm(LocalDateTime.now());
        vo.setDelAt("Y");

        boolean result = commonCodeService.delete(vo);

        String msg = "fail";
        if (result) {
            msg = "ok";
        } else {
            msg = "fail";
        }
        return msg;
    }

    @ResponseBody
    @PostMapping("/api/commonCode/listForUppCdPid")
    public List<CommonCode> listForUppCdPid(Model model,
                                            @RequestBody CommonCodeVO vo) {
        List<CommonCode> list = commonCodeService.menuListForUppCdPid(vo.getPrntCodePid());
        return list;
    }

    private List<Map<String, Object>> getMakeTree(Long uppCdPid, List<CommonCode> subList) {

        List<Map<String, Object>> retList = new ArrayList<>();
        Map<String, Object> tmp = null;
        try {

            for (CommonCode commonCodeVo2 : subList) {
                if (commonCodeVo2.getPrntCodePid() != null && commonCodeVo2.getPrntCodePid().equals(uppCdPid)) {
                    tmp = new HashMap<>();

                    tmp.put("text", commonCodeVo2.getCodeNm());
                    tmp.put("id", commonCodeVo2.getId());
                    tmp.put("items", getMakeTree(commonCodeVo2.getId(), subList));

                    retList.add(tmp);
                }
            }
        } catch (Exception e) {
            // LOGGER.error(e.getMessage());
            // LOGGER.error(e.getStackTrace());
        }

        return retList;
    }
}
