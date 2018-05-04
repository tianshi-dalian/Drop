package com.qhp334.drop.bean;

/**
 * Created by Admin on 2017/12/21.
 */

public class Video {

//    http://localhost:8090/DropQhp/Admin/ShareVideo/getSharevideo
    /**
     * vid : 1
     * vdetail : Android开发实践全录屏
     * video : 1.mp4
     * vcreatetime : 0000-00-00 00:00:00
     * vstate : 0
     * uid : 2
     * telephone : 123
     * password : 123
     * uname : 123
     * pic :
     * createtime : 2017-12-03 09:31:22
     * state : 1
     */

    private int vid;
    private String vdetail;
    private String video;
    private String vcreatetime;
    private int vstate;
    private int uid;
    private String telephone;
    private String password;
    private String uname;
    private String pic;
    private String createtime;
    private int state;

    public void setVid(int vid) {
        this.vid = vid;
    }

    public void setVdetail(String vdetail) {
        this.vdetail = vdetail;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVcreatetime(String vcreatetime) {
        this.vcreatetime = vcreatetime;
    }

    public void setVstate(int vstate) {
        this.vstate = vstate;
    }

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

    public int getVid() {
        return vid;
    }

    public String getVdetail() {
        return vdetail;
    }

    public String getVideo() {
        return video;
    }

    public String getVcreatetime() {
        return vcreatetime;
    }

    public int getVstate() {
        return vstate;
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
