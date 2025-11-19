package at1.domain;

import java.util.Date;

public class Task {

    private int id;
    private String name;
    private boolean isDone;
    private Date createdAt;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.isDone = false;
        this.createdAt = new Date();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // 保存用メソッド
    public String toSaveString() {
        return id + "," + name + "," + isDone + "," + createdAt;
    }
}
