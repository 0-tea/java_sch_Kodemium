package com.example.abrakadyabra123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Player_choice-scene.fxml"));
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 240, 280);
        stage.setTitle("Lesson_23");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}