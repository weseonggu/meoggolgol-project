function reply(reply){
	var id = document.getElementById('qa_num').val();
	reply.action = "/qna/detail/"+id;
	reply.submit();
}

function updateQna(update){
	var id = document.getElementById('qa_num').val();
	update.action = "/qna/update/"+id;
	update.submit();
}


function deleteqna(){
	var id = $("#qa_num").text();
	location.href="/qna/detail/" + id + "/delete";
}

function deleteQnaReply(){
}