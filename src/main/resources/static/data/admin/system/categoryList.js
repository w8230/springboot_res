var chk
var categoryNm
var categoryDsc
var subcategoryNm
var categoryPid
var selectCate

function fn_cateDvTy() {
    var chk = $('input[name=cateDvTy]:checked').val();

    if(chk === 'MAIN'){
        console.log('main ajax');
        $.ajax({

            url: '/admin/operation/category/list'
        })
        location.reload();
    }
    if(chk === 'SUB'){
        console.log('sub ajax');
        $.ajax({
            url: '/admin/operation/category/sub/list'
        })
        location.reload();
    }
}

////-------- page load
$(function () {

    /*$('.cateDvTyMAIN[value="MAIN"]').prop('checked' ,true);*/
    $('#cateDvTyMAIN').prop('checked' , true);
    $('#sub_div').hide();

    $('#btnSave').click(function () {
        if (!fn_chkField()) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/admin/operation/category/register');
        frm.submit();
    });
});