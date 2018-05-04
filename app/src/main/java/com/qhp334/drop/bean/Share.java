package com.qhp334.drop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 2017/12/18.
 */

public class Share implements Parcelable{

    /**
     * sid : 5
     * sdetail : 写后台真累,不喜欢！写后台真累,不喜欢！
     * screatetime : 2017-12-03 10:11:14
     * smedia : 1.jpg
     * sstate : 0
     * uid : 1
     * telephone : 15382291002
     * password : 123
     * uname : 覃慧萍
     * pic : 1.jpg
     * createtime : 2017-12-21 14:39:11
     * state : 0
     */

    private int sid;
    private String sdetail;
    private String screatetime;
    private String smedia;
    private int sstate;
    private int uid;
    private String telephone;
    private int password;
    private String uname;
    private String pic;
    private String createtime;
    private int state;

    protected Share(Parcel in) {
        sid = in.readInt();
        sdetail = in.readString();
        screatetime = in.readString();
        smedia = in.readString();
        sstate = in.readInt();
        uid = in.readInt();
        telephone = in.readString();
        password = in.readInt();
        uname = in.readString();
        pic = in.readString();
        createtime = in.readString();
        state = in.readInt();
    }

    public static final Creator<Share> CREATOR = new Creator<Share>() {
        @Override
        public Share createFromParcel(Parcel in) {
            return new Share(in);
        }

        @Override
        public Share[] newArray(int size) {
            return new Share[size];
        }
    };

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSdetail(String sdetail) {
        this.sdetail = sdetail;
    }

    public void setScreatetime(String screatetime) {
        this.screatetime = screatetime;
    }

    public void setSmedia(String smedia) {
        this.smedia = smedia;
    }

    public void setSstate(int sstate) {
        this.sstate = sstate;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(int password) {
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

    public int getSid() {
        return sid;
    }

    public String getSdetail() {
        return sdetail;
    }

    public String getScreatetime() {
        return screatetime;
    }

    public String getSmedia() {
        return smedia;
    }

    public int getSstate() {
        return sstate;
    }

    public int getUid() {
        return uid;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getPassword() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sid);
        parcel.writeString(sdetail);
        parcel.writeString(screatetime);
        parcel.writeString(smedia);
        parcel.writeInt(sstate);
        parcel.writeInt(uid);
        parcel.writeString(telephone);
        parcel.writeInt(password);
        parcel.writeString(uname);
        parcel.writeString(pic);
        parcel.writeString(createtime);
        parcel.writeInt(state);
    }
}
