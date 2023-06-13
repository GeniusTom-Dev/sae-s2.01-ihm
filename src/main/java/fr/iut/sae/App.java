package fr.iut.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * La classe principale de l'application.
 */
public class App extends Application {

    private static BorderPane root;

    private static Stage appStage;

    /**
     * La méthode principale qui lance l'application.
     * @author Even Tom
     */
    public static void main() {
        launch();
    }

    /**
     * Démarre l'application et configure la scène initiale.
     *
     * @param stage La scène principale de cette application.
     * @author Even Tom
     */
    @Override
    public void start(Stage stage) throws Exception {
        appStage = stage;
        setHome();
        stage.setResizable(false);

        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("assets/logo-app.png"));
        appStage.getIcons().add(icon);

        appStage.setTitle("Sis-France");
        appStage.show();
    }

    /**
     * Définit la scène sur le fichier FXML spécifié et renvoie le contrôleur associé.
     *
     * @param fxmlPath Le chemin vers le fichier FXML.
     * @param <T>      Le type du contrôleur.
     * @return Le contrôleur associé au fichier FXML.
     * @author Tamarin Maxime
     */
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

    /**
     * Définit la scène sur la page d'accueil.
     * @author Even Tom
     */
    public static void setHome() {
        try {
            root = FXMLLoader.load(App.class.getClassLoader().getResource("layout/home.fxml"));
            appStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
