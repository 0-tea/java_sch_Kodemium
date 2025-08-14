package com.example.abrakadyabra123;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.abrakadyabra123.GameSceneController.*;

public class PlayerChoiceSceneController {

    @FXML private Label PlayChoice_P1_goes;
    @FXML private Label PlayChoice_P2_goes;
    @FXML private TextField PlayChoice_Player1;
    @FXML private TextField PlayChoice_Player2;
    @FXML private Button PlayChoice_ExitBut;
    @FXML private Button PlayChoice_ChangeGoBut;
    @FXML private Button PlayChoice_StartBut;

    boolean count = true;
    static Map<String,String> Player_Goes_XO = new HashMap<>();
    static Map<String,Integer> P1_NickWins = new HashMap<>();
    static Map<String,Integer> P2_NickWins = new HashMap<>();

    @FXML
    public void initialize() {
        Player_Goes_XO.put("P1", "0");
        Player_Goes_XO.put("P2", "X");
    }

    @FXML
    private void CLICK_PlayChoice_ExitBut(){
        System.exit(0);
    }

    @FXML
    private void CLICK_PlayChoice_ChangeGoBut(){

        if(count){
            count=false;

            PlayChoice_P1_goes.setText("X");
            PlayChoice_P2_goes.setText("0");

            Player_Goes_XO.clear();
            Player_Goes_XO.put("P1", "X");
            Player_Goes_XO.put("P2", "0");
        }else{
            count=true;

            PlayChoice_P1_goes.setText("0");
            PlayChoice_P2_goes.setText("X");

            Player_Goes_XO.clear();
            Player_Goes_XO.put("P1", "0");
            Player_Goes_XO.put("P2", "X");
        }
    }

    @FXML
    private void CLICK_PlayChoice_StartBut() throws IOException {
        if(count_game>=1) {

            String P1_Nk = String.valueOf(P1_NickWins.keySet());
            P1_Nk = P1_Nk.substring(1, P1_Nk.length() - 1);
            P1_NickWins.clear();
            if (!PlayChoice_Player1.getText().equals(P1_Nk)) {
                if (PlayChoice_Player1.getText().isEmpty()) {
                    P1_NickWins.put("P1", 0);
                } else {

                    P1_NickWins.put(PlayChoice_Player1.getText(), 0);
                }
            } else {
                P1_NickWins.put(P1_Nk, P1_Wins);
            }

            String P2_Nk = String.valueOf(P2_NickWins.keySet());
            P2_Nk = P2_Nk.substring(1, P2_Nk.length() - 1);
            P2_NickWins.clear();
            if (!Objects.equals(PlayChoice_Player2.getText(), P2_Nk)) {
                if (PlayChoice_Player2.getText().isEmpty()) {
                    P2_NickWins.put("P2", 0);
                } else {
                    P2_NickWins.put(PlayChoice_Player2.getText(), 0);
                }
            }else{
                P2_NickWins.put(P2_Nk, P2_Wins);
            }
        }else{
            if (PlayChoice_Player1.getText().isEmpty()) {
                P1_NickWins.put("P1", 0);
            } else {
                P1_NickWins.clear();
                P1_NickWins.put(PlayChoice_Player1.getText(), 0);
            }

            if (PlayChoice_Player2.getText().isEmpty()) {
                P2_NickWins.put("P2", 0);
            } else {
                P2_NickWins.clear();
                P2_NickWins.put(PlayChoice_Player2.getText(), 0);
            }

        }
        PlayChoice_Player1.setText("");
        PlayChoice_Player2.setText("");

         FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TicTacToe-scene.fxml"));
         Stage stage = (Stage) PlayChoice_StartBut.getScene().getWindow();
         Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
         stage.getIcons().add(icon);

         Scene scene = new Scene(fxmlLoader.load(), 240, 280);
         stage.setScene(scene);
         stage.show();
    }
}
