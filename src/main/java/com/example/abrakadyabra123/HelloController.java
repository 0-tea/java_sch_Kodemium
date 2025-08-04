package com.example.abrakadyabra123;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML private Label text;
    @FXML private Label textError;
    @FXML private Label textEnter;

    @FXML private TextField myTextField;

    @FXML private Button buttonGreen;
    @FXML private Button buttonPurple;
    @FXML private Button buttonBlack;
    @FXML private Button buttonBlue;
    @FXML private Button buttonEnter;

    private final Map<String, Button> buttons_Map = new HashMap<>();
    private final Map<String, Integer> clicks_button = new HashMap<>();


    @FXML
    private void initialize() {
        buttons_Map.put("PURPLE", buttonPurple);
        buttons_Map.put("BLUE", buttonBlue);
        buttons_Map.put("GREEN", buttonGreen);
        buttons_Map.put("BLACK", buttonBlack);

        clicks_button.put("PURPLE", 0);
        clicks_button.put("BLUE", 0);
        clicks_button.put("GREEN", 0);
        clicks_button.put("BLACK", 0);

        textError.setOpacity(0);
    }

    @FXML
    protected void onGreenButtonClick() {
        mainButtonClick("GREEN", Color.GREEN);
    }

    @FXML
    protected void onPurpleButtonClick() {
        mainButtonClick("PURPLE", Color.PURPLE);
    }

    @FXML
    protected void onBlackButtonClick() {
        mainButtonClick("BLACK", Color.BLACK);
    }

    @FXML
    protected void onBlueButtonClick() {
        mainButtonClick("BLUE", Color.BLUE);
    }


    private void mainButtonClick(String buttonName, Color color) {
        buttons_Map.values().forEach(i -> i.setDisable(false));

        int count = clicks_button.get(buttonName) + 1;
        clicks_button.put(buttonName, count);

        if (count == 2) {
            buttons_Map.get(buttonName).setDisable(true);
            textError.setOpacity(1);
            clicks_button.put(buttonName, 0);
        } else {
            text.setTextFill(color);
            textError.setOpacity(0);
            zeroingOtherButton(buttonName);
            buttons_Map.get(buttonName).setDisable(false);
        }
    }

    private void zeroingOtherButton(String thisButton) {
        clicks_button.keySet().stream()
                .filter(key -> key != (thisButton))
                .forEach(key -> clicks_button.put(key, 0));
    }


    String input123;
    public void enterInput(ActionEvent event) {
        input123 = myTextField.getText();
        textEnter.setText(input123);
        myTextField.setText("");
    }
}