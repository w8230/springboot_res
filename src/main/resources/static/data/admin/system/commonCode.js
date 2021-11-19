$(function () {
    doBind();
});

function fn_saveChk(tp) {

    var id = 0;
    var prntCodePid = $("#prntCodePid").val();
    var codeSno = 1;
    var codeNm;
    var codeValue;

    if (tp != 'u') {
        //수정
        id = $('#id').val() == '' ? 0 : $('#id').val();

        codeSno = $('#codeSno').val() == '' ? 0 : $('#codeSno').val();
        codeNm = $('#codeNm');
        codeValue = $('#codeValue');
        if (id <= 0) {
            alert('코드를 선택하세요!');
            return false;
        }
    } else {
        //하위등록
        prntCodePid = $('#id').val() == '' ? 0 : $('#id').val();

        codeSno = $('#u_codeSno').val() == '' ? 0 : $('#u_codeSno').val();
        codeNm = $('#u_codeNm');
        codeValue = $('#u_codeValue');

        if (prntCodePid <= 0) {
            alert('상위코드를 선택하세요!');
            return false;
        }
    }

    if (!$.trim(codeNm.val())) {
        alert('코드명을 작성해주세요!');
        codeNm.focus();
        return false;
    }

    var jsonData = {
        'id': '' + id + ''
        , 'prntCodePid': '' + prntCodePid + ''
        , 'codeSno': '' + codeSno + ''
        , 'codeNm': '' + codeNm.val() + ''
        , 'codeValue': '' + codeValue.val() + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/commonCode/save',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (data === 'ok') {
                alert('정상등록 되었습니다.');
                // document.location.reload();
                if (id == 0) {
                    doBind(prntCodePid);
                } else {
                    doBind(capabilityCodeTree.getParentId(id));
                }

                if (tp != 'u') {
                    /*$('#id').val('');
                    $('#prntCodePid').val('');
                    $('#codeSno').val('');
                    $('#codeNm').val('');
                    $('#codeValue').val('');*/
                } else {
                    //하위등록
                    $('#u_codeSno').val('');
                    $('#u_codeNm').val('');
                    $('#u_codeValue').val('');
                }
            } else {
                alert('실패되었습니다 관리자에게 문의 하세요');
            }
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });
}

function fn_delete() {

    if (!confirm('삭제하시겠습니까?')) {
        return false;
    }
    var id = $('#id').val() == '' ? '0' : $('#id').val();

    var jsonData = {
        'id': '' + id + ''
    };
    $.ajax({
        type: 'post',
        url: '/api/commonCode/delete',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (data === 'ok') {
                alert('정상 처리 되었습니다.');
                doBind(capabilityCodeTree.getParentId(id));
            } else {
                alert('실패되었습니다 관리자에게 문의 하세요');
            }
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });
}

function fn_reset() {
    $('#id').val('');
    $('#prntCodePid').val('');
    $('#codeSno').val('');
    $('#codeNm').val('');
    $('#codeValue').val('');

    $('#u_codeSno').val('');
    $('#u_codeNm').val('');
    $('#u_codeValue').val('');
}

function fn_refresh() {
    fn_reset();
    doBind();
}

/* dhtmlx */
var capabilityCodeTree;
var codeList;

function doBind(parentId) {
    var params = "";

    if (!parentId) {
        parentId = '1';
    }
    var jsonData = {};
    $.ajax({
        type: 'post',
        url: '/api/commonCode/tree',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'json',
        success: function (data) {
            //var chartData = [data['retList'][0]];
            codeList = data;
            doOnLoad(data, parentId);
        }
    });
}

function doOnLoad(chartData, parentId) {
    capabilityCodeTree = new dhtmlXTreeView({
        parent: 'capabilityCodeTree',
        json: chartData
    });
    capabilityCodeTree.attachEvent('onSelect', function (id, state) {
        if (state == true) {
            fn_reset();
            $('#id').val(id);
            $('#prntCodePid').val(id);
            fn_select(id);
        }
    });
    capabilityCodeTree.openItem('1');
    if (parentId != '1') {
        capabilityCodeTree.openItem(parentId);
    }
}

function fn_select(id) {
    capabilityCodeTree.openItem(id);
    var id = id;
    //console.log('fn_select : id => ', id);
    var jsonData = {
        'id': '' + id + ''
    };

    $.ajax({
        type: 'post',
        url: '/api/commonCode/load',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'json',
        success: function (data) {
            //var jsonObj = JSON.parse(data);
            //var obj = data['retList'];

            $('#prntCodePid').val(data.prntCodePid);
            //$('#companyCd').val(data.company_cd);
            //$('#u_company_cd').val(data.company_cd);
            $('#codeSno').val(data.codeSno);
            $('#codeNm').val(data.codeNm);
            $('#codeValue').val(data.codeValue);
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });

    //$('#wrtModal').modal();
    return false;
}