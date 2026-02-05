package model;

public class Task {
    private int id;
    private String title;
    private String descriptoin;
    private TaskStatus status;

    public Task(int id, String title, String descriptoin, TaskStatus status) {
    this.id = id;
    this.title = title;
    this.descriptoin = descriptoin;
    this.status = status;
    }


@Override
public String toString() {
    return id + ". [" + status + "] " + title + ":" + descriptoin;
   }
}