function fn_openModal(){
    document.querySelector('.category_modal').style.display = 'block';
    document.querySelector('.modal_bg').style.display = 'block';
}

function fn_closeModal(){
    document.querySelector('.category_modal').style.display = 'none';
    document.querySelector('.modal_bg').style.display = 'none';
}

function layerShow(ele) {
    $('#' + ele).addClass('active')
}
function layerHide(ele) {
    $('#'+ ele).removeClass('active')
}
