let index = {
	init: function() {
		$("#btn-save").on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-login").on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서
			this.save();
		});
	},
	//회원가입
	save: function() {
		//alert('user의 save함수 호출 됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);

		// ajax 호출시 default가 비동기 호출
		// ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청할 것!
		// ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/blog/api/user",
			data:JSON.stringify(data), // http body데이터 (마임데이터가 필요)
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" //응답을 json으로 받음. 응답이 왔을때 기본적으로 모든것은 문자열 String. (만약 생긴게 json이라면 -> 자바스크립트 object로 변경)
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href="/blog"; 
		}).fail(function(error){
			alert(JSON.stringify(error));
			});
	},
	
	// 로그인
	login: function() {
		//alert('user의 save함수 호출 됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		}
		
		$.ajax({
			type:"POST",
			url:"/blog/api/user/login",
			data:JSON.stringify(data), // http body데이터 (마임데이터가 필요)
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" //응답을 json으로 받음. 응답이 왔을때 기본적으로 모든것은 문자열 String. (만약 생긴게 json이라면 -> 자바스크립트 object로 변경)
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();