package com.combat.subscribe;

/**
 * @description: 配置信息
 * @author: darben
 * @create: 2020-03-27 14:41
 */
public class ServerConfig {

    //数据库连接地址
    private String dbUrl;
    //数据库密码
    private String dbPwd;
    //数据库用户名
    private String dbUser;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
}
