package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.common.annotation.CurrentUser;
import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Account;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.vo.admin.CategoryVO;
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

        return "pages/admin/operation/category/list";
    }

    @RequestMapping("/register")
    public String register(Model model ,
                           @ModelAttribute CategoryVO categoryVO ,
                           @CurrentUser Account account){
        /*Cont에서 찍은 로그
            1.Cont Run - getCategoryNm;
            2.length if - length();
            3.verification if Run - getCategory Nm
            4.Call Service - getLoginId

            총 4개
        */
        //터진 로그(컨트롤러 실행 확인로그 , 컨트롤러 실행 컨트롤러, 서비스 호출 로그)
        log.info("categoryNm log , cont :: " , categoryVO.getCategoryNm());

        if(categoryVO.getCategoryNm().equals("") || categoryVO.getCategoryNm() == null || categoryVO.getCategoryNm().length() < 1) {
            log.info("length if run" ,categoryVO.getCategoryNm().length());
            model.addAttribute("altmsg" , "카테고리이름을 입력해주세요.");
            model.addAttribute("locurl" , "/pages/admin/operation/category/register");
            return "/message";
        }
        //불리언으로 정한 서비스가 false 라면
        if(!categoryService.verifyDuplicateCategoryNm(categoryVO.getCategoryNm())) {
            log.info("if verifycation Run  :: " , categoryVO.getCategoryNm());

            model.addAttribute("altmsg" , "이미 등록된 카테고리 입니다.");
            model.addAttribute("locurl" , "/pages/admin/operation/category/register");
            return "/message";
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setLoginId(account.getLoginId());

        log.info("category register Call" , account.getLoginId());

        categoryService.register(categoryVO , memberVO);
        return "/pages/admin/operation/category/register";
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