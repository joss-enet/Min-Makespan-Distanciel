package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class UI extends Application {

    private Stage stage;
    private Controller controller;
    private Instance instance;

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

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public Instance getInstance() {
        return this.instance;
    }

}
