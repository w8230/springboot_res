function fn_select(mberPid) {

    $('#mberPid').val(mberPid);

    $.each($('tr[id*=tr_member]'),function (index, value){
        $(this).prop('style','')
    });
    $('#tr_member_' + mberPid).prop('style','background-color:#f5f8fc');

    var jsonData = {
        'mberPid': '' + mberPid + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/menu/authList',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'json',
        success: function (data) {
            $("input[name=menuPid]").prop('checked', false);
            for (var i = 0; i < data.length; i++) {
                var menuPid = data[i].menuPid;
                $('#tr_menu').find($('input[id=menuPid_' + menuPid + ']:checkbox')).prop('checked', true);
            }
        }
    });
}

function fn_chkSave(menuPid) {

    var mberPid = $('#mberPid');
    if (isNaN(parseInt(mberPid.val())) == true || mberPid.val() == '') {
        alert('사용자을 선택해주세요!');
        $('#tr_menu').find($('input[id=menuPid_' + menuPid + ']:checkbox')).attr('checked', false);
        return false;
    }

    var confmAt = $('input[id=menuPid_' + menuPid + ']:checkbox').is(':checked') ? 'Y' : 'N';

    var jsonData = {
        'menuPid': '' + menuPid + ''
        , 'mberPid': '' + mberPid.val() + ''
        , 'confmAt': '' + confmAt + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/menu/authSave', 
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (data === 'ok') {
                /*alert('정상 처리 되었습니다.');
                fn_select();*/
            } else {
                alert('실패되었습니다 관리자에게 문의 하세요');
            }
        }
    });
}

function fn_allSave() {

    var mberPid = $('#mberPid');

    if (isNaN(parseInt(mberPid.val())) == true || mberPid.val() == '') {
        alert('사용자을 선택해주세요!');
        //$('#tr_menu').find($('input[id=menuPid_' + menuPid + ']:checkbox')).attr('checked', false);
        return false;
    }

    var jsonData = {
        'mberPid': '' + mberPid.val() + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/menu/authAllSave',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (data === 'ok') {
                $('#tr_menu input[type=checkbox]').prop('checked', true)
            } else {
                alert('실패되었습니다 관리자에게 문의 하세요');
            }
        }
    });
}

function fn_allDelete() {

    var mberPid = $('#mberPid');

    if (isNaN(parseInt(mberPid.val())) == true || mberPid.val() == '') {
        alert('사용자을 선택해주세요!');
        //$('#tr_menu').find($('input[id=menuPid_' + menuPid.val() + ']:checkbox')).attr('checked', false);
        return false;
    }

    var jsonData = {
        'mberPid': '' + mberPid.val() + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/menu/authAllDelete',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (data === 'ok') {
                $('#tr_menu input[type=checkbox]').prop('checked', false)
            } else {
                alert('실패되었습니다 관리자에게 문의 하세요');
            }
        }
    });
}


function fn_reset() {
    $('#mberPid').val('');
    $('#tr_menu').find($('input[name=menuPid]:checkbox')).attr('checked', false);
}

function fn_search() {
    var frm = $('#form1');
    frm.prop('action', '/admin/system/menuAuth');
    frm.submit();
}

//----------- page load
$(function () {
    fn_reset();

    //전체 선택
    $('.btn_chk_true').on('click', function () {
        //$('#tr_menu input[type=checkbox]').prop('checked', true);
        fn_allSave();
    });
    //선택 해제
    $('.btn_chk_false').on('click', function () {
        // $('#tr_menu input[type=checkbox]').prop('checked', false)
        fn_allDelete();
    });
})