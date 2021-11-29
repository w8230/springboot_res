package kr.co.team.res.domain.enums;

public enum LoginLogMessage {
    LOGINFAIL("로그인 실패"),
    LOGINSUCESS("로그인 성공"),
    INITPASSWORD("비밀번호 최초 변경"),
    REFRESHPASSWORD("비밀번호 초기화");

    private String value;
    public String getValue() {
        return this.value;
    }
    public String getName() {
        return value;
    }
    LoginLogMessage(String value) {
        this.value = value;
    }
}
