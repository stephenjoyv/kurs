package com.kurs.kurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationController {
    @FXML
    private TextField login_field1;
    @FXML
    private TextField login_field2;
    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML
    private DatePicker inputDate;
    @FXML
    private AnchorPane vis;
    @FXML
    private AnchorPane vis1;
    private boolean date_validate = false;

    public void initialize(){
        inputDate.focusedProperty().addListener(o -> handleFocus(inputDate.isFocused()));
        vis.setVisible(false);
        vis1.setVisible(false);

    }
    public void registration(ActionEvent e){

        if (date_validate){
            Authorization auth = new Authorization();
            auth.registration(login_field1.getText(),login_field2.getText(),inputDate.getValue().toString());

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
    public boolean checkDate(){
        try {
            DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate td = inputDate.getValue();
            System.out.println(td.toString());
            String tmd = td.format(ft);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }

    }

    public void handleFocus(boolean focus){
        try {

            //((Node)secondERR).setVisible(true);
        }
        catch (Exception e){
            System.out.printf(e.toString());
        }
        if (focus){

        }
        else {
            if(checkDate()){
                vis1.setVisible(true);
                vis.setVisible(false);
                System.out.println("good");
                date_validate = true;
            }
            else {
                System.out.println("Error");
                vis1.setVisible(false);
                vis.setVisible(true);
                date_validate = false;
            }
        }
    }
}

