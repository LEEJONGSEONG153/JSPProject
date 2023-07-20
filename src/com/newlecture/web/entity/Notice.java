package com.newlecture.web.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Notice {

    private Integer id;
    private String title ;
    private String writerId ;
    private Timestamp regDate ;
    private String hit ;
    private String files ;
    private String content;

    public Notice() {
    }

    public Notice(Integer id, String title, String writerId, Timestamp regDate, String hit, String files, String content) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.regDate = regDate;
        this.hit = hit;
        this.files = files;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writerId='" + writerId + '\'' +
                ", regDate=" + regDate +
                ", hit='" + hit + '\'' +
                ", files='" + files + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
