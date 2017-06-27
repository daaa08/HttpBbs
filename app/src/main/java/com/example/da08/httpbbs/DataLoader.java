package com.example.da08.httpbbs;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Da08 on 2017. 6. 26..
 */

public class DataLoader {

    public void getData(String url, final CallBack callBack){
        new AsyncTask<String, Void, List<Bbs>>(){
            @Override
            protected List<Bbs> doInBackground(String... params) {
                // url 가져오기
                String url = params[0];
                String result = getDataFromUrl(url);  // jsonString형태로 넘어옴

                Gson gson = new Gson();
                Data data = gson.fromJson(result, Data.class);  //result의 String을 gson으로 object 컨버팅

                return data.bbsList;
            }

            @Override
            protected void onPostExecute(List<Bbs> list) {
                callBack.setData(list);

            }
        }.execute(url);
    }

    public interface CallBack {  // 호출되는 측에 데이터를 넘겨줄 방법이 없으므로
        public void setData(List<Bbs> list);
    }


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


}
