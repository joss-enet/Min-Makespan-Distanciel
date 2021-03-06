import java.util.ArrayList;
import java.util.Collections;

public class Instance {


    private int[] D;
    private Machine[] M;

    public Instance(int m, int[] D) {
        this.M = new Machine[m];
        this.D = new int[D.length];
        for (int i = 0; i<m; i++) {
            this.M[i] = new Machine();
        }
        for(int i=0;i<D.length;i++){
            this.D[i] = D[i];
        }
    }

    public void executeAllAlgorithm(){
        listSchedulingAlgorithm();
        largestProcessingTime();
        myAlgo();
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

    public Result listSchedulingAlgorithm(){
        clearMachines();
        assignTask();
        return new Result(machineWithMoreTask(this.M).getCumTask(), ((double)machineWithMoreTask(this.M).getCumTask()/(this.inf())));
    }

    public Result largestProcessingTime(){
        clearMachines();
        copyOfSortedD(this.D);
        assignTask();
        return new Result(machineWithMoreTask(this.M).getCumTask(), ((double)machineWithMoreTask(this.M).getCumTask()/(this.inf())));
    }

    public Result myAlgo(){
        clearMachines();
        ArrayList<Integer> tasks = new ArrayList<>();
        ArrayList<Integer> tamp = new ArrayList<>();
        for(int i =0;i<D.length;i++){
            tasks.add(D[i]);
        }
        tamp = copyListOfInteger(tasks);
        int bound = sumOfTask()/M.length;
        int j = 0;
        //Fill machine with task until theoric bound has been reached.
        for(int i=0;i<this.M.length;i++){
            tasks =  copyListOfInteger(tamp);
            for (Integer task:tasks
                 ) {
                if(task+M[i].getCumTask()<=bound){
                    M[i].addTask(task);
                    tamp.remove(task);
                }
            }
        }
        //If all task where not added because solution can't be perfectly fit with the theoric bound.
        for (Integer task:tasks
             ) {
            M[machineWithLessTask(this.M)].addTask(task);
        }

        return new Result(machineWithMoreTask(this.M).getCumTask(), ((double)machineWithMoreTask(this.M).getCumTask()/(this.inf())));
    }

    public double inf() {
        double infMax = infMax();
        double infAvg = infAvg();
        if (infMax>infAvg) {
            return infMax;
        } else {
            return infAvg;
        }
    }

    public double infMax() {
        double max = D[0];
        for (int i=1; i<D.length; i++) {
            if(D[i]>max) {
                max = D[i];
            }
        }
        return max;
    }

    public double infAvg() {
        double res = 0;
        for (int i=0; i<D.length; i++) {
            res += D[i];
        }
        return (res/D.length);
    }

    private ArrayList<Integer> copyListOfInteger(ArrayList<Integer> copy){
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer task:copy
        ) {
            res.add(task);
        }
        return res;
    }

    /**
     * This method is used for LSA and LPT because it use the same process.
     */
    private void assignTask() {
        for (int i = 0; i < D.length; i++) {
            M[machineWithLessTask(this.M)].addTask(D[i]);
        }
    }

    /**
     * return the machine less task
     * @param machines array of Machine
     * @return machine with less task
     */
    private int machineWithLessTask(Machine[] machines){
        int indice = 0;
        int sum=Integer.MAX_VALUE;
        for(int i=0;i<machines.length;i++){
            if(sum>machines[i].getCumTask()){
                indice = i;
                sum = machines[i].getCumTask();
            }
        }
        return indice;
    }

    /**
     * Return the machin with more task
     * @param machines array of machines
     * @return machine with more task
     */
    private Machine machineWithMoreTask(Machine[] machines){
        Machine machine = new Machine();
        int sum=Integer.MIN_VALUE;
        for(int i=0;i<machines.length;i++){
            if(sum<machines[i].getCumTask()){
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
