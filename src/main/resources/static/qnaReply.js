function reply(reply){
	var id = document.getElementById('qa_num').val();
	reply.action = "/qna/detail/"+id;
	reply.submit();
}

function deleteqna(){
	var id = $("#qa_num").text();
	location.href="/qna/detail/" + id + "/delete";
}

