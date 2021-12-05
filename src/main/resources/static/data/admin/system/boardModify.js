function fn_chkField() {
    var id = $('#id');
    var bbsTy = $("#bbsTy");
    var bbsNm = $("#bbsNm");
    var bbsUpendCn = contentEditor.getData();

    if (!$.trim(bbsNm.val())) {
        alert('게시판명은 필수입니다.');
        bbsNm.focus();
        return false;
    }

    if($.trim(bbsUpendCn) == ''){
        alert('게시판상단 내용은 필수입니다..');
        contentEditor.focus();
        return false;
    }

    return true;
}

function setEditor(name, editor) {
    contentEditor = editor;
}

////-------- page load
$(function () {
    $('#btnSave').click(function () {
        if (!fn_chkField()) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/soulGod/boardMaster/modify');
        frm.submit();
    });

    $('#btnDelete').click(function () {
        if (!confirm('삭제하시겠습니까?')) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/soulGod/boardMaster/delete');
        frm.submit();
    });
});