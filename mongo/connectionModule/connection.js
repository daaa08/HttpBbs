var mongo = require("mongoDB").MongoClient;
// database를 select 하는 함수 
exports.select = function(callback){  // callback 에는 실행코드가 넘어 옴
	// 데이터베이스 연결 
	mongo.connect('mongoDB://localhost:27017/Bbs',function(err,db){

		if(err){
				console.log(err);
			}else{
			// 1 board 테이블의 데이터를 검색해서 가져온다 
			var board = db.collection("board").find();
			// 2 toArray 함수문 배열로 만들어서 docs 함수로 가져온다 
			board.toArray(function(error, docs){
				// mongodb 에서 item 하나하나를 document라고 함
				if(error){
					// 조회한 데이터를 result 배열에 담아준다 
					console.log("error:"+error);
				}else{
					if(docs != null){ // 조회한 데이터를 배열 결과값에 담아 줌 
					console.log(docs);
					// callback 에는 실행가능한 코드 전체가 넘어오기 대문에
					// 넘어온 코드에 결과 데이터를 담아서 실행할 수 있다
					callback(docs); 
					}   
				}
			});

			// 로직 처리 후 db 닫기 
			db.close();
			
		}
	});

}

exports.insert = function(data, callback){
	mongo.connect('mongoDB://localhost:27017/Bbs',function(err,db){
		// 연결 에러가 발생하면 
		if(err){
			console.log(err);
		}else{
			console.log("connected"+db);
			db.collection("board").insert(data);
			// 처리후 db 닫기
			db.close();
			//처리후 callback을 실행해 준다;


			// 로직 처리 됨

			// 데이터 입력
			// var data = {
			// 	id : 1,
			// 	title : "title",
			// 	content : "content",
			// 	author : "author",
			// 	date : "2017-06-29 12:13:33"
			// };
		}
	});
}