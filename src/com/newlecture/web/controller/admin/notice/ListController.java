package com.newlecture.web.controller.admin.notice;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] openIds = request.getParameterValues("open-id");
        String ids_ = request.getParameter("ids");
        String[] delIds = request.getParameterValues("del-id");
        String[] ids = ids_.trim().split(" ");

        String cmd = request.getParameter("cmd");

        NoticeService service = new NoticeService();

        switch (cmd){
            case "일괄공개":
                List<String> oids = Arrays.asList(openIds);
                List<String> cids = new ArrayList(Arrays.asList(ids));
                cids.removeAll(oids);
                
                //트랜잭션 처리를 위해서 하나로 처리
                service.pubNoticeAll(oids,cids);

                break;

            case "일괄삭제":

                int[] ids1 = new int[delIds.length];
                for (int i = 0; i < delIds.length; i++) {
                    ids1[i] = Integer.parseInt(delIds[i]);
                }
                int result = service.deleteNoticeAll(ids1);
                break;
            default:
                response.sendRedirect("list");  //get 요청이
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String field_ = request.getParameter("f");
        String query_ = request.getParameter("q");
        String page_ = request.getParameter("p");

        String field = "title";
        if(field_ != null && !field_.equals("")){
            field = field_;
        }

        String query = "";
        if(query_ != null && !query_.equals("")){
            query = query_;
        }

        int page = 1;
        if(page_ != null && !page_.equals("")){
            page = Integer.parseInt(page_);
        }

        NoticeService service = new NoticeService();
        List<NoticeView> list = service.getNoticeList(field, query, page);

        int count = service.getNoticeCount(field, query);

        request.setAttribute("count",count);
        request.setAttribute("list",list);

        //forward  : 이어나갈때 데이터 request 에 담아서 이동
        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request,response);
    }
}
