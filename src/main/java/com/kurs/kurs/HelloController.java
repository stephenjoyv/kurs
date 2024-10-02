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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;


public class HelloController {
    private Scene scene;
    private TextFlow textFlow1,textFlow2;
    @FXML
    private TextFlow login_text;
    private Text main_title_text;
    private Text description;
    private Text login_title;
    private String mainText,descriptionText,loginString;
    @FXML
    private BorderPane iguana;
    @FXML
    private AnchorPane main;
    @FXML
    private Text textOne,
    textTwo;
    private Region textRect1, textRect2;
    static int move_count;
    private boolean isAnimationActive;
    private ImageView img;
    private ArrayList<TextFlow> textArray;
    public void title(){

        Font ft = Font.loadFont(getClass().getResource("/FT/Resist.ttf").toString().replace("%20"," "),32);
        System.out.println(ft);
//        DB db = new DB();
//        db.connectToDb();
//        db.fillTags(new ArrayList<String>(Arrays.asList("Млекопитающее", "Птица", "Пресмыкающееся", "Земноводное", "Рыба")));
        textArray = new ArrayList<TextFlow>();
        textFlow1 = new TextFlow();
        textFlow2 = new TextFlow();
        login_text = new TextFlow();
        textArray.add(textFlow1);
        textArray.add(textFlow2);
        textArray.add(login_text);
        for(int i= 0;i<textArray.size();i++){
            //textArray.set(i, new TextFlow());
            textArray.get(i).setPrefWidth(main.getPrefWidth());
        }

        mainText = new String("Youre welcome \nДобро пожаловать, это зоо-магазин.\n");
        descriptionText = new String("Здесь вы можете осмотреть животных,а если они вам понравятся, то и купить.\n " +
                "Тут вам представлено абсолютное разнообразие существ, от малюсеньких ящерок до огромных алабаев");

        loginString = new String("Пройдите авторизацию, нажав на одну из кнопок ниже");

        main_title_text = new Text();
        //main_title_text.setFont(Font.font("Resist Sans Display",32));
        main_title_text.setText(mainText);
        main_title_text.setFill(Color.BLACK);
        //main_title_text.setStrokeWidth(1.2);
        //main_title_text.setStrokeType(StrokeType.OUTSIDE);
        //main_title_text.setStroke(Color.RED);
        main_title_text.setTextAlignment(TextAlignment.CENTER);
        main_title_text.setStyle("-fx-alignment: center");
        main_title_text.setFont(ft);
        //main_title_text.setStyle("-fx-font-weight:bold");


        description = new Text();
        description.setFont(Font.font("Montserrat",32));
        description.setText(descriptionText);
        description.setFill(Color.BLACK);

        //description.setStrokeWidth(1.8);
        //description.setStroke(Color.WHITE);
        DropShadow ds = new DropShadow();

        ds.setOffsetX(10);
        ds.setOffsetY(5);
        ds.setColor(Color.ANTIQUEWHITE);
        description.setEffect(ds);

        login_title = new Text();
        login_title.setFont(Font.font("Verdana",15));
        login_title.setText(loginString);
        login_title.setFill(Color.WHITE);


        move_count = 0;


        //TranslateTransition ts = new TranslateTransition();
        isAnimationActive = true;
        move(move_count,getCrPos(),iguana);
        textOne.setFont(Font.font("Montserrat",27));
        textOne.setStrokeWidth(0);
        textTwo.setFont(Font.font("Montserrat",17));
        textTwo.setStrokeWidth(0);
//        System.out.println(main_title_text.getLayoutBounds().getWidth());
//        System.out.println(main_title_text.getLayoutBounds().getHeight());
//        System.out.println(main_title_text.getLayoutX());
//        System.out.println(main_title_text.getLayoutY());
        textRect1 = new Region();
        textRect1.setLayoutX(100);
        textRect1.setLayoutX(100);
        textRect1.setPrefWidth(550);
        textRect1.setPrefHeight(95);
        textRect1.getStyleClass().add("title");
        //textRect1.setStyle("-fx-background-color: yellow; -fx-background-radius: 20px, 20px,20px,20px");
//        main_title_tx.getChildren().add(textRect1);
        //System.out.println(ts.getDuration().toSeconds());
//        ts.setOnFinished((ActionEvent ex)->{
//                        move(move_count,getCrPos(),ts,books);
//                }
//        );
        //iguana-Photoroom
//        try {
////            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/kurs/kurs/iguana-Photoroom.png"));
////            String curLine = reader.readLine();
////            System.out.println(curLine);
////            while (curLine!=null){
////                curLine = reader.readLine();
////                System.out.println(curLine);
////            }
////            reader.close();
////            System.out.println(curLine);
//            File file = new File("src/main/java/com/kurs/kurs/iguana-Photoroom.png");
//            Image image = new Image(file.toURI().toString());
//            img = new ImageView(image);
//            main.getChildren().add(img);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.toString());
//        }
        //img = new ImageView(new Image());
        //books.getScene().getStylesheets().add(this.getClass().getResource("/CSS/Welcome.css").toExternalForm());
    }

    private Stage stage;
    private Parent root;

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
                    move(move_count,getCrPos(),book);
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
}