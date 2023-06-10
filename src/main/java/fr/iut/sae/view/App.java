package fr.iut.sae.view;

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
        appStage.setTitle("Sis-France");
        appStage.show();
    }

    public static void setHome(){
        try{
            root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/home.fxml"));
            appStage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setDashboard(){
        try{
            root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/dashboard.fxml"));
            appStage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setEarthquakesResearch(){
        try{
            root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/EarthquakesResearch.fxml"));
            appStage.setScene(new Scene(root));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
