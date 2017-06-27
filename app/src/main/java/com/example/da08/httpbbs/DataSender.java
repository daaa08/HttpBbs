package com.example.da08.httpbbs;

import android.os.AsyncTask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Da08 on 2017. 6. 27..
 */

public class DataSender {

    public void sendData(String url, String jsonString, final CallBack callBack){
        // 서브 스레드 테스크
        new AsyncTask<String, Void, Boolean>(){

            @Override
            protected Boolean doInBackground(String... params) {
                String url = params[0];
                String jsonString = params[1];
                boolean success = sendJsonStringByUrl(url, jsonString);
                return success;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                callBack.call(result);
            }
        }.execute(url, jsonString);

    }

    // 데이터 전송 , 처리 , 결과받기
    private boolean sendJsonStringByUrl(String url, String jsonString){
        try {
            // 1 서버 연결
            URL serverUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();

            // 2 저농방식 결정
            con.setRequestMethod("POST");

            // 3 데이터 전송
            con.setDoOutput(true);  // 전송할 데이터가 있다고 알려줌
            String data = "jsonString=" + URLEncoder.encode(jsonString,"utf-8");  // 키 = 값의 형태로 전송할 데이터 모양을 만들고
            OutputStream os = con.getOutputStream();  // 스트림을 열고
            os.write(data.getBytes());  // 데이터를 바이트형으로 반환하여 전송 (네트워크를 주고받는 데이터 형식이 바이트라서 어쩔 수 없음)
            os.flush();  // 버퍼가 가득차지 않았어도 데이터를 전송하기위헤 flush호출
            os.close();  // close를 호출해도 전송이 됨 위의 flush를 쓴 이유는 그런 방식도 있다고 알려준 것

            // 4 전송결과 체크
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public interface CallBack{
        public void call(boolean result);  // 데이터를 전송한다음에 호출
    }
}
