<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="com.google.gson.*" %>

   <%

  Class.forName("com.mysql.jdbc.Driver"); //동적로딩 기술.
   String url = "jdbc:mysql://127.0.0.1:3306/Bbs?useUnicode=true&characterEncoding=utf8";
  
   //커넥션이 열림
                                               //db주소, userid, password
   Connection con = DriverManager.getConnection(url, "root", "root");

  Statement stmt = con.createStatement();
   String query = "select * from board";
   //마치 안드로이드의 컨텐츠리졸버 처럼 동작함.
   ResultSet cursor = stmt.executeQuery(query);
   // 데이터 반복문으로 화면에 출력 
   List<Bbs> list = new ArrayList<>();
   while(cursor.next()){
     int id = cursor.getInt("id");
     String title = cursor.getString("title");
     String author = cursor.getString("author");
     String content = cursor.getString("content");
     String date = cursor.getString("date");
     Bbs data = new Bbs();
     data.title = title;
     data.author = author;
     data.content = content;
     data.date = date;

     list.add(data);

   }
   con.close();

   Gson gson = new Gson();
   String jsonString = gson.toJson(list);
   out.print("{\"bbsList\":"+jsonString+"}");

  %>
<%!
  class Data{
    List<Bbs> bbsList;
      }
      
  class Bbs{
    String title;
    String author;
    String content;
    String date;
  }
  %>