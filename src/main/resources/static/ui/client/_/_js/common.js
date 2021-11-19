/*정규식 체크*/
function fn_chkField(){

}
function fn_comparePwd(){
    pwd = $('#pwd');
    pwdChk = $('#pwdChk');
    pwdChk.siblings('.err.emph').remove();
    if(pwd.val() != pwdChk.val()){
        alert('비밀번호가 다릅니다.');
    }
}
/*
//responseText 분석하여 작성 할 것.
function fn_chkIdDup(){
    loginId = $('#loginId');
    loginId.siblings('.err.emph').remove();

    if(!loginIdCheck(loginId)) {
        return false;
    }
    $.ajax({
        type: "POST",
        url: "/api/member/isExistsByLoginId",
        data: {
            "loginId" : loginId.val()
        },
        success: function (data){
            if(loginId.val() != ''){
                alert('사용가능한 아이디 입니다.')
                idDupBool = true;
            }
        },
        error: function (data) {
            //responseText 작성 할 것.
            alert(error.responseText);
            idDupBool = false;
            loginId.val('');
            loginId.focus();
        }
    });

}
*/

/*function loginIdCheck(loginId, errTgt) {
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
}*/

function pwdCheck(pwd) {
    pwd.siblings('.err.emph').remove();
    var regExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

    if($.trim(pwd.val()) == ''){
        alert('비밀번호를 입력해주세요.');
        pwd.focus();
        return false;
    }
    if(regExp.test($.trim(pwd.val())) == false) {
        alert('비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.');
        return false;
    }
    return true;
}
/*이메일 인증 체크*/
function fn_chkEmail() {
    if(tempKey == $('.eKey').val()) {
        alert('인증되었습니다.');
        $('.eKey').attr("disabled", true);
        $('#authEmailChk').val(2)
        /*$('#chkTempKeyBtn').text('인증완료');
        $('#sendEmailBtn').attr('disabled',true);
        $('#chkTempKeyBtn').attr('disabled',true);
        $('input[name=authPrTy]').attr('disabled',true);
        $('#authFiledPHONE_AUTH').hide();
        $('#authFiledEMAIL_AUTH').hide();
        $('#resultText').text('이메일 인증완료');*/
    } else {
        alert('인증번호가 올바르지 않습니다.');
        $('.eKey').text('');
        $('.eKey').focus();
    }
}
function fn_chkMoblphon(){
    if(tempKey == $('.eKey').val()){
        alert('인증되었습니다.')
        $('.eKey').attr("disabled" , true);
        $('#authMoblileChk').val(2)
        /*$('#chkTempKeyBtn').text('인증완료');
         $('#sendEmailBtn').attr('disabled',true);
         $('#chkTempKeyBtn').attr('disabled',true);
         $('input[name=authPrTy]').attr('disabled',true);
         $('#authFiledPHONE_AUTH').hide();
         $('#authFiledEMAIL_AUTH').hide();
         $('#resultText').text('이메일 인증완료');*/
    } else {
        alert('인증번호가 올바르지 않습니다.')
        $('.eKey').text('')
        $('.eKey').focus();
    }
}
/*핸드폰 인증 체크*/
function fn_chkMoblPhon() {

}