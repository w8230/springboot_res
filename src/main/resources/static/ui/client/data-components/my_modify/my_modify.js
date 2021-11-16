//닉네임 중복체크[s]
function nickcheckbtn() {
	var nickname = $("#nickname").val();
	var nickname2 = $("#nickname2").val();
	var regExp_nickname = /[~!@#$%^&*()_+|<>?:{}]/;
	
	if(nickname == "") {
		$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 닉네임 입력해 주세요.");
		$("#nickname").val("");
		$("#nickname").focus();
		return false;
	} else if(regExp_nickname.test(nickname)) {
		$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 없이 입력해 주세요.");
		$("#nickname").val("");
		$("#nickname").focus();
		return false;
	} else if(nickname == nickname2) {
		$("#valid_nickname").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 닉네임 입니다.");
		$("#nickname_validation").val("1");
	} else {
		$.ajax({
			url : "nickname_chk",
			type : "POST",
			dataType : "json",
			data : {"nickname" : nickname},
			success : function(result) {
				if(result == 1) {
					$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 해당 닉네임이 이미 존재합니다.");
					$("#nickname").val("");
					$("#nickname").focus();
				} else {
					$("#valid_nickname").removeClass();
					$("#valid_nickname").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 닉네임 입니다.");
					$("#nickname_validation").val("1");
				}
			}
		});
	}
	
}
// 닉네임 중복체크[e]

// 파트너명 중복체크[s]
function partnercheckbtn() {
	var partner_name = $("#partner_name").val();
	var partner_name2 = $("#partner_name2").val();
	
	if(partner_name.length == 0) {
		$("#valid_partner_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 파트너명을 입력해주세요.");
		$("#partner_name").val("");
		$("#partner_name").focus();
		return false;
	} else if(partner_name == partner_name2) {
		$("#valid_partner_name").removeClass();
		$("#valid_partner_name").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 파트너명 입니다.");
		$("#partner_validation").val("1");
	} else {
		$.ajax({
			url : "partner_name_chk",
			type : "POST",
			dataType : "json",
			data : {"partner_name" : partner_name},
			success : function(result) {
				if(result == 1) {
					$("#valid_partner_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 해당 파트너명이 이미 존재합니다.");
					$("#partner_name").val("");
					$("#partner_name").focus();
				} else {
					$("#valid_partner_name").removeClass();
					$("#valid_partner_name").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 파트너명 입니다.");
					$("#partner_validation").val("1");
				}
			}
		});
	}
}
// 파트너명 중복체크[e]

function form_validation() {
	var status = $("#status").val();
	var nickname = $("#nickname").val();
	var partner_name = $("#partner_name").val();
	var name = $("#name").val();
	var phone_num = $("#phone_num").val();
	var tel_num = $("#tel_num").val();
	var email = $("#email").val();
	var gender = $("#gender").val();
	var birth = $("#birth").val();
	var user_job = $("#user_job").val();
	var partner_num = $("#partner_num").val();
	var bank = $("#bank").val();
	var account_num = $("#account_num").val();
	var account_holder = $("#account_holder").val();	
	var nickname_validation = $("#nickname_validation").val();
	var partner_validation = $("#partner_validation").val();
	var user_pw = $("#user_pw").val();
	var user_pwchk = $("#user_pwchk").val();

	// 정규식
	var regExp_id = /^[a-z]+[a-z0-9]{5,19}$/g;
	var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
	var regExp_nickname = /[~!@#$%^&*()_+|<>?:{}]/;
	var regExp_name = /^[가-힣]{2,4}$/;
	var regExp_phone_num = /^\d{3}\d{3,4}\d{4}$/;
	var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var regExp_birth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	var regExp_partner_name = /[~!@#$%^&*()_+|<>?:{}]/;
	var regExp_tel_num = /^\d{2,3}\d{3,4}\d{4}$/;
	
	if(user_pw != "" || user_pwchk != "") {
		if(!regExp_pw.test(user_pw)) {
			$("#valid_password").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 포함 8자이상 16자 이하로 입력하세요.");
			$("#user_pw").val("");
			$("#user_pw").focus();
			return false;
		} else if(user_pw == null) {
			$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호확인을 입력해주세요.");
			$("#user_pwchk").val("");
			$("#user_pwchk").focus();
			return false;
		} else if(user_pwchk == null) {
			$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호를 입력해주세요.");
			$("#user_pw").val("");
			$("#user_pw").focus();
			return false;
		} else if(user_pw != user_pwchk) {
			$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
			$("#user_pwchk").val("");
			$("#user_pwchk").focus();
			return false;
		}
	}
	
	if(status == "P") {
		if(regExp_partner_name.test(partner_name)) {
			$("#valid_partner_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 특수문자 없이 입력해 주세요.");
			$("#partner_name").val("");
			$("#partner_name").focus();
			return false;
		} else if(partner_name.length == 0) {
			$("#valid_partner_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 파트너명을 입력해주세요.");
			$("#partner_name").val("");
			$("#partner_name").focus();
			return false;
		} else if(partner_validation == "0") {
			$("#valid_partner_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 파트너명 중복확인은 필수입니다.");
			$("#partner_name").focus();
			return false;
		} else if(partner_num.length == 0) {
			$("#valid_partner_num").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 사업자번호를 입력해주세요.");
			$("#partner_num").val("");
			$("#partner_num").focus();
			return false;
		} else if(!regExp_name.test(name)) {
			$("#valid_name").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 이름을 확인해주세요.");
			$("#name").val("");
			$("#name").focus();
			return false;
		} else if(!regExp_phone_num.test(phone_num)) {
			$("#valid_phone_num").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 잘못된 핸드폰 번호입니다.");
			$("#phone_num").val("");
			$("#phone_num").focus();
			return false;
		} else if(!regExp_tel_num.test(tel_num)) {
			$("#valid_tel_num").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 잘못된 전화 번호입니다.");
			$("#tel_num").val("");
			$("#tel_num").focus();
			return false;
		} else if(!regExp_email.test(email)) {
			$("#valid_email").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 이메일을 다시 입력해주세요.");
			$("#email").val("");
			$("#email").focus();
			return false;
		} else if(gender == "") {
			$("#valid_gender").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 성별을 선택해주세요.");
			return false;
		}  else if(bank == "") {
			$("#valid_bank").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 잘못된 은행명 입니다.");
			$("#bank").val("");
			$("#bank").focus();
			return false;
		} else if(account_num == "") {
			$("#valid_account_num").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 잘못된 계좌번호 입니다.");
			$("#account_num").val("");
			$("#account_num").focus();		
			return false;
		} else if(account_holder == "") {
			$("#valid_account_holder").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 잘못된 예금주명 입니다.");
			$("#account_holder").val("");
			$("#account_holder").focus();
			return false;
		}
	} else if(status == "U") {
		if(regExp_nickname.test(nickname)) {
			$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 없이 입력해 주세요.");
			$("#nickname").val("");
			$("#nickname").focus();
			return false;
		} else if(nickname == "") {
			$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 닉네임을 입력해주세요.");
			$("#nickname").val("");
			$("#nickname").focus();
			return false;
		} else if(nickname_validation == "0") {
			$("#valid_nickname").css("color", "#f30c43").addClass("fas fa-times").text(" 닉네임 중복확인은 필수입니다.");
			$("#nickname").focus();
			return false;
		} else if(!regExp_name.test(name)) {
			$("#valid_name").css("color", "#f30c43").addClass("fas fa-times").text(" 이름을 확인해주세요.");
			$("#name").val("");
			$("#name").focus();
			return false;
		} else if(!regExp_phone_num.test(phone_num)) {
			$("#valid_phone_num").css("color", "#f30c43").addClass("fas fa-times").text(" 잘못된 핸드폰 번호입니다.");
			$("#phone_num").val("");
			$("#phone_num").focus();
			return false;
		} else if(!regExp_email.test(email)) {
			$("#valid_email").css("color", "#f30c43").addClass("fas fa-times").text(" 이메일을 다시 입력해주세요.");
			$("#email").val("");
			$("#email").focus();
			return false;
		} else if(gender == "") {
			$("#valid_gender").css("color", "#f30c43").addClass("fas fa-times").text(" 성별을 선택해주세요.");
			return false;
		} else if(!regExp_birth.test(birth)) {
			$("#valid_birth").css("color", "#f30c43").addClass("fas fa-times").text(" 생년월일을 입력해주세요.");
			$("#birth").val("");
			$("#birth").focus();
			return false;
		} else if(birth == "") {
			$("#valid_birth").css("color", "#f30c43").addClass("fas fa-times").text(" 생년월일을 입력해주세요.");
			$("#birth").val("");
			$("#birth").focus();
			return false;
		} else if(user_job == "") {
			$("#valid_user_job").css("color", "#f30c43").addClass("fas fa-times").text(" 직업을 입력해주세요.");
			$("#valid_user_job").val("");
			$("#user_job").focus();
			return false;
		}
	}
}


//keyup, keydown 되었을때, 메세지 제거 [s]
$(function() {
	var nickname2 = "${userData.NICKNAME}";
	var partner_name2 = "${userData.PARTNER_NAME}";
	var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
	
	$("#user_pw").on("keyup keydown", function() {
		if(!regExp_pw.test($("#user_pw").val())) {
			$("#valid_password").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 포함 8자이상 16자 이하로 입력하세요.");
		} else {
			$("#valid_password").removeClass();
			$("#valid_password").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 비밀번호 입니다.");
		}
	});
	$("#user_pwchk").on("keyup keydown", function() {
		if($("#user_pw").val() != $("#user_pwchk").val()) {
			$("#valid_password2").removeClass();
			$("#valid_password2").css("color", "#f30c43").addClass("form-control-p fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
		} else {
			$("#valid_password2").removeClass();
			$("#valid_password2").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 비밀번호가 동일합니다.");
		}
	});
	$("#user_pw").on("keyup keydown", function() {
		if($("#user_pw").val().length == 0) {
			$("#valid_password").removeClass();
			$("#valid_password").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 포함 8자이상 16자 이하로 입력해 주세요.");
		}
	});
	$("#user_pwchk").on("keyup keydown", function() {
		if($("#user_pwchk").val().length == 0) {
			$("#valid_password2").empty().removeClass();
			$("#valid_password2").addClass("form-control-p");
		}
	});
	
	$("#nickname").on("keyup keydown", function() {
		nickname = $("#nickname").val();
		if(nickname2 == nickname) {
			$("#nickname_validation").val("1");
		} else {
			$("#nickname_validation").val("0");
		}
	});
	
	$("#partner_name").on("keyup keydown", function() {
		partner_name = $("#partner_name").val();
		if(partner_name2 == partner_name) {
			$("#partner_validation").val("1");
		} else {
			$("#partner_validation").val("0");
		}
	});
	
	$("#nickname").on("keyup keydown", function() {
		if($("#nickname").val().length > 0) {
			$("#valid_nickname").removeClass();
			$("#valid_nickname").addClass("form-control-p");
			$("#valid_nickname").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
		}
	});
	
	$("#partner_name").on("keyup keydown", function() {
		if($("#partner_name").val().length > 0) {
			$("#valid_partner_name").empty().removeClass();
			$("#valid_partner_name").addClass("form-control-p");
			$("#valid_partner_name").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
		}
	});
	
	$("#partner_num").on("keyup keydown", function() {
		if($("#partner_num").val().length > 0) {
			$("#valid_partner_num").empty().removeClass();
			$("#valid_partner_num").addClass("form-control-p");
		}
	});
	
	$("#name").on("keyup keydown", function() {
		if($("#name").val().length > 0) {
			$("#valid_name").removeClass();
			$("#valid_name").addClass("form-control-p");
			$("#valid_name").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
		}
	});
	
	$("#phone_num").on("keyup keydown", function() {
		if($("#phone_num").val().length > 0) {
			$("#valid_phone_num").empty().removeClass();
			$("#valid_phone_num").addClass("form-control-p");
		}
	});
	
	$("#tel_num").on("keyup keydown", function() {
		if($("#tel_num").val().length > 0) {
			$("#valid_tel_num").empty().removeClass();
			$("#valid_tel_num").addClass("form-control-p");
		}
	});
	
	$("#email").on("keyup keydown", function() {
		if($("#email").val().length > 0) {
			$("#valid_email").empty().removeClass();
			$("#valid_email").addClass("form-control-p");
		}
	});
	
	$("#gender").on("click", function() {
		$("#valid_gender").empty().removeClass();
		$("#valid_gender").addClass("form-control-p");
		$("#valid_gender").css("color", "#7A838B").addClass("form-control-p");
	});
	
	$("#birth").on("keyup keydown", function() {
		if($("#birth").val().length > 0) {
			$("#valid_birth").empty().removeClass();
			$("#valid_birth").addClass("form-control-p");
		}
	});
	
	$("#user_job").on("keyup keydown", function() {
		if($("#user_job").val().length > 0) {
			$("#valid_user_job").empty().removeClass();
			$("#valid_user_job").addClass("form-control-p");
		}
	});
	
	$("#bank").on("keyup keydown", function() {
		if($("#bank").val().length > 0) {
			$("#valid_bank").empty().removeClass();
			$("#valid_bank").addClass("form-control-p");
		}
	});
	
	$("#account_num").on("keyup keydown", function() {
		if($("#account_num").val().length > 0) {
			$("#valid_account_num").empty().removeClass();
			$("#valid_account_num").addClass("form-control-p");
		}
	});
	
	$("#account_holder").on("keyup keydown", function() {
		if($("#account_holder").val().length > 0) {
			$("#valid_account_holder").empty().removeClass();
			$("#valid_account_holder").addClass("form-control-p");
		}
	});
});
//keyup, keydown 되었을때, 메세지 제거 [e]