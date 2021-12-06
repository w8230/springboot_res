package kr.co.team.res.domain.enums;

public enum BanDvTy {
    MAIN("메인배너"),
    CARD("카드뉴스");

    final private String name;

    public String getName() {
        return name;
    }

    private BanDvTy(String name){
        this.name = name;
    }
}
