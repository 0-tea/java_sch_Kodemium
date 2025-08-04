package com.example.abrakadyabra123;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartController {
    static int count = 0;
    @FXML private Button nextButton;

    @FXML
    private void nextScene() throws IOException{
        count++;
        System.out.println(StartController.count);

//        Parent root = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(root);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage = (Stage) nextButton.getScene().getWindow();

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 700, 300);
        stage.setScene(scene);
        stage.show();
    }
}
