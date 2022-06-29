
let index = {
	init: function() {
		$("#btn-save").bind("click", () => {
			this.save();
		});
		
		$("#btn-delete").bind("click", () => {
			this.deleteById();
		});
		
		$("#btn-update").bind("click", () => {
			this.update();
		});

		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});
		
	},
	
	save: function() {
		
		// 데이터 가져오기
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		console.log("데이터 확인");
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(data, textStatus, xhr) {
			if(data.status) {
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			}
		})
		.fail(function(error) {
			alert("글쓰기에 실패하였습니다.");
		});
	},
	
	deleteById: function() {
		let id = $("#board-id").text();
		
		$.ajax({
			type:"DELETE",
			url: "/api/board/" + id // 페이지x 데이터 리턴
		}) 
		.done(function(data) {
			if(data.status) {
				alert("삭제가 완료되었습니다.");
				location.href="/";
			}
		})
		.fail(function() {
			alert("삭제 실패.");
		});
	},
	
	update: function() {
		let boardId = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			async: false // 필요에 따라 동기적으로 받을 때 true 지정
		})
		.done(function(data) {
			if(data.status) {
				alert("글 수정이 완료되었습니다.");
				location.href="/";
			}
		})
		.fail(function(error) {
			alert("글 수정에 실패하였습니다.")
		});
	},
	
		// 댓글 등록
	replySave: function() {

		// 데이터 가져오기 (boardId : 해당 게시글 id)
		let data = {
			boardId: $("#board-id").text(),
			content: $("#reply-content").val()
		}
		
		console.log("데이터 확인");
		console.log(data);
		
		// ``백틱 (자바스크립트 변수를 문자열 안에 넣어서 사용할 수 있다.)
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		})
		.done(function(response) {
			if(response.status) {
				alert("댓글 작성이 완료되었습니다.");
				location.href = `/board/${data.boardId}`;
			}
		})
		.fail(function(error) {
			alert("댓글 작성에 실패하였습니다.");
			console.log(error);
		});
		
	}
	
}

index.init();