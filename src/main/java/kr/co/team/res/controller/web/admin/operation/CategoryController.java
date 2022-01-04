package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.entity.SubCategory;
import kr.co.team.res.domain.vo.admin.CategoryVO;
import kr.co.team.res.domain.vo.admin.SubCategoryVO;
import kr.co.team.res.domain.vo.common.SearchVO;
import kr.co.team.res.domain.vo.user.MemberVO;
import kr.co.team.res.service.web.admin.operation.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Access;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/operation/category")
public class CategoryController extends BaseCont {
    private final CategoryService categoryService;


    @RequestMapping("/list")
    public String list(Model model ,
                       @PageableDefault Pageable pageable ,
                       @ModelAttribute SearchVO searchVO ,
                       @ModelAttribute CategoryVO categoryVO) {
        //페이징 데이터
        model.addAttribute("form" , searchVO);
        //카테고리 데이터 set
        Page<Category> categoryList = categoryService.list(pageable , searchVO , categoryVO);
        //카테고리 데이터 push
        model.addAttribute("list" , categoryList);

        return "/pages/admin/operation/category/list";
    }
    @RequestMapping("/add")
    public String add(Model model ,
                      @ModelAttribute CategoryVO categoryVO ,
                      @ModelAttribute SubCategoryVO subCategoryVO,
                      @CurrentUser Account account) {


        List<Category> mclist = categoryService.mclist();
        model.addAttribute("mclist" , mclist);

        /*for(int i = 0; i < mclist.size(); i++) {
            System.out.println(mclist.get(i).getCategoryNm());
            System.out.println(mclist.get(i).getCategoryDsc());
            System.out.println(mclist.get(i).getCateDvTy());
            System.out.println(mclist.get(i).getDelAt());
            System.out.println(mclist.get(i).getRegDtm());

        }*/

        return "/pages/admin/operation/category/register";
    }

    @RequestMapping("/register")
    public String register(Model model ,
                           @ModelAttribute CategoryVO categoryVO ,
                           @ModelAttribute SubCategoryVO subCategoryVO,
                           @CurrentUser Account account){

        if(categoryVO.getCategoryNm().equals("") || categoryVO.getCategoryNm() == null || categoryVO.getCategoryNm().length() < 1) {
            model.addAttribute("altmsg" , "카테고리이름을 입력해주세요.");
            model.addAttribute("locurl" , "/pages/admin/operation/category/register");
            return "/message";
        }
        if(!categoryService.verifyDuplicateCategoryNm(categoryVO.getCategoryNm())) {
            model.addAttribute("altmsg" , "이미 등록된 카테고리 입니다.");
            model.addAttribute("locurl" , "/pages/admin/operation/category/register");
            return "/message";
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setLoginId(account.getLoginId());
        categoryService.register(categoryVO , memberVO, subCategoryVO);

        return "redirect:/admin/operation/category/list";
    }

    @RequestMapping("/del")
    public String del(){
        return null;
    }

    @RequestMapping("/modify")
    public String modify(){
        return null;
    }

    @RequestMapping("/detail")
    public String detail(){
        return null;
    }
}