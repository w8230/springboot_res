$(function () {

    $('#btnDelete').click(function () {
        if (!confirm('삭제하시겠습니까?')) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/soulGod/banner/delete');
        frm.submit();
    });
});