import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class UI extends Application {

    private Stage stage;
    private Controller controller;
    private Instance[] instances;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Min-Makespan Approximate Solver");
        this.stage = primaryStage;
        setController(new InstanceSelectionController(this));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void setController(Controller newController) {
        this.controller = newController;
        stage.setScene(controller.createScene());
        stage.show();
    }

    public void setInstances(Instance[] instances) {
        this.instances = Arrays.copyOf(instances, instances.length);
    }

    public Instance[] getInstances() {
        return this.instances;
    }

}
