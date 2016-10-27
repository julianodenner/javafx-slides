package com.jdenner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Juliano
 */
public class Aplicacao extends javafx.application.Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        enableAntialiasing();
        launch(args);
    }

    private static void enableAntialiasing() {
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Janela.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

}
