package kr.co.team.res.common;

public class Constants {

    /*페이징*/
    public static final int 	DEFAULT_PAGESIZE	= 9;
    public static final int 	DEFAULT_THUMBNAIL_PAGESIZE	= 6;
    public static final int 	DEFAULT_MAIN_NOTICE_PAGESIZE	= 6;
    public static final int 	DEFAULT_MAIN_NEWS_PAGESIZE	= 4;
    public static final int 	DEFAULT_PAGESIZE_3	= 3;
    public static final int 	DEFAULT_PAGESIZE_5	= 5;

    /*첨부파일*/
    public static final Long imageMaxFileSize = 1024 * 500L;       //이미지 업로드 최대 사이즈 (Byte)
    public static final Long bigImageMaxFileSize = 1024 * 1024 * 10L;    //큰 이미지 업로드 최대 사이즈 (Byte)
    public static final Long docMaxFileSize = 1024 * 1024 * 10L;    //문서 업로드 최대 사이즈 (Byte)
    public static final String allowExtFileImg = "jpg,jpeg,gif,png,bmp";
    public static final String allowExtFileDoc = "doc,xls,docx,xlsx,ppt,pptx,hwp,pdf";
    public static final String allowExtFileBusi = "jpg,jpeg,pdf";
    public static final String allowExtFile = "jpg,jpeg,gif,png,bmp,doc,xls,docx,xlsx,ppt,pptx,hwp,pdf";

    public static final String ROOT_FOLDER = "/upload/";
    public static final String FOLDERNAME_IMAGES = "images";
    public static final String FOLDERNAME_DOWNLOAD = "download";
    public static final String FOLDERNAME_CAMPAIGN = "campaign";
    public static final String FOLDERNAME_COURSE = "course";
    public static final String FOLDERNAME_COURSEITEM = "courseItem";
    public static final String FOLDERNAME_COURSETASTE = "courseTaste";
    public static final String FOLDERNAME_COURSEMASTERSEQ = "courseMasterSeq";
    public static final String FOLDERNAME_EVENT = "event";
    public static final String FOLDERNAME_EXPERIENCE = "experience";
    public static final String FOLDERNAME_BOARDDATA = "boardData";
    public static final String FOLDERNAME_LICENSE = "license";
    public static final String FOLDERNAME_ADVICE = "advice";
    public static final String FOLDERNAME_COMMENT = "comment";
    public static final String FOLDERNAME_EDITOR = "editor";
    public static final String FOLDERNAME_BANNER = "banner";
    public static final String FOLDERNAME_POSTSCRIPT = "postscript";


    /**
     * AES 암복호화 key
     */
    public static final String AESEncryptKey = "bluetreeelephant";

    /**
     * 초기 비밀번호
     */
    public static final String DEFAULT_PASSWORD = "btf123$%^";

    /**
     * 과정마스터 순번
     */
    public static Integer TasteCrsSn = 1;
    public static Integer priorSvySn = 2;
    public static Integer priorCrsSn = 3;
    public static Integer fieldCrsSn = 4;
    public static Integer afterCrsSn = 5;
    public static Integer afterSvySn = 6;
    public static Integer satisfSvySn = 7;

    /**
     * 사전검사 코드 배열
     */
    public static Long[] inspectionQuestionDvCodeList= {70L, 69L, 71L, 59L, 60L, 61L};

}