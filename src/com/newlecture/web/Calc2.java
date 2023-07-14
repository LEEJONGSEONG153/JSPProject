package com.newlecture.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String v_ = request.getParameter("v");
        String operator = request.getParameter("operator");

        int v = 0;

        if(v_ != null && !v_.equals((""))) v = Integer.parseInt(v_);



        int result = 0;

        if(operator.equals("덧셈")){
            //result= x+y;
        }
        out.println("덧셈 결과 : " +result);
    }
}
