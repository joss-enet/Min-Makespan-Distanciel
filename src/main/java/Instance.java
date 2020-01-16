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
        System.out.println("ration LSA = ");
    }

    public void largestProcessingTime(){
        clearMachines();
        long debut = System.currentTimeMillis();
        copyOfSortedD(this.D);
        assignTask();
        System.out.println("Résultat LPT = "+machineWithMoreTask(this.M));
        System.out.println("ration LPT = ");
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
}
