/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author carpediem
 */
public class DbListener implements EventHandler {
    BorderPane bp;
    GridPane gp;
    TextField tf;
    Label l;
    TilePane tp;
    
    DbListener(BorderPane layout, GridPane search, TextField searchDb, Label title, TilePane column) {
        bp = layout;
        gp = search;
        tf = searchDb;
        l = title;
        tp = column;
    }
    
    @Override
    public void handle (Event e) {
        l = new Label(tf.getText());
            try {
                //il char deve essere passato poi in base a dove vuoi cercare d = DB, t = imdb
                tp = new Movie(l, 'd');
            } catch (Exception ex) {
                Logger.getLogger(MovieDetector.class.getName()).log(Level.SEVERE, null, ex);
            }
            bp.getChildren().remove(gp);
            bp.setCenter(tp);
            tf.setPromptText("Search Film");
    }
}
