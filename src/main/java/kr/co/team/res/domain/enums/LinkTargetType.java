package kr.co.team.res.domain.enums;

public enum LinkTargetType {
    SELF("_self"), BLANK("_blank");

    final private String name;

    public String getName() {
        return name;
    }

    private LinkTargetType(String name){
        this.name = name;
    }
}
