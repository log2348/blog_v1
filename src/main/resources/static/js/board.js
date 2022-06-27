
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
			type: "post",
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
	}
}

index.init();