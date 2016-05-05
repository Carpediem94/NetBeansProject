/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *
 * @author carpediem
 */
public final class MovieList extends TilePane {
    String title;
    String year;
    Image poster;
    Film film;
    BorderPane layout;
    TilePane column;
    
    public MovieList(String titleSearch, char c, BorderPane bp, TilePane tp) throws SQLException, ClassNotFoundException {
        film = new Film(titleSearch, c);
        layout = bp;
        column = tp;
        setPrefColumns(1);
        HBox hb;
        Separator sepa;
        ListView listView = new ListView();
        ObservableList films = FXCollections.observableArrayList();
        
        for (int i=0; i<film.filmList.length(); i++) {
            title = film.filmList.getJSONObject(i).get("Title").toString();
            year = film.filmList.getJSONObject(i).get("Year").toString();
            if(film.filmList.getJSONObject(i).get("Poster").toString().equals("N/A")) {
                poster = new Image(getClass().getResourceAsStream("style/pagenotfound.png"), 83.75, 125, true, true);
            } else {
                poster = new Image(film.filmList.getJSONObject(i).get("Poster").toString(), 83.75, 125, true, true);
            }
            
            hb = SingleMovie(title, year, poster);
            hb.setPadding(new Insets(13, 45, 13, 10));
            hb.setPrefWidth(750);
            
            sepa = new Separator();
            sepa.setPadding(new Insets(0,45,0,10));
            if(i==film.filmList.length()-1) {
                films.addAll(hb);
            } else {
                films.addAll(hb, sepa);
            }
        }
        
        listView.setPrefWidth(750);
        listView.setPrefHeight(535);
        listView.setItems(films);
        getChildren().add(listView);
    }
    
    public HBox SingleMovie(String title, String year, Image poster) {
        ImageView iw = new ImageView(poster);
        Label t = new Label(title);
        Label y = new Label("(" + year + ")");
        HBox hb = new HBox();
        VBox vb = new VBox();
        Button show = new ButtoM("Show");
        TextField tf = new TextField(title);
        
        DbListener dl = new DbListener(layout, null, null, tf, column, 't');
        show.addEventHandler(ActionEvent.ACTION, dl);
        
        HBox hbShow = new HBox(show);
        hbShow.setAlignment(Pos.BOTTOM_RIGHT);
        HBox.setHgrow(hbShow, Priority.ALWAYS);
        
        vb.setPadding(new Insets(0,0,5,0));
        vb.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(vb, Priority.ALWAYS);
        
        HBox.setMargin(iw, new Insets(0, 20, 0, 0));
        
        vb.getChildren().addAll(t, y);
        hb.getChildren().addAll(iw, vb, hbShow);
        return hb;
    }
    
}
