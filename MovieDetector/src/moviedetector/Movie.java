/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 *
 * @author carpediem
 */
public final class Movie extends TilePane {
    //private final DataFilmsDB dataFilms;
    Button imdb = new Button();
    String id;
    TextFlow rating = new TextFlow();
    TextFlow plot = new TextFlow();
    TextFlow year = new TextFlow();
    TextFlow director = new TextFlow();
    TextFlow actors = new TextFlow();
    TextFlow time = new TextFlow();
    TextFlow genre = new TextFlow();
    Image poster;
    ImageView iw;
    Text r, p, y, d, a, t, g;
    Film film;
    
    public Movie(String titleFilm, char c) throws Exception {
        Label title = new Label(titleFilm);
        //d = movie in db
        if(c=='d') {
            //d = return db movie
            film = new Film(titleFilm, 'd');
        //t = search for title
        } else if (c=='t') {
            //j = return jason
            film = new Film(titleFilm, 'j');
        }
        
        r = new Text(film.rating);
        p = new Text(film.plot);
        y = new Text(film.year);            
        d = new Text(film.director);
        a = new Text(film.actors);
        t = new Text(film.time);
        g = new Text(film.genres);
        id = "http://www.imdb.com/title/" + film.id;
        if(film.poster.equals("N/A")) {
            poster = new Image(getClass().getResourceAsStream("style/pagenotfound.png"), 335, 500, true, true);
        } else {
            poster = new Image(film.poster, 335, 500, true, true);
        }
        
        GridPane info = new GridPane();
                
        Text bold = new Text("Time:\t");
        Text bold2 = new Text("Rate:\t");
        Text bold3 = new Text("Genres:\t");
        Text bold4 = new Text("Director:\t");
        Text bold5 = new Text("Actors:\n");
        Text bold6 = new Text("Plot:\n");
        
        Bold(bold, bold2, bold3, bold4, bold5, bold6);
        TextWidth(t, r, g, d, a, p);
        
        time.getChildren().addAll(bold, t);
        info.addRow(0, time);
        rating.getChildren().addAll(bold2, r);
        info.addRow(1, rating);
        genre.getChildren().addAll(bold3, g);
        info.addRow(2, genre);
        director.getChildren().addAll(bold4, d);
        info.addRow(3, director);
        actors.getChildren().addAll(bold5, a);
        info.addRow(4, actors);
        plot.getChildren().addAll(bold6, p);
        info.addRow(5, plot);
        
        info.setVgap(5);
        imdb.getStyleClass().add("imdb-logo");
        imdb.setText("Lookup on IMDb");
        imdb.setPrefSize(220, 50);
        imdb.setId("button");
        HBox hb = new HBox(imdb);
        hb.setAlignment(Pos.CENTER);
        HBox.setHgrow(hb, Priority.ALWAYS);
        
        imdb.setOnAction((ActionEvent e) -> {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + id);
            } catch (IOException ex) {
                Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        GridPane row = new GridPane();
        title.setText(titleFilm + " (" + y.getText() + ")");
        title.setTextFill(Color.web("#336699"));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        title.setWrapText(true);
        plot.setTextAlignment(TextAlignment.JUSTIFY);
        GridPane.setHalignment(title, HPos.CENTER);
        row.addRow(0, title);
        row.addRow(1, info);
        row.addRow(3, hb);
        row.setVgap(25);
        
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setPrefColumns(2);
        this.setPrefTileWidth(355);
        iw = new ImageView(poster);
        iw.getStyleClass().add("border");
        TilePane.setAlignment(iw, Pos.CENTER_RIGHT);
        this.getChildren().addAll(row, iw);
    }
    
    public static void Bold(Text...param) {
        for (Text param1 : param) {
            param1.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        }
    }
    
    public static void TextWidth(Text...param) {
        for (Text param1 : param) {
            param1.setFont(Font.font("Verdana", 14));
        }
    }
}
