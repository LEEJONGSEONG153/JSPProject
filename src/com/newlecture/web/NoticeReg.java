package com.newlecture.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //입력받을때 utf-8로 읽어드림 , 톰캣은 기본적 1바이트 씩 읽어드림 -> 톰캣 설정에서 변경할 수있지만 그렇게 하지 않음.. WHY? 톰캣하나로 여러개의 서비스를 돌릴수 있음
        //request.setCharacterEncoding("UTF-8");

        //출력시 문자를 인코딩할 방식을 결정
        response.setCharacterEncoding("UTF-8");
        //브라우저에게 인코딩된 방식을 알리고, 웹 문서의 확장자를 알림
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        out.println(title);
        out.println(content);

    }
}