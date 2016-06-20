/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author carpediem
 */
public class Authentication extends Application {
    Connection con;
    
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane layout = new BorderPane();
        GridPane input = new GridPane();
        Label title = new Label("Authentication");
        Label remember = new Label("Remember me");
        CheckBox cb = new CheckBox();
        HBox hb = new HBox();
        Button btn = new Button("Connect");
        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        List<String> list = new ArrayList();
        String conf = ".authConfig.conf";
        File f = new File(conf);
                
        if(!f.exists()) {
            writeFile("Username", "Password", conf);
        }
        
        for (String line : Files.readAllLines(Paths.get(conf))) {
                list.add(line);
        }
        
        user.setText(list.get(0));
        pass.setText(list.get(1));
        user.setMaxWidth(150);
        pass.setMaxWidth(150);
        user.setCursor(Cursor.TEXT);
        pass.setCursor(Cursor.TEXT);
        cb.setCursor(Cursor.HAND);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        title.setAlignment(Pos.CENTER);
        btn.setAlignment(Pos.CENTER);
        
        GridPane.setHalignment(user, HPos.CENTER);
        GridPane.setHalignment(pass, HPos.CENTER);
        GridPane.setHalignment(btn, HPos.CENTER);
        GridPane.setMargin(title, new Insets(0,0,5,0));
        
        hb.getChildren().addAll(remember, cb);
        HBox.setMargin(cb, new Insets(0,0,0,5));
        HBox.setMargin(hb, new Insets(2,0,0,0));
        hb.setAlignment(Pos.CENTER);
        
        input.addRow(0, title);
        input.addRow(1, user);
        input.addRow(2, pass);
        input.addRow(3, hb);
        input.addRow(4, btn);
        input.setVgap(10);
        input.setAlignment(Pos.CENTER);
                
        btn.setCursor(Cursor.HAND);
        btn.setOnAction((ActionEvent e) -> {
            connect(user, pass, cb, conf);
        });
                
        layout.setCenter(input);
        BorderPane.setMargin(input, new Insets(20,20,20,20));
        
        Group root = new Group(layout);
        
        Scene scene = new Scene(root);        
        scene.setFill(Color.rgb(205,205,205));
        scene.setOnKeyPressed((KeyEvent ke) -> {
            switch (ke.getCode()) {
                case ENTER: connect(user, pass, cb, conf); break; 
            }
        });
        
        stage.setTitle("Authentication");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void writeFile (String user, String pass, String conf) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter(conf, "UTF-8")) {
            writer.println(user);
            writer.println(pass);
        }
    }
    
    public void connect (TextField user, TextField pass, CheckBox cb, String conf) {
        con = new Connection(user.getText(), pass.getText());
        if(cb.isSelected()) {
            try {
                writeFile(user.getText(), pass.getText(), conf);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
