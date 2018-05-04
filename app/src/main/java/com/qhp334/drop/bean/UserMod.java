package com.qhp334.drop.bean;


public class UserMod {

    /**
     * uid : 1
     * telephone : 15382291002
     * password : 123
     * uname : 覃慧萍
     * pic : 1.jpg
     * createtime : 2017-12-21 14:39:11
     * state : 0
     */

    private int uid;
    private String telephone;
    private String password;
    private String uname;
    private String pic;
    private String createtime;
    private int state;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUid() {
        return uid;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getUname() {
        return uname;
    }

    public String getPic() {
        return pic;
    }

    public String getCreatetime() {
        return createtime;
    }

    public int getState() {
        return state;
    }
}
