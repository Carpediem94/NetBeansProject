/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;

/**
 *
 * @author carpediem
 */
public class Navbar extends HBox {
    public Navbar(BorderPane layout, TilePane column, GridPane s, Label title, TextField searchDb, TextField searchImdb) {
        TextField search = new TextField();
        Button go = new ButtoM("GO!");
        Image image = new Image(getClass().getResourceAsStream("style/home.png"));
        ImageView iw = new ImageView(image);
        Button home = new ButtoM("");
        
        DbListener dl = new DbListener(layout, s, search, null, title, column, 'd');
        go.addEventHandler(ActionEvent.ACTION, dl);
        
        iw.setFitWidth(20);
        iw.setFitHeight(20);
        home.setGraphic(iw);
        
        home.setOnAction((ActionEvent e) -> {
            layout.getChildren().remove(column);
            layout.setCenter(s);
            searchDb.setText("");
            searchImdb.setText("");
        });
        
        search.setPromptText("Search Film");
        search.setPrefSize(200, 30);
        HBox.setMargin(search, new Insets(0, 5, 0, 0));
        
        HBox hb = new HBox(home);
        hb.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(hb, Priority.ALWAYS);
        
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setStyle("-fx-background-color: #336699;");
        this.getChildren().addAll(search, go, hb);
    }    
}
