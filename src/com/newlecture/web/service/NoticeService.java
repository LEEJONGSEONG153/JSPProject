package com.newlecture.web.service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoticeService {

    public int removeNoticeAll(int[] ids){
        return 0;
    }
    public int pubNoticeAll(int[] oids, int[] cids){
        List<String> oidsList = new ArrayList<>();
        for (int i = 0; i < oids.length; i++) {
            oidsList.add(String.valueOf(oids[i]));
        }

        List<String> cidsList = new ArrayList<>();
        for (int i = 0; i < cids.length; i++) {
            cidsList.add(String.valueOf(cids[i]));
        }

        String oidsCSV = String.join(",",oidsList);
        String cidsCSV = String.join(",",cidsList);

        return pubNoticeAll(oidsCSV,cidsCSV);
    }
    public int pubNoticeAll(List<String> oids, List<String> cids){
        String oidsCSV = String.join(",",oids);
        String cidsCSV = String.join(",",cids);

        return pubNoticeAll(oidsCSV,cidsCSV);
    }
    public int pubNoticeAll(String oidsCSV, String cidsCSV){

        int result = 0;
        String sqlOpen = String.format("UPDATE NOTICE SET PUB=1 WHERE ID IN (%s)",oidsCSV);
        String sqlClose = String.format("UPDATE NOTICE SET PUB=0 WHERE ID IN (%s)",cidsCSV);

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            Statement st = con.createStatement();

            result += st.executeUpdate(sqlOpen);
            result += st.executeUpdate(sqlClose);

            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public int insertNotice(Notice notice){

        int result = 0;

        String sql = "INSERT INTO " +
                     "NOTICE(TITLE, CONTENT, WRITER_ID, PUB, FILES)" +
                     "VALUES(?,?,?,?,?)";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,notice.getTitle());
            st.setString(2,notice.getContent());
            st.setString(3,notice.getWriterId());
            st.setBoolean(4,notice.getPub());
            st.setString(5,notice.getFiles());

            result = st.executeUpdate();

            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
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
                boolean pub = rs.getBoolean("PUB");

                NoticeView notice = new NoticeView(id,title,writerId,regDate,hit,files,content,cmtCount,pub);

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

    public List<NoticeView> getNoticePubList(String field, String query, int page) {
        List<NoticeView> list = new ArrayList<>();

        String sql ="select * " +
                "from NOTICE_VIEW " +
                "where " +field+ " LIKE ?" +
                "and PUB = 1 " +
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
                boolean pub = rs.getBoolean("PUB");

                NoticeView notice = new NoticeView(id,title,writerId,regDate,hit,files,content,cmtCount,pub);

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
                boolean pub = rs.getBoolean("PUB");


                notice = new Notice(nid,title,writerId,regDate,hit,files,content,pub);
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
                boolean pub = rs.getBoolean("PUB");

                notice = new Notice(nid,title,writerId,regDate,hit,files,content,pub);
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
                boolean pub = rs.getBoolean("PUB");

                notice = new Notice(nid,title,writerId,regDate,hit,files,content,pub);
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


    public int deleteNoticeAll(int[] ids) {

        int result = 0;

        String params = "";
        for (int i = 0; i < ids.length; i++) {
            params += ids[i];
            if(i < ids.length-1){
                params += ",";
            }
        }

        String sql = "DELETE FROM NOTICE WHERE ID IN ("+params+")";

        //String url= "jdbc:mariadb://localhost:33066/testdb";
        String url= "jdbc:mariadb://localhost:3306/studydb";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,"lee","whdtjd89!");
            Statement st = con.createStatement();
            result = st.executeUpdate(sql);

            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}