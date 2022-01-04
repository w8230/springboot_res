package kr.co.team.res.domain.enums;

public enum CateDvTy {

    MAIN("메인카테고리"),
    SUB("서브카테고리");

    final private String name;

    public String getName() {
        return name;
    }

    private CateDvTy(String name){
        this.name = name;
    }
}

