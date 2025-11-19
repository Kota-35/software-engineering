package at1.domain;

public class Task {
    
    private int id;
    private String name;
    private boolean isDone;


    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.isDone = false;
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

}
