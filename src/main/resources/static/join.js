/**
 * 회원가입
 */

const signupInputs = document.querySelectorAll('.su-input');
const submitBtns = document.querySelectorAll('.su-submit-btn');


function signup() {
	let signupObj = {
		mamber_id : signupInputs[0].value,
		mamber_pw : signupInputs[1].value,
		member_nickname : signupInputs[2].value,
		member_birth : signupInputs[3].value, 
		member_phoneNumber : signupInputs[4].value,
		member_email : signupInputs[5].value
	}
	
	$.ajax({
		
		type : "post",
		url : "/member/join",
		data : signupObj,
		dataType : "text",
		success : function(data) {
			
		},
		error : function() {
			alert('비동기 처리 오류');
		}
	});
}

submitBtns[1].onclick = () => {

       signup()
}