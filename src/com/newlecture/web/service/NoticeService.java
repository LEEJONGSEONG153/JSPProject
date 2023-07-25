package com.newlecture.web.service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeService {

    public int removeNoticeAll(int[] ids){
        return 0;
    }
    public int pubNoticeAll(int[] ids){
        return 0;
    }
    public int insertNotice(Notice notice){
        return 0;
    }
    public int deleteNotice(Notice notice){
        return 0;
    }
    public int updateNotice(Notice notice){
        return 0;
    }
    public List<Notice> getNoticeNewestList(){
        return null;
    }

    public List<NoticeView> getNoticeList(){
        return getNoticeList("TITLE","",1);
    }
    public List<NoticeView> getNoticeList(int page){
        return getNoticeList("TITLE","",page);
    }
    public List<NoticeView> getNoticeList(String field, String query, int page){


        List<NoticeView> list = new ArrayList<>();

        String sql ="select * " +
                    "from NOTICE_VIEW " +
                    "where " +field+ " LIKE ?" +
                    "order by REG_DATE desc limit ? offset ?";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"%"+query+"%");
            st.setInt(2,10);
            st.setInt(3,0+(page-1)*10);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String writerId = rs.getString("WRITER_ID");
                Timestamp regDate = rs.getTimestamp("REG_DATE");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");
                int cmtCount = rs.getInt("CMT_COUNT");

                NoticeView notice = new NoticeView(id,title,writerId,regDate,hit,files,content,cmtCount);

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

        return list;
    }
    public int getNoticeCount(){
        return getNoticeCount("title","");
    }
    public int getNoticeCount(String field, String query){

        int count =0;

        String sql ="select COUNT(ID) COUNT " +
                    "from NOTICE " +
                    "where " +field+ " LIKE ?" +
                    "order by REG_DATE";
        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";



        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"%"+query+"%");
            ResultSet rs = st.executeQuery();
            if(rs.next())
                count = rs.getInt("count");

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
    public Notice getNotice(int id){

        Notice notice = null;
        String sql ="select * from NOTICE where ID=?";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int nid = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String writerId = rs.getString("WRITER_ID");
                Timestamp regDate = rs.getTimestamp("REG_DATE");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(nid,title,writerId,regDate,hit,files,content);
            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notice;
    }
    public Notice getNextNotice(int id){
        Notice notice = null;
        String sql = "select * " +
                     "from NOTICE" +
                     "where ID = (select A.ID from (" +
                                                     "select @ROWNUM := @ROWNUM+1 as ROWNUM, n.ID from notice n , (select @ROWNUM := 0) TMP " +
                                                     "where REG_DATE  > (select REG_DATE from NOTICE where ID=?)) A" +
                                 "where  ROWNUM =1);";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int nid = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String writerId = rs.getString("WRITER_ID");
                Timestamp regDate = rs.getTimestamp("REG_DATE");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(nid,title,writerId,regDate,hit,files,content);
            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notice;
    }
    public Notice getPreNotice(int id){

        Notice notice = null;
        String sql = "select * " +
                     "from NOTICE" +
                     "where ID = (select A.ID from (" +
                                                 "select @ROWNUM := @ROWNUM+1 as ROWNUM, n.ID from notice n , (select @ROWNUM := 0) TMP " +
                                                 "where REG_DATE  < (select REG_DATE from NOTICE where ID=?)" +
                                                 "order by REG_DATE asc) A" +
                                  "where  ROWNUM =1);";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int nid = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String writerId = rs.getString("WRITER_ID");
                Timestamp regDate = rs.getTimestamp("REG_DATE");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                notice = new Notice(nid,title,writerId,regDate,hit,files,content);
            }
            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notice;
    }
}