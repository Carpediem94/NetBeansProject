package dadi;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author carpediem
 */
public class Dadi extends Application {
    double X = 600;
    double Y = 400;
    Group root;
    int countDadi = 0;
    static int point = 30;
    static Score points = new Score("PUNTEGGIO");
    static Score totale = new Score("TOTALE");
    Rectangle campo;
    BorderPane layout;
    Button anime;
    
    @Override
    public void start(Stage stage) {
        campo = new Rectangle(X, Y);
        layout = new BorderPane();
        HBox buttons = new HBox();
        HBox score = new HBox();
        Button newGame = new Button("Nuova Partita");
        Button print = new Button("Stampa");
        anime = new Button("Spostamento");
        //Score totale = new Score("TOTALE");
        //points = new Score("PUNTEGGIO");
        
        anime.setOnAction((ActionEvent e) ->{
            if(anime.getText().equals("Spostamento")) {
                anime.setText("Dissolvimento");
            } else {
                anime.setText("Spostamento");
            }
        });
        
        print.setOnAction((ActionEvent e) ->{
            print();
        });
        
        newGame.setOnAction((ActionEvent e) ->{
            newGame();
        });
        
        points.setScore(point);
        campo.setFill(Color.GREEN);
        buttons.getChildren().addAll(newGame, print, anime);
        score.getChildren().addAll(totale, points);
        layout.setTop(buttons);
        layout.setCenter(campo);
        layout.setBottom(score);
        
        //aggiunge l'evento se clicchi nel contnuto centrale del border pane
        layout.getCenter().setOnMouseClicked((MouseEvent me) ->{
            if(countDadi < 5) {
                double dx = me.getSceneX()-35; //ritorna la coordinata X del punt cliccato
                double dy = me.getSceneY()-35; //ritorna la coordinata Y del punt cliccato
                if(((dx>5)&&(dx+75<X)) && ((dy>30)&&(dy+50<Y))) {
                    Dado d = new Dado(rand());
                    d.setLayoutX(dx); //associa a d la coordinata X
                    d.setLayoutY(dy); //associa a d la coordinata Y
                    root.getChildren().add(d);
                    int tot = totale.getScore() + d.getValue();
                    totale.setScore(tot);
                    countDadi++;
                    point = point-3;
                    points.setScore(point);
                    if(totale.getScore()==15) {
                        win();
                    }
                }
            }
        });
        
        root = new Group(layout);
        Scene scene = new Scene(root, X, Y+50);
        
        //aggiunge alla scena gli eventi da tastiera
        scene.setOnKeyPressed((KeyEvent ke) -> { 
            switch(ke.getCode()) {
                case S: print(); break;
                case N: newGame(); break;
            }
        });
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * 
     * @return ritorna un numero random tra 1 e 6 
     */
    public int rand() {
        int r = (int) (Math.random()*6);
        if(r == 0) {
            return 6;
        } else {
            return r;
        }
    }
    
    /**
     * mostra una nuova finestra se hai vinto e il punteggio fatto
     */
    public static void win() {
        Label l = new Label("Hai vinto - Hai ottenuto " + point + " punti");
        Scene scene = new Scene(l);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * stampa in una nuova finestra i dadi, il punteggio e il totale
     */
    public void print() {
        Label dado;
        Label l = new Label("Lista dadi:");
        VBox g = new VBox();
        g.getChildren().add(l);
        
        for (Node node : root.getChildren()) { //cicla sui nodi della root
            if(node instanceof Dado) { //prendo solo le instanze di dado
                int v = ((Dado)node).getValue();
                dado = new Label(String.valueOf(v));
                g.getChildren().add(dado);
            }
        }
        Label tot = new Label("Totale: " + totale.getScore());
        Label punteggio = new Label("Punteggio: " + points.getScore());
        g.getChildren().addAll(tot, punteggio);
        Scene scene = new Scene(g, 110, 150);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * pulisce il campo e crea un nuovo gioco
     */
    public void newGame() {
        //ciclo sui nodi della root e ritorno solo le istanze di Dado
        root.getChildren().stream().filter((node) -> (node instanceof Dado)).forEach((node) 
        -> {
            animation(((Dado) node)); //aggiunge l'animazione ai dadi
        });
        PauseTransition pt = new PauseTransition(Duration.millis(1000)); //mette in pausa
        pt.setOnFinished((ActionEvent e) -> {
            //rimuove da root i nodi da 1 alla sua dimensione
            root.getChildren().remove(1, root.getChildren().size()); 
            countDadi = 0;
            point = 30;
            points.setScore(30);
            totale.setScore(0);
        });
        pt.play();
    }
    /**
     * aggiunge un animazione al dado in base al parametro text del buttone anime
     * @param dado tutti i dadi presenti al momento sul campo
     */
    public void animation(Dado dado) {
        int dx = (int) (Math.random()*10);
        int dy = (int) (Math.random()*10);
        if(anime.getText().equals("Dissolvimento")) {
            FadeTransition ft = new FadeTransition(Duration.millis(1000), dado);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
        } else {
            Timeline t = new Timeline(); //100x10 durata totale dell'animazione
            t.setCycleCount(100); //quante volte ripeto il frame
            //durata del frame
            KeyFrame move =new KeyFrame(Duration.millis(10), (ActionEvent event) -> { 
                dado.setTranslateX(dado.getTranslateX() + dx);
                dado.setTranslateY(dado.getTranslateY() + dy);
            });
            t.getKeyFrames().add(move);
            t.play();
        }
    }
}
