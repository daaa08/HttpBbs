<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page language = "java" import="java.sql.*" %>
<%@ page language = "java" import="com.google.gson.*" %>
<%!
	class Data{
        String title;
        String author;
        String content;
    }
%>
<%
		// 요청값에 대한 한글처리 
		request.setCharacterEncoding("utf-8");

		String json = request.getParameter("json");

		// out.print(json);

		Gson gson = new Gson();
		Data data = gson.fromJson(json, Data.class); 


        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/Bbs?useUnicode=true&characterEncoding=utf8";
        Connection con = DriverManager.getConnection(url,"root","root");

        String query = "insert into board(title,author,content,date) value(?,?,?,now())";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1,data.title);
        stmt.setString(2,data.author);
        stmt.setString(3,data.content);
        stmt.executeUpdate();

        con.close();

        
%>

<h1>
성공적으로 입력 되었습니다.
</h1>
<a href="/Bbs/index.jsp">목록으로</a>
