
var banDvTy = {
    '[[${T(kr.co.team.res.domain.enums.BanDvTy).MAIN.name()}]]' : {'pc' : '1920 * 792', 'mobile' : '1280 * 1186'},
    '[[${T(kr.co.team.res.domain.enums.BanDvTy).CARD.name()}]]' : {'pc' : '625 * 250', 'mobile' : '625 * 250'}
};

function fn_chkField(){
    var banNm = $("#banNm");
    var dsc = $("#dsc");
    var stYmd = $('#stYmd');
    var edYmd = $('#edYmd');
    var imgFl = $('#imgFl');


    if(!$.trim(banNm.val())){
        alert("제목은 필수입니다.");
        banNm.focus();
        return false;
    }
    if(!$.trim(dsc.val())){
        alert("설명은 필수입니다.");
        dsc.focus();
        return false;
    }
    if (!$.trim(stYmd.val()) || !$.trim(edYmd.val())) {
        alert('기간은 필수입니다');
        stYmd.val() == '' ? stYmd.focus() : edYmd.focus();
        return false
    }
    if (!imgFl.val()) {
        if ($('input[name=attachImgFl]').length == 1) {
            alert('이미지는 필수입니다.');
            return false;
        }
    }

    return true;
}

$(function(){
    $('#btnSave').click(function () {
        if (!fn_chkField()) {
            return;
        }

        var frm = $('#form1');
        frm.prop('action', '/soulGod/banner/register');
        frm.submit();
    });

    fn_setDatePicker('#stYmd', {
        onShow:function( ct ){
            this.setOptions({
                maxDate:$('#edYmd').val()?$('#edYmd').val():false
            });
        }
    });

    fn_setDatePicker('#edYmd', {
        onShow:function( ct ){
            this.setOptions({
                minDate:$('#stYmd').val()?$('#stYmd').val():false
            });
        }
    });
    $('input[name=banDvTy]').change(function (){
        var ty = $(this).val();
        $("#pcBanWidth").html(banDvTy[ty].pc);
        $("#mobileBanWidth").html(banDvTy[ty].mobile);
    });
    $('input[name=banDvTy]:input[value='+'[[${form.banDvTy.name()}]]'+']').trigger('click');
});