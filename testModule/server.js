// 1 http 서버 모듈 가져오기 
// var 변수명 = 값
var http = require("http"); // java의 import와 같음 
var url = require("url");
// 커스텀 모듈 
var con = require("./connectionModule");

// 2 요청 url을 분석하는 모듈 가져오기
// var url = require("url");

// 2 http 모듈로 서버 생성하기 
var server = http.createServer(function(request,response){

	var parsedUrl = url.parse(request.url);
	var realPath = parsedUrl.pathname;  // 실제 요청 주소
	// 요청 url 체크
	if(realPath == '/Bbs/List'){
		// 응답 헤더 
		response.writeHead(200,{'Content-Type':'application/json'});
		// db 처리 함수 호출 
		con.getData(response);
	}else{
		response.writeHead(404,{'Content-Type':'text/html'});
		response.end("<h1>404 not found</h1>");

	}

	// console.log("요청URL:"+request.url);
	// // 문자열로 된 요청 url을 객체로 만들어서 적용하게 해준다
	// var parsedUrl = url.parse(request.url);
	// console.log("객체화된Url:"+parsedUrl);
	// console.log("주소 분리:"+parsedUrl.pathname);
	// console.log("qurey 분리:"+parsedUrl.query);
	
		
	} );
// 3 서버가 로드되면 알려주고, 사용자 요청 대기 
server.listen(8080, function(){   // listen이 function을 호출함 
	console.log('Server is running...');
});