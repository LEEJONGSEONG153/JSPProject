package com.newlecture.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String v_ = request.getParameter("v");
        String op = request.getParameter("operator");

        int v = 0;

        if(v_ != null && !v_.equals((""))) v = Integer.parseInt(v_);

        //계산
        if(op.equals("=")){
            //int x = (Integer)application.getAttribute("value");
            //int x = (Integer)session.getAttribute("value");
            int x = 0;
            //String operator = (String)application.getAttribute("op");
            //String operator = (String)session.getAttribute("op");
            String operator = "";
            for (Cookie c : cookies){
                if(c.getName().equals("value")){
                    x = Integer.parseInt(c.getValue());
                }else if(c.getName().equals("op")){
                    operator = c.getValue();

                }
            }

            int y = v;
            int result = 0;

            if(operator.equals("+")){
                result= x+y;
            } else{
                result= x-y;
            }
            response.getWriter().printf("result is %d\n", result);
        //값을 저장
        }else{
            //application.setAttribute("value",v);
            //session.setAttribute("value",v);
            //application.setAttribute("op",op);
            //session.setAttribute("op",op);
            Cookie valueCookie = new Cookie("value",String.valueOf(v));
            Cookie opCookie = new Cookie("op",op);
            valueCookie.setPath("/calc2");
            valueCookie.setMaxAge(24*60*60);
            opCookie.setPath("/calc2");
            response.addCookie(valueCookie);
            response.addCookie(opCookie);

            response.sendRedirect("calc2.html");
        }
    }
}
