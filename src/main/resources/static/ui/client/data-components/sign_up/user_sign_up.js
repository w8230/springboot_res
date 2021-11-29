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
let zip;
let mberDvTy;
let authMobileChk;

let bnm;
let bno;
let badres;
let bzip;
let bdtlAdres;
let tel;
let thumnail;



let idDupBool = false;
let emailDupBool = false;


var themeObj = {
	bgColor: "#8AD19B",
	searchBgColor: "#171717",
	contentBgColor: "",
	pageBgColor: "",
	textColor: "",
	queryTextColor: "#FEFBFB",
	postcodeTextColor: "",
	emphTextColor: "",
	outlineColor: "",

};

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
	mberDvTy = $('#mberDvTy');

	bnm = $('#bnm');
	bno = $('#bno');
	badres = $('#badres');
	bdtlAdres = $('#bdtlAdres');
	tel = $('#tel');
	thumnail = $('#thumnail');

	if(mberDvTy.val() == 'PARTNERS') {
		if(!bnm.val()){
			alert('사업장 이름을 입력해주세요.')
			bnm.focus();
			return false;
		}
		if(!badres.val()) {
			alert('사업장 주소 입력은 필수 입니다.')
			badres.focus()
			return false
		}
		if(!bdtlAdres.val()) {
			alert('사업장 상세주소를 입력해주세요.')
			bdtlAdres.focus()
			return false;
		}
		if(!tel.val()){
			alert('사업장 연락처를 입력해주세요.')
			tel.focus();
			return false;
		}
	}

	if(!loginId.val()){
		alert('아이디를 입력해주세요.')
		loginId.focus();
		return false;
	}
	if(!idDupBool){
		alert('아이디 중복체크를 해주세요.');
		loginId.after('<p class="err emph">아이디 중복체크를 해주세요.</p>')
		loginId.focus();
		return false;
	}

	if(!$.trim(loginId.val())){
		alert('아이디는 필수 값입니다.');
		loginId.focus();
		return false;
	} else {
		if(!loginIdCheck(loginId)){
			return false;
		}
	}
	if (!ncnm.val()) {
		alert('닉네임을 입력하세요');
		ncnm.after('<p class="err emph text-gray">닉네임을 입력해주세요.</p>');
		ncnm.focus();
		return false;
	}
	if(!nm.val()){
		alert('이름을 입력해주세요.')
		nm.focus();
		return false;
	}
	/*if(!pwd.val()){
		alert('비밀번호를 입력해주세요.')
		pwd.focus();
		return false;
	}
	if(!pwdChk()){
		alert('비밀번호를 확인해주세요.')
		pwdChk.focus();
		return false;
	}*/
	if(!fn_chkPwdDup()){
		return false;
	}
	if(!email.val()){
		alert('이메일을 입력해주세요.')
		email.focus();
		return false;
	}

	if(!sexPrTy.val()){
		alert('성별을 선택해주세요.')
		sexPrTy.focus();
		return false;
	}
	//나이스 인증 후 핸드폰인증 여부 체크하는 정규식 추가. if authMoblie == 1
	if(!adres.val()){
		alert('주소를 입력하세요.')
		adres.focus()
		return false;
	}
	if(!dtlAdres.val()){
		alert('상세 주소를 입력하세요.')
		dtladres.focus()
		return false;
	}

	if ($.trim(year.val())) {
		brthdyStr += $.trim(year.val());
	}else{
		//alert('출생년도를 입력하세요');
		alert('생년월일을 선택해주세요..');
		year.focus();
		return false;
	}

	if ($.trim(month.val())) {
		brthdyStr += $.trim(month.val());
	}else{
		//alert('생년월일을 선택하세요');
		alert('생년월일을 선택해주세요..');
		month.focus();
		return false;
	}

	if ($.trim(day.val())) {
		brthdyStr += $.trim(day.val());
	}else{
		//alert('생년월일을 선택하세요');
		alert('생년월일을 선택해주세요..');
		day.focus();
		return false;
	}

	brthdy.val(brthdyStr);



	return true;
}
//정상 작동
function fn_chkIdDup(){
	loginId = $('#loginId');
	loginId.siblings('.err.emph').remove();

	var regExp = /[a-z0-9]{6,12}/
	if(!regExp.test(loginId.val())){
		alert('아이디는 6~12자 영문 소문자 , 숫자로 입력해주세요.')
		loginId.val("");
		loginId.focus();
		return false;
	}
	if(!loginIdCheck(loginId)){
		return false;
	} else {
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

	if ($.trim(pwd.val()) != $.trim(pwdChk.val())) {
		alert('비밀번호가 일치하지 않습니다.');
		pwdChk.focus();
		return false;
	}else{
		return true;
	}
}
//키업 이벤트 문재 없음.
function pwdCheck(pwd) {
	pwd.siblings('.err.emph').remove();
	var regExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

	if($.trim(pwd.val()) == ''){
		pwd.after('<p class="err emph">비밀번호를 입력해주세요.</p>');
		pwd.focus();
		return false;
	}
	if(regExp.test($.trim(pwd.val())) == false) {
		pwd.after('<p class="err emph">비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.</p>')
		/*alert('비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.');*/
		return false;
	}
	return true;
}

function fn_comparePwd() {
	pwd = $("#pwd");
	pwdChk = $('#pwdChk');
	pwdChk.siblings('.err.emph').remove();
	if (pwd.val() != pwdChk.val()) {
		pwdChk.after('<p class="err emph">비밀번호가 일치하지 않습니다.</p>')
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
	//idChkDup과 정규식 공유함 6~12자 영문 소문자, 숫자.
	if (regExp.test(loginId.val()) == false) {
		alert("아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.");
		loginId.focus();
		return false;
	}

	return true;
}
function fn_openMap() {
	new daum.Postcode({
		oncomplete: function(data) {

			var addr = '';
			var extraAddr = '';

			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				addr = data.jibunAddress;
			}

			if(data.userSelectedType === 'R'){
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraAddr += data.bname;
				}
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				}
				document.getElementById("adres").value = extraAddr;

			} else {
				document.getElementById("adres").value = '';
			}
			document.getElementById('zip').value = data.zonecode;
			document.getElementById("adres").value = addr;
			document.getElementById("dtlAdres").focus();
		}
	}).open();
}
function fn_openBmap() {
	new daum.Postcode({
		oncomplete: function(data) {

			theme: themeObj
			var addr = '';
			var extraAddr = '';

			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				addr = data.jibunAddress;
			}

			if(data.userSelectedType === 'R'){
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraAddr += data.bname;
				}
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				}
				document.getElementById("badres").value = extraAddr;

			} else {
				document.getElementById("badres").value = '';
			}
			document.getElementById('bzip').value = data.zonecode;
			document.getElementById("badres").value = addr;
			document.getElementById("bdtlAdres").focus();
		}
	}).open();
}
window.name ="Parent_window";
function fn_niceAuth() {
	window.open('/api/nice/call', 'popupNiceCall', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
}

window.addEventListener('message', function (e) {
	if (e.origin !== "https://nice.checkplus.co.kr") {
		return ;
	}

	console.log(e.data.moblphon);
	console.log(e.data.sRequestNumber);
	console.log(e.data.sResponseNumber);
});

$(function (){

	$('#btnSave').on('click',function () {
		$('#btnSave').prop('disabled', true);

		if(!fn_chkField()){
			$('#btnSave').prop('disabled', false);
			return false;
		} else {
			var frm = $('#form1');
			frm.prop('action' , '/api/member/insert')
			frm.submit();
			alert('RES 회원가입이 완료되었습니다.');
		}
	});
	appendYear();


})