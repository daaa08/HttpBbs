var connect = require('./connection');
// 데이터를 읽는 함수 
exports.getData = function(response){
	// connection 모듈의 select 함수 호출 : 함수를 호출하면서 코드 전체를 인자로 넘겨준다.
	connect.select(function(data){
		var result = {
			bbsList : []
		}
		// 원본 데이터를 클라이언트 데이터 구조에 맞게 수정 
			data.forEach(function(item){
				var newItem = {
					id : item.id,
					title: item.title,
					content: item.content,
					author: item.author,
					date: item.date
			};
			// 배열에 데이터 담기
			result.bbsList.push(newItem);
		});

		var jsonString = JSON.stringify(result);
		response.end(jsonString);
	});
}
exports.insert = function(data){

}
exports.update = function(data){

}
exports.removr = function(data){

}

/*  mysql 연결
var mysql = require('mysql');

var conInfo = {
	host : '127.0.0.1',  // 데이터 베이스 아이피 또는 url
	user :  'root',      // 사용자 아이디 (나)
	password : 'root',  // 비밀번호
	port : '3306', 		// 포트  (mysql 전용 포트)
	database : 'Bbs'	// 데이터베이스
}
exports.getData = function(callback){  // response객체가 넘어옴 
	// 연결 정보를 담은 객체 생성 
	var con = mysql.createConnection(conInfo);  // 가져다 쓰겠다는 의미 
	// 연결 정보를 이용해서 데이터베이스 연결
	con.connect();

	// 데이터 베이스의 쿼리 실행
	con.query('select * from board', function(err, items,fields){

		if(err){
			console.log("error message================"+err);
		}else{
			var data = {
			bbsList : []
		    };
		    // 반복문을 통해 배열의 item을 하나씩 담는다 
			items.forEach(function(item){  // item을 꺼낼때마다 함수 실행 
				data.bbsList.push(item);
			});
			// json String으로 변환
			var jsonString = JSON.stringify(data);
			// callback 에 넘어온 response 객체의 end 함수에 결과 데이터를 싫어 보낸다.
			callback.end(jsonString);
			// console.log(jsonString);
			console.log("when?=========================");
		}
	});
	console.log(" ah?=========================");
	con.end(); // 필수! 안하면 계속 연결된 상태 
}

*/