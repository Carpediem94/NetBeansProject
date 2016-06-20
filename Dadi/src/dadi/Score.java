/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadi;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author carpediem
 */
public class Score extends HBox {
    TextField tf = new TextField();
    Label l = new Label();
    Score(String s) {
        l.setText(s);
        tf.setText("0");
        this.getChildren().addAll(l, tf);
    }
    
    public void setScore(int x) {
        tf.setText(String.valueOf(x));
    }
    
    public int getScore() {
        return Integer.parseInt(tf.getText());
    }
}
