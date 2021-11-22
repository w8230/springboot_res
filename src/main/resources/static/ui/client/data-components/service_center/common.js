// 게시글 폼 검증 [s]
function form_validation() {
	var title = $("#title").val();
	
	if(title == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
	} else if(CKEDITOR.instances.content.getData() == '' || CKEDITOR.instances.content.getData().length == 0) {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;
	}
}
// 게시글 폼 검증 [e]

// 공지사항 삭제 [s]
function notice_delete() {
	var no = $("#no").val();
	
	if(confirm("게시글을 삭제하시겠습니까?") == true) {
		$.ajax({
			url : "notice_delete",
			type : "POST",
			data : {"no" : no},
			dataType : "json",
			success : function(result) {
				location.href = "notice_page";
			}
		});
	} else {
		return;
	}
}
// 공지사항 삭제 [e]

// 자주하는 질문 삭제 [s]
function questions_delete() {
	var no = $("#no").val();
	
	if(confirm("게시글을 삭제하시겠습니까?") == true) {
		$.ajax({
			url : "questions_delete",
			type : "POST",
			data : {"no" : no},
			dataType : "json",
			success : function(result) {
				location.href = "questions_page";
			}
		});
	} else {
		return;
	}
}
// 자주하는 질문 삭제 [e]