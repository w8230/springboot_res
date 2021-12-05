	var userId;
	var userPw;
	var msg;
	var errormsg;
	let rsMsg;

	$(function () {
	rsMsg = '[[${rsMsg}]]';
	msg = $('#msg');

	/*if(rsMsg){
        alert(rsMsg);
        location.href='/logout?dv=n';
    }

    if (msg.val()) {
        alert(msg.val());
    }*/

	errormsg = $('#errormsg');
	if (errormsg.val()) {
	alert(errormsg.val());
}
});

	function fn_chkLogin() {
	var btnLogin = $("#btnLogin");
	userId = $("#userId")
	userPw = $("#userPw")

	var frm = $('#form1');
	frm.prop("action", "/login");
	frm.submit();

}