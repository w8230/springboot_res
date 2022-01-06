var chk
var categoryNm
var categoryDsc
var subcategoryNm
var categoryPid
var selectCate

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

        /*document.getElementById("main_div").style.display="block";
        document.getElementById("sub_div").style.display="none";*/
    } else if (chk === 'SUB') {
        console.log('sub run')
        $('#main_div').hide();
        $('#sub_div').show();

        /*document.getElementById("main_div").style.display="none";
        document.getElementById("sub_div").style.display="block";*/
    }

}
function fn_chkField() {
    console.log('fn run')
    chk = $('input[name=cateDvTy]:checked').val();
    categoryNm = $('#category_nm');
    categoryDsc = $('#category_dsc');
    subcategoryNm = $('#subcategory_nm');
    categoryPid = $('#categoryPid');
    selectCate = $('#selectCate');


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

        categoryPid.val(selectCate.val());

        if(!$.trim(categoryPid.val())){
            alert('반드시 메인카테고리를 선택 하여야 합니다.')
            categoryPid.focus();
            return false
        }
        /*if(!$('#categoryPid > option:selected').val()){

        }*/
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