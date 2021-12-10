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


    //고객센터 질문 상세보기
    @RequestMapping("/_temppage/notice_details")
    public String notice_details() { return _tempurl + "service_center/detail_page/notice_details"; }
    //문의 상세보기
    @RequestMapping("/_temppage/inquiry_details")
    public String inquiry_details(){
        return _tempurl + "service_center/detail_page/inquiry_details";
    }
}
