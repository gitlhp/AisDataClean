package com.ruoyi.project.system.aistarget.domain;

/**
 * @ClassName TargetDbconn
 * @Description TODO
 * @Author 李怀鹏
 * @Date 2019/5/7 10:28
 * @Version 1.0
 **/
public class TargetDbconn {
    private int id;//自增
    private String sourceip;//源数据库ip
    private String ip;//主机名或者IP
    private String port;//端口号
    private String dbname;//数据库名称
    private String username;//用户名
    private String password;//密码
    private String status;//是否融合成功
    private String time;//运行时间
    private String sumdata;//影响条数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSumdata() {
        return sumdata;
    }

    public void setSumdata(String sumdata) {
        this.sumdata = sumdata;
    }

    @Override
    public String toString() {
        return "TargetDbconn{" +
                "id=" + id +
                ", sourceip='" + sourceip + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", dbname='" + dbname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", sumdata='" + sumdata + '\'' +
                '}';
    }
}
