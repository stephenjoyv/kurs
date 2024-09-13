package com.kurs.kurs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;


public class HelloApplication extends Application {

    @FXML
    private DatePicker inputDate;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        try{

            //Class.forName("org.postgresql.Driver");
            System.out.println(getClass().getResource("/Welcome.fxml"));
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);//Color.RED);
            //scene.getStylesheets().add(getClass().getResource("/NewScene.css").toExternalForm());
            DB db = new DB();
            db.connectToDb();
//            String [] tag  = {"Fantastic","Horror","Romance"};
//            db.insertBook("Le jardin",2000,457,tag,"The fantastic romance about young man" +
//                    "who be loved in pretty girl with beautiful wide smile");
            db.getTags();
            String css = this.getClass().getResource("/CSS/Welcome.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Smart Library");
            HelloController hc = loader.getController();
            hc.title();

//            create table logins (
//              id SERIAL PRIMARY KEY,
//              login VARCHAR(100),
//              password VARCHAR(100),
//                status VARCHAR(100)
//              last_enter DATE
//            );
//            Authorization au = new Authorization();
//            au.login("Jen","312541");

//            create table authors (
//              id SERIAL PRIMARY KEY,
//              name VARCHAR(100),
//              books INT DEFAULT 0,
//              maximum_size INT DEFAULT 0
//            );
//            create table books (
//              id SERIAL PRIMARY KEY,
//              name VARCHAR(100),
//                release INT,
//                length INT,
//              books INT DEFAULT 0,
//              maximum_size INT DEFAULT 0
//            );
            stage.setScene(scene);
            stage.show();
//            DB database = new DB();
//            database.connectToDb();
//            //database.checkUser("dwad","dwawd");
//            //database.insertUser("dwad","dwadaw");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
        //Group root = new Group();

//        Text text = new Text();
//        text.setText("Example");
//        text.setX(100);text.setY(100);
//        text.setFont(Font.font("Arial",75));
        //root.getChildren().add(text);


    }
}