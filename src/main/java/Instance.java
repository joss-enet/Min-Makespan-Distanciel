import java.util.Arrays;

public class Instance {

    private int[] D;
    private int[] M;

    public Instance(int m, int[] D) {
        this.M = new int[m];
        for (int i = 0; i<m; i++) {
            this.M[i] = 0;
        }

        this.D = Arrays.copyOf(D, D.length);
    }

    public int[] getM() {
        return M;
    }

    public String tasksToString() {
        String res = String.valueOf(D[0]);
        for (int i=1; i<D.length; i++) {
            res = res + " ; " + D[i];
        }

        return res;
    }
}
