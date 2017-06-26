## 웹 서버 통신

```java
public String getDataFromUrl(String url){
        StringBuilder result = new StringBuilder();
        try {
            // 1 요청 처리
            URL serverUrl = new URL(url);
            // 주소에 해당하는 서버의 소켓을 연결
            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
            // outputStream으로 데이터 요청
            con.setRequestMethod("GET");  // http 통신중에 get으로 통신하겠다

            // 2 응담 처리
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); // 줄단위로 데이터를 읽기위해서 버퍼사용(속도 향상도)
                String temp = "";
                while((temp = br.readLine())!= null){
                    result.append(temp+"\n");
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
```
## MongoDB
- Nosql의 종류중 하나
- Json과 똑같은 형태
- 테이블 설계를 하지 않아도 됨(Mysql과 가장 큰 차이점!)
![enter image description here](http://cfile4.uf.tistory.com/image/23094D3F58DA12EE14E9EE)

![enter image description here](http://cfile2.uf.tistory.com/image/222C44475298846D24892C)
