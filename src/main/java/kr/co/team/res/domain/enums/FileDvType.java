package kr.co.team.res.domain.enums;

public enum FileDvType {
    ATTACH("첨부파일"),
    THUMB("썸네일"),
    MAINVOD("대표영상"),
    PC("PC배너"),
    MOBILE("모바일배너"),
    LICENSE("사업자등록증");

    final private String name;

    public String getName() {
        return name;
    }

    private FileDvType(String name){
        this.name = name;
    }
}
