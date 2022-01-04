
function layerShow(ele) {
    $('#' + ele).addClass('active')
}
function layerHide(ele) {
    $('#'+ ele).removeClass('active')
}

function fn_hide() {
    console.log('fn run')
    var chk = $('input[name=cateDvTy]:checked').val();


    if( chk === 'MAIN') {
        console.log('main run')
        $('#main_div').show();
        $('#sub_div').hide();
        $('#test1').hide();
        $('#test2').show();

        /*document.getElementById("main_div").style.display="block";
        document.getElementById("sub_div").style.display="none";*/
    } else if (chk === 'SUB') {
        console.log('sub run')
        $('#main_div').hide();
        $('#sub_div').show();
        $('#test1').show();
        $('#test2').hide();

        /*document.getElementById("main_div").style.display="none";
        document.getElementById("sub_div").style.display="block";*/
    }

}
function fn_chkField() {
    console.log('fn run')
    var chk = $('input[name=cateDvTy]:checked').val();
    var categoryNm = $('#category_nm');
    var categoryDsc = $('#category_dsc');
    var subcategoryNm = $('#subcategory_nm');
    var categoryPid = $('#categoryPid');


    if( chk === 'MAIN') {
        if (!$.trim(categoryNm.val())) {
            alert('메인카테고리명을 입력해주세요.')
            categoryPid.focus();
            return false
        }

        if (!$.trim(categoryDsc.val())) {
            alert('메인카테고리의 용도를 입력해주세요.')
            categoryDsc.focus();
            return false
        }
    }

    if (chk === 'SUB') {
        if(!$('#categoryPid > option:selected').val()){
            alert('반드시 메인카테고리를 선택 하여야 합니다.')
            categoryPid.focus();
            return false
        }
        if(!$.trim(subcategoryNm.val())){
            alert('서브카테고리의 이름을 입력해주세요.')
            subcategoryNm.focus();
            return false
        }

    }
    return true;
}
function appendCpid() {
    var catePid = $('selectCate');
    var categoryPid = $('categoryPid');
    categoryPid.val(catePid);
}

////-------- page load
$(function () {

    $('#btnSave').click(function () {
        if (!fn_chkField()) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/admin/operation/category/register');
        frm.submit();
    });
});