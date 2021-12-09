package kr.co.team.res.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TempPageController extends BaseCont{

    String _tempurl = "pages/_temppage/";
    //검색 리스트 페이지
    @RequestMapping("/_temppage/search_list")
    public String search_list() {
        return _tempurl + "search_list";
    }

    @RequestMapping("/_temppage/gh_write")
    public String gh_write(){
        return _tempurl + "gh_write";
    }

    @RequestMapping("/_temppage/gh_modify")
    public String gh_modify(){
        return _tempurl + "gh_modify";
    }
    @RequestMapping("/_temppage/inquiry_details")
    public String inquiry_details(){
        return _tempurl + "inquiry_details";
    }
    
}
