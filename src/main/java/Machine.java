package main.java;

import java.util.ArrayList;

public class Machine {


    private int cumTask;
    private ArrayList<Integer> tasks;

    public Machine(){
        cumTask = 0;
        tasks = new ArrayList<>();
    }

    public void addTask(int task){
        if(task>0){
            cumTask +=task;
            tasks.add(task);
        }

    }

    public int getCumTask() {
        return cumTask;
    }

    public ArrayList<Integer> getTasks() {
        return tasks;
    }

}
