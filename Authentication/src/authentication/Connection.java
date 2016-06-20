/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author carpediem
 */
public final class Connection {
    Connection (String user, String pass) {
        WebDriver driver = new HtmlUnitDriver();
        
        driver.get("http://google.com");
        
        if (driver.getTitle().equals("Firewall Authentication")) {
            WebElement userName = driver.findElement(By.name("username"));
            WebElement passWord = driver.findElement(By.name("password"));
            userName.sendKeys(user);
            passWord.sendKeys(pass);
            passWord.submit();
            
            driver.get("http://google.com");
            if(driver.getTitle().equals("Google")) {
                error("Connection estabilished!");
                driver.quit();
                pause();
            } else {
                error("Firewall authentication failed. Please try again.");   
            }
        } else {
            error("Connection already estabilished!");
            driver.quit();
            pause();
        }
    }
    
    public void error(String er) {
        Label l = new Label(er);
        l.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        l.setAlignment(Pos.CENTER);
        
        HBox hb = new HBox(l);
        HBox.setMargin(l, new Insets(10,10,10,10));
        hb.setStyle("-fx-background-color: rgb(205,205,205);");
        
        Scene scene = new Scene(hb);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        if(!er.equals("Firewall authentication failed. Please try again.")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
    
    public void pause () {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
