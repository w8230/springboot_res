package kr.co.team.res.domain.enums;

public enum UserRollType {
    MASTER("마스터"),
    ADMIN("관리자"),
    NORMAL("일반"),
    PARTNERS("협력사");

    final private String name;

    public String getName() {
        return name;
    }

    private UserRollType(String name){
        this.name = name;
    }
}