package com.newlecture.web.controller.admin.notice;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;

@MultipartConfig(       //멀티파트 설정 web.xml에 하는 방법도 있음
        //location = "/tmp",            //절대경로로 적어야하는데 리눅스 윈도우 차이있으므로 차라리 자바가 지정한 임시디렉토리 쓰자
        fileSizeThreshold = 1024*1024,  //1메가 이상일때 메모리대신 디스크를 쓰자    디스크 경로는 default로 사용하는게 일반적
        maxFileSize = 1024*1024*50,      //1개의 파일 사이즈 제한
        maxRequestSize = 1024*1024*50*5  //전체 파일 사이즈 제한
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //forward  : 이어나갈때 데이터 request 에 담아서 이동
        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        System.out.println(title);

        String content = request.getParameter("content");
        String isOpen = request.getParameter("open");

        Collection<Part> parts = request.getParts();
        StringBuilder builder = new StringBuilder();

        for(Part p : parts){

            if(!p.getName().equals("file")) continue;
            if(p.getSize() == 0 ) continue;

            Part filePart = p;
            String fileName = filePart.getSubmittedFileName();
            InputStream fis = filePart.getInputStream();

            builder.append(fileName);
            builder.append(",");

            String realPath = request.getServletContext().getRealPath("/upload");
            System.out.println(realPath);
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdir();
            }

            String filePath = realPath + File.separator + fileName;

            FileOutputStream fos = new FileOutputStream(filePath);

            int b;
            byte[] buf = new byte[1024];
            int size = 0;
            while((size = fis.read(buf)) != -1){
                fos.write(buf, 0,size);
            }
            fos.close();
            fis.close();

        }

        builder.delete(builder.length()-1,builder.length());

        boolean pub = false;
        if(isOpen != null){
            pub = true;
        }

        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setContent(content);
        notice.setPub(pub);
        notice.setWriterId("newlec");
        notice.setFiles(builder.toString());

        NoticeService service = new NoticeService();
        int result = service.insertNotice(notice);

        response.sendRedirect("list");
    }
}
