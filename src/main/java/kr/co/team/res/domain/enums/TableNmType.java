package kr.co.team.res.domain.enums;

public enum TableNmType {
    TBL_MEMBER ("회원정보"),
    TBL_MEMBER_SCHOOL ("학교정보"),
    TBL_MEMBER_PARENT ("부모연계"),
    TBL_MEMBER_TEACHER ("교원정보"),
    TBL_COUSE_HIS ("수강이력"),
    TBL_COURSE ("강좌정보"),
    TBL_COUSE_ITEM ("강좌차시"),
    TBL_COUSE_TASTE ("강좌맛보기"),
    TBL_COURSE_MASTER_SEQ ("강좌마스터pid"),
    TBL_COURSE_MASTER ("강좌마스터"),
    TBL_COURSE_TARGET ("강좌대상자"),
    TBL_EXPERIENCE ("체험정보"),
    TBL_ADVICE_REQUEST ("상담신청"),
    TBL_ADVICE_ANSWER ("상담답변"),
    TBL_EVENT ("행사정보"),
    TBL_CAMPAIGN ("캠페인정보"),
    TBL_SURVEY_TARGET ("설문대상자"),
    TBL_EXPERIENCE_TARGET ("체험대상자"),
    TBL_FILE_INFO ("첨부파일"),
    TBL_COMMON_CODE ("공통코드"),
    TBL_COMMON_COMMENT("공통댓글"),
    TBL_BOARD_MASTER ("게시판마스터"),
    TBL_MNG_MENU_AUTH ("메뉴별사용자권한"),
    TBL_MNG_INFO ("관리자정보"),
    TBL_MNG_MENU ("관리자메뉴"),
    TBL_BOARD_DATA ("게시판데이터"),
    TBL_STATISTIC_LOG ("접속정보"),
    TBL_LOGIN_CNNT_LOGS ("로그인접속로그"),
    TBL_PWD_HIS ("비밀번호변경이력"),
    TBL_WORK_LOG ("관리자작업이력"),
    TBL_SURVEY_ISDEPT ("설문수감부서"),
    TBL_SURVEY_QUESTION_ITEM ("설문문항"),
    TBL_SURVEY_RESPONSE_PERSON ("설문응답자"),
    TBL_SURVEY ("설문관리"),
    TBL_SURVEY_RESPONSE ("설문응답"),
    TBL_SURVEY_ANSWER_ITEM ("설문답변"),
    TBL_BANNER ("배너"),
    TBL_POSTSCRIPT("교육후기"),
    TBL_POSTSCRIPT_IMAGE("교육후기이미지"),
    TBL_MEMBER_GROUP("사업자등록증");


    private String value;
    public String getValue() {
        return this.value;
    }
    TableNmType(String value) {
        this.value = value;
    }
}
