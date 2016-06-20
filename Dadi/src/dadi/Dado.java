/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dadi;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author carpediem
 */
public final class Dado extends StackPane{
    Dadi d1 = new Dadi();
    Rectangle dado;
    Circle circ;
    Color color = Color.RED;
    int value;
    double R = 5;
    
    /**
     * costruisce una faccia del dado in base a <num>
     * @param num accetta un valore di tipo intero da 1 a 6
     */
    Dado(int num) {
        dado = new Rectangle(70,70);
        dado.setFill(Color.WHITE);
        
        dado.setOnMouseClicked((MouseEvent me) -> {
            Dadi.totale.setScore(Dadi.totale.getScore()-this.getValue());
            this.getChildren().clear(); //rimuovo tutto dallo stackPane
            Dado d = new Dado(d1.rand());
            this.getChildren().add(d);
            Dadi.point--;
            Dadi.points.setScore(Dadi.point);
            Dadi.totale.setScore(Dadi.totale.getScore()+d.getValue());
            if(Dadi.totale.getScore()==15) {
                Dadi.win();
            }
        });
        
        this.getChildren().add(dado);
        switch(num) {
            case 1:
                setCircle(0, 0);
                setValue(1);
                break;
            case 2:
                setCircle(-20, 20);
                setCircle(20, -20);
                setValue(2);
                break;
            case 3:
                setCircle(-20, 20);
                setCircle(0, 0);
                setCircle(20, -20);
                setValue(3);
                break;
            case 4:
                setCircle(-20, 20);
                setCircle(20, 20);
                setCircle(20, -20);
                setCircle(-20, -20);
                setValue(4);
                break;
            case 5:
                setCircle(-20, 20);
                setCircle(20, 20);
                setCircle(20, -20);
                setCircle(-20, -20);
                setCircle(0, 0);
                setValue(5);
                break;
            case 6:
                setCircle(-20, 20);
                setCircle(20, 20);
                setCircle(20, -20);
                setCircle(-20, -20);
                setCircle(20, 0);
                setCircle(-20, 0);
                setValue(6);
                break;
        }
    }
    /**
     * crea un pallino del dado e lo posiziona nella coordinata corrispondente
     * @param dx coordinata x
     * @param dy coordinata y
     */
    public void setCircle(double dx, double dy) {
        circ = new Circle(R);
        circ.setFill(color);
        circ.setTranslateX(dx);
        circ.setTranslateY(dy);
        this.getChildren().add(circ);
    }
    
    /**
     * setta il valore della faccia del dado
     * @param x intero da 1 a 6
     */
    public void setValue(int x) {
        value = x;
    }
    
    /**
     * 
     * @return ritorna il valore della faccia del dado
     */
    public int getValue() {
        return value;
    }
}
