package fr.iut.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private static BorderPane root;

    private static Stage appStage;

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        appStage = stage;
        setHome();
        stage.setResizable(false);
        appStage.setTitle("Sis-France");
        appStage.show();

    }

    public static <T> T setScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource(fxmlPath));
            root = loader.load();
            appStage.setScene(new Scene(root));
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setHome(){
        try{
            root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/home.fxml"));
            appStage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
