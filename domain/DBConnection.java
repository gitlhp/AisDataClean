package com.ruoyi.project.system.aistarget.domain;

/**
 * @ClassName DBConnection
 * @Description 数据库参数字段
 * @Author 李怀鹏
 * @Date 2019/4/26 11:08
 * @Version 1.0
 **/
public class DBConnection {
    private int connid;//自增
    private String ip;//主机名或者IP
    private String port;//端口号
    private String dbname;//数据库名称
    private String username;//用户名
    private String password;//密码
    private int datacount;//连接成功数
    private String status;//是否融合成功
    private String time;//运行时间
    private String sumdata;//影响条数

    public int getConnid() {
        return connid;
    }

    public void setConnid(int connid) {
        this.connid = connid;
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

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public int getDatacount() {
        return datacount;
    }

    public void setDatacount(int datacount) {
        this.datacount = datacount;
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
        return "DBConnection{" +
                "connid=" + connid +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", dbname='" + dbname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", datacount=" + datacount +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", sumdata='" + sumdata + '\'' +
                '}';
    }
}
