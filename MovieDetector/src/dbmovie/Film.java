/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmovie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author carpediem
 */
public final class Film {
    String title;
    public String poster;
    public String year;
    public String id;
    public String plot;
    public String director;
    public String actors;
    public String rating;
    public String time;
    public String genres;
    String[] genre;
    
    public Film(String title, char c) throws SQLException, ClassNotFoundException {
        if(c=='d') {
            DbFilm(title);
        } else if (c=='j') {
            JsonFilm(title, 't');
        }  
    }
    
    public void DbFilm(String title) throws SQLException {
        DataFilmsDB dataFilms = new DataFilmsDB();

        String sql = "SELECT * FROM Movie WHERE title = ?";
        dataFilms.open();
        ResultSet rs = dataFilms.getData(sql, title);
        if(rs.next()) {
            rating = rs.getString("rating");
            plot = rs.getString("plot");
            year = rs.getString("year");
            director = rs.getString("director");
            actors = rs.getString("actors");
            time = rs.getString("time");
            genres = rs.getString("genre");
            id = "http://www.imdb.com/title/" + rs.getString("imdbID");
            poster = rs.getString("poster");    
        }
        dataFilms.close();
    }
    
    public void JsonFilm(String title, char c) throws ClassNotFoundException, SQLException {
        String t = title.replace(" ", "+");
        try {
            if (c == 't') {
                readJsonFromUrl("http://www.omdbapi.com/?t=" + t);
            } else if (c == 's') {
                readJsonFromUrl("http://www.omdbapi.com/?s=" + t);
            } else {
                //errore
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(Film.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private void readJsonFromUrl(String url) throws IOException, JSONException, ClassNotFoundException, SQLException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject film = new JSONObject(jsonText);
            
            title = film.get("Title").toString();
            poster = film.get("Poster").toString();
            year = film.get("Year").toString();
            id = film.get("imdbID").toString();
            plot = film.get("Plot").toString();
            director = film.get("Director").toString();
            actors = film.get("Actors").toString();
            rating = film.get("imdbRating").toString();
            time = film.get("Runtime").toString();
            genres = film.get("Genre").toString();
            genre = film.get("Genre").toString().split(", ");
                /*
                DataFilmsDB dataFilms = new DataFilmsDB();
                dataFilms.open();

                String sql = "INSERT INTO Movie (title, poster, year, imdbID, plot, director, actors, rating, time, genre) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                dataFilms.setData(sql, title, poster, year, id, plot, director, actors, rating, time, genres);

                for (String genre1 : genre) {
                    String sql2 = "INSERT IGNORE INTO Genres (genre) values(?)";
                    dataFilms.setData(sql2, genre1);
                }

                String sqlGet = "SELECT movieID FROM Movie WHERE title = ?";
                ResultSet rs = dataFilms.getData(sqlGet, title);
                String movieID = "";
                String genreID = "";
                if(rs.next()) {
                    movieID = rs.getString("movieID");
                }

                String sqlGet2 = "SELECT genreID FROM Genres WHERE genre = ?";
                for (String genre2 : genre) {
                    ResultSet rs2 = dataFilms.getData(sqlGet2, genre2);
                    if(rs2.next()) {
                        genreID = rs2.getString("genreID");
                    }
                    String sql3 = "INSERT INTO MovieGen (movieID, genreID) values(?, ?)";
                    dataFilms.setData(sql3, movieID, genreID);
                }
                        
                //System.out.println("Title: " + title + "\nYear: " + year + "\nDirector: " + director + "\nActors: " + actors);
                //System.out.println("Rating: " + rating + "\nTime: " + time + "\nGenre: " + genre);
                //System.out.println("Poster: " + poster + "\nPlot: " + plot + "\nid" + id);
                dataFilms.close();*/
            
        }
    }
}
