import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
    TabPane tabPane = new TabPane();
    Scene scene = new Scene(tabPane, 800, 600);
    Tab memberTab = new Tab();
    stage.setScene(scene);
    stage.show();
    }
}
