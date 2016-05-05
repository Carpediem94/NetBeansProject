/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import moviedetector.Movie;
import moviedetector.MovieDetector;
import moviedetector.MovieList;

/**
 *
 * @author carpediem
 */
public class DbListener implements EventHandler {
    BorderPane bp;
    GridPane gp;
    TextField tf;
    TextField tf2;
    Label l;
    TilePane tp;
    char cc;
    
    DbListener(BorderPane layout, GridPane search, TextField searchDb, TextField searchImdb, TilePane column, char c) {
        bp = layout;
        gp = search;
        tf = searchDb;
        tf2 = searchImdb;
        tp = column;
        cc = c;
    }
    
    @Override
    public void handle (Event e) {
            try {
                //il char deve essere passato poi in base a dove vuoi cercare d = DB, t-s = imdb
                if (cc=='d') {
                    tp = new Movie(tf.getText(), cc);
                } else if (cc=='t') {
                    tp = new Movie(tf2.getText(), cc);
                } else if(cc=='s') {
                    tp = new MovieList(tf2.getText(), cc, bp, tp);
                }
            } catch (Exception ex) {
                Logger.getLogger(MovieDetector.class.getName()).log(Level.SEVERE, null, ex);
            }
            bp.getChildren().remove(gp);
            bp.setCenter(tp);
    }
}
