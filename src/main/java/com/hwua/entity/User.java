package com.hwua.entity;

import javax.persistence.*;
import java.sql.Date;

//用户类
@Table(name = "amz_user", schema = "d_shop", catalog = "")
public class User {
    private long id;//id
    private String uname;//用户名
    private String pwd;//用户密码
    private int sex;//年龄
    private Date birthday;//生日
    private String idcard;//身份证
    private String email;//邮箱
    private String mobile;//电话号码
    private String address;//地址
    private int utype;//用户类型

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", idcard='" + idcard + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", utype=" + utype +
                '}';
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "sex")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "utype")
    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User amzUser = (User) o;

        if (id != amzUser.id) return false;
        if (sex != amzUser.sex) return false;
        if (utype != amzUser.utype) return false;
        if (uname != null ? !uname.equals(amzUser.uname) : amzUser.uname != null) return false;
        if (pwd != null ? !pwd.equals(amzUser.pwd) : amzUser.pwd != null) return false;
        if (birthday != null ? !birthday.equals(amzUser.birthday) : amzUser.birthday != null) return false;
        if (idcard != null ? !idcard.equals(amzUser.idcard) : amzUser.idcard != null) return false;
        if (email != null ? !email.equals(amzUser.email) : amzUser.email != null) return false;
        if (mobile != null ? !mobile.equals(amzUser.mobile) : amzUser.mobile != null) return false;
        if (address != null ? !address.equals(amzUser.address) : amzUser.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + sex;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + utype;
        return result;
    }
}
