import java.util.ArrayList;

public class Machine {


    private int cumTask;
    private ArrayList<Integer> tasks;

    public Machine(){
        cumTask = 0;
        tasks = new ArrayList<>();
    }

    public void addTask(int tache){
        if(tache>0){
            cumTask +=tache;
            tasks.add(tache);
        }

    }

    public int getCumTask() {
        return cumTask;
    }

    public ArrayList<Integer> getTasks() {
        return tasks;
    }

}
