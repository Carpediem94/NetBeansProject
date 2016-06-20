/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class BattleShip extends Application {
    CountButton a;
    CountButton h;
    CountButton v;
    GridPane griglia;
    Button reset;
    boolean[][] matrix;
    int aC;
    int hC;
    int vC;
    
    @Override
    public void start(Stage stage) {
        HBox hb = new HBox();
        VBox vb = new VBox();
        Button ok = new Button("OK");
        TextField aTf = new TextField();
        TextField hTf = new TextField();
        TextField vTf = new TextField();
        Label aL = new Label("1");
        Label hL = new Label("2H");
        Label vL = new Label("2V");
        Label title = new Label("Quante navi vuoi?");
        
        setGraphic(vTf, vL);
        setGraphic(hTf, hL);
        setGraphic(aTf, aL);
        
        vb.setAlignment(Pos.CENTER);
        VBox.setMargin(hb, new Insets(0,10,10,10));
        VBox.setMargin(ok, new Insets(0,10,10,10));
        VBox.setMargin(title, new Insets(10,10,10,10));
        
        hb.getChildren().addAll(aL, aTf, hL, hTf, vL, vTf);
        vb.getChildren().addAll(title, hb, ok);
        ok.setOnAction((ActionEvent event) -> {
            aC = Integer.parseInt(aTf.getText());
            hC = Integer.parseInt(hTf.getText());
            vC = Integer.parseInt(vTf.getText());
            battleShip(aC, hC, vC);
            stage.close();
        });
        
        Group root = new Group(vb);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void setGraphic(TextField tf, Label l) {
        l.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(l, new Insets(0,5,0,5));
        HBox.setMargin(tf, new Insets(0,5,0,5));
        tf.setPrefWidth(40);
    }
    
    public void battleShip(int aC, int hC, int vC) {
        griglia = new GridPane();
        HBox buttons = new HBox();
        reset = new Button("Reset");
        matrix = new boolean[10][10];
        
        createGrill();
        
        BorderPane root = new BorderPane();
        BorderPane.setMargin(griglia, new Insets(10,10,10,10));
        BorderPane.setMargin(buttons, new Insets(0,10,10,10));
        
        a = new CountButton("1", aC);
        h = new CountButton("2H", hC);
        v = new CountButton("2V", vC);
        a.btn.setOnAction((ActionEvent event) -> {
            offButton(h, v);
            if(Integer.parseInt(a.count.getText())==0) {
                a.btn.setSelected(false);
            }
        });
        h.btn.setOnAction((ActionEvent event) -> {
            offButton(a, v);
            if(Integer.parseInt(v.count.getText())==0) {
                v.btn.setSelected(false);
            }
        });
        v.btn.setOnAction((ActionEvent event) -> {
            offButton(h, a);
            if(Integer.parseInt(h.count.getText())==0) {
                h.btn.setSelected(false);
            }
        });
        
        buttons.getChildren().addAll(a, h, v, reset);
        buttons.setAlignment(Pos.CENTER);
                
        root.setCenter(griglia);
        root.setBottom(buttons);
        Scene scene = new Scene(root, 530,570);
        Stage stage = new Stage();
        stage.setTitle("Battle Ship");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void changeCell(int c, int r, Rectangle cella, boolean[][] matrix) {
        int aCount = Integer.parseInt(a.count.getText());
        int hCount = Integer.parseInt(h.count.getText());
        int vCount = Integer.parseInt(v.count.getText());
        
        if(a.btn.isSelected() && cella.getFill().equals(Color.WHITE)) {
            cella.setFill(Color.BLACK);
            matrix[c][r] = true;
            a.setCount(aCount-1);
            if(aCount-1==0) {
                a.btn.setSelected(false);
                if(aCount-1==0 && hCount==0 && vCount==0) {
                    startGame();
                }
            }
        } else if (h.btn.isSelected() && cella.getFill().equals(Color.WHITE)) {
            Rectangle cell = (Rectangle)getCell(griglia, c+1, r);
            if(cell != null) {
                if(cell.getFill().equals(Color.WHITE)) {
                    cell.setFill(Color.BLACK);
                    cella.setFill(Color.BLACK);
                    matrix[c][r] = true;
                    matrix[c+1][r] = true;
                    h.setCount(hCount-1);
                    if(hCount-1==0) {
                        h.btn.setSelected(false);
                        if(aCount==0 && hCount-1==0 && vCount==0) {
                            startGame();
                        }
                    }
                }
            }
        } else if (v.btn.isSelected() && cella.getFill().equals(Color.WHITE)) {
            Rectangle cell = (Rectangle)getCell(griglia, c, r+1);
            if(cell != null) {
                if(cell.getFill().equals(Color.WHITE)) {
                    cell.setFill(Color.BLACK);
                    cella.setFill(Color.BLACK);
                    matrix[c][r] = true;
                    matrix[c][r+1] = true;
                    v.setCount(vCount-1);
                    if(vCount-1==0) {
                        v.btn.setSelected(false);
                        if(aCount==0 && hCount==0 && vCount-1==0) {
                            startGame();
                        }
                    }
                }
            }
        }
    }
    
    public void offButton(CountButton a, CountButton b) {
        a.btn.setSelected(false);
        b.btn.setSelected(false);
    }
        
    private Node getCell(GridPane gp, int col, int row) {
        if(col<10 || row<10) {
            //cicla sul grid pane e ritorna il nodo alle coordinate precise
            for (Node node : gp.getChildren()) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return node;
                }
            }
        }
        return null;
    }
    
    public void startGame() {
        int win = aC+(2*hC)+(2*vC);
        Game game = new Game(matrix, griglia, win);
        reset.setOnAction((ActionEvent event) -> {
            game.close();
            reset(aC, hC, vC);
        });
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> game.play());
        delay.play();
    }
    
    public void createGrill() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                Rectangle cella = new Rectangle(50, 50);
                cella.setFill(Color.WHITE);
                cella.setStroke(Color.BLACK);
                cella.setOnMouseClicked((MouseEvent event) -> {
                    changeCell(GridPane.getColumnIndex(cella), GridPane.getRowIndex(cella), cella, matrix);    
                });
                griglia.add(cella, i, j);
                matrix[i][j] = false; 
            }
        }
    }
    
    public void reset(int aC, int hC, int vC) {
        a.setCount(aC);
        h.setCount(hC);
        v.setCount(vC);
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                Rectangle cella = (Rectangle)getCell(griglia, i, j);
                cella.setFill(Color.WHITE);
                matrix[i][j] = false;
            }
        }
    }

}
