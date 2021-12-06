function fn_search() {
    var frm = $('#form1');
    frm.prop('action', '?');
    frm.submit();
}

function fn_reset() {
    $('#srchWord').val('');
    $('#useAt').val('');
    $('#banDvTy').val('');

    fn_search();
}

$(function(){
});