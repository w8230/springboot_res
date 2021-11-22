function cancel() {
	window.history.back();
}

$(function(){
	CKEDITOR.replace("content",{
		height: 500
	});	
});
