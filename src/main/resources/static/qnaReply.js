function reply(cherry){
	var id = document.getElementById('qa_num').val();
	cherry.action = "/qna/detail/"+id;
	cherry.submit();
}