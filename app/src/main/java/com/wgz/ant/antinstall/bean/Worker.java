package com.wgz.ant.antinstall.bean;

/**
 * Created by qwerr on 2015/12/23.
 */
public class Worker {
    private String workNum;
    private String workername;
    private String workerID;
    private String addtime;

    public String getWorkNum() {
        return workNum;
    }

    public String getWorkername() {
        return workername;
    }

    public String getWorkerID() {
        return workerID;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workNum='" + workNum + '\'' +
                ", workername='" + workername + '\'' +
                ", workerID='" + workerID + '\'' +
                ", addtime='" + addtime + '\'' +
                '}';
    }
}
