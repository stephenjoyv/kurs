package com.kurs.kurs;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.Descriptor;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;


public class HelloController {
    @FXML
    private TextFlow main_title_tx;
    @FXML
    private TextFlow login_text;
    private Text main_title_text;
    private Text description;
    private Text login_title;
    private String main_title;
    private String description_text;
    private String login_string;
    @FXML
    private BorderPane books;
    static int move_count;
    public void title(){
        main_title = new String("\t\t\t\tДобро пожаловать, это умная библиотека.\n");
        description_text = new String("\t\tЗдесь вы можете опубликовать свои книги,ознакомиться с данными существующих материалов," +
                "а также получить справку по различным характеристикам хранящихся здесь произведений.");
        login_string = new String("Пройдите авторизацию, нажав на одну из кнопок ниже");
        //Добро пожаловать, это умная библиотека.
         //Здесь вы можете опубликоать свои книги,
         //ознакомиться с данными существующих материалов,
         //а также получить справку по различным характеристикам хранящихся здесь произведений.
        main_title_text = new Text();
        main_title_text.setFont(Font.font("Montserrat",32));
        main_title_text.setText(main_title);
        main_title_text.setFill(Color.WHITE);
        main_title_text.setStrokeWidth(1.2);
        main_title_text.setStrokeType(StrokeType.OUTSIDE);
        main_title_text.setStroke(Color.RED);
        main_title_text.setStyle("-fx-font-weight:bold");

        description = new Text();
        description.setFont(Font.font("Montserrat",26));
        description.setText(description_text);
        description.setFill(Color.BLACK);

        description.setStrokeWidth(1.8);
        description.setStroke(Color.WHITE);
        DropShadow ds = new DropShadow();

        ds.setOffsetX(10);
        ds.setOffsetY(5);
        ds.setColor(Color.RED);
        description.setEffect(ds);

        login_title = new Text();
        login_title.setFont(Font.font("Verdana",15));
        login_title.setText(login_string);
        login_title.setFill(Color.WHITE);

        main_title_tx.getChildren().add(main_title_text);
        main_title_tx.getChildren().add(description);
        move_count = 0;

        login_text.getChildren().add(login_title);
        TranslateTransition ts = new TranslateTransition();
        move(true,ts,books);
        ts.setOnFinished((ActionEvent ex)->{
                    if (move_count%2==0) {
                        move(true,ts,books);

                    }
                    else {
                        move(false,ts,books);
                        //books.setLayoutX(0);
                    }
                }


        );
    }
    private Scene scene;
    private Stage stage;
    private Parent root;
    public void first(ActionEvent e){
        System.out.println("request 1");
    }
    public void second(ActionEvent e){
        System.out.println("request 2");
    }
    public void third(ActionEvent e){
        System.out.println("request 3");
    }
    public void login(ActionEvent e){
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/Login.css").toExternalForm();
            scene.getStylesheets().add(css);
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void move(boolean x,TranslateTransition ts,BorderPane book){
        if (x){
            ts.setDuration(Duration.seconds(2.5));
            ts.setNode(book);
            ts.setToY(20);
            ts.play();
        }
        else {
            ts.setDuration(Duration.seconds(2.5));
            ts.setNode(book);
            ts.setToY(-20);
            ts.play();
        }
        move_count++;
    }
    public void registration(ActionEvent e){
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/Registration.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/Registration.css").toExternalForm();
            scene.getStylesheets().add(css);
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void setScene1(ActionEvent e) throws IOException {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/NewScene.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/NewScene.css").toExternalForm();
            scene.getStylesheets().add(css);
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

    }
    public void setScene2(ActionEvent e) throws IOException {
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/NewScene1.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

}