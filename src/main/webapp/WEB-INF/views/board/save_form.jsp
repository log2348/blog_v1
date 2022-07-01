<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

	<div class="container">
	
		<form action="">
		
			<div class="form-group">
				<label for="title">Title</label>
				<input type="text" class="form-control" placeholder="Enter title" name="title" id="title">
			</div>
			
			<div class="form-group" >
				<label for="content">content</label>
				<textarea class="form-control summernote" rows="5" id="content"></textarea>
			</div>
			
		</form>
		<button type="button" id="btn-save" class="btn btn-primary">글쓰기 완료</button>
	
	</div>
	<br/>
	<br/>
	
 <script type="text/javascript">
 /*
	 function savePost() {
	 	let title = document.querySelector("#title").value;
	 	let content = document.querySelector("#content").value;
	 	//let userId = document.querySelector('#userId').value;
	 	
	 	let board = {
	 		title: title,
	 		content: content
	 	}
	 	
	 fetch("/blog/savePost", {
	 	method: "post",
	 	headers: {
	 		'content-type': 'application/json; charset=utf-8'
	 	},
	 	body: JSON.stringify(board)
	 })
	 .then(res => res.text())
	 .then(res => {
	 	if(res == "ok") {
	 		alert("글쓰기 성공");
	 		location.href = "/";
	 	} else {
	 		alert("글쓰기 실패");
	 	}
	 });  
	 
	 }
*/   
 	$('.summernote').summernote({
     placeholder: '내용을 입력해주세요',
     tabsize: 5,
     height: 300
   	});
 </script>
 <script src="/js/board.js"></script>
				
<%@ include file="../layout/footer.jsp" %>
