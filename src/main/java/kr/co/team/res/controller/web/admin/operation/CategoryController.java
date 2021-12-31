package kr.co.team.res.controller.web.admin.operation;

import kr.co.team.res.controller.web.BaseCont;
import kr.co.team.res.domain.entity.Category;
import kr.co.team.res.domain.vo.admin.CategoryVO;
import kr.co.team.res.domain.vo.common.SearchVO;
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
        log.info("run Category List Cont");
        model.addAttribute("form" , searchVO);
        //카테고리 데이터 set
        Page<Category> categoryList = categoryService.list(pageable , searchVO , categoryVO);
        //카테고리 데이터 push
        model.addAttribute("list" , categoryList);



        return "pages/admin/operation/category/list";
    }

    @RequestMapping("/add")
    public String add(){
        return null;
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
