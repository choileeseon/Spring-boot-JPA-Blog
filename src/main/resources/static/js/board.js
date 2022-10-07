let index = {
	init: function() {
		$("#btn-save").on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-delete").on("click", () => {  
			this.deleteById();
		});
		$("#btn-update").on("click", () => {  
			this.update();
		});
		
	},
	//글쓰기
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		
		$.ajax({
			//글쓰기 수행 요청
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/"; 
		}).fail(function(error){
			alert(JSON.stringify(error));
			});
	},
	
	// 글 상세보기 삭제 
	deleteById: function() {
		let id = $("#id").text();
		
		$.ajax({
			//글삭제 수행 요청
			type:"DELETE",
			url:"/api/board/" + id,
			dataType: "json" 
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/"; 
		}).fail(function(error){
			alert(JSON.stringify(error));
			});
	},
	
	//글 수정하기(update)
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		
		$.ajax({
			//글 수정하기 수행 요청
			type:"PUT",
			url:"/api/board/" + id,
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp){
			alert("글 수정이 완료되었습니다.");
			location.href="/"; 
		}).fail(function(error){
			alert(JSON.stringify(error));
			});
	},
}

index.init();