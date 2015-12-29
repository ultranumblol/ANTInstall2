package com.wgz.ant.antinstall.bean;

/**
 * Created by qwerr on 2015/12/23.
 */
public class Worker {
   private  String workID;
    private String azdispatchingnumber;
    private String workerName;
    private String addtime;
    private String userid;
    private String username;
    private String state;

    @Override
    public String toString() {
        return "Worker{" +
                "workID='" + workID + '\'' +
                ", azdispatchingnumber='" + azdispatchingnumber + '\'' +
                ", workerName='" + workerName + '\'' +
                ", addtime='" + addtime + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public void setAzdispatchingnumber(String azdispatchingnumber) {
        this.azdispatchingnumber = azdispatchingnumber;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWorkID() {

        return workID;
    }

    public String getAzdispatchingnumber() {
        return azdispatchingnumber;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }
}
