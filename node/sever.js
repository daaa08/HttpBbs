// 1 http 서버 모듈 가져오기 
// var 변수명 = 값
var http = require("http"); // java의 import와 같음 
// 2 요청 url을 분석하는 모듈 가져오기
// var url = require("url");

// 2 http 모듈로 서버 생성하기 
var server = http.createServer(function(request,response){

	console.log("요청URL:"+request.url);
	// // 문자열로 된 요청 url을 객체로 만들어서 적용하게 해준다
	// var parsedUrl = url.parse(request.url);
	// console.log("객체화된Url:"+parsedUrl);
	// console.log("주소 분리:"+parsedUrl.pathname);
	// console.log("qurey 분리:"+parsedUrl.query);

	// 응답 헤더 
	response.writeHead(200,{'Content-Type':'application/json'});
	// 응답 데이터 
	var one = '{bbsList:[{id:"1", title:"제목", content:"내용", author:"작성자", date:"2017-06-09"}]}'
	// 응답 데이터 전송 후 완료 
	response.end(one);

});
// 3 서버가 로드되면 알려주고, 사용자 요청 대기 
server.listen(8080, function(){   // listen이 function을 호출함 
	console.log('Server is running...');
});