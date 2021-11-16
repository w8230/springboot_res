// 아이디 중복체크[s]
function idcheckbtn() {
	var user_id = $("#user_id").val();
	var regExp_id = /^[a-z]+[a-z0-9]{5,19}$/g;
	
	if(!regExp_id.test(user_id)) {
		$("#valid_id").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 아이디는 6~20자 영어 또는 숫자로 입력하세요.");
		$("#user_id").val("");
		$("#user_id").focus();
		return false;
	} else {
		$.ajax({
			url : "user_id_chk",
			type : "POST",
			dataType : "json",
			data : {"user_id" : user_id},
			success : function(result) {
				if(result == 1) {
					$("#valid_id").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 해당 아이디가 이미 존재합니다.");
					$("#user_id").val("");
					$("#user_id").focus();
				} else {
					$("#valid_id").removeClass();
					$("#valid_id").css("color", "#57c1be").addClass("warning_msg fa fa-check").text(" 사용 가능한 아이디 입니다.");
					$("#id_validation").val("1");
				}
			}
		});
	}
}
// 아이디 중복체크[e]

// 파트너명 중복체크[s]
function partnercheckbtn() {
	var partner_name = $("#partner_name").val();
	
	if(partner_name.length == 0) {
		$("#valid_partner_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 파트너명을 입력해주세요.");
		$("#partner_name").val("");
		$("#partner_name").focus();
		return false;
	} else {
		$.ajax({
			url : "partner_name_chk",
			type : "POST",
			dataType : "json",
			data : {"partner_name" : partner_name},
			success : function(result) {
				if(result == 1) {
					$("#valid_partner_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 해당 파트너명이 이미 존재합니다.");
					$("#partner_name").val("");
					$("#partner_name").focus();
				} else {
					$("#valid_partner_name").removeClass();
					$("#valid_partner_name").css("color", "#57c1be").addClass("warning_msg warning_msg fa fa-check").text(" 사용 가능한 파트너명 입니다.");
					$("#partner_validation").val("1");
				}
			}
		});
	}
}
// 파트너명 중복체크[e]

// 이메일 중복체크[s]
function emailchkbtn() {
	var email = $("#email").val();
	var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	if(email == "") {
		$("#valid_email").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 이메일을 입력해주세요.");
		$("#email").val("");
		$("#email").focus();
	} else if(!regExp_email.test(email)) {
		$("#valid_email").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 이메일을 다시 입력해주세요.");
		$("#email").val("");
		$("#email").focus();
	} else {
		$.ajax({
			url : "email_chk",
			type : "POST",
			dataType : "json",
			data : {"email" : email},
			success : function(result) {
				if(result == 1) {
					$("#valid_email").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 해당 이메일이 이미 존재합니다.");
					$("#email").val("");
					$("#email").focus();
				} else {
					$("#valid_email").removeClass();
					$("#valid_email").css("color", "#57c1be").addClass("warning_msg fa fa-check").text(" 인증번호가 전송되었습니다.");
					$("#email_validation").val("1");
					$("#check_num1").val(result);
					$(".email_btn").text("다시 받기");
					$("#check_num").attr("disabled", false).val("");
					$("#check_num").focus();
				}
			}
		});
	}
}
// 이메일 중복체크[e]

// 회원등록 폼 검증[s]
function form_validation() {
	var partner_name = $("#partner_name").val();
	var partner_num = $("#partner_num").val();
	var user_id = $("#user_id").val();
	var name = $("#name").val();
	var user_pw = $("#user_pw").val();
	var user_pwchk = $("#user_pwchk").val();
	var email = $("#email").val();
	var gender = $("#gender").val();
	var phone_num = $("#phone_num").val();
	var tel_num = $("#tel_num").val();
	var bank = $("#bank").val();
	var account_num = $("#account_num").val();
	var account_holder = $("#account_holder").val();
	var id_validation = $("#id_validation").val();
	var partner_validation = $("#partner_validation").val();
	var check_num = $("#check_num").val();
	var check_num1 = $("#check_num1").val();
	
	// 정규식
	var regExp_id = /^[a-z]+[a-z0-9]{5,19}$/g;
	var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
	var regExp_partner_name = /[~!@#$%^&*()_+|<>?:{}]/;
	var regExp_name = /^[가-힣]{2,4}$/;
	var regExp_phone_num = /^\d{3}\d{3,4}\d{4}$/;
	var regExp_tel_num = /^\d{2,3}\d{3,4}\d{4}$/;
	var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	
	if(regExp_partner_name.test(partner_name)) {
		$("#valid_partner_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 특수문자 없이 입력해 주세요.");
		$("#partner_name").val("");
		$("#partner_name").focus();
		return false;
	} else if(partner_name.length == 0) {
		$("#valid_partner_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 파트너명을 입력해주세요.");
		$("#partner_name").val("");
		$("#partner_name").focus();
		return false;
	} else if(partner_validation == "0") {
		$("#valid_partner_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 파트너명 중복확인은 필수입니다.");
		$("#partner_name").focus();
		return false;
	} else if(partner_num.length == 0) {
		$("#valid_partner_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 사업자번호를 입력해주세요.");
		$("#partner_num").val("");
		$("#partner_num").focus();
		return false;
	} else if(!regExp_id.test(user_id)) {
		$("#valid_id").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 아이디는 6~20자 영어 또는 숫자로 입력하세요.");
		$("#user_id").val("");
		$("#user_id").focus();
		return false;
	} else if(id_validation == "0") {
		$("#valid_id").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 아이디 중복확인은 필수입니다.");
		$("#user_id").focus();
		return false;
	} else if(!regExp_name.test(name)) {
		$("#valid_name").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 이름을 확인해주세요.");
		$("#name").val("");
		$("#name").focus();
		return false;
	} else if(!regExp_pw.test(user_pw)) {
		$("#valid_password").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 특수문자 포함 8자이상 16자 이하로 입력하세요.");
		$("#user_pw").val("");
		$("#user_pw").focus();
		return false;
	} else if(user_pwchk == 0) {
		$("#valid_password2").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 비밀번호를 입력해주세요.");
		$("#user_pwchk").val("");
		$("#user_pwchk").focus();
		return false;
	} else if(user_pw != user_pwchk) {
		$("#valid_password2").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
		$("#user_pwchk").val("");
		$("#user_pwchk").focus();
		return false;
	} else if(!regExp_email.test(email)) {
		$("#valid_email").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 이메일을 다시 입력해주세요.");
		$("#email").val("");
		$("#email").focus();
		return false;
	} else if(check_num != check_num1) {
		$("#valid_check_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 인증번호가 일치하지 않습니다.");
		$("#check_num").val("");
		$("#check_num").focus();
		return false;
	} else if(!regExp_phone_num.test(phone_num)) {
		$("#valid_phone_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 잘못된 핸드폰 번호입니다.");
		$("#phone_num").val("");
		$("#phone_num").focus();
		return false;
	} else if(!regExp_tel_num.test(tel_num)) {
		$("#valid_tel_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 잘못된 전화 번호입니다.");
		$("#tel_num").val("");
		$("#tel_num").focus();
		return false;
	} else if(gender == "") {
		$("#valid_gender").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 성별을 선택해주세요.");
		return false;
	} else if(bank == "") {
		$("#valid_bank").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 잘못된 은행명 입니다.");
		$("#bank").val("");
		$("#bank").focus();
		return false;
	} else if(account_num == "") {
		$("#valid_account_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 잘못된 계좌번호 입니다.");
		$("#account_num").val("");
		$("#account_num").focus();		
		return false;
	} else if(account_holder == "") {
		$("#valid_account_holder").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 잘못된 예금주명 입니다.");
		$("#account_holder").val("");
		$("#account_holder").focus();
		return false;
	}
}
// 회원등록 폼 검증[e]

// keyup, keydown 되었을때, 메세지 제거 [s]
$(function(){
	var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
	$("#user_pw").on("keyup keydown", function() {
		if(!regExp_pw.test($("#user_pw").val())) {
			$("#valid_password").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 포함 8자이상 16자 이하로 입력하세요.");
		} else {
			$("#valid_password").removeClass();
			$("#valid_password").css("color", "#57c1be").addClass("warning_msg fa fa-check").text(" 사용 가능한 비밀번호 입니다.");
		}
	});
	$("#user_pwchk").on("keyup keydown", function() {
		if($("#user_pw").val() != $("#user_pwchk").val()) {
			$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
		} else {
			$("#valid_password2").removeClass();
			$("#valid_password2").css("color", "#57c1be").addClass("warning_msg fa fa-check").text(" 비밀번호가 동일합니다.");
		}
	});
	$("#user_pw").on("keyup keydown", function() {
		if($("#user_pw").val().length == 0) {
			$("#valid_password").removeClass();
			$("#valid_password").css("color", "#7A838B").addClass("warning_msg").text("");
		}
	});
	$("#user_pwchk").on("keyup keydown", function() {
		if($("#user_pwchk").val().length == 0) {
			$("#valid_password2").empty().removeClass();
			$("#valid_password2").addClass("warning_msg");
		}
	});
	
	$("#partner_name").on("keyup keydown", function() {
		if($("#partner_name").val().length > 0) {
			$("#valid_partner_name").empty().removeClass();
			$("#partner_validation").val("0");
		}
	});
	
	$("#partner_num").on("keyup keydown", function() {
		if($("#partner_num").val().length > 0) {
			$("#valid_partner_num").empty().removeClass();
		}
	});
	
	$("#user_id").on("keyup keydown", function() {
		if($("#user_id").val().length > 0) {
			$("#valid_id").empty().removeClass();
			$("#id_validation").val("0");
		}
	});
	
	$("#name").on("keyup keydown", function() {
		if($("#name").val().length > 0) {
			$("#valid_name").empty().removeClass();
		}
	});
	
	$("#email").on("keyup keydown", function() {
		if($("#email").val().length > 0) {
			$("#valid_email").empty().removeClass();
			$("#email_validation").val("0");
			$("#check_num1").val("");
			$(".email_btn").text("인증번호 받기");
			$("#check_num").attr("disabled", true).val("");
		}
	});
	
	$("#check_num").on("keyup keydown", function() {
		if($("#check_num").val().length == 0) {
			$("#valid_check_num").empty().removeClass();
		} else if($("#check_num").val() != $("#check_num1").val()) {
			$("#valid_check_num").css("color", "#f30c43").addClass("warning_msg fas fa-times").text(" 인증번호가 일치하지 않습니다.");
		} else {
			$("#valid_check_num").removeClass();
			$("#valid_check_num").css("color", "#57c1be").addClass("warning_msg fa fa-check").text(" 인증번호가 일치합니다.");
		}
	});
	
	$("#gender").on("click", function() {
		$("#valid_gender").empty().removeClass();
	});
	
	$("#phone_num").on("keyup keydown", function() {
		if($("#phone_num").val().length > 0) {
			$("#valid_phone_num").empty().removeClass();
		}
	});
	
	$("#tel_num").on("keyup keydown", function() {
		if($("#tel_num").val().length > 0) {
			$("#valid_tel_num").empty().removeClass();
		}
	});
	
	$("#bank").on("keyup keydown", function() {
		if($("#bank").val().length > 0) {
			$("#valid_bank").empty().removeClass();
		}
	});
	
	$("#account_num").on("keyup keydown", function() {
		if($("#account_num").val().length > 0) {
			$("#valid_account_num").empty().removeClass();
		}
	});
	
	$("#account_holder").on("keyup keydown", function() {
		if($("#account_holder").val().length > 0) {
			$("#valid_account_holder").empty().removeClass();
		}
	});
	
});
// keyup, keydown 되었을때, 메세지 제거 [e]