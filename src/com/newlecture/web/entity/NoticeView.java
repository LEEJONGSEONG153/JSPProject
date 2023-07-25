package com.newlecture.web.entity;

import java.sql.Timestamp;

public class NoticeView  extends Notice{

    private int cmtCount;

    public NoticeView() {
    }

    public NoticeView(int id, String title, String writerId, Timestamp regDate, String hit, String files, String content, int cmtCount) {
        super(id, title, writerId, regDate, hit, files, content);
        this.cmtCount = cmtCount;
    }

    public int getCmtCount() {
        return cmtCount;
    }

    public void setCmtCount(int cmtCount) {
        this.cmtCount = cmtCount;
    }
}
