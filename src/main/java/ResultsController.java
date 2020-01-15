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

        for (int i=0; i<ui.getInstances().length; i++) {
            Text nbMachines = new Text("Number of machines : "+ui.getInstances()[i].getM().length);
            Text tasks = new Text("Tasks lengths : "+ui.getInstances()[i].tasksToString());
            root.add(nbMachines, 0, 2*(i+1)-1);
            root.add(tasks, 0, 2*(i+1));
        }


        //Back button

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            ui.setInstances(new Instance[0]);
            ui.setController(new InstanceSelectionController((this.ui)));
        });
        root.add(backButton, 0, ui.getInstances().length*2+1);

        return new Scene(root, 700, 350);
    }
}
