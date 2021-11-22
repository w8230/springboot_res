function gh_like_up_btn() {
	var no = $("#no").val();
	var user_id = $("#user_id").val();
	
	if(user_id == "") {
		alert("로그인 후 이용이 가능합니다.");
		location.href = "login_page";
	} else {
		$.ajax({
			url : "gh_like_up",
			type : "POST",
			data : {"no" : no, "user_id" : user_id},
			dataType : "json",
			success : function(result) {
				if(result == 1) {
					$("#heart").css("color", "#ff4a52").addClass("fas fa-heart fa-2x");
					location.reload();
				} else {
					$("#heart").removeClass();
					$("#heart").css("color", "#212529").addClass("far fa-heart fa-2x");
					location.reload();
				}
			}
		});
	}
}

function gh_delete_btn() {
	var no = $("#no").val();
	
	$.ajax({
		url : "gh_delete",
		type : "POST",
		data : {"no" : no},
		dataType : "json",
		success : function(result) {
			console.log(result);
			if(result > 0) {
				alert("게시글이 삭제되었습니다.");
				location.href = "index";
			} else {
				alert("실패");
			}
		}
	});
}

$(function(){
	var public_facility = $("#public_facility").val();
	var split_public = public_facility.split(",");
	
	var public_list = "Wifi,헤어드라이기,전자렌지,커피포트,주차가능,게스트 부엌,토스트기,TV,엘리베이터,다리미," +
						"휴게실,공용PC,프린터 사용,정원,바베큐시설,수영장,레스토랑,카페,헬스시설,팩스 사용,스파시설";
	var split_public_list = public_list.split(",");
	var change_taget = $(".public_list .additional_contents");
	var str = "";
	
	for(var i = 0; i < split_public_list.length; i++) {
		if(split_public[i] == "Y") {
			if(split_public_list.length-1 != i) {
				str += split_public_list[i] + ", ";
			} else {
				str += split_public_list[i]
			}
		}
	}
	change_taget.text(str);
});