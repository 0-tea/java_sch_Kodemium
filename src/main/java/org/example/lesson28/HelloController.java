package org.example.lesson28;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class HelloController implements Initializable {

    @FXML
    AnchorPane root = new AnchorPane();
    @FXML Label labScore;
    int scoreValue;

    int gameValue = 0;
    boolean valueForRestart = false;
    int gameStatDelRow = 0;
    int gameStatDelCol = 0;
    int gameStatMaxValue;


    private GridPane grid = new GridPane();
    private boolean[][] field = new boolean[10][];
    int[][] matrix_game = new int[10][10];
    private Double[][] polygons = {
            {0.0, 0.0, 50.0, 0.0, 50.0, 50.0, 0.0, 50.0}, // квадрат
            {0.0, 0.0, 100.0, 0.0, 100.0, 100.0, 0.0, 100.0}, // большой квадрат
            {0.0, 0.0, 150.0, 0.0, 150.0, 50.0, 0.0, 50.0}, // прямоугольник горизонтальный
            {0.0, 0.0, 50.0, 0.0, 50.0, 150.0, 0.0, 150.0}, // прямоугольник вертикальный
            {0.0, 0.0, 50.0, 0.0, 50.0, 100.0, 150.0, 100.0, 150.0, 150.0, 0.0, 150.0},  // угловая
            {0.0, 0.0, 50.0, 0.0, 50.0, -50.0, 100.0, -50.0, 100.0, 50.0, 50.0, 50.0, 50.0, 100.0, 0.0, 100.0, }, // ступеньки вверх слева на право
            {0.0, 0.0, 50.0, 0.0, 50.0, 50.0, 100.0, 50.0, 100.0, 150.0, 50.0, 150.0, 50.0, 100.0, 0.0, 100.0, }, // ступеньки вверх справа на лево
            {0.0, 0.0, 100.0, 0.0, 100.0, 50.0, 50.0, 50.0, 50.0, 100.0, -50.0, 100.0, -50.0, 50.0, 0.0, 50.0, }, // ступеньки на право
            {0.0, 0.0, 100.0, 0.0, 100.0, 50.0, 150.0, 50.0, 150.0, 100.0, 50.0, 100.0, 50.0, 50.0, 0.0, 50.0, }, // ступеньки влево
            {0.0, 0.0, 50.0, 0.0, 50.0, -50.0, 100.0, -50.0, 100.0, 0.0, 150.0, 0.0, 150.0, 50.0, 0.0, 50.0}, // гора вверх
            {0.0, 0.0, 50.0, 0.0, 50.0, 150.0, 0.0, 150.0, 0.0, 100.0, -50.0, 100.0, -50.0, 50.0, 0.0, 50.0, }, // горав влево
            {0.0, 0.0, 50.0, 0.0, 50.0, 50.0, 100.0, 50.0, 100.0, 100.0, 50.0, 100.0, 50.0, 150.0, 0.0, 150.0, }, // горав вправо

    };

    private int[][] polygonsShift = {
            {},                         // квадрат
            {1, 0, 1, 1, 0, 1},         // большой квадрат
            {1, 0, 2, 0},               // прямоугольник горизонтальный
            {0, 1, 0, 2},               // прямоугольник вертикальный
            {0, 1, 0, 2, 1, 2, 2, 2},   // угловая
            {0, 1, 1, 0, 1, -1},        // ступеньки вверх слева на право
            {0, 1, 1, 1, 1, 2},         // ступеньки вверх справа на лево
            {1, 0, 0, 1, -1, 1},        // ступеньки на право
            {1, 0, 1, 1, 2, 1},         // ступеньки влево
            {1, 0, 2, 0, 1, -1},        // гора вверх
            {-1, 1, 0, 1, 0, 2},        // гора влево
            {1, 1, 0, 1, 0, 2},         // гора вправо

    };
    int currentPolygonIndex;
    private Random rand = new Random();

    boolean alertStat = false;
    boolean alertGGscene = false;
    Polygon newPolygon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGameShow();
    }

    private void startGameShow(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Главное меню");
        alert.setHeaderText(null);
        System.out.println(alertStat);
        if(alertStat){
            alert.setContentText("Игр сыграно: "+gameValue +
                    "\nУдалено вертикалей: "+gameStatDelCol +
                    "\nУдалено горизонталей: "+gameStatDelRow +
                    "\nМаксимум очков набрано: "+gameStatMaxValue);
        }else if(alertGGscene){
            alert.setContentText("Конец игры, похоже больше фигур поставить нельзя. Желаете сыграть еще раз ?");
        } else{
            alert.setContentText("Главное меню");
        }

        ButtonType playAgain = new ButtonType("Сыграть");
        ButtonType playStop = new ButtonType("Выйти", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType playStat = new ButtonType("Статистика");
        alert.getButtonTypes().setAll(playAgain, playStop, playStat);

        Optional<ButtonType> answer = alert.showAndWait();

        if(answer.isPresent() && answer.get() == playAgain){
            startGame();
        }if(answer.isPresent() && answer.get() == playStop){
            System.exit(1234);
        }
        if(answer.isPresent() && answer.get() == playStat){
            alertStat=true;
            alert.close();
            startGameShow();
        }
    }

    private void startGame(){
        alertStat=false;

        gameValue++;
        root.getChildren().remove(grid);
        grid = new GridPane();
        grid.setLayoutX(50);
        grid.setLayoutY(50);
        setupGridConstraints(grid);

        root.getChildren().add(grid);
        createPolygon();
        initializeField();
        scoreValue=0;
        labScore.setText("Score: " + scoreValue);

    }

    @FXML
    private void butRestartGame_CLICK(){
        if (valueForRestart){
            root.getChildren().remove(newPolygon);
            startGame();
            valueForRestart=false;
        }
    }


    private void initializeField() {
        for (int i = 0; i < 10; i++) {
            field[i] = new boolean[10];
            for(int j = 0; j < 10; j++ ) {
                field[i][j] = false;
            }
        }
    }

    private Polygon createPolygon() {
        valueForRestart=true;
        for (int i = 0; i < 10; i++) {

            System.out.println(Arrays.toString(matrix_game[i]));
        }
        Polygon polygon = new Polygon();
        currentPolygonIndex = rand.nextInt(polygons.length);
        polygon.getPoints().addAll(polygons[currentPolygonIndex]);
        polygon.setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
        polygon.setLayoutX(600);
        polygon.setLayoutY(300);
        setupDragHandlers(polygon);
        root.getChildren().add(polygon);
        return polygon;
    }

    private void setupDragHandlers(Polygon polygon) {

        final double[] anchorX = {0};
        final double[] anchorY = {0};
        final double[] initialTranslateX = {0};
        final double[] initialTranslateY = {0};

        polygon.setOnMousePressed(event -> {
            anchorX[0] = event.getSceneX();
            anchorY[0] = event.getSceneY();
            initialTranslateX[0] = polygon.getTranslateX();
            initialTranslateY[0] = polygon.getTranslateY();
        });

        polygon.setOnMouseDragged(event -> {
            double offsetX = event.getSceneX() - anchorX[0];
            double offsetY = event.getSceneY() - anchorY[0];
            polygon.setTranslateX(initialTranslateX[0] + offsetX);
            polygon.setTranslateY(initialTranslateY[0] + offsetY);
        });

        polygon.setOnMouseReleased(event -> {

            double currentX = polygon.getLayoutX() + polygon.getTranslateX();
            double currentY = polygon.getLayoutY() + polygon.getTranslateY();
            int currentI = (int) ((currentX - 42) / 50);
            int currentJ = (int) ((currentY - 42) / 50);
            System.out.println(currentI + " " + currentJ);
            if (currentX < 550 && currentX > 42 && currentY < 550 && currentY > 42 && canBePlaced(currentI, currentJ)) {

                fillField(currentI, currentJ, polygon.getFill());

                polygon.setOnMouseDragged(null);
                polygon.setOnMousePressed(null);
                polygon.setOnMouseReleased(null);

                polygon.setTranslateX(0);
                polygon.setTranslateY(0);
                polygon.setLayoutX(0);
                polygon.setLayoutY(0);
                root.getChildren().remove(polygon);

                labScore.setText("Score: " + scoreValue);
                if(gameStatMaxValue<scoreValue){
                    gameStatMaxValue=scoreValue;
                }
                newPolygon = createPolygon();
                if(filledField()){
                    labScore.setText("КОНЕЦ ИГРЫ");
                    root.getChildren().remove(newPolygon);
                    startGameShow();
                }
            } else {
                polygon.setTranslateX(initialTranslateX[0]);
                polygon.setTranslateY(initialTranslateY[0]);
            }
        });
    }

    private void fillField(int i, int j, Paint color) {
        field[i][j] = true;
        matrix_game[i][j] = 1;
        int[] currentShift = polygonsShift[currentPolygonIndex];
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(polygons[0]);
        polygon.setFill(color);
        grid.add(polygon, i, j);
        for (int k = 0; k < currentShift.length; k = k + 2) {
            matrix_game[i + currentShift[k]][j + currentShift[k + 1]] = 1;
            field[i + currentShift[k]][j + currentShift[k + 1]] = true;
            polygon = new Polygon();
            polygon.getPoints().addAll(polygons[0]);
            polygon.setFill(color);
            grid.add(polygon, i+currentShift[k], j+currentShift[k+1]);

        }
        checkingField();


        scoreValue += currentShift.length / 2 + 1;
    }

    private void checkingField(){
        List<Integer> filledRows = new ArrayList<>();
        List<Integer> filledColown = new ArrayList<>();

        List<Integer> saves_i = new ArrayList<>();
        int count_stolb = 0;
        // вертикаль игры
        for (int i = 0 ; i < 10 ; i++){
            for ( int j = 0 ; j < 10 ; j++){
                if(matrix_game[i][j]!=1){
                    break;
                }
                count_stolb++;
            }
            if(count_stolb==10){
                gameStatDelCol++;
                filledColown.add(i);
                saves_i.add(i);
            }

            count_stolb=0;
        }

        // горизонталь игры
        for (int j = 0 ; j < 10 ; j++){
            for ( int i = 0 ; i < 10 ; i++){
                if(matrix_game[i][j]!=1){
                    break;
                }
                count_stolb++;
            }
            if(count_stolb==10){
                gameStatDelRow++;
                for (int i1 = 0 ; i1 < 10 ; i1++){
                    field[i1][j]=false;
                    matrix_game[i1][j]=0;

                }
                filledRows.add(j);
            }

            count_stolb=0;
        }
        for (int i : saves_i){
            for (int i1 = 0 ; i1 < 10 ; i1++){
                field[i][i1]=false;
                matrix_game[i][i1]=0;
            }
        }

        if(!filledRows.isEmpty() || !filledColown.isEmpty()){
            clearField(filledRows, filledColown);
            scoreValue += filledRows.size()*10 + filledColown.size()*10;
        }

    }
    private void clearField(List filledRows, List filledColown){

        List<Node> nodeList = new ArrayList<>();
        for(Node node : grid.getChildren()){
            Integer rowIdx = GridPane.getRowIndex(node);
            Integer colIdx = GridPane.getColumnIndex(node);

            if (rowIdx == null) rowIdx = -1;
            if (colIdx == null) colIdx = -1;

            if (filledRows.contains(rowIdx) || filledColown.contains(colIdx)){
                nodeList.add(node);
            }
        }

        grid.getChildren().removeAll(nodeList);
        grid.requestLayout();
        grid.setGridLinesVisible(true);

    }


    private boolean canBePlaced(int i, int j) {
        if (field[i][j] || i >= 10 || j >= 10 ) {
            return false;
        }
        int[] currentShift = polygonsShift[currentPolygonIndex];
        for (int k = 0; k < currentShift.length; k = k + 2) {
            if (i + currentShift[k] > 9 || j + currentShift[k + 1] > 9 || i + currentShift[k] < 0 || j + currentShift[k + 1] < 0) {
                return false;
            }
            if (field[i + currentShift[k]][j + currentShift[k + 1]]) {
                return false;
            }
        }
        return true;
    }

    private boolean filledField(){
        boolean end = true;
        for (int i = 0 ; i < 10 ; i++){
            for ( int j = 0 ; j < 10 ; j++){
                if(canBePlaced(i, j)){
                    end = false;
                    break;
                }
            }
        }
        alertGGscene = true;
        return end;
    }


    private void setupGridConstraints(GridPane gridPane) {
        // Настройка столбцов
        for (int i = 0; i < 10; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setMinWidth(50);
            colConst.setMaxWidth(50);
            colConst.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(colConst);
        }

        // Настройка строк
        for (int i = 0; i < 10; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(50);
            rowConst.setMaxHeight(50);
            rowConst.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConst);
        }

        gridPane.setGridLinesVisible(true);
//        // Дополнительные настройки внешнего вида
//        gridPane.setHgap(3);
//        gridPane.setVgap(3);
    }
}