package com.newlecture.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hi")
public class Nana extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //문자를 인코딩할 방식을 결정
        response.setCharacterEncoding("UTF-8");
        //브라우저에게 인코딩된 방식을 알리고, 웹 문서의 확장자를 알림
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        String temp = request.getParameter("cnt");
        int cnt = 1;
        if(temp != null && !temp.equals("")){
            cnt = Integer.parseInt(temp);
        }
        for (int i = 0; i < cnt; i++) {
            out.println((i+1)+":안녕 Servlet!!<br >");
        }
    }
}