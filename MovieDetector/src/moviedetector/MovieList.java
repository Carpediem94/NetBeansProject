/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    
    public MovieList(String titleSearch, char c) throws SQLException, ClassNotFoundException {
        film = new Film(titleSearch, c);
        setPrefColumns(1);
        for (int i=0; i<film.filmList.length(); i++) {
            title = film.filmList.getJSONObject(i).get("Title").toString();
            year = film.filmList.getJSONObject(i).get("Year").toString();
            poster = new Image(film.filmList.getJSONObject(i).get("Poster").toString(), 83.75, 125, true, true);
            getChildren().add(SingleMovie(title, year, poster));
        }
        
    }
    
    public HBox SingleMovie(String title, String year, Image poster) {
        ImageView iw = new ImageView(poster);
        Label t = new Label(title);
        Label y = new Label(year);
        HBox hb = new HBox();
        VBox vb = new VBox();
        Button show = new ButtoM("Show");
        
        vb.getChildren().addAll(t, y);
        
        hb.getChildren().addAll(iw, vb, show);
        return hb;
    }
    
}
