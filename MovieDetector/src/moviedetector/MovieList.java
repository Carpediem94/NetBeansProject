/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 *
 * @author carpediem
 */
public class MovieList extends TilePane {
    String title;
    String year;
    String poster;
    public MovieList(String titleSearch, char c) throws SQLException, ClassNotFoundException {
        Film film = new Film(titleSearch, c);
        
        for (int i=0; i<film.filmList.length(); i++) {
            title = film.filmList.getJSONObject(i).get("Title").toString();
            year = film.filmList.getJSONObject(i).get("Year").toString();
            poster = film.filmList.getJSONObject(i).get("Poster").toString();
            //System.out.println("Title: " + title + "\nYear: " + year + "\nPoster: " + poster);
        }
        
    }
    
}
