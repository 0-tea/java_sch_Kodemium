package com.example.abrakadyabra123;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FINAL {

    @FXML private Button finalButton;
    @FXML private Label finalLabel123;

    @FXML
    private void finalScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FINAL!1.fxml"));
        Stage stage = (Stage) finalButton.getScene().getWindow();

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 700, 300);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void lost_vote() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("lost.fxml"));
        Stage stage = (Stage) finalButton.getScene().getWindow();

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 700, 300);
        stage.setScene(scene);
        stage.show();
    }
}
