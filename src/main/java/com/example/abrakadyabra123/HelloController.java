package com.example.abrakadyabra123;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label text;

    @FXML
    private Label textError;

    @FXML
    private Button buttonLeft;

    @FXML
    protected void onLeftButtonClick() {
        textError.setOpacity(0);

        buttonLeft.setOpacity(0);
        buttonRight.setOpacity(1);
        text.setStyle("-fx-text-fill: green;");
        text.setText("надпись ");
    }

    @FXML
    private Button buttonRight;

    @FXML
    protected void onRightButtonClick() {
        textError.setOpacity(0);

        buttonLeft.setOpacity(1);
        buttonRight.setOpacity(0);
        text.setStyle("-fx-text-fill: purple;");
        text.setText("надпись ");
    }

    @FXML
    private Button button;

    @FXML
    protected void onbuttonButtonClick() {
        if ( text.getStyle() == "-fx-text-fill: purple;"){
            textError.setText("Нажмите на кнопку Left1 что бы поменять цвет ");
            textError.setOpacity(1);

        }if ( text.getStyle() == "-fx-text-fill: green;"){
            textError.setText("Нажмите на кнопку Right2 что бы поменять цвет ");
            textError.setOpacity(1);
        }
    }
}