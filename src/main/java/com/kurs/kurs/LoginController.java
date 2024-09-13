package com.kurs.kurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private TextField login_field1;
    @FXML
    private PasswordField login_field2;
    @FXML
    private DatePicker inputDate;
    @FXML
    private Text errorMSG;
    //@FXML
    //public ImageView firstERR;
    //@FXML
    //private ImageView secondERR;
    @FXML
    private AnchorPane vis;

    private boolean isValidate;
    private Scene scene;
    private Stage stage;
    private Parent root;
    static String username;
    static String status;
    public void initialize(){
        vis.setVisible(false);
    }

    public void login(ActionEvent e){
        Authorization au = new Authorization();
        System.out.println("Login " +login_field1.getText());
        System.out.println("Password " +login_field2.getText());

        if(au.login(login_field1.getText(),login_field2.getText())){
            username = login_field1.getText();

            DB db = new DB();
            db.connectToDb();
            status = db.getUserStatus(username,login_field2.getText());


            vis.setVisible(false);
            toUserWorkSpace(e,username,status);
        }
        else{
            vis.setVisible(true);
        }
    }
    public void toMenu(ActionEvent e){
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/Welcome.css").toExternalForm();
            scene.getStylesheets().add(css);
            HelloController hc = loader.getController();
            hc.title();
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    public void toUserWorkSpace(ActionEvent e,String user, String stat){
        try {
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/UserWorkSpace.fxml"));


            root = loader.load();
            UserWorkSpaceController sc = loader.getController();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/UserWorkSpace.css").toExternalForm();
            scene.getStylesheets().add(css);
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }

    }
    public void setUser(String name){

    }

}
