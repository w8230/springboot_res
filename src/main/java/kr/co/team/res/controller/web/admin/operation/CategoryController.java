package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.*;
import kr.co.team.res.domain.enums.CateDvTy;
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
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
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
        log.info("run list");
        model.addAttribute("form" , searchVO);
        Page<Category> categoryList = categoryService.list(pageable , searchVO , categoryVO);
        model.addAttribute("list" , categoryList);


        return "/pages/admin/operation/category/list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable(name = "id") Long id) {
        Category category = categoryService.load(id);
        model.addAttribute("form" , category);

        List<SubCategory> list = categoryService.subcategoryList(id);
        model.addAttribute("sub" , list);
        return "/pages/admin/operation/category/detail";
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
                           @CurrentUser Account account) {

        if(categoryVO.getCateDvTy().equals(CateDvTy.MAIN)){
            if(categoryVO.getCategoryNm().equals("") || categoryVO.getCategoryNm() == null) {
                model.addAttribute("altmsg" , "메인 카테고리이름을 입력해주세요.");
                model.addAttribute("locurl" , "/pages/admin/operation/category/register");
                return "/message";
            }
        }
        if(categoryVO.getCateDvTy().equals(CateDvTy.SUB)){
            if(subCategoryVO.getSubcategoryNm().equals("") || subCategoryVO.getSubcategoryNm() == null) {
                model.addAttribute("altmsg" , "서브 카테고리이름을 입력해주세요.");
                model.addAttribute("locurl" , "/pages/admin/operation/category/register");
                return "/message";
            }
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

    @RequestMapping("/main/delete/{id}")
    public String delete(Model model,
                         @ModelAttribute CategoryVO categoryVO,
                         @PathVariable(name = "id") Long id ,
                         @CurrentUser Account account) throws Exception {

        if(!categoryService.existsBySubCategory(id)) {
            model.addAttribute("altmsg" , "서브카테고리가 존재하는 메인카테고리는 삭제할 수 없습니다.");
            model.addAttribute("locurl" , "/admin/operation/category/detail/"+id);

        } else if(categoryService.existsBySubCategory(id)) {
            categoryVO.setCateDvTy(CateDvTy.MAIN);
            categoryVO.setUpdDtm(LocalDateTime.now());
            categoryVO.setUpdPsId(account.getLoginId());
            categoryVO.setDelAt("Y");
            categoryService.delete(categoryVO);
            model.addAttribute("altmsg" , "메인카테고리가 삭제 되었습니다.");
            model.addAttribute("locurl" , "/admin/operation/category/list");
        }

        /*
        if(categoryVO.getCateDvTy().equals(CateDvTy.SUB)) {
        0111 메모 참고하여 구현할 것.
        위에 if문 필요 없음 컨트롤러 새로 만들거임
        categoryVO.setCateDvTy(CateDvTy.SUB);
        categoryVO.setUpdDtm(LocalDateTime.now());
        categoryVO.setUpdPsId(account.getLoginId());
        categoryVO.setDelAt("Y");
        categoryService.delete(categoryVO);
        model.addAttribute("altmsg" , "서브카테고리가 삭제 되었습니다.");
        model.addAttribute("locul" , "/pages/admin/operation/category/list" + id);
        return "/message";
        }*/

        return "/message";
    }
    @RequestMapping(value = "/sub/delete/{id}")
    public String subdelete(Model model,
                            @ModelAttribute CategoryVO categoryVO,
                            @PathVariable(name = "id") Long id ,
                            @CurrentUser Account account) throws Exception {

        categoryVO.setCateDvTy(CateDvTy.SUB);
        categoryVO.setUpdDtm(LocalDateTime.now());
        categoryVO.setUpdPsId(account.getLoginId());
        categoryVO.setDelAt("Y");
        categoryService.delete(categoryVO);

        model.addAttribute("altmsg" , "서브카테고리가 삭제 되었습니다.");
        model.addAttribute("locurl" , "/admin/operation/category/list" + id);

        return "/message";
    }

    @RequestMapping("/modify")
    public String modify(){
        return null;
    }


}