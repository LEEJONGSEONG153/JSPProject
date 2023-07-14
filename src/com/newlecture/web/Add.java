package com.newlecture.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class Add extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String temp1 = request.getParameter("x");
        String temp2 = request.getParameter("y");

        int x = 0;
        int y = 0;
        try{
            x = Integer.parseInt(temp1);
            y = Integer.parseInt(temp2);

        }catch (NumberFormatException e){
            out.println("입력한 값 중 숫자가 아닌 값이 있습니다.<br>");
        }
        int sum = x+y;
        out.println("덧셈 결과 : " +sum);
    }
}
