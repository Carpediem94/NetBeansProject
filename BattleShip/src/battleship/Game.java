/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author carpediem
 */
public final class Game {
    GridPane griglia;
    Stage stage;
    int winner;
    public Game(boolean[][] matrix, GridPane setUp, int win) {
        griglia = new GridPane();
        winner = win;
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                Rectangle cella = new Rectangle(50, 50);
                cella.setFill(Color.WHITE);
                cella.setStroke(Color.BLACK);
                cella.setOnMouseClicked((MouseEvent event) -> {
                    fillCell(GridPane.getColumnIndex(cella), GridPane.getRowIndex(cella), cella, matrix, setUp);    
                });
                griglia.add(cella, i, j); 
            }
        }
    }
    
    public void play() {
        BorderPane root = new BorderPane();
        BorderPane.setMargin(griglia, new Insets(10,10,10,10));
        root.setCenter(griglia);
        Scene scene = new Scene(root, 530,530);
        stage = new Stage();
        stage.setTitle("Battle Ship");
        stage.setScene(scene);
        stage.show();
    }
    
    public void fillCell(int col, int row, Rectangle cell, boolean[][] matrix, GridPane setUp) {
        if(matrix[col][row]) {
            Rectangle suCell = (Rectangle)getCell(setUp, col, row);
            fade(cell);
            cell.setFill(Color.RED);
            suCell.setFill(Color.RED);
        } else {
            Rectangle suCell = (Rectangle)getCell(setUp, col, row);
            fade(cell);
            cell.setFill(Color.AQUA);
            suCell.setFill(Color.AQUA);
        }
        win(griglia);
    }
    
    private Node getCell(GridPane gp, int col, int row) {
        for (Node node : gp.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    public void close() {
        stage.close();
    }
    
    public void win(GridPane gp) {
        int win = 0;
        for (Node node : gp.getChildren()) {
            Rectangle rec = (Rectangle)node;    
            if (rec.getFill().equals(Color.RED)) {
                win++;
                if(win==winner) {
                    Label l = new Label("Hai Vinto!");
                    l.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                    l.setAlignment(Pos.CENTER);
                    HBox hb = new HBox(l);
                    HBox.setMargin(l, new Insets(10,10,10,10));
                    Scene winScene = new Scene(hb);
                    Stage winStage = new Stage();
                    winStage.setScene(winScene);
                    winStage.show();
                }
            }
        }
    }
    
    public void fade(Rectangle cell) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), cell);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    
}
