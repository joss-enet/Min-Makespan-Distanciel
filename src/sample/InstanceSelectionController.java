package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Arrays;

public class InstanceSelectionController extends Controller {

    public InstanceSelectionController(UI ui) {
        super(ui);
    }

    @Override
    public Scene createScene() {
        GridPane root = new GridPane();

        Text title = new Text("Select an import method for your instance :");
        root.add(title, 0, 0);

        //From file

        TextField fileTextField = new TextField();
        Label lbFileTextField = new Label("File name :");
        root.add(fileTextField, 1 ,2);
        root.add(lbFileTextField, 0 ,2);

        Button fileButton = new Button();
        fileButton.setText("From a file");
        fileButton.setOnAction(event -> {
            handleIF();
        });
        root.add(fileButton, 0, 3);


        //From keyboard

        TextField fullInstanceTextField = new TextField();
        Label lbFullInstanceTextField = new Label("Instance :");
        root.add(fullInstanceTextField, 1 ,5);
        root.add(lbFullInstanceTextField, 0 ,5);

        Button fullInstanceButton = new Button();
        fullInstanceButton.setText("From keyboard");
        fullInstanceButton.setOnAction(event -> {
            handleIC(fullInstanceTextField.getText());
        });
        root.add(fullInstanceButton, 0, 6);


        //One instance generation

        TextField oneRandomInstanceTextField = new TextField();
        Label lbOneRandomInstanceTextField = new Label("m :");
        root.add(oneRandomInstanceTextField, 1 ,8);
        root.add(lbOneRandomInstanceTextField, 0 ,8);

        Button oneRandomInstanceButton = new Button();
        oneRandomInstanceButton.setText("Random instance");
        oneRandomInstanceButton.setOnAction(event -> {
            handleIM();
        });
        root.add(oneRandomInstanceButton, 0, 9);


        //5 instances generation

        TextField randomInstancesMTextField = new TextField();
        Label lbRandomInstancesMTextField = new Label("m :");
        root.add(randomInstancesMTextField, 1 ,11);
        root.add(lbRandomInstancesMTextField, 0 ,11);
        TextField randomInstancesNTextField = new TextField();
        Label lbRandomInstancesNTextField = new Label("n :");
        root.add(randomInstancesNTextField, 3 ,11);
        root.add(lbRandomInstancesNTextField, 2 ,11);
        TextField randomInstancesKTextField = new TextField();
        Label lbRandomInstancesKTextField = new Label("k :");
        root.add(randomInstancesKTextField, 5 ,11);
        root.add(lbRandomInstancesKTextField, 4 ,11);
        TextField randomInstancesMinTextField = new TextField();
        Label lbRandomInstancesMinTextField = new Label("min :");
        root.add(randomInstancesMinTextField, 7 ,11);
        root.add(lbRandomInstancesMinTextField, 6 ,11);
        TextField randomInstancesMaxTextField = new TextField();
        Label lbRandomInstancesMaxTextField = new Label("max :");
        root.add(randomInstancesMaxTextField, 9 ,11);
        root.add(lbRandomInstancesMaxTextField, 8 ,11);

        Button randomInstancesButton = new Button();
        randomInstancesButton.setText("Random instances");
        randomInstancesButton.setOnAction(event -> {
            handleIR();
        });
        root.add(randomInstancesButton, 0, 12);

        return new Scene(root, 300, 275);
    }

    public void handleIF() {

    }

    public void handleIC(String instance) {
        String[] parsedInstance = instance.split(":");
        int[] intParsedInstance = new int[parsedInstance.length];
        for (int i = 0; i<parsedInstance.length; i++) {
            intParsedInstance[i] = Integer.parseInt(parsedInstance[i]);
        }
        int[] tasks = Arrays.copyOfRange(intParsedInstance, 2, parsedInstance.length);
        ui.setInstance(new Instance(intParsedInstance[0], tasks));
        ui.setController(new ResultsController(ui));
    }

    public void handleIM() {

    }

    public void handleIR() {

    }
}
