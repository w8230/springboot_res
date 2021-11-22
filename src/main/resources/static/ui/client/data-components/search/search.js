function S_search() {
	var KEYWORD = $("#keyword").val();
	var SEARCH_TYPE = $('.searchTag.sp_active').text();
	
	if(KEYWORD == '') {
		alert("검색어를 입력해주세요.");
		return false;
	} else if(SEARCH_TYPE == "숙소") {
		SEARCH_TYPE = "0";
		$('#search_type').val(SEARCH_TYPE);
	} else if(SEARCH_TYPE == "지역") {
		SEARCH_TYPE = "1";
		$('#search_type').val(SEARCH_TYPE);
	} else if(SEARCH_TYPE == "트립") {
		search_type = "2";
		$('#search_type').val(SEARCH_TYPE);
		
		$("#keyword").val("");
		alert("준비중 입니다.");
		return false;
	}
}