package com.newlecture.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class Calc extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String x_ = request.getParameter("x");
        String y_ = request.getParameter("y");

        String operator = request.getParameter("operator");

        int x = 0;
        int y = 0;

        if(x_ != null && !x_.equals((""))) x = Integer.parseInt(x_);
        if(y_ != null && !y_.equals((""))) y = Integer.parseInt(y_);

        int result = 0;

        if(operator.equals("덧셈")){
            result= x+y;
        } else{
            result= x-y;
        }
        out.println("덧셈 결과 : " +result);
    }
}
