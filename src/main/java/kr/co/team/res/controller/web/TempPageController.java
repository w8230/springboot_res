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


    @RequestMapping("/_temppage/inquiry_details")
    public String inquiry_details(){
        return _tempurl + "inquiry_details";
    }

    //게시물 리스트
    @RequestMapping("/_temppage/gh_list")
    public String gh_list() {
        return _tempurl + "gh_list";
    }

    //게시물 작성
    @RequestMapping("/_temppage/gh_write")
    public String gh_write(){
        return _tempurl + "gh_write";
    }

    //게시물 상세보기
    @RequestMapping("/_temppage/gh_details")
    public String gh_details(){
        return _tempurl + "gh_details";
    }

    //게시물 수정
    @RequestMapping("/_temppage/gh_modify")
    public String gh_modify(){
        return _tempurl + "gh_modify";
    }
}
