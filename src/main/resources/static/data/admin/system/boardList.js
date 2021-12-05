function fn_search() {
    var frm = $('#form1');
    frm.prop('action', '?');
    frm.submit();
}

function fn_reset() {
    $('#srchGbn').val('').niceSelect('update');
    $('#srchWord').val('');

    fn_search();
}
