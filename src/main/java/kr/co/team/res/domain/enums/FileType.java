package kr.co.team.res.domain.enums;


import kr.co.team.res.common.Constants;

public enum FileType {

    PROFILE_IMG(Constants.imageMaxFileSize, Constants.allowExtFileImg, 50, 1024, 50, 1024),
    EXHIBITION_POSTER(Constants.imageMaxFileSize, Constants.allowExtFileImg, 480, 480, 605, 605),
    EXHIBITION_MAIN(Constants.bigImageMaxFileSize, Constants.allowExtFileImg, 1080, 1080, -1, -1),
    SUBEVENT_THUMB(Constants.imageMaxFileSize, Constants.allowExtFileImg, 480, 480, 605, 605),
    SUBEVENT_POSTER(Constants.bigImageMaxFileSize, Constants.allowExtFileImg, 1080, 1080, -1, -1),
    COUPON_IMG(Constants.imageMaxFileSize, Constants.allowExtFileImg, 480, 480, 605, 605),
    COUPON_THUMB(Constants.imageMaxFileSize, Constants.allowExtFileImg, 480, 480, 605, 605),
    COUPON_DESC(Constants.bigImageMaxFileSize, Constants.allowExtFileImg, 1200, 1200, -1, -1),
    COMMON_DOC(Constants.docMaxFileSize, Constants.allowExtFileDoc, 0, 0, 0, 0),
    BUSI_FILE(Constants.docMaxFileSize, Constants.allowExtFileBusi, 0, 0, 0, 0);

    private Long fileMaxSize;       //파일 최대 사이즈 (MB)
    private String allowExt;        //허용 확장자 ('.'제외 확장자만 ','로 구별)
    private Integer widthMin;       //가로 최소 (px)
    private Integer widthMax;       //가로 최대 (px)
    private Integer heightMin;      //세로 최소 (px)
    private Integer heightMax;      //세로 최대 (px)

    FileType(Long fileMaxSize, String allowExt, Integer widthMin, Integer widthMax, Integer heightMin, Integer heightMax) {
        this.fileMaxSize = fileMaxSize;
        this.allowExt = allowExt;
        this.widthMin = widthMin;
        this.widthMax = widthMax;
        this.heightMin = heightMin;
        this.heightMax = heightMax;
    }

    public Long getFileMaxSize() {
        return this.fileMaxSize;
    }
    public String getAllowExt() {
        return this.allowExt;
    }
    public Integer getWidthMin() {
        return this.widthMin;
    }
    public Integer getWidthMax() {
        return this.widthMax;
    }
    public Integer getHeightMin() {
        return this.heightMin;
    }
    public Integer getHeightMax() {
        return this.heightMax;
    }
}
