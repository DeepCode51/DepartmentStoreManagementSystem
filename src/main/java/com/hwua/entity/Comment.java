package com.hwua.entity;


import java.sql.Timestamp;

public class Comment {
/*评论*/
  private long id;/*评论id*/
  private String reply;/*标题*/
  private String content;/*内容*/
  private String create_Time;/*提问时间*/
  private String reply_Time;/*回答时间*/
  private String nick_Name;/*回答人姓名*/
  private String state;/*状态*/

  public Comment() {
  }

  public Comment(long id, String reply, String content, String createTime, String replyTime, String nickName, String state) {
    this.id = id;
    this.reply = reply;
    this.content = content;
    this.create_Time = createTime;
    this.reply_Time = replyTime;
    this.nick_Name = nickName;
    this.state = state;
  }
  public Comment( String reply, String content, String createTime, String replyTime, String nickName, String state) {

    this.reply = reply;
    this.content = content;
    this.create_Time = createTime;
    this.reply_Time = replyTime;
    this.nick_Name = nickName;
    this.state = state;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getCreate_Time() {
    return create_Time;
  }

  public void setCreate_Time(String create_Time) {
    this.create_Time = create_Time;
  }


  public String getReply_Time() {
    return reply_Time;
  }

  public void setReply_Time(String reply_Time) {
    this.reply_Time = reply_Time;
  }


  public String getNick_Name() {
    return nick_Name;
  }

  public void setNick_Name(String nick_Name) {
    this.nick_Name = nick_Name;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", reply='" + reply + '\'' +
            ", content='" + content + '\'' +
            ", create_Time=" + create_Time +
            ", reply_Time=" + reply_Time +
            ", nick_Name='" + nick_Name + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
