package kr.co.team.res.controller.web.shop;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Partners;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.service.web.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController extends BaseCont {

    private final ShopService shopService;

    String shopurl = "pages/shop/";

    //게시물 리스트
    @RequestMapping("list")
    public String gh_list() {
        return shopurl + "list";
    }

    //게시물 작성
    @RequestMapping("write")
    public String gh_write(){
        return shopurl + "write";
    }

    //게시물 상세보기
    @RequestMapping("details")
    public String gh_details(){
        return shopurl + "details";
    }

    //게시물 수정
    @RequestMapping("modify")
    public String gh_modify(){
        return shopurl + "modify";
    }

    /*@RequestMapping("/api/search/shoplist")
    public String srchShoplist(HttpServletRequest request ,
                               @PageableDefault Pageable pageable ,
                               @ModelAttribute SearchVO searchVO,
                               @CurrentUser Account account ,
                               Model model) {
        if(searchVO.getTotalSrchWord() == null
                || "".equals(searchVO.getTotalSrchWord())
                || searchVO.getTotalSrchWord().length() < 2) {
            String referrer = request.getHeader("Referer");
            model.addAttribute("altmsg" , "검색어를 두 글자 이상 입력해주세요.");
            model.addAttribute("locurl", "/index");
            return "/message";
        }



        return "/pages/shop/list";
    }*/

    @GetMapping("/api/search/shop")
    public String shopsrch(Model model,
                           HttpServletRequest request,
                           @PageableDefault Pageable pageable,
                           @ModelAttribute SearchVO searchVO) {

        if(searchVO.getSrchWord() == null || searchVO.getSrchWord().equals("") || searchVO.getSrchWord().length() < 2) {
            String referrer = request.getHeader("Referer");
            model.addAttribute("altmsg" , "검색어를 두 글자 이상 입력해주세요.");
            model.addAttribute("locurl", referrer);
            return "/message";
        } else {
            String srchWord = searchVO.getSrchWord();
            Page<Partners> shoplist = shopService.searchShoplist(srchWord , pageable);
            model.addAttribute("shoplist" , shoplist);
        }

        return "/pages/shop/list";
    }

    @GetMapping("/pages/shop/list/{id}")
    public String shopDetail(Model model,
                              @PathVariable("id") Long id
                            ){
        List<Partners> shopData = shopService.findShopData(id);

        if(shopData == null){
            log.debug("not shop data");
        }
        model.addAttribute("id",id);
        model.addAttribute("shopData",shopData);

        return shopurl + "details";
    }

}
