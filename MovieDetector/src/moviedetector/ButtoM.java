/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedetector;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author carpediem
 */
public class ButtoM extends Button {
    public ButtoM (String s) {
        setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        setPrefHeight(30);
        setId("button");
        setText(s);
    }
    
}
