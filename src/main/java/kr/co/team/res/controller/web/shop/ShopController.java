package kr.co.team.res.controller.web.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ShopController {

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
}
