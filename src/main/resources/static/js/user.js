let index = {
	
	init: function() {
		$("#btn-save").bind("click", () => {
			this.save();
		});
	},
	
	save: function()  {
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email: $("#email").val()
		}
	// console.log(data);
	
	// ajax 호출
	$.ajax({
		// 서버측에 회원가입 요청
		type: "POST",
		url: "/blog/api/user", // 컨트롤러
		data: JSON.stringify(data), // 중간언어 필요
		contentType: "application/json; charset=utf-8",
		dataType: "json" // 응답이 왔을 때 기본 데이터 타입(Buffered 문자열) => js object 자동 변환
	}).done(function(data, textStatus, xhr) {
		// 통신 성공시
		console.log("xhr : " + xhr);
		console.log(xhr);
		console.log("textStatus : " + textStatus);
		console.log("data : " + data);
		alert("회원가입이 완료되었습니다.")
		location.href  = "/blog";
	}).fail(function(error) {
		// 통신 실패시
		console.log(error);
		alert("회원가입에 실패하였습니다.")
	});
	
	}	
}

index.init();