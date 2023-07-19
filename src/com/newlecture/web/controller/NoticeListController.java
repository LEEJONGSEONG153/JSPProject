package com.newlecture.web.controller;

import com.newlecture.web.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Notice> list = new ArrayList<>();

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";
        String sql= "select * from notice";


        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String writerId = rs.getString("WRITER_ID");
                Date regDate = rs.getDate("REG_DATE");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                Notice notice = new Notice(id,title,writerId,regDate,hit,files,content);

                list.add(notice);


            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list",list);
        //forward  : 이어나갈때 데이터 request 에 담아서 이동
        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request,response);

    }

}
