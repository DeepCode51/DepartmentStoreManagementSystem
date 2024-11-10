package com.hwua.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/*新闻类*/
@Table(name = "amz_news", schema = "d_shop", catalog = "")
public class News {
    private Integer id;//id
    private String title;//标题
    private String content;//内容
    private Timestamp create_time;//创建时间
    private String time;//字符串时间


    //把时间戳转换为字符串
    public String getTime() {
        time = create_time.toString();
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News amzNews = (News) o;

        if (id != amzNews.id) return false;
        if (title != null ? !title.equals(amzNews.title) : amzNews.title != null) return false;
        if (content != null ? !content.equals(amzNews.content) : amzNews.content != null) return false;
        if (create_time != null ? !create_time.equals(amzNews.create_time) : amzNews.create_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
