package com.example.abrakadyabra123;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.abrakadyabra123.PlayerChoiceSceneController.*;


public class GameSceneController {
    @FXML private GridPane gridButAll;
    @FXML private Label labInf;
    @FXML private Label lubP1wins;
    @FXML private Label lubP2wins;
    @FXML private Button butNextGame;
    @FXML private Button butBack;
    @FXML private Button butExit;

    ArrayList<String> not_Empty_Button = new ArrayList<>();
    String[][] game_matrix = new String[3][3];
    int count=0;
    boolean draw = true;
    boolean and = false;
    int count_goes = 0;
    String P1_Nick;static int P1_Wins;
    String P2_Nick;static int P2_Wins;
    static int count_game;

    @FXML
    public void initialize() {
        count_game++;

        if(P1_NickWins.get("P1") == null){
            P1_Nick = String.valueOf(P1_NickWins.keySet());
            P1_Nick = P1_Nick.substring( 1, P1_Nick.length() - 1 );
            lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
        }else{
            P1_Nick = "P1";
            lubP1wins.setText("P1 wins " + P1_NickWins.get("P1"));
        }

        if(P2_NickWins.get("P2") == null){
            P2_Nick = String.valueOf(P2_NickWins.keySet());
            P2_Nick = P2_Nick.substring( 1, P2_Nick.length() - 1 );
            lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
        }else{
            P2_Nick = "P2";
            lubP2wins.setText("P2 wins " + P2_NickWins.get("P2"));
        }

        int count1 = 0;
        for (int i = 0 ; i<3 ; i++){
            for (int j = 0 ; j<3 ; j++){count1++;
                game_matrix[i][j] = String.valueOf(count1);
            }
        }

        butNextGame.setDisable(true);
        butNextGame.setOpacity(0);
        labInf.setText("Первый ход -> "+ Player_Goes_XO.get("P1"));

        gridButAll.getChildren().stream()
                .filter(but -> but instanceof Button)
                .map(but -> (Button) but)
                .forEach(button -> {
                    button.setOnAction(click_button);
                });
    }


//    private void dubleStart() {
//        if(P1_NickWins.get("P1") == null){
//            P1_Nick = String.valueOf(P1_NickWins.keySet());
//            P1_Nick = P1_Nick.substring( 1, P1_Nick.length() - 1 );
//            lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
//        }else{
//            P1_Nick = "P1";
//            lubP1wins.setText("P1 wins " + P1_NickWins.get("P1"));
//        }
//
//        if(P2_NickWins.get("P2") == null){
//            P2_Nick = String.valueOf(P2_NickWins.keySet());
//            P2_Nick = P2_Nick.substring( 1, P2_Nick.length() - 1 );
//            lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
//        }else{
//            P2_Nick = "P2";
//            lubP2wins.setText("P2 wins " + P2_NickWins.get("P2"));
//        }
//    }

    @FXML
    private void on_butExit_CLICK(){
        System.exit(0);
    }

    @FXML
    private void on_butBack_CLICK() throws IOException {
        P1_Wins = P1_NickWins.get(P1_Nick);
        P2_Wins = P2_NickWins.get(P2_Nick);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Player_choice-scene.fxml"));
        Stage stage = (Stage) butBack.getScene().getWindow();

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/7e203bcb22d836c781d1ed379ba7cace.jpg")));
        stage.getIcons().add(icon);

        Scene scene = new Scene(fxmlLoader.load(), 240, 280);
        stage.setScene(scene);
        stage.show();

    }


    EventHandler<ActionEvent> click_button = (ActionEvent event) -> {
//        dubleStart();

        labInf.setText("");
        count_goes++;

        Button used_button = (Button) event.getSource();
        if(used_button.getText().isEmpty()){
            not_Empty_Button.add(used_button.getId().substring(1));
            count++;

            if (count%2==0){
                game_matrix[Integer.parseInt(used_button.getId().substring(1).split("_")[0])]
                    [Integer.parseInt(used_button.getId().substring(1).split("_")[1])] = Player_Goes_XO.get("P2");
                used_button.setText(Player_Goes_XO.get("P2"));
                count=0;

            }else{
                game_matrix[Integer.parseInt(used_button.getId().substring(1).split("_")[0])]
                        [Integer.parseInt(used_button.getId().substring(1).split("_")[1])] = Player_Goes_XO.get("P1");
                used_button.setText(Player_Goes_XO.get("P1"));
            }
        }
        Search_win();

    };

    private void Search_win(){
        // горизонтали
        for (int i = 0 ; i < 3 ; i++){
            if (game_matrix[i][0] == game_matrix[i][1] && game_matrix[i][0] == game_matrix[i][2]){
                wins_draw("Победили "+game_matrix[i][0]+" ряд "+i);
                if(Player_Goes_XO.get("P1").contains(game_matrix[i][0])){
                    P1_NickWins.merge(P1_Nick, 1, Integer::sum);
                    lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
                }else{
                    P2_NickWins.merge(P2_Nick, 1, Integer::sum);
                    lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
                }
                and = true;
                return;
            }
        }
        // вертикали
        for (int i = 0 ; i < 3 ; i++){
            if (game_matrix[0][i] == game_matrix[1][i] && game_matrix[0][i] == game_matrix[2][i]){
                wins_draw("Победили "+game_matrix[0][i]+" столбец "+i);
                if(Player_Goes_XO.get("P1").contains(game_matrix[0][i])){
                    P1_NickWins.merge(P1_Nick, 1, Integer::sum);
                    lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
                }else{
                    P2_NickWins.merge(P2_Nick, 1, Integer::sum);
                    lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
                }
                and = true;
                return;
            }
        }
        // горизонтали
        if (game_matrix[0][0] == game_matrix[1][1] && game_matrix[0][0] == game_matrix[2][2]){
            wins_draw("Победили "+game_matrix[0][0]+" горизонталь слева направо ");
            if(Player_Goes_XO.get("P1").contains(game_matrix[0][0])){
                P1_NickWins.merge(P1_Nick, 1, Integer::sum);
                lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
            }else{
                P2_NickWins.merge(P2_Nick, 1, Integer::sum);
                lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
            }
            and = true;
            return;
        }
        if (game_matrix[0][2] == game_matrix[1][1] && game_matrix[0][2] == game_matrix[2][0]){
            wins_draw("Победили "+game_matrix[0][2]+" горизонталь справа налево ");
            if(Player_Goes_XO.get("P1").contains(game_matrix[0][2])){
                P1_NickWins.merge(P1_Nick, 1, Integer::sum);
                lubP1wins.setText(P1_Nick + " wins " + P1_NickWins.get(P1_Nick));
            }else{
                P2_NickWins.merge(P2_Nick, 1, Integer::sum);
                lubP2wins.setText(P2_Nick + " wins " + P2_NickWins.get(P2_Nick));
            }
            and = true;
            return;
        }
        // ничья
        if(count_goes > 8){
            wins_draw("Ничья!");
            and = true;
            return;
        }


    }


    private void wins_draw(String text){

        labInf.setText(text);
        butNextGame.setDisable(false);
        butNextGame.setOpacity(1);
    }

    // Next game
    @FXML
    private void set_scene(ActionEvent event){
        if (and){

            int count1 = 0;
            for (int i = 0 ; i<3 ; i++){
                for (int j = 0 ; j<3 ; j++){count1++;
                    game_matrix[i][j] = String.valueOf(count1);
                }
            }

            gridButAll.getChildren().stream()
                    .filter(node -> node instanceof Button)
                    .forEach(node -> {
                            Button but =  (Button) node;
                            but.setText("");
            });

             labInf.setText("Первый ход -> "+ Player_Goes_XO.get("P1"));
             and = false;
             butNextGame.setOpacity(0);
             butNextGame.setDisable(true);
             count=0;
             count_goes = 0;
        }
    }
}
