/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author carpediem
 */
public class CountButton extends HBox {
    Label count;
    ToggleButton btn;
    
    public CountButton(String s, int n) {
        btn = new ToggleButton(s);
        count = new Label("" + n);
        count.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setMargin(count, new Insets(0,10,0,0));
        setMargin(btn, new Insets(0,5,0,0));
        getChildren().addAll(btn, count);
    }
    
    public void setCount(int n) {
        count.setText("" + n);
    }
}
