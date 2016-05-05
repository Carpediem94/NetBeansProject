/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    public MovieList(String titleSearch, char c) throws SQLException, ClassNotFoundException {
        film = new Film(titleSearch, c);
        setPrefColumns(1);
        HBox hb;
        Separator sepa;
        VBox vb;
        
        for (int i=0; i<film.filmList.length(); i++) {
            title = film.filmList.getJSONObject(i).get("Title").toString();
            year = film.filmList.getJSONObject(i).get("Year").toString();
            poster = new Image(film.filmList.getJSONObject(i).get("Poster").toString(), 83.75, 125, true, true);
            
            hb = SingleMovie(title, year, poster);
            hb.setPadding(new Insets(20, 20, 0, 20));
            hb.setPrefWidth(750);
            
            sepa = new Separator();
            sepa.setPadding(new Insets(20,20,0,20));
            vb = new VBox();
            if(i<(film.filmList.length()-1)) {
                vb.getChildren().addAll(hb, sepa);
            } else {
                vb.getChildren().add(hb);
            }
            getChildren().add(vb);
        }
        
    }
    
    public HBox SingleMovie(String title, String year, Image poster) {
        ImageView iw = new ImageView(poster);
        Label t = new Label(title);
        Label y = new Label("(" + year + ")");
        HBox hb = new HBox();
        VBox vb = new VBox();
        Button show = new ButtoM("Show");
        
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
