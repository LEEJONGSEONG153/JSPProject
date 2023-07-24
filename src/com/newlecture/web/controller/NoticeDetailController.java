package com.newlecture.web.controller;

import com.newlecture.web.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url= "jdbc:mariadb://localhost:33066/testdb";
        //String url= "jdbc:mariadb://localhost:3306/studydb";
        int id = Integer.parseInt(request.getParameter("id"));
        String sql= "select * from notice where ID=?";


        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();

            rs.next();

            String title = rs.getString("TITLE");
            String writerId = rs.getString("WRITER_ID");
            Timestamp regDate = rs.getTimestamp("REG_DATE");
            String hit = rs.getString("HIT");
            String files = rs.getString("FILES");
            String content = rs.getString("CONTENT");

            Notice notice = new Notice(id,title,writerId,regDate,hit,files,content);

            request.setAttribute("n",notice);
//            request.setAttribute("title",title);
//            request.setAttribute("writerId",writerId);
//            request.setAttribute("regDate",regDate);
//            request.setAttribute("hit",hit);
//            request.setAttribute("files",files);
//            request.setAttribute("content",content);

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //redirect : 새로운 페이지 이동할때

        //forward  : 이어나갈때 데이터 request 에 담아서 이동
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request,response);


    }
}
