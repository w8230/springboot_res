$(document).ready(function(){


    //상단으로 스크롤
    $('.btn_top').on('click', function(){
        $('html, body').stop().animate({'scrollTop':0},500)
    })

	//캘린더
    /*$('.form_date').datetimepicker({
        timepicker:false,
        format:'Y-m-d',
        scrollMonth:false
    });

    $('.form_datetime').datetimepicker({
        format:'Y-m-d H:i',
        scrollMonth:false
    });

    $('.form_time').datetimepicker({
        datepicker:false,
        format:'H:i',
        scrollMonth:false
    });*/
	
	//날짜 기간 선택
	$('.btn_term').each(function(){
		$(this).on('click',function(){
			$(this).addClass('active').siblings('.btn_term').removeClass('active')
		})
	})

	//ie input type number
	$('input[type=number]').on('keyup', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	});

	//nav tab
	$('.nav_tab > li').each(function(){
		$(this).find('a').on('click',function(){
			$(this).parent().addClass('active').siblings().removeClass('active');
		})
		$(this).find('.nav_tab_close').on('click',function(){
			$(this).parent().remove();
		})
	})
	
	//tab
	$('.tab').each(function(){
		var tab = $(this);
		tab.find('li').each(function(){
			var li = $(this),
				 idx = li.index();
			li.find('a').on('click', function(e){
				if($(this).attr('href') == '#'){
					e.preventDefault();
				}
				li.addClass('active').siblings().removeClass('active');
				
				if(tab.parent('div').hasClass('tab_area')){
					tab.siblings('.tab_cont').find('.cont').eq(idx).addClass('active').siblings().removeClass('active');
				}
			})
		})
	})
	
	//레이어 팝업 딤 클릭 시 팝업 닫기
	$('.pop_layer').each(function(){
		$(this).children('.dim').on('click', function(){
			$(this).parent('.pop_layer').removeClass('active');
			$('html').removeAttr('style');
		})
	})

	//이메일 주소에서 직접입력 선택시 입력창 출력
	$('.email_form').each(function(){
		$(this).find('select').on('change',function(){
			if($(this).val() == '직접입력'){
				$(this).parents('.email_form').find('.email_inp').addClass('active').val('').focus()
			}else{
				$(this).parents('.email_form').find('.email_inp').removeClass('active').val('')
			}
		})
	})

	$('select').niceSelect();

	//부서 토글
	fn_toggleDept();

    //커스텀 스크롤바
	$('.scrollbar, .popup').each(function(index, el){
	    new SimpleBar(el,{
	        autoHide:false
	    })
	});

    //file_form 스크립트 적용
    setFileForm();
    lnb(); termForm();

    $(window).resize(function(){
        lnb(); termForm();
    })
})

//lnb responsive
function lnb(){
    var winW = $(window).width();

    if(winW > 1300){
        $('#wrap').removeClass('close active')
    }else{
        $('#wrap').addClass('close').removeClass('active')
        $('.btn_menu').on('click',function(){
            $(this).parents('#wrap').toggleClass('active')
        })
    }
}

//term
function termForm(){
    $('.term_form').each(function(){
        var tdW = $(this).parent('td').width();

        if(tdW <= 296 && tdW >= 282){
            $(this).addClass('w100p')
        }else{
            $(this).removeClass('w100p')
        }

        if(tdW < 283){
            $(this).addClass('full')
        }else{
            $(this).removeClass('full')
        }
    })
}

//file_form 스크립트 적용
function setFileForm(className) {
    className = className ? className : '.file_form';
    $(className).each(function(){
        var fileForm =$(this),
             filePlaceholder = fileForm.find('.file_name').text();

        //첨부 파일 삭제
        fileForm.find('.btn_file_del').on('click',function(){
            var agent = navigator.userAgent.toLowerCase();
            if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ){ //ie
                fileForm.find('input').replaceWith($('.file_form input').clone(true));
            }else{//other browser
                fileForm.find('input').val('');
                fileForm.removeClass('active').find('.file_name').text(filePlaceholder)
            }
        });

        //첨부 파일 multilfile에서 label 클릭 시 첨부 파일 업로드
        fileForm.find('label').on('click',function(){
            fileForm.find('.MultiFile-wrap input').each(function(){
                if(!$(this).attr('style')){
                    $(this).click()
                }
            })
        })
    });
}

//첨부파일
function uploadChange(file){
	var node = file.previousElementSibling;
	if(node.className == 'file_name' || node.className == 'file_name active'){
		node.innerHTML = file.value;
	}
	file.parentElement.classList.add('active')
}

//글자수 제한
function maxLengthCheck(object){
	if(object.value.length > object.maxLength){
		object.value = object.value.slice(0, object.maxLength);
	} 
}

//레이어 팝업 열기
function layerShow(ele){
	/* var top = $(window).scrollTop() + 20;
	if(top < 50){
	    top = 50;
	}
	$('#'+ele).addClass('active').css({'top':top+'px'}) */
	$('#'+ele).addClass('active')
}
//레이어 팝업 닫기
function layerHide(ele){
	$('#'+ele).removeClass('active');
}

//ifram 콘텐츠 높이
function autoResize(obj){ 
	var newheight; 
	var newwidth; 
	if(obj.contentDocument){
		newheight = obj.contentDocument.documentElement.scrollHeight+30;
		newwidth = obj.contentDocument.documentElement.scrollWidth+30; 
	}else{
		newheight=obj.contentWindow.document.body.scrollHeight+30;
		newwidth=obj.contentWindow.document.body.scrollWidth+30; 
	}
	obj.height= newheight + 'px';
	obj.width= newwidth + 'px';
}
function isNumeric(num, opt) {
	// 좌우 trim(공백제거)을 해준다.
	num = String(num).replace(/^\s+|\s+$/g, "");

	if (typeof opt == "undefined" || opt == "1") {
		// 모든 10진수 (부호 선택, 자릿수구분기호 선택, 소수점 선택)
		var regex = /^[+\-]?(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+){1}(\.[0-9]+)?$/g;
	} else if (opt == "2") {
		// 부호 미사용, 자릿수구분기호 선택, 소수점 선택
		var regex = /^(([1-9][0-9]{0,2}(,[0-9]{3})*)|[0-9]+){1}(\.[0-9]+)?$/g;
	} else if (opt == "3") {
		// 부호 미사용, 자릿수구분기호 미사용, 소수점 선택
		var regex = /^[0-9]+(\.[0-9]+)?$/g;
	} else {
		// only 숫자만(부호 미사용, 자릿수구분기호 미사용, 소수점 미사용)
		var regex = /^[0-9]$/g;
	}

	if (regex.test(num)) {
		num = num.replace(/,/g, "");
		return isNaN(num) ? false : true;
	} else {
		return false;
	}
}

function fn_getDate(dateType, addCount, dateFormat){
	var defaultFormat = 'yyyyMMdd';
	var date = new Date();

	dateType = dateType ? dateType : "d";
	addCount = addCount ? addCount : 0;
	dateFormat = dateFormat ? dateFormat : defaultFormat;

	if (dateType != 'y' && dateType != 'm' && dateType != 'd') {
		return false;
	}
	dateType = dateType.toLowerCase();	// 소문자로 변경

	if (!isNumeric(addCount)) {
		return false;
	}
	// addCount 정규식 체크

	//값 체크 끝나고 날짜 계산 로직 Start
	if (dateType == 'y') {
		date.setFullYear(date.getFullYear()+addCount);
	} else if (dateType == 'm') {
		date.setMonth(date.getMonth()+addCount);
	} else if (dateType == 'd') {
		date.setDate(date.getDate()+addCount);
	}
	return date.format(dateFormat);
}

//date format 함수  : Date 내장 객체에 format함수 추가
Date.prototype.format = function(f) {
	if (!this.valueOf()) return " ";

	var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
	var d = this;

	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
		switch ($1) {
			case "yyyy": return d.getFullYear();
			case "yy": return (d.getFullYear() % 1000).zf(2);
			case "MM": return (d.getMonth() + 1).zf(2);
			case "dd": return d.getDate().zf(2);
			case "E": return weekName[d.getDay()];
			case "HH": return d.getHours().zf(2);
			case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
			case "mm": return d.getMinutes().zf(2);
			case "ss": return d.getSeconds().zf(2);
			case "a/p": return d.getHours() < 12 ? "오전" : "오후";
			default: return $1;
		}
	});};

//한자리일경우 앞에 0을 붙여준다.
String.prototype.string = function(len){
	var s = '', i = 0;
	while (i++ < len) { s += this; }
	return s;
};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

function fn_toggleDept(select) {
	select = select ? select : ".list_chk";
	$(select+' > dt').each(function(){
		$(this).on('click',function(){
			$(this).next('dd').stop().slideToggle()
		})
	})
}

function loginIdCheck(loginId) {
	loginId.siblings('.err.emph').remove();
	var regExp = /[a-z0-9]{6,12}/;

	if($.trim(loginId.val()) == ''){
		loginId.siblings('.btn').after('<p class="err emph">아이디는 필수 값입니다.</p>');
		loginId.focus();
		return false;
	}

	if(regExp.test($.trim(loginId.val())) == false) {
		//alert("아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.");
		loginId.siblings('.btn').after('<p class="err emph">아이디는 영문 소문자, 숫자를 포함해서 6~12자리 이내로 입력해주세요.</p>');
		loginId.focus();
		return false;
	}
	return true;
}

function pwdCheck(pwd) {
	pwd.siblings('.err.emph').remove();
	var regExp = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,16}$/;

	if($.trim(pwd.val()) == ''){
		pwd.after('<p class="err emph">비밀번호는 필수 값입니다.</p>');
		pwd.focus();
		return false;
	}

	if(regExp.test($.trim(pwd.val())) == false) {
		//alert("비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.");
		pwd.after('<p class="err emph">비밀번호는 특수문자를 포함하여 8~16자리 이내로 입력해주세요.</p>');
		return false;
	}
	return true;
}