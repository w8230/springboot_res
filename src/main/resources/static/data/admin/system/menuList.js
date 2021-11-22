function fn_select(id) {
    $("#id").val(id);
    //$('#tr_' + id + ' td').css('background', '#efefef');
    $('#btnDelete').show();

    var jsonData = {
        'id': '' + id + ''
    };

    $.ajax({
        url: '/api/menu/load', // 요청 할 주소
        //async:true, // false 일 경우 동기 요청으로 변경
        type: 'POST', // GET, POST
        contentType: "application/json",
        data: JSON.stringify(jsonData), // 전송할 데이터
        dataType: 'json',// xml, json, script, html
        success: function (data) {
            //$('#id').val(data['id']);
            $('#menuNm').val(data['menuNm']);
            $('#menuUrl').val(data['menuUrl']);
            $('#newwinAt').val(data['newwinAt']).niceSelect('update');
            $('#menuGroupCdPid').val(data['menuGroupCdPid']).niceSelect('update');
            $('#menuSn').val(data['menuSn']);
        },// 요청 완료 시
        error: function (xhr, status, err) {
            if (xhr.status == 401) {
                alert("인증에 실패 했습니다.");
            } else if (xhr.status == 403) {
                alert("세션이 만료가 되었습니다.");
            }
        }// 요청 실패.
        //complete:function(msg) {console.log('complete :' + msg.toString());}// 요청의 실패, 성공과 상관 없이 완료 될 경우 호출
    });
    return;
}

var id;
var menuNm;
var menuUrl;
var newwinAt;
var menuGroupCdPid;
var menuSn;

function fn_chkField() {

    id = $('#id');
    menuNm = $("#menuNm");
    menuUrl = $("#menuUrl");
    newwinAt = $("#newwinAt");
    menuGroupCdPid = $('#menuGroupCdPid');
    menuSn = $('#menuSn');

    if (!$.trim(menuNm.val())) {
        alert('메뉴명을 입력하세요');
        menuNm.focus();
        return false;
    }

    if (!$.trim(menuUrl.val())) {
        alert('경로를 입력하세요');
        menuUrl.focus();
        return false;
    }

    if (!$.trim(menuSn.val())) {
        alert('메뉴순번을 입력하세요');
        menuSn.focus();
        return false;
    }

    return true;
}


function fn_resetForm() {
    $('#id').val('');
    $('#menuNm').val('');
    $('#menuUrl').val('');
    $('#newwinAt').val('N').niceSelect('update');
    $('#menuGroupCdPid').val('').niceSelect('update');
    $('#menuSn').val('')

    $('#btnDelete').hide();
    //$('#btnModify').hide()
}

$(function () {
    fn_resetForm();

    $('#btnSave').click(function () {
        if (!fn_chkField()) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/api/menu/save');
        frm.submit();
    });

    $('#btnDelete').click(function () {
        if (!confirm('삭제하시겠습니까?')) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/api/menu/delete');
        frm.submit();
    });
    $("#srchMnGbnCdPid").change(function () {
        var frm = $('#form1');
        frm.prop('action', '?');
        frm.submit();
    });
});