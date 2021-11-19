let loginId;
let ncnm;
let nm;
let pwd;
let pwdChk;
let email;
let moblphon;
let adres;
let dtlAdres;
let sexPrTy;
let brthdy;
let year;
let month;
let day;

var idDupBool = false;
var emailDupBool = false;


$(function (){

	$('#btnSave').click(function() {
		if(!fn_chkField()) {
			return;

		}
		var frm = $('#form1');
		frm.prop('action' , '/api/member/insert')
		frm.submit();
	})

	appendYear();
})

function fn_chkField(){
	$('.err.emph').remove();
	var brthdyStr = "";
	loginId = $('#loginId');
	ncnm = $('#ncnm');
	nm = $('#nm');
	pwd = $('#pwd');
	pwdChk = $('#pwdChk');
	email = $('#email');
	moblphon = $('#moblphon');
	adres = $('#adres');
	dtlAdres = $('#dtlAdres');
	sexPrTy = $('#sexPrTy');
	brthdy = $("#brthdy");
	year = $("#year");
	month = $("#month");
	day = $("#day");


	if(!loginId.val()){
		if(!loginIdCheck(loginId)){
			alert('error : loginIdCheck');
			return false;
		}
	}
	if(!idDupBool){
		alert('error : idDupBool');
		loginId.focus();
		return false;
	}
	if (!fn_chkPwdDup()) {
		return false;
	}
	if ($.trim(year.val())) {
		brthdyStr += $.trim(year.val());
	}else{
		//alert('출생년도를 입력하세요');
		brthdy.after('<p class="err emph">생년월일은 필수 값입니다.</p>');
		year.focus();
		alert('error : brthdyStr');
		return false;
	}

	if ($.trim(month.val())) {
		brthdyStr += $.trim(month.val());
	}else{
		//alert('생년월일을 선택하세요');
		brthdy.after('<p class="err emph">생년월일은 필수 값입니다.</p>');
		month.focus();
		return false;
	}

	if ($.trim(day.val())) {
		brthdyStr += $.trim(day.val());
	}else{
		//alert('생년월일을 선택하세요');
		brthdy.after('<p class="err emph">생년월일은 필수 값입니다.</p>');
		day.focus();
		return false;
	}

	brthdy.val(brthdyStr);

	if(!sexPrTy.val()){
		alert('성별을 선택해주세요.')
		sexPrTy.focus();
		return false;
	}
	/*if($('#authMoblieChk') ==1 ){
		alert('핸드폰 인증을 해주세요.');
		moblphon.focus();
		return false;
	}*/
	if (!ncnm.val()) {
		//alert('닉네임을 입력하세요');
		ncnm.after('<p class="err emph">닉네임은 필수 값입니다.</p>');
		ncnm.focus();
		return false;
	}
}
function fn_chkIdDup(){
	loginId = $('#loginId');
	loginId.siblings('.err.emph').remove();

	if(!loginIdCheck(loginId)){
		return false;
	}
	$.ajax({
		type : "POST" ,
		url : "/api/member/isExistsByLoginId" ,
		data : {
			"loginId" : loginId.val()
		},
		success : function (data) {
			if(loginId.val() != '') {
				alert('사용 가능한 아이디 입니다.')
				idDupBool = true;
			}
		},
		error : function (error) {
			alert(error.responseText);
			idDupBool = false;
			loginId.val('');
			loginId.focus();
		}
	})
}
function fn_chkPwdDup(){
	pwd = $("#pwd");
	pwdChk = $("#pwdChk");

	pwd.siblings('.err.emph').remove();
	if (!$.trim(pwd.val())) {
		alert('비밀번호를 입력하세요');
		pwd.focus();
		return false;
	}else{
		if(!pwdCheck(pwd)){
			pwd.val("");
			pwd.focus();
			return false;
		}
	}

	pwdChk.siblings('.err.emph').remove();
	if (!$.trim(pwdChk.val())) {
		alert('비밀번호 확인을 입력하세요');
		pwdChk.focus();
		return false;
	}else{
		if(!pwdCheck(pwdChk)){
			pwdChk.val("");
			pwdChk.focus();
			return false;
		}
	}

	if (pwd.val() != pwdChk.val()) {
		alert('비밀번호가 일치하지 않습니다.');
		pwdChk.focus();
		return false;
	}else{
		return true;
	}
}
function fn_comparePwd() {
	pwd = $("#pwd");
	pwdChk = $('#pwdChk');
	pwdChk.siblings('.err.emph').remove();
	if (pwd.val() != pwdChk.val()) {
		/*pwdChk.after('<p class="err emph">비밀번호가 일치하지 않습니다.</p>');*/
		alert('비밀번호가 일치하지 않습니다.')
	}
}
function appendYear() {
	var date = new Date();
	var currentYear = date.getFullYear();

	year = $('#year');
	year.append("<option value=''>년도</option>")

	for (var y = (currentYear - 1); (currentYear - 70) <= y; y--) {
		year.append("<option value='" + y + "'>" + y + "년" + "</option>");
	}
}
function loginIdCheck(loginId, errTgt) {
	var errTgt;
	if (errTgt) {
		errTgt = errTgt;
	} else {
		errTgt = loginId;
	}
	errTgt.siblings('.err.emph').remove();
	var regExp = /[a-z0-9]{6,12}/;
	if ($.trim(loginId.val()) == '') {
		errTgt.after('<p class="err emph">아이디는 필수 값입니다.</p>');
		loginId.focus();
		return false;
	}

	if (regExp.test(loginId.val()) == false) {
		//alert("아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.");
		errTgt.after('<p class="err emph">아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.</p>');
		loginId.focus();
		return false;
	}
	return true;
}
/*//아이디 중복체크
function idcheckbtn() {
	var user_id = $("#user_id").val();
	var regExp_id = /^[a-z]+[a-z0-9]{5,19}$/g;
	
	if(!regExp_id.test(user_id)) {
		$("#valid_id").css("color", "#f30c43").addClass("fas fa-times").text(" 아이디는 6~20자 영어 또는 숫자로 입력하세요.");
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
					$("#valid_id").css("color", "#f30c43").addClass("fas fa-times").text(" 해당 아이디가 이미 존재합니다.");
					$("#user_id").val("");
					$("#user_id").focus();
				} else {
					$("#valid_id").removeClass();
					$("#valid_id").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 사용 가능한 아이디 입니다.");
					$("#id_validation").val("1");
				}
			}
		});
	}
}
// 아이디 중복체크[e]

// 닉네임 중복체크[s]
function nickcheckbtn() {
	var nickname = $("#nickname").val();
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

// 이메일 중복체크[s]
function emailchkbtn() {
	var email = $("#email").val();
	var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
	
	if(email == "") {
		$("#valid_email").css("color", "#f30c43").addClass("fas fa-times").text(" 이메일을 입력해주세요.");
		$("#email").val("");
		$("#email").focus();
	} else if(!regExp_email.test(email)) {
		$("#valid_email").css("color", "#f30c43").addClass("fas fa-times").text(" 이메일을 다시 입력해주세요.");
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
					$("#valid_email").css("color", "#f30c43").addClass("fas fa-times").text(" 해당 이메일이 이미 존재합니다.");
					$("#email").val("");
					$("#email").focus();
				} else {
					$("#valid_email").removeClass();
					$("#valid_email").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 인증번호가 전송되었습니다.");
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
	var user_id = $("#user_id").val();
	var nickname = $("#nickname").val();
	var name = $("#name").val();
	var user_pw = $("#user_pw").val();
	var user_pwchk = $("#user_pwchk").val();
	var phone_num = $("#phone_num").val();
	var email = $("#email").val();
	var gender = $("#gender").val();
	var birth = $("#birth").val();
	var user_job = $("#user_job").val();
	var id_validation = $("#id_validation").val();
	var nickname_validation = $("#nickname_validation").val();
	var check_num = $("#check_num").val();
	var check_num1 = $("#check_num1").val();
	
	// 정규식
	var regExp_id = /^[a-z]+[a-z0-9]{5,19}$/g;
	var regExp_pw = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;
	var regExp_nickname = /[~!@#$%^&*()_+|<>?:{}]/;
	var regExp_name = /^[가-힣]{2,4}$/;
	var regExp_phone_num = /^\d{3}\d{3,4}\d{4}$/;
	var regExp_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var regExp_birth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	/!* var regExp_tel_num = /^\d{2,3}-\d{3,4}-\d{4}$/; *!/
	
	if(!regExp_id.test(user_id)) {
		$("#valid_id").css("color", "#f30c43").addClass("fas fa-times").text(" 아이디는 6~20자 영어 또는 숫자로 입력하세요.");
		$("#user_id").val("");
		$("#user_id").focus();
		return false;
	} else if(id_validation == "0") {
		$("#valid_id").css("color", "#f30c43").addClass("fas fa-times").text(" 아이디 중복확인은 필수입니다.");
		$("#user_id").focus();
		return false;
	} else if(regExp_nickname.test(nickname)) {
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
	} else if(!regExp_pw.test(user_pw)) {
		$("#valid_password").css("color", "#f30c43").addClass("fas fa-times").text(" 특수문자 포함 8자이상 16자 이하로 입력하세요.");
		$("#user_pw").val("");
		$("#user_pw").focus();
		return false;
	} else if(user_pwchk == 0) {
		$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호를 입력해주세요.");
		$("#user_pwchk").val("");
		$("#user_pwchk").focus();
		return false;
	} else if(user_pw != user_pwchk) {
		$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
		$("#user_pwchk").val("");
		$("#user_pwchk").focus();
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
	} else if(check_num != check_num1) {
		$("#valid_check_num").css("color", "#f30c43").addClass("fas fa-times").text(" 인증번호가 일치하지 않습니다.");
		$("#check_num").val("");
		$("#check_num").focus();
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
// 회원등록 폼 검증[e]

// focusout [s]
$(function() {
	$("#user_id").focusout(function(){
		if($("#user_id").val().length == 0) {
			$("#valid_id").removeClass();
			$("#valid_id").css("color", "#7A838B").addClass("form-control-p").text(" 6~20자 이내로 영문 소문자, 숫자만 사용 가능합니다.");
			$("#id_validation").val("0");
		} else {
			return;
		}
	});
	
	$("#nickname").focusout(function(){
		if($("#nickname").val().length == 0) {
			$("#valid_nickname").removeClass();
			$("#valid_nickname").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
			$("#nickname_validation").val("0");
		} else {
			return;
		}
	});
	
	$("#email").focusout(function(){
		if($("#email").val().length == 0) {
			$("#valid_email").removeClass().text("");
			$("#valid_email").addClass("form-control-p");
			$("#email_validation").val("0");
			$("#check_num1").val("");
			$(".email_btn").text("인증번호 받기");
			$(".key_num_chk_btn").css("display", "none");
			$("#key_num").attr("disabled", true).val("");
		} else {
			return;
		}
	});
});
//focusout [e]

// keyup, keydown 되었을때, 메세지 제거 [s]
$(function(){
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
			$("#valid_password2").css("color", "#f30c43").addClass("fas fa-times").text(" 비밀번호가 동일하지 않습니다.");
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
	
	$("#user_id").on("keyup keydown", function() {
		if($("#user_id").val().length > 0) {
			$("#valid_id").removeClass();
			$("#id_validation").val("0");
			$("#valid_id").css("color", "#7A838B").addClass("form-control-p").text(" 6~20자 이내로 영문 소문자, 숫자만 사용 가능합니다.");
		}
	});
	
	$("#nickname").on("keyup keydown", function() {
		if($("#nickname").val().length > 0) {
			$("#valid_nickname").removeClass();
			$("#nickname_validation").val("0");
			$("#valid_nickname").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
		}
	});
	
	$("#name").on("keyup keydown", function() {
		if($("#name").val().length > 0) {
			$("#valid_name").removeClass();
			$("#valid_name").css("color", "#7A838B").addClass("form-control-p").text(" 특수문자 없이 입력해 주세요.");
		}
	});
	
	$("#phone_num").on("keyup keydown", function() {
		if($("#phone_num").val().length > 0) {
			$("#valid_phone_num").empty().removeClass();
			$("#valid_phone_num").addClass("form-control-p");
		}
	});
	
	$("#email").on("keyup keydown", function() {
		if($("#email").val().length > 0) {
			$("#valid_email").empty().removeClass();
			$("#valid_email").addClass("form-control-p");
			$("#email_validation").val("0");
			$("#check_num1").val("");
			$(".email_btn").text("인증번호 받기");
			$("#check_num").attr("disabled", true).val("");
		}
	});
	
	$("#check_num").on("keyup keydown", function() {
		if($("#check_num").val().length == 0) {
			$("#valid_check_num").empty().removeClass();
			$("#valid_check_num").addClass("form-control-p");
		} else if($("#check_num").val() != $("#check_num1").val()) {
			$("#valid_check_num").css("color", "#f30c43").addClass("fas fa-times").text(" 인증번호가 일치하지 않습니다.");
		} else {
			$("#valid_check_num").removeClass();
			$("#valid_check_num").css("color", "#57c1be").addClass("form-control-p fa fa-check").text(" 인증번호가 일치합니다.");
		}
	});
	
	$("#gender").on("click", function() {
		$("#valid_gender").empty().removeClass();
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
		}
	});
});*/

// keyup, keydown 되었을때, 메세지 제거 [e]