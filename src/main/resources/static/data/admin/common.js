$(document).ready(function() {
    $('select').niceSelect();
});

function getCenterLeft(nWidth) {
    var nAvail = getMaxWidth() - nWidth;
    if (nAvail <= 0)
        return 0;
    return parseInt(Number(nAvail / 2));

}

function getCenterTop(nHeight) {
    var nAvail = getMaxHeight() - nHeight;
    if (nAvail <= 0)
        return 0;
    return parseInt(Number(nAvail / 2));

}

function getMaxWidth() {
    return screen.availWidth;
}

function getMaxHeight() {
    return screen.availHeight - 55;
}

function openPopUpWindow(strURL, strName, nWidth, nHeight, nScrollbars, option) {
    var strMsg = "can't open popup window";
    var strFeatures = "";

    var nTop = getCenterTop(nHeight);
    var nLeft = getCenterLeft(nWidth);

    var nToolbar = 0;
    var nDirectory = 0;
    var nFullscreen = 0;

    var nLocation = 0;
    var nMenubar = 0;
    var nResizable = 1;
    var nScrollbars = nScrollbars;
    var nStatus = 0;
    var nTitlebar = 1;
    strFeatures = "top=" + (option && option.top ? option.top : nTop);
    strFeatures += ",left=" + (option && option.left ? option.left : nLeft);
    strFeatures += ",width=" + (option && option.width ? option.width : nWidth);
    strFeatures += ",height=" + (option && option.height ? option.height : nHeight);
    strFeatures += ",toolbar=" + (option && option.toolbar ? option.toolbar : nToolbar);
    strFeatures += ",directory=" + (option && option.directory ? option.directory : nDirectory);
    strFeatures += ",fullscreen=" + (option && option.fullscreen ? option.fullscreen : nFullscreen);
    strFeatures += ",location=" + (option && option.location ? option.location : nLocation);
    strFeatures += ",menubar=" + (option && option.menubar ? option.menubar : nMenubar);
    strFeatures += ",resizable=" + (option && option.resizable ? option.resizable : nResizable);
    strFeatures += ",scrollbars =" + (option && option.scrollbars != null ? option.scrollbars : nScrollbars);
    strFeatures += ",status=" + (option && option.status ? option.status : nStatus);
    strFeatures += ",titlebar=" + (option && option.titlebar ? option.titlebar : nTitlebar);
    try {

        var popWnd = window.open(strURL, strName, strFeatures);

        if (popWnd == null) {
            alert(strMsg);
            return null;
        }
        popWnd.focus();
        return popWnd;
    } catch (e) {
        alert(e.message);
        return null;
    }

}

function makeYearOption(option) {
    var now = new Date();
    var _option = {
        'count': option && option.count ? option.count : 5,
        'startYear': option && option.startYear ? option.startYear : 2000,
        'endYear': option && option.endYear ? option.endYear : now.getFullYear(),
        'all': option && option.all != null ? option.all : true,
        'selected': option && option.selected ? option.selected : ''
    }
    var rtnHtml = "";
    if (_option.all == true) {
        rtnHtml += "<option value=''>전체</option>";
    }
    if (_option.count > 0) {
        for (var y = now.getFullYear(); y > (now.getFullYear() - _option.count); y--) {
            rtnHtml += "<option value='" + y + "'"
            if (_option.selected == y) {
                rtnHtml += " selected "
            }
            rtnHtml += ">" + y + "</option>";
        }
    } else {
        for (var y = _option.startYear; y <= _option.endYear; y++) {
            rtnHtml += "<option value='" + y + "'"
            if (_option.selected == y) {
                rtnHtml += " selected "
            }
            rtnHtml += ">" + y + "</option>";
        }
    }
    return rtnHtml;
}

function fnTableToExcel(tableId, fileNm) {
    var table = $("#" + tableId);
    if (table && table.length) {
        var preserveColors = (table.hasClass('table2excel_with_colors') ? true : false);
        $(table).table2excel({
            exclude: ".noExl",
            name: "Excel Document Name",
            filename: fileNm + '.xls',
            fileext: ".xls",
            exclude_img: true,
            exclude_links: true,
            exclude_inputs: true,
            preserveColors: preserveColors
        });
    } else {
        alert("존재하지 않는 테이블 입니다.");
        return false;
    }
}


function fn_openAll(treeObj, codeList) {
    if (treeObj && codeList) {
        fn_openTree(codeList);
    }
}
function fn_openTree(obj) {
    if (!obj || obj.length == 0) {
        return false;
    }
    for (var i=0; i<obj.length; i++) {
        var id = obj[i].id;
        //if (capabilityCodeTree.getLevel(id) < 3) {
        try {
            capabilityCodeTree.openItem(id);
            if (obj[i].items) {
                fn_openTree(obj[i].items);
            }
        } catch(e) {
            //console.log(e);
        }
        //}
    }
}
function fn_closeAll(treeObj, codeList) {
    if (treeObj && codeList) {
        fn_closeTree(codeList);
    }
}
function fn_closeTree(obj) {
    if (!obj || obj.length == 0) {
        return false;
    }
    for (var i=0; i<obj.length; i++) {
        var id = obj[i].id;
        //if (capabilityCodeTree.getLevel(id) < 3) {
        try {
            capabilityCodeTree.closeItem(id);
            if (obj[i].items) {
                fn_closeTree(obj[i].items);
            }
        } catch(e) {
            //console.log(e);
        }
        //}
    }
}
function fn_searchAll(treeObj, codeList) {
    if (treeObj && codeList) {
        fn_searchTree(codeList);
    }
}
function fn_treeParentOpenAll(id) {
    try {
        var parentId = capabilityCodeTree.getParentId(id);
        if (parentId) {
            capabilityCodeTree.openItem(parentId);
            fn_treeParentOpenAll(parentId);
        }
    } catch(e) {
        //console.log(e);
    }
}
function fn_searchTree(obj) {
    if (!obj || obj.length == 0) {
        return false;
    }
    var search = $('#srch_cd_nm').val();
    console.log(obj);
    console.log(search);
    for (var i=0; i<obj.length; i++) {
        var id = obj[i].id;
        //if (capabilityCodeTree.getLevel(id) > 0) {
        try {
            var text = capabilityCodeTree.getItemText(id);
            console.log(text);
            if(text.indexOf(search) >= 0) {
                fn_treeParentOpenAll(id);
                /*var parentId = capabilityCodeTree.getParentId(id);
                if (parentId) {
                    capabilityCodeTree.openItem(parentId);
                    var parentParentId = capabilityCodeTree.getParentId(parentId);
                    if (parentParentId) {
                        capabilityCodeTree.openItem(parentParentId);
                    }
                }*/
                //capabilityCodeTree.openItem(id);
                //var cnt = capabilityCodeTree.getSubItems(id);
                //var addItem = cnt && cnt.length > 0 ? " ("+cnt.length+")" : "";
                if (text.indexOf("background-color:yellow") <= 0) {
                    text = text.replaceAll(search, "<span style='background-color:yellow'>"+search+"</span>");
                    text = text.replace(/undefined/gi, "/");
                }
            } else {
                text = text.replaceAll("<span style='background-color:yellow'>", "");
                text = text.replaceAll("</span>", "");
            }
            capabilityCodeTree.setItemText(id, text);
            console.log(obj[i].items);
            if (obj[i].items) {
                fn_searchTree(obj[i].items);
            }
        } catch(e) {
            console.log(e);
        }
        //}
    }
}

String.prototype.replaceAll = function (org, dest) {
    return this.split(org).join(dest);
}

function fn_fileDownload(path, name) {
    location.href = "/api/common/download?path=" + path + "&name=" +  encodeURI(name);
    return false;
}

function fn_fileDownloadZip(table_id_arr, table_nm) {
    location.href = "/api/v1/common/file/downloadZip?table_id_arr=" + table_id_arr + "&table_nm=" + table_nm
}

function makeFck(objName, callback) {
    try {
        var editor = CKEDITOR.replace(objName, {
            removePlugins: 'easyimage, cloudservices',
            filebrowserUploadUrl: '/api/common/ckeditor/fileUpload?',
            allowedContent: true /*200720 추가*/
        });

        if (callback && typeof callback === 'function') {
            callback(objName, editor);
        }
    } catch (e) {

    }
    /*ClassicEditor.create(document.querySelector('#' + objName), {
        toolbar: ['heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote', 'insertTable','undo','redo'],
        autoParagraph: false,
        enterMode: 'div',
        ShiftEnterMode: 'br'
    }).then(editor=>{
        //editor.setData(ckdata);
        //this.ckEditor = editor;
        //console.log(editor);
        if (callback && typeof callback === 'function') {
            callback(objName, editor);
        }
    }).catch(err=> {
        console.error(err.stack);
    });*/
}

function makeFckWithImage(objName, callback) {
    try {
        var editor = CKEDITOR.replace(objName, {
            removePlugins: 'easyimage, cloudservices',
            filebrowserUploadUrl: '/api/common/ckeditor/fileUpload?',
            allowedContent: true /*200720 추가*/
        });

        if (callback && typeof callback === 'function') {
            callback(objName, editor);
        }
    } catch (e) {

    }
    /*ClassicEditor.create(document.querySelector('#' + objName), {
        toolbar: ['heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote', 'insertTable','undo','redo','imageUpload'],
        autoParagraph: false,
        enterMode: 'div',
        ShiftEnterMode: 'br',
        ckfinder: {
            uploadUrl: '/api/common/ckeditor/fileUpload'
        }
    }).then(editor=>{
        if (callback && typeof callback === 'function') {
            callback(objName, editor);
        }
    }).catch(err=> {
        console.error(err.stack);
    });*/
}

function setCookie(name, value, expirehours, domain) {
    var today = new Date();
    today.setTime(today.getTime() + (60 * 60 * 1000 * expirehours));
    document.cookie = name + "=" + escape(value) + "; path=/; expires=" + today.toGMTString() + ";";
    if (domain) {
        document.cookie += "domain=" + domain + ";";
    }
}

function getCookie(name) {
    var find_sw = false;
    var start, end;
    var i = 0;
    for (i = 0; i <= document.cookie.length; i++) {
        start = i;
        end = start + name.length;
        if (document.cookie.substring(start, end) == name) {
            find_sw = true
            break
        }
    }
    if (find_sw == true) {
        start = end + 1;
        end = document.cookie.indexOf(";", start);
        if (end < start) end = document.cookie.length;
        return unescape(document.cookie.substring(start, end));
    }
    return "";
}

function fn_setDhtmlxVault(id, fileList, option) {
    $("#" + id).html("");
    var vault = new dhx.Vault(id, {
        mode: "grid",
        uploader: {
            target: option.uploadUrl,
            downloadURL: ""
        }
    });
    vault.data.parse(fileList);

    vault.toolbar.data.add({
        type: "iconButton",
        id: "all-download",
        tooltip: "All download",
        icon: "dxi-download"
    }, 2);
    /*vault.toolbar.data.add({
        type: "text",
        id: "size"
    }, 3);*/

    vault.toolbar.events.on("click", function (id) {
        if (id === "calc-size") {
            var size = vault.data.reduce(function (sum, item) {
                return sum + item.size;
            }, 0);
            vault.toolbar.data.update("size", {value: getBasis(size)});
            vault.toolbar.show("size");
        } else if (id === "all-download") {
            var data = vault.data;
            if (data.getLength() == 0) {
                alert("파일이 없습니다.");
                return false;
            }
            if (!confirm("해당디스크의 모든 파일을 다운로드하시겠습니까?")) {
                return false;
            }
            var table_id_arr = option.table_id;
            var table_nm = option.table_nm;
            location.href = "/api/v1/common/file/downloadZip?table_id_arr=" + table_id_arr + "&table_nm=" + table_nm
        } else if (id === "remove-all") {
            var data = vault.data;
            if (data.getLength() == 0) {
                alert("파일이 없습니다.");
                return false;
            }
            if (!confirm("해당디스크의 모든 파일을 삭제하시겠습니까?")) {
                return false;
            }
            fn_dhtmlxVaultFileDelete(option.deleteUrl);
        }
    });
    vault.data.events.on("change", function () {
        if (!vault.data.getLength()) {
            vault.toolbar.data.update("size", {value: ""});
            vault.toolbar.hide(["calc-size", "size"]);
        } else {
            vault.toolbar.show(["calc-size", "size"]);
        }
    })
    /*vault.data.events.on("keydown", function() {
        alert("asdasd");
    })*/
    vault.events.on("BeforeRemove", function (file) {
        if (!confirm(file.name + " 을(를) 삭제하시겠습니까?")) {
            return false;
        }

        fn_dhtmlxVaultFileDelete(option.deleteUrl, file.file_id);

        return true;
    });
}

function fn_dhtmlxVaultFileDelete(deleteUrl, file_id) {
    if (file_id) {
        deleteUrl = deleteUrl + "&file_id=" + file_id;
    }
    var jsonData = {};

    $.ajax({
        type: 'post',
        url: deleteUrl,
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'json',
        success: function (data) {

        },
        error: function (error) {
            alert('error::' + error.statusText);
        }
    });
}

function fn_makeCalendar(id, option) {
    if (!option) {
        option = {};
    }

    console.log(option);
    var jsonData = {
        'empno': '' + (option.empno ? option.empno : '') + ''
    };

    $.ajax({
        type: 'post',
        url: '/api/common/getCalendar',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'json',
        success: function (data) {
            fn_initCalendar(id, data, option);
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });
}

function fn_initCalendar(id, list, option) {
    if (!id || !list) {
        return false;
    }
    dhtmlXCalendarObject.prototype.langData['ko'] = {
        monthesFNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
        daysSNames: ["일", "월", "화", "수", "목", "금", "토"],
    }

    var myCalendar = new dhtmlXCalendarObject(id);
    myCalendar.hideTime();
    myCalendar.show();
    myCalendar.setWeekStartDay(7);
    myCalendar.loadUserLanguage('ko');

    var holidayObj = {};
    var tooltipObj = {};
    $.each(list, function (index, item) {
        var date = getBetweenDates(setStringToDate(item.st_ymd), setStringToDate(item.end_ymd));
        $.each(date, function (index2, item2) {
            if (item.restde_yn == 'Y') {
                holidayObj = setCalendarDupCheck(holidayObj, item2, item.ttl);
            }
            tooltipObj = setCalendarDupCheck(tooltipObj, item2, item.ttl);
        });
    });

    for (var item in holidayObj) {
        myCalendar.setHolidays(item);
    }
    for (var item in tooltipObj) {
        myCalendar.setTooltip(item, tooltipObj[item], true, true);
    }

    myCalendar.attachEvent("onClick", function (date) {
        if (option.callback) {
            option.callback(date.format('yyyy-MM-dd'));
        }
    });
}

function setCalendarDupCheck(obj, date, text) {
    if (obj[date]) {
        obj[date] += "<br/>" + text
    } else {
        obj[date] = text;
    }
    return obj;
}

Date.prototype.addDays = function (days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

function getBetweenDates(startDate, stopDate) {
    var dateArray = [];
    var currentDate = startDate;
    while (currentDate <= stopDate) {
        dateArray.push(new Date(currentDate).format('yyyy-MM-dd'));
        currentDate = currentDate.addDays(1);
    }
    return dateArray;
}

function setStringToDate(str) {
    var yyyy = str.substr(0, 4);
    var mm = str.substr(5, 2);
    var dd = str.substr(8, 2);

    return new Date(yyyy, mm - 1, dd);
}

function isApp() {
    var userAgent = navigator.userAgent;
    return (userAgent.indexOf('N2_FULL_APP') > 0);
}

function osCheck() {
    var userAgent = navigator.userAgent.toLowerCase(); //userAgent 값 얻기
    if (userAgent.match('android') != null) {
        return "ANDROID";
    } else if (userAgent.indexOf("iphone") > -1 || userAgent.indexOf("ipad") > -1 || userAgent.indexOf("ipod") > -1) {
        return "IOS";
    } else {
        return "";
    }
}

function fn_setDatePicker(selector, option) {
    if (!option) {
        option = {};
    }
    option.timepicker = false;
    option.format = 'Y-m-d';
    option.scrollMonth = false;
    $(selector).datetimepicker(option);
}
function fn_setDateTimePicker(selector, option) {
    if (!option) {
        option = {};
    }
    option.format = 'Y-m-d H:i';
    option.scrollMonth = false;
    $(selector).datetimepicker(option);
}
function fn_setTimePicker(selector, option) {
    if (!option) {
        option = {};
    }
    option.datepicker = false;
    option.format = 'H:i';
    option.scrollMonth = false;
    $(selector).datetimepicker(option);
}
function fn_deleteFile(id, obj, callback) {
    if (!id) {
        return false;
    }
    if (confirm("파일을 삭제하시겠습니까?")) {
        var jsonData = {
            'id': '' + id + ''
        };
        $.ajax({
            type: 'post',
            url: '/api/common/file/delete',
            contentType: "application/json",
            data: JSON.stringify(jsonData),
            datatype: 'text',
            success: function (data) {
                if(data ===  'ok'){
                    if (obj) {
                        $(obj).parent().remove();
                    }
                    if (callback && typeof callback == 'function') {
                        callback(id);
                    } else {
                        alert('삭제 되었습니다.');
                    }
                }else{
                    alert('실패되었습니다 관리자에게 문의 하세요');
                }
            },
            error: function (error) {
                //alert('save error!!!');
                alert('error::' + error.statusText);
            }
        });
    }
}

function winPop(url, title, w, h){
    var top = (screen.height/2)-(h/2),
        left = (screen.width/2)-(w/2);
    return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}
function winPop(url, title, w, h, s){
    var top = (screen.height/2)-(h/2),
        left = (screen.width/2)-(w/2);
    return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars='+s+', resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}

function execPostCode(zipCd,addr,dtlAddr) {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            //console.log(data.zonecode);
            //console.log(fullRoadAddr);


            $('#' + zipCd).val(data.zonecode);
            $('#' + addr).val(fullRoadAddr);
            $('#' + dtlAddr).focus();

            /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
            document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
        }
    }).open();
}

function fn_linkUrl(url, target) {
    var jsonData = {
        'cnctUrl': url
    };
    $.ajax({
        type: 'post',
        url: '/api/openData/extraLink',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            console.log(data);
            if (target) {
                window.open(url);
            } else {
                location.href = url;
            }
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });
}

function fn_audioEndCheck(id, url, ty, callback) {
    if (!url || !ty) {
        return false;
    }
    var isFirst = true;
    var audio = document.querySelector(id);

    audio.addEventListener('ended', function(){
        fn_viewVideo(url, ty, callback);
        isFirst = false;
    });
}

function fn_videoEndCheck(url, ty, callback) {
    if (!url || !ty) {
        return false;
    }
    if (ty == 'YOUTUBE') {
        var isFirst = true;
        var video2 = videojs('video').ready(function(){
            var player = this;
            player.on('ended', function() {
                if (!isFirst) {
                    return false;
                }
                $("#video").stop();
                fn_viewVideo(url, ty, callback);
                isFirst = false;
            });
        });
    } else if (ty == 'CDN') {
        var isFirst = true;
        var video = document.querySelector('video');

        video.addEventListener('ended', function(){
            fn_viewVideo(url, ty, callback);
            isFirst = false;
        });
    }
}

function fn_viewVideo(url, ty, callback) {
    var jsonData = {
        'cntntsUrl': url,
        'cntntsDvTy' : ty
    };
    $.ajax({
        type: 'post',
        url: '/api/openData/viewVideo',
        contentType: "application/json",
        data: JSON.stringify(jsonData),
        datatype: 'text',
        success: function (data) {
            if (callback && typeof callback === 'function') {
                callback(data);
            }
        },
        error: function (error) {
            //alert('save error!!!');
            alert('error::' + error.statusText);
        }
    });
}