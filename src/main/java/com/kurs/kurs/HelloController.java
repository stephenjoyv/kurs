package com.kurs.kurs;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.Pair;

import javax.management.Descriptor;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;


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
    @FXML
    private Text textOne,
    textTwo;
    static int move_count;
    private boolean isAnimationActive;
    public void title(){
        main_title = new String("Добро пожаловать, это зоо-магазин.\n");
        description_text = new String("Здесь вы можете осмотреть животных,а если они вам понравятся, то и купить.\n " +
                "Тут вам представлено абсолютное разнообразие существ, от малюсеньких ящерок до огромных алабаев");
        login_string = new String("Пройдите авторизацию, нажав на одну из кнопок ниже");
        //Добро пожаловать, это умная библиотека.
         //Здесь вы можете опубликоать свои книги,
         //ознакомиться с данными существующих материалов,
         //а также получить справку по различным характеристикам хранящихся здесь произведений.
        main_title_text = new Text();
        main_title_text.setFont(Font.font("Montserrat",32));
        main_title_text.setText(main_title);
        main_title_text.setFill(Color.BLACK);
        //main_title_text.setStrokeWidth(1.2);
        //main_title_text.setStrokeType(StrokeType.OUTSIDE);
        //main_title_text.setStroke(Color.RED);
        main_title_text.setTextAlignment(TextAlignment.CENTER);
        main_title_text.setStyle("-fx-alignment: center");
        main_title_text.setStyle("-fx-font-weight:bold");


        description = new Text();
        description.setFont(Font.font("Montserrat",28));
        description.setText(description_text);
        description.setFill(Color.BLACK);

        //description.setStrokeWidth(1.8);
        //description.setStroke(Color.WHITE);
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
        main_title_tx.setTextAlignment(TextAlignment.CENTER);
        move_count = 0;

        login_text.getChildren().add(login_title);
        //TranslateTransition ts = new TranslateTransition();
        isAnimationActive = true;
        move(move_count,getCrPos(),books);
        textOne.setFont(Font.font("Montserrat",27));
        textOne.setStrokeWidth(0);
        textTwo.setFont(Font.font("Montserrat",17));
        textTwo.setStrokeWidth(0);
        //System.out.println(ts.getDuration().toSeconds());
//        ts.setOnFinished((ActionEvent ex)->{
//                        move(move_count,getCrPos(),ts,books);
//                }
//        );
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

    private ArrayList<Pair<Double, Double>> getCrPos(){
        ArrayList<Pair<Double,Double>> firstHalf = new ArrayList<Pair<Double, Double>>(),
                secondHalf = new ArrayList<Pair<Double, Double>>();
        for (Integer x = -10; x <= 10; x++){
            Double y = Math.sqrt(100 - Math.pow(x,2));
            firstHalf.add(new Pair<Double,Double>(x.doubleValue(),-1*y));
            secondHalf.add(new Pair<Double,Double>(x.doubleValue(),y));
        }
        Collections.reverse(secondHalf);
        firstHalf.addAll(secondHalf);
        return firstHalf;
    }
    public void move(int x, ArrayList<Pair<Double, Double>> posArray,BorderPane book){
        TranslateTransition ts = new TranslateTransition();
        ts.setOnFinished((ActionEvent ex)->{
                    move(move_count,getCrPos(),books);
                    //добавить проверку на соответствие сцен, иначе
//                    if(stage.isFocused()) System.out.println("stage1");
//                    else System.out.println("stage2");

                }
        );
        ts.setDuration(Duration.seconds(0.05));
        ts.setNode(book);
        ts.setToX(posArray.get(x).getKey());
        ts.setToY(posArray.get(x).getValue());
        if (!isAnimationActive) return;
        ts.play();
        System.out.println(move_count);
        move_count++;
        if(move_count==36) move_count=0;
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
            isAnimationActive = false;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
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
            isAnimationActive = false;
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