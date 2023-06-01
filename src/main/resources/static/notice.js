function deleteNotice(){
	var id = $("#notice_num").text();
	location.href="/notice/detail/" + id + "/delete";
}