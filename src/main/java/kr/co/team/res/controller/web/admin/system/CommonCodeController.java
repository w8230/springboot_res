package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.CommonCode;
import kr.co.team.res.service.web.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        for (CommonCode commonCodeVo2 : commonCodeList) {
            if (commonCodeVo2.getPrntCodePid() == null) {
                tmp = new HashMap<>();

                tmp.put("text", commonCodeVo2.getCodeNm());
                tmp.put("id", commonCodeVo2.getId());
                tmp.put("items", getMakeTree(commonCodeVo2.getId(), commonCodeList));

                retList.add(tmp);
            }
        }
        return retList;
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
