/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cambiaColor;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author carpediem
 */
public class ChangeColor extends Application {
    int r = 0;
    int g = 0;
    int b = 0;
    
    Slider red = new Slider(0,255,0);
    Slider green = new Slider(0,255,0);
    Slider blue = new Slider(0,255,0);
    Rectangle rec = new Rectangle(200, 200);
    
    Label lr = new Label("Rosso");
    Label lg = new Label("Verde");
    Label lb = new Label("Blu");
    
    Label redValue = new Label("" + r);
    Label greenValue = new Label("" + g);
    Label blueValue = new Label("" + b);
    
    @Override
    public void start (Stage stage) {
        stage.setTitle("Strega Comanda Color");
        
        rec.setFill(Color.rgb(r,g,b));
        rec.setStroke(Color.BLACK);
        rec.setStrokeWidth(5);
        
        lr.setTextFill(Color.rgb(255, 0, 0));
        lg.setTextFill(Color.rgb(0, 255, 0));
        lb.setTextFill(Color.rgb(0, 0, 255));
        
        GridPane slider = new GridPane();
        slider.setAlignment(Pos.CENTER_LEFT);
        slider.setVgap(10);
        slider.setHgap(40);
        
        slider.add(lr, 0,0);
        slider.add(red, 1,0);
        slider.add(redValue, 2,0);
        
        slider.add(lg, 0,3);
        slider.add(green, 1,3);
        slider.add(greenValue, 2,3);
        
        slider.add(lb, 0,6);
        slider.add(blue, 1,6);
        slider.add(blueValue, 2,6);
        
        TilePane column = new TilePane();
        column.setPadding(new Insets(20, 20, 20, 20));
        column.setPrefColumns(2);
        column.setPrefTileWidth(300);
        column.setPrefTileHeight(200);
        column.setAlignment(Pos.CENTER);
        column.getChildren().addAll(slider, rec);
        
        Group root = new Group(column);
        Scene scene = new Scene(root, 600, 240);
        stage.setScene(scene);
        stage.show();
        
        red.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
            r = newVal.intValue();
            redValue.setText("" + r);
            rec.setFill(Color.rgb(r,g,b));
        });
        
        green.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
            g = newVal.intValue();
            greenValue.setText("" + g);
            rec.setFill(Color.rgb(r,g,b));
        });
        
        blue.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
            b = newVal.intValue();
            blueValue.setText("" + b);
            rec.setFill(Color.rgb(r,g,b));
        });
   }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
