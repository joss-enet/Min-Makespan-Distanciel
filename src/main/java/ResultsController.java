import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ResultsController extends Controller {

    public ResultsController(UI ui) {
        super(ui);
    }

    public Scene createScene() {

        if (ui.getInstances().length > 1) {
            return createSceneMultipleInstances();
        }

        GridPane root = new GridPane();
        int currentRow = 0;

        Text title = new Text("Results for this instance :");
        root.add(title, 0, currentRow++);
        root.add(new Text(""), 0, currentRow++);


        //Instance

        Text nbMachines = new Text("Number of machines : "+ui.getInstances()[0].getM().length);
        Text tasks = new Text("Tasks lengths : "+ui.getInstances()[0].tasksToString());
        root.add(nbMachines, 0, currentRow++);
        root.add(tasks, 0, currentRow++);
        root.add(new Text(""), 0, currentRow++);


        //Results

        double infMax = ui.getInstances()[0].infMax();
        double infAvg = ui.getInstances()[0].infAvg();
        Text infMaxRes = new Text("'Maximum' lower bound = "+infMax);
        Text infAvgRes = new Text("'Average' lower bound = "+infAvg);
        root.add(infMaxRes, 0, currentRow++);
        root.add(infAvgRes, 0, currentRow++);

        Result resultLSA = ui.getInstances()[0].listSchedulingAlgorithm();
        Result resultLPT = ui.getInstances()[0].largestProcessingTime();
        Result resultMyAlgo = ui.getInstances()[0].myAlgo();

        Text resLSA = new Text("Result LSA = "+resultLSA.getRes());
        Text ratioLSA = new Text("Ratio LSA = "+resultLSA.getRatio());
        Text resLPT = new Text("Result LPT = "+resultLPT.getRes());
        Text ratioLPT = new Text("Ratio LPT = "+resultLPT.getRatio());
        Text resMyAlgo = new Text("Result MyAlgo = "+resultMyAlgo.getRes());
        Text ratioMyAlgo = new Text("Ratio MyAlgo = "+resultMyAlgo.getRatio());

        root.add(resLSA, 0, currentRow++);
        root.add(ratioLSA, 0, currentRow++);
        root.add(resLPT, 0, currentRow++);
        root.add(ratioLPT, 0, currentRow++);
        root.add(resMyAlgo, 0, currentRow++);
        root.add(ratioMyAlgo, 0, currentRow++);
        root.add(new Text(""), 0, currentRow++);


        //Back button

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            ui.setInstances(new Instance[0]);
            ui.setController(new InstanceSelectionController((this.ui)));
        });
        root.add(backButton, 0, currentRow++);

        return new Scene(root, 700, 350);
    }

    public Scene createSceneMultipleInstances() {
        GridPane root = new GridPane();
        int currentRow = 0;

        Text title = new Text("Results for this instance :");
        root.add(title, 0, currentRow++);
        root.add(new Text(""), 0, currentRow++);


        //Instances

        for (int i=0; i<ui.getInstances().length; i++) {
            Text nbMachines = new Text("Number of machines : "+ui.getInstances()[i].getM().length);
            Text tasks = new Text("Tasks lengths : "+ui.getInstances()[i].tasksToString());
            root.add(nbMachines, 0, currentRow++);
            root.add(tasks, 0, currentRow++);
        }
        root.add(new Text(""), 0, currentRow++);


        //Results

        double ratioLSA = 0;
        double ratioLPT = 0;
        double ratioMyAlgo = 0;

        for (int i=0; i<ui.getInstances().length; i++) {
            Result currentResultLSA = ui.getInstances()[i].listSchedulingAlgorithm();
            Result currentResultLPT = ui.getInstances()[i].largestProcessingTime();
            Result currentResultMyAlgo = ui.getInstances()[i].myAlgo();

            ratioLSA += currentResultLSA.getRatio();
            ratioLPT += currentResultLPT.getRatio();
            ratioMyAlgo += currentResultMyAlgo.getRatio();
        }

        Text avgRatioLSA = new Text("Average ratio LSA = "+(ratioLSA/ui.getInstances().length));
        Text avgRatioLPT = new Text("Average ratio LPT = "+(ratioLPT/ui.getInstances().length));
        Text avgRatioMyAlgo = new Text("Average ratio LSA = "+(ratioMyAlgo/ui.getInstances().length));

        root.add(avgRatioLSA, 0, currentRow++);
        root.add(avgRatioLPT, 0, currentRow++);
        root.add(avgRatioMyAlgo, 0, currentRow++);
        root.add(new Text(""), 0, currentRow++);


        //Back button

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            ui.setInstances(new Instance[0]);
            ui.setController(new InstanceSelectionController((this.ui)));
        });
        root.add(backButton, 0, currentRow++);

        return new Scene(root, 700, 350);
    }

}
