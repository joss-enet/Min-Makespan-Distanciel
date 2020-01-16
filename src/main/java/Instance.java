import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Instance {


    private int[] D;
    private Machine[] M;

    public Instance(int m, int[] D) {
        this.M = new Machine[m];
        for (int i = 0; i<m; i++) {
            this.M[i] = new Machine();
        }

        this.D = Arrays.copyOf(D, D.length);
    }

    public void executeAllAlgorithm(){
        listSchedulingAlgorithm();
        largestProcessingTime();
    }

    /**
     * Use to copy an array of integer to a sorted array of integer.
     * @param D array of integer that need to be sort
     */
    private void copyOfSortedD(int[] D) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        for(int i=0;i<D.length;i++){
            sortedList.add(D[i]);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        for(int i = 0;i<sortedList.size();i++){
            this.D[i] = sortedList.get(i);
        }
    }

    public Machine[] getM() {
        return M;
    }

    public String tasksToString() {
        String res = String.valueOf(D[0]);
        for (int i=1; i<D.length; i++) {
            res = res + " ; " + D[i];
        }

        return res;
    }

    public void listSchedulingAlgorithm(){
        clearMachines();
        assignTask();
        System.out.println("Résultat LSA = "+ machineWithMoreTask(this.M));
        System.out.println("ration LSA = "+(machineWithMoreTask(this.M).getCumTask()/(sumOfTask()/this.M.length)));
    }

    public void largestProcessingTime(){
        clearMachines();
        copyOfSortedD(this.D);
        assignTask();
        System.out.println("Résultat LPT = "+machineWithMoreTask(this.M));
        System.out.println("ration LPT = "+(machineWithMoreTask(this.M).getCumTask()/(sumOfTask()/this.M.length)));
    }

    public void myAlgo(){
        clearMachines();
        int bound = (sumOfTask()/this.M.length);
        int j = 0;
        //Fill machine with task until theoric bound has been reached.
        for(int i=0;i<this.M.length;i++){
            while(j<D.length && M[i].getCumTask()<bound){
                if(D[j]+M[i].getCumTask()<=bound){
                    M[i].addTask(D[j]);
                }
                j++;
            }
        }
        //If all task where not added because solution can't be perfectly fit with the theoric bound.
        for (int i = j; i < D.length; i++) {
            machineWithLessTask(this.M).addTask(D[i]);
        }

        System.out.println("Résultat MyAlgo = "+machineWithMoreTask(this.M));
        System.out.println("ration MyAlgo = "+(machineWithMoreTask(this.M).getCumTask()/(sumOfTask()/this.M.length)));

    }

    /**
     * This method is used for LSA and LPT because it use the same process.
     */
    private void assignTask() {
        for (int i = 0; i < D.length; i++) {
            machineWithLessTask(this.M).addTask(D[i]);
        }
    }

    /**
     * return the machine less task
     * @param machines array of Machine
     * @return machine with less task
     */
    private Machine machineWithLessTask(Machine[] machines){
        Machine machine = new Machine();
        int sum=-1;
        for(int i=0;i<machines.length;i++){
            if(sum<machines[i].getCumTask() || sum>-1){
                machine = machines[i];
                sum = machine.getCumTask();
            }
        }

        return machine;
    }

    /**
     * Return the machin with more task
     * @param machines array of machines
     * @return machine with more task
     */
    private Machine machineWithMoreTask(Machine[] machines){
        Machine machine = new Machine();
        int sum=-1;
        for(int i=0;i<machines.length;i++){
            if(sum>machines[i].getCumTask()){
                machine = machines[i];
                sum = machine.getCumTask();
            }
        }

        return machine;
    }

    /**
     * used to clear machine
     */
    private void clearMachines(){
        for (int i = 0; i<M.length; i++) {
            this.M[i] = new Machine();
        }
    }

    private int sumOfTask(){
        int res=0;
        for(int i=0;i<this.D.length;i++){
            res+=D[i];
        }
        return res;
    }
}
