/**
 * 회원가입
 */
let index = {
	init: function(){
		$("#btn-save").on("click", ()=> { 
			this.save(); 
		});
	}, save: function(){
		let data = { // 값을 찾아서 변수에 담아준다.
			ID: $("#member_id").val(),
			PW: $("#member_pw").val(),
			nickname: $("#member_nickname").val(),
			birth: $("#member_birth").val(),
			phoneNumber: $("#member_phoneNumber").val(),
			email: $("#member_email").val()
		};
		
		$.ajax({ 
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
			

		}).done(function(resp){ 
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog";
		}).fail(function(error){ 
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();