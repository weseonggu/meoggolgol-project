
$(function() {
	$("#submitBtn").on('click', function() {
		var title = $("#title").val();
		var content = $("#editor").text().trim();
		
		
		// 상세 내용이 빈 칸일 경우 <p><br></p> 값으로 넘어감
        if (title === '' && content === '<p><br></p>') {
            // 제목과 내용이 모두 비어 있는 경우 경고창 표시
            alert('제목과 내용을 입력해주세요.');
        } else if (title === '') {
            // 제목이 비어 있는 경우 경고창 표시
            alert('제목을 입력해주세요.');
        } else if (content === '<p><br></p>') {
            // 내용이 비어 있는 경우 경고창 표시
            alert('상세 내용을 입력해 주세요.');
        } else {
        	var NoticeRequest = {'title': title, 'content': content};
        	alert(NoticeRequest.title);
    		alert(NoticeRequest.content);
        	$.ajax({
          		url: '/notice/update',
			  	method: 'POST',
			  	data: JSON.stringify(NoticeRequest),
              	contentType: "application/json",
			  
			  	// 성공했을 경우
			  	success: function(response) {
					//성공 콘솔 출력
			    	console.log('공지사항 수정 성공');
			    	// 성공 후 폼 제출
			    	//document.querySelector('form').submit();
					//window.location.href = '/notice/detail/{id}';
			  	},
			  	// 실패했을 경우
			  	error: function(error) {
				  	// 실패 콘솔 출력
				  	console.error('공지사항 수정 실패:', error);
				  	//window.location.href = '/notice/update/{id}';
			  	}
			});
        }
	})
})