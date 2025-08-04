package com.example.abrakadyabra123;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class F1nal_conroller {

    @FXML
    private Label finalLabel123;

    @FXML
    private void initialize() {
        finalLabel123.setText("Поздравляем! Вы успешно прошли викторину. Попыток потрачено: "+ StartController.count);
    }

    @FXML
    protected void onExitClick() {
        System.exit(6969696);
    }
}
