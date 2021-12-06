package kr.co.team.res.controller.web.admin.system;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.domain.entity.Account;
import org.apache.poi.util.StringUtil;
import kr.co.team.res.common.Constants;
import kr.co.team.res.common.utils.FileUtilHelper;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Banner;
import kr.co.team.res.domain.entity.FileInfo;
import kr.co.team.res.domain.enums.TableNmType;
import kr.co.team.res.domain.vo.admin.BannerVO;
import kr.co.team.res.domain.vo.common.FileInfoVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.admin.system.BannerService;
import kr.co.team.res.service.web.admin.system.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * BannerController [관리자 페이지 배너 컨트롤러]
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/08/09
 * 수정사항 : 전체 RequestMapping 추가
**/
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BannerController extends BaseCont {

    private final BannerService bannerService;
    private final FileInfoService fileInfoService;

    @RequestMapping("/banner/list")
    public String list(Model model,
                       @PageableDefault Pageable pageable,
                       @ModelAttribute SearchVO searchForm,
                       @ModelAttribute BannerVO bannerForm) {

        model.addAttribute("form", searchForm);

        Page<Banner> banners = bannerService.list(pageable, searchForm, bannerForm);
        model.addAttribute("banners", banners);
        model.addAttribute("banDvTy", bannerForm.getBanDvTy());

        model.addAttribute("mc", "inspection");
        return "/pages/admin/system/banner/list";
    }

    @GetMapping("/banner/detail/{id}")
    public String detail(Model model,
                         @PathVariable(name = "id") Long id,
                         @Value("${Globals.fileStoreUriPath}") String filepath) {

        Banner banner = bannerService.load(id);
        model.addAttribute("form", banner);

        model.addAttribute("banDvTy", banner.getBanDvTy());

        FileInfoVO fileInfoForm = new FileInfoVO();
        fileInfoForm.setDataPid(id);
        fileInfoForm.setTableNm(TableNmType.TBL_BANNER.name());
        List<FileInfo> fileList = fileInfoService.list(fileInfoForm);
        model.addAttribute("fileList", fileList);

        model.addAttribute("filePath", filepath+"/"+ Constants.FOLDERNAME_BANNER);

        return "/pages/admin/system/banner/detail";
    }

    @GetMapping("/banner/register")
    public String register(Model model,
                           @ModelAttribute BannerVO bannerForm) {

        model.addAttribute("form", bannerForm);
        model.addAttribute("imageAccept", StringUtil.join(",", ArrayUtils.addAll(FileUtilHelper.imageExt)));

        return "/pages/admin/system/banner/register";
    }

    @PostMapping(value = "/banner/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String registerProc(Model model,
                               @ModelAttribute BannerVO bannerForm,
                               @RequestParam(name = "attachImgFl", required = false) MultipartFile attachImgFl,
                               @RequestParam(name = "attachMoImgFl", required = false) MultipartFile attachMoImgFl,
                               @CurrentUser Account account,
                               HttpServletResponse response,
                               RedirectAttributes redirect) throws Exception {

        bannerForm.setRegDtm(LocalDateTime.now());
        bannerForm.setRegPsId(account.getLoginId());
        bannerForm.setUpdDtm(LocalDateTime.now());
        bannerForm.setUpdPsId(account.getLoginId());
        bannerForm.setDelAt("N");

        Banner banner = new Banner();
        if (bannerForm.getId() == null) {
            banner = bannerService.insert(bannerForm, attachImgFl, attachMoImgFl);
        } else {
            bannerService.update(bannerForm, attachImgFl, attachMoImgFl);
        }


        return "redirect:/admin/banner/list";
    }

    @GetMapping("/banner/modify/{id}")
    public String modify(Model model,
                         @PathVariable(name = "id") Long id) {

        Banner banner = bannerService.load(id);
        model.addAttribute("form", banner);
        model.addAttribute("banDvTy", banner.getBanDvTy());

        FileInfoVO fileInfoForm = new FileInfoVO();
        fileInfoForm.setDataPid(id);
        fileInfoForm.setTableNm(TableNmType.TBL_BANNER.name());
        List<FileInfo> fileList = fileInfoService.list(fileInfoForm);
        model.addAttribute("fileList", fileList);

        model.addAttribute("imageAccept", StringUtil.join(",", ArrayUtils.addAll(FileUtilHelper.imageExt)));

        return "/pages/admin/system/banner/modify";
    }

    @PostMapping("/banner/delete")
    public String delete(Model model,
                         @ModelAttribute BannerVO bannerForm,
                         @CurrentUser Account account) throws Exception {

        bannerForm.setUpdDtm(LocalDateTime.now());
        bannerForm.setUpdPsId(account.getLoginId());
        bannerForm.setDelAt("Y");
        bannerService.delete(bannerForm);

        return "redirect:/admin/banner/list";
    }
}
