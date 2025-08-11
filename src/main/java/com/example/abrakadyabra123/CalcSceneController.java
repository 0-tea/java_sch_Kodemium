package com.example.abrakadyabra123;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Collections;



public class CalcSceneController {
    // lab
    @FXML private Label labMainNum;
    @FXML private Label labHis;
    @FXML private Label labCalcError;
    @FXML private AnchorPane HisCalcScene;
    @FXML private AnchorPane CalcScene;
    @FXML private ScrollPane scrollPane1233;
    //
    @FXML private GridPane PainMainButton;

    @FXML private Button butMainDellAll;
    @FXML private Button butMainDell;
    @FXML private Button butMainHisView;


    ArrayList<String> Resources_example = new ArrayList<>();
    ArrayList<String> Separate_Resources_example = new ArrayList<>();
    ArrayList<String> History_example = new ArrayList<>();
    private final StringProperty dynamicText = new SimpleStringProperty("");

    @FXML
    public void initialize() {
        HisCalcScene.setDisable(true);
        HisCalcScene.setOpacity(0);

        Collections.addAll(Separate_Resources_example, "<-", "HIS", "С", "=");

        labMainNum.textProperty().bind(dynamicText);
        labMainNum.setMaxWidth(Double.MAX_VALUE);

        scrollPane1233.setContent(labMainNum);
        scrollPane1233.setFitToWidth(false);
        scrollPane1233.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        PainMainButton.getChildren().stream()
                .filter(but -> but instanceof Button)
                .map(but -> (Button) but)
                .forEach(button -> {
                    if (!Separate_Resources_example.contains(button.getText() )){
                        button.setOnAction(han12dler);
                    }
                });
    }

    public void updateText(String newText) {
        Platform.runLater(() -> {
            dynamicText.set(newText);
        });
    }

    EventHandler<ActionEvent> han12dler = (ActionEvent event) -> {
         String label = "";
            if (Resources_example.size() <= 23){
                Button used_button = (Button) event.getSource();
                Resources_example.add(used_button.getText());
                for(String i : Resources_example){
                    label+=i;
                }
                updateText(label);
            }else{
                labCalcError.setText("Слишком длинный ввод");
            }

    };

    @FXML
    private void on_butMainDell_Click(){
        Resources_example.removeLast();
        String label = "";
        for(String i : Resources_example){
            label+=i;
        }
        updateText(label);
    }

    @FXML
    private void on_butMainDellAll_Click(){
        Resources_example.clear();
        String label = "";
        updateText(label);
    }

    @FXML
    private void on_butCalcHis_Click(){
        CalcScene.setDisable(true);
        CalcScene.setOpacity(0);
        HisCalcScene.setDisable(false);
        HisCalcScene.setOpacity(1);
        String len;
        int count=0;
        for (String i : History_example){
            len = labHis.getText();

            count++;
            if(count == 3) {
                labHis.setText(len + i + "   \n");
                count=0;
            }else{
                labHis.setText(len + i + "   ");
            }
        }
    }

    @FXML
    private void on_butHisExit_Click(){

        HisCalcScene.setDisable(true);
        HisCalcScene.setOpacity(0);
        CalcScene.setDisable(false);
        CalcScene.setOpacity(1);
    }

    @FXML
    private void on_butMainResult_Click(){
        String example = "";
        System.out.println(Resources_example);
        for (String s : Resources_example) {
            example += s;
        }
        calc(example);
    }

    public void calc(String example) {
        try {
            example = example.replaceAll(" ", "");
            String[] Arrays_example = example.split("\\W");
            int num1 = Integer.parseInt(Arrays_example[0]);
            int num2 = Integer.parseInt(Arrays_example[1]);

            String symbol = example.replaceAll("[0-9]", "");

            float result = 0;

            switch (symbol) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "/":
                    result = (float) num1 / num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "^":
                    int num11 = num1;
                    for (int i = 1; i < num2; i++) {
                        num1 = num1 * num11;
                    }
                    result = num1;
                    break;
            }
            System.out.println(result);
            updateText("=" + result);
            History_example.add(example + "=" + result);

        } catch (NumberFormatException e) {
            dynamicText.set("Большие числа! ");
            Resources_example.clear();
        }
    }

}
