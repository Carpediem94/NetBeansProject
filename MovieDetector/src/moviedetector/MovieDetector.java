/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import dbmovie.Film;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

/**
 *
 * @author carpediem
 */
public class MovieDetector extends Application {
    //private DataFilmsDB dataFilms;
    Label title;
    TilePane column;
        
    @Override
    public void start(Stage stage) throws Exception {
        //JsonFilm film = new JsonFilm("Inception");
        BorderPane layout = new BorderPane();
        TextField searchDb = new TextField();
        TextField searchImdb = new TextField();
        Button sd = new ButtoM("GO!");
        Button si = new ButtoM("GO!");
        GridPane search = new GridPane();
        
        searchDb.setPromptText("Search movie in database");
        searchDb.setAlignment(Pos.CENTER);
        searchDb.setPrefSize(300, 30);
        searchImdb.setPromptText("Search movie on IMDb");
        searchImdb.setAlignment(Pos.CENTER);
        searchImdb.setPrefSize(300, 30);
        
        //d = search in db
        DbListener dl = new DbListener(layout, search, searchDb, null, title, column, 'd');
        sd.addEventHandler(ActionEvent.ACTION, dl);
        
        //t = search for title
        DbListener dl2 = new DbListener(layout, search, null, searchImdb, title, column, 's');
        si.addEventHandler(ActionEvent.ACTION, dl2);
                        
        search.add(searchDb, 0,0);
        search.add(sd, 1,0);
        search.add(searchImdb, 0,1);
        search.add(si, 1,1);
        search.setVgap(10);
        search.setHgap(5);
        search.setTranslateY(190);
        search.setTranslateX(190);
                
        Pane nav = new Navbar(layout, column, search, title, searchDb, searchImdb);
        nav.setPrefWidth(750);
        layout.setTop(nav);
        layout.setCenter(search);
        
        Group root = new Group(layout);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("style/style.css").toExternalForm());
        scene.setFill(Color.rgb(228, 222, 255));
                
        stage.setTitle("Movie Detector");
        stage.setMaxWidth(750);
        stage.setMinWidth(750);
        stage.setMaxHeight(615);
        stage.setMinHeight(615);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
