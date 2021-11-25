package kr.co.team.res.domain.vo;

import kr.co.team.res.domain.enums.UserRollType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberVO {
    private Long id;

    @NotNull(message = "아이디는 필수 값입니다.")
    @NotBlank(message = "아이디는 필수 값입니다.")
    @Pattern(regexp="^[a-z0-9]{6,12}$", message = "아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.")
    private String loginId;

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @NotBlank(message = "비밀번호는 필수 값입니다.")
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$", message = "비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.")
    private String pwd;
    private String pwdChk;

    @NotNull(message = "이름은 필수 값입니다.")
    @NotBlank(message = "이름은 필수 값입니다.")
    private String nm;

    @NotNull(message = "닉네임은 필수 값입니다.")
    @NotBlank(message = "닉네임은 필수 값입니다.")
    private String ncnm;

    @NotNull(message = "생년월일은 필수 값입니다.")
    @NotBlank(message = "생년월일은 필수 값입니다.")
    private String brthdy;

    @NotNull(message = "성별은 필수 값입니다.")
    @NotBlank(message = "성별은 필수 값입니다.")
    private String sexPrTy;
    private UserRollType mberDvTy;
    private String moblphon;
    private String email;
    private Integer zip;
    private String adres;
    private String dtlAdres;
    private LocalDateTime updDtm;
    private String delAt;

    private LocalDateTime emailAttcDtm;
    private LocalDateTime mobileAttcDtm;
    private String mobileAttcAt;
    private String emailAttcAt;
    private int authEmailChk;
    private int authMobileChk;

    private String approval;
    private LocalDateTime regDtm;

}
