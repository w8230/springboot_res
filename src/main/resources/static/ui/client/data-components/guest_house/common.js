function search_Postcode() {
	$("#warning_post_code").empty();
	$("#warning_addr").empty();
	
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                /* document.getElementById("sample6_extraAddress").value = extraAddr; */
            
            } /* else {
                document.getElementById("sample6_extraAddress").value = '';
            } */

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("post_code").value = data.zonecode; // 우편번호
            document.getElementById("addr").value = addr; // 주소
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detail_addr").focus(); // 상세주소
        }
    }).open();
}

//방정보 추가 [s]
function add_Room_info() {
	var room_info1_orig = $("tr", $("#room_info1"));
	var room_info2_orig = $("tr", $("#room_info2"));
	var room_info3_orig = $("tr", $("#room_info3"));
	
	var room_info1_copy = $("#room_info1").clone();
	var room_info2_copy = $("#room_info2").clone();
	var room_info3_copy = $("#room_info3").clone();
	
	var room_img_id = $("#room_info").find("tr").last().find("td").find("span").find("span").find("input:file").attr("id");
	var split_num = room_img_id.split("room_img");
	var num = Number(split_num[1]) + 1;
	
	if(confirm("방 정보를 추가하시겠습니까??") == true) {
		$("tr", room_info1_copy).val(room_info1_orig.val());
		$("tr", room_info2_copy).val(room_info2_orig.val());
		$("tr", room_info3_copy).val(room_info3_orig.val());
		
		room_info1_copy.find("#rooms1").attr("id", "rooms" + num).val("");
		room_info1_copy.find("#price1").attr("id", "price" + num).val("");
		room_info2_copy.find("#room_gender1").attr("id", "room_gender" + num).val("");
		room_info2_copy.find("#personnel1").attr("id", "personnel" + num).val("");

		room_info1_copy.find("#warning_rooms1").attr("id", "warning_rooms" + num).text("");
		room_info1_copy.find("#warning_price1").attr("id", "warning_price" + num).text("");
		room_info2_copy.find("#warning_room_gender1").attr("id", "warning_room_gender" + num).text("");
		room_info2_copy.find("#warning_personnel1").attr("id", "warning_personnel" + num).text("");
		
		
		room_info3_copy.find("#room_img1").attr("id", "room_img" + num).val("");
		room_info3_copy.find("label[for = room_img1]").attr("for", "room_img" + num).val("");
		room_info3_copy.find("#room_img_name1").attr("id", "room_img_name" + num).val("");
		
		room_info1_copy.appendTo("#room_info");
		room_info2_copy.appendTo("#room_info");
		room_info3_copy.appendTo("#room_info");
	} else {
		return;
	}
}
// 방정보 추가 [e]

// 방정보 삭제 [s]
function del_Room_info() {
	var room_info = document.getElementById("room_info");
 	var room_info_child = room_info.children;
	var length = $("tr[id=room_info1]").length;
	var num = (length*3)-1;

	if(length == 1) {
		alert("삭제할 수 없습니다.");
		return;
	} else {
		room_info_child[num].remove();
		room_info_child[num-1].remove();
		room_info_child[num-2].remove();
	}
}
// 방정보 삭제 [e]

// 게시글 폼 검증 [s]
function form_validation() {
	var partner_name = $("#partner_name").val();
	var post_code = $("#post_code").val();
	var addr = $("#addr").val();
	var detail_addr = $("#detail_addr").val();
	var introduce = $("#introduce").val();
	var check_in = $("#check_in").val();
	var check_out = $("#check_out").val();
	var lights_out_time = $("#lights_out_time").val();
	
	var num = $("tr[id=room_info1]").length;
	
	if(partner_name == "") {
		$("#warning_partner_name").text("게스트하우스 이름을 입력해주세요.");
		$("#partner_name").val("");
		$("#partner_name").focus();
		return false;
	} else if(post_code == "" && addr == "") {
		$("#warning_post_code").text("주소 찾기를 진행해주세요.");
		$("#warning_addr").text("주소 찾기를 진행해주세요.");
		$("#post_code").focus();
		return false;
	} else if(detail_addr == "") {
		$("#warning_detail_addr").text("상세주소를 입력해주세요.");
		$("#detail_addr").val("");
		$("#detail_addr").focus();
		return false;
	} else if(introduce == "") {
		$("#warning_introduce").text("숙소 소개글을 입력해주세요.");
		$("#introduce").val("");
		$("#introduce").focus();
		return false;
	} else if(check_in == "") {
		$("#warning_check_in").text("체크인 시간을 입력해주세요");
		$("#check_in").val("");
		$("#check_in").focus();
		return false;
	} else if(check_out == "") {
		$("#warning_check_out").text("체크아웃 시간을 입력해주세요.");
		$("#check_out").val("");
		$("#check_out").focus();
		return false;
	} else if(lights_out_time == "") {
		$("#warning_lights_out_time").text("소등시간을 입력해주세요.");
		$("#lights_out_time").val("");
		$("#lights_out_time").focus();
		return false;
	}
	
	for(var i=1; i<= num; i++) {
		var rooms = $("#rooms" + i).val();
		var price = $("#price" + i).val();
		var room_gender = $("#room_gender" + i).val();
		var personnel = $("#personnel" +i).val();

		if(rooms == "") {
			$("#warning_rooms" + i).text("객실 이름을 입력해주세요.");
			$("#rooms" + i).val("");
			$("#rooms" + i).focus();
			return false;
		} else if(price == "") {
			$("#warning_price" + i).text("1박 단가를 입력해주세요.");
			$("#price" + i).val("");
			$("#price" + i).focus();
			return false;
		} else if(room_gender == "") {
			$("#warning_room_gender" + i).text("객실 성별을 입력해주세요.");
			$("#room_gender" + i).val("");
			$("#room_gender" + i).focus();
			return false;
		} else if(personnel == "") {
			$("#warning_personnel" + i).text("객실 정원을 입력해주세요.");
			$("#personnel" + i).val("");
			$("#personnel" + i).focus();
			return false;
		}
	}
}
// 게시글 폼 검증 [e]

// 객실 사진 [s]
function btn_click(e) {
	var room_img_id = $("#room_info").find("tr").find("td").find("span").find("span").find("input:text").attr("id");
	var split_id = room_img_id.split("1");
	var img_id = split_id[0];
	
	var room_img_num = $(e).attr("id");
	var split_num = room_img_num.split("room_img");
	var img_num = split_num[1];

	var room_img_name = img_id+img_num;
	
	$("#" + room_img_num).on("change", function() {
		if(window.FileReader) {
			var fileName = $(this)[0].files[0].name;
		} else {
			var fileName = $(this).val().split("/").pop().split("\\").pop();	
		}
		$("#" + room_img_name).val(fileName);
	});
}
// 객실 사진 [s]

//keyup, keydown 되었을때, 메세지 제거 [s]
function keyevent(e) {
	var room_num = e.split("rooms");
	var price_num = e.split("price");
	var gender_num = e.split("room_gender");
	var personnel_num = e.split("personnel");
	
	$("#rooms" + room_num[1]).on("keyup keydown", function() {
		if($("#rooms" + room_num[1]).val().length > 0) {
			$("#warning_rooms" + room_num[1]).empty();
		}
	});
	$("#price" + price_num[1]).on("keyup keydown", function() {
		if($("#price" + price_num[1]).val().length > 0) {
			$("#warning_price" + price_num[1]).empty();
		}
	});
	$("#room_gender" + gender_num[1]).on("keyup keydown", function() {
		if($("#room_gender" + gender_num[1]).val().length > 0) {
			$("#warning_room_gender" + gender_num[1]).empty();
		}
	});
	$("#personnel" + personnel_num[1]).on("keyup keydown", function() {
		if($("#personnel" + personnel_num[1]).val().length > 0) {
			$("#warning_personnel" + personnel_num[1]).empty();
		}
	});
}
//keyup, keydown 되었을때, 메세지 제거 [e]

$(function() {
	// 체크 박스 체크 [s]
	$(".chk_list").on("click", "input", function() {
		var chk_box = $(this).attr("id");
		var chk_boxSplit = chk_box.split("chk_box");
		var num = chk_boxSplit[1];
		
		if($("input[id=chk_box" + num + "]").is(":checked")) {
			$("input[id=chk" + num + "]").val("Y");
		} else {
			$("input[id=chk" + num + "]").val("N");
		}
	});
	// 체크 박스 체크 [e]
	
	// 썸네일 [s]
	$("#thumbnail").on("change", function() {
		if(window.FileReader) {
			var fileName = $(this)[0].files[0].name;
		} else {
			var fileName = $(this).val().split("/").pop().split("\\").pop();			
		}
		$("#thumbnail_name").val(fileName);
	});
	// 썸네일 [e]
	
	// keyup, keydown 되었을때, 메세지 제거 [s]
	$("#partner_name").on("keyup keydown", function() {
		if($("#partner_name").val().length > 0) {
			$("#warning_partner_name").empty();
		}
	});
	
	$("#detail_addr").on("keyup keydown", function() {
		if($("#detail_addr").val().length > 0) {
			$("#warning_detail_addr").empty();
		}
	});	
	
	$("#introduce").on("keyup keydown", function() {
		if($("#introduce").val().length > 0) {
			$("#warning_introduce").empty();
		}
	});
	
	$("#check_in").on("keyup keydown", function() {
		if($("#check_in").val().length > 0) {
			$("#warning_check_in").empty();
		}
	});
	
	$("#check_out").on("keyup keydown", function() {
		if($("#check_out").val().length > 0) {
			$("#warning_check_out").empty();
		}
	});
	
	$("#lights_out_time").on("keyup keydown", function() {
		if($("#lights_out_time").val().length > 0) {
			$("#warning_lights_out_time").empty();
		}
	});
	// keyup, keydown 되었을때, 메세지 제거 [e]
});