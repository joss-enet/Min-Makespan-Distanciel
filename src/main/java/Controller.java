import javafx.scene.Scene;

public abstract class Controller {
    UI ui;

    public Controller(UI ui) {
        this.ui = ui;
    }

    public abstract Scene createScene();
}
