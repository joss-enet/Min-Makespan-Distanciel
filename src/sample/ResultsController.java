package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ResultsController extends Controller {

    public ResultsController(UI ui) {
        super(ui);
    }

    public Scene createScene() {
        GridPane root = new GridPane();

        Text title = new Text("Results for this instance :");
        root.add(title, 0, 0);


        //Instance

        Text nbMachines = new Text("Number of machines : "+ui.getInstance().getM().length);
        Text tasks = new Text("Tasks lengths : "+ui.getInstance().tasksToString());
        root.add(nbMachines, 0, 1);
        root.add(tasks, 0, 2);


        //Back button

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            ui.setInstance(null);
            ui.setController(new InstanceSelectionController((this.ui)));
        });
        root.add(backButton, 0, 3);

        return new Scene(root, 300, 275);
    }
}
