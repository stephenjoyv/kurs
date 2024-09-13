package com.kurs.kurs;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserWorkSpaceController {
    @FXML
    private AnchorPane sliderMenu;
    @FXML
    private VBox menuPanel;
    @FXML
    private Button menuButton;
    @FXML
    private Button menuButton1;
    @FXML
    private AnchorPane search_pane;
    @FXML
    private AnchorPane public_pane;
    @FXML
    private BorderPane left_menu;
    @FXML
    private ListView<String> tags_list;
    private ArrayList<String> tags;
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
    @FXML
    private TextField field4;
    @FXML
    private AnchorPane error;
    @FXML
    private AnchorPane sending;
    @FXML
    private TextField find_book;
    @FXML
    private TextField find_author;
    @FXML
    private TextField find_red;

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Text new_book_nam;
    @FXML
    private Text new_book_re;
    @FXML
    private Text new_book_de;
    @FXML
    private Text new_book_le;
    @FXML
    private Text new_book_mar;
    @FXML
    private Text new_book_autho;
    @FXML
    private Text new_book_nam1;
    @FXML
    private Text new_book_re1;
    @FXML
    private Text new_book_de1;
    @FXML
    private Text new_book_le1;
    @FXML
    private Text new_book_mar1;
    @FXML
    private Text new_book_autho1;


    @FXML
    private Text author_name;
    @FXML
    private Text author_author;
    @FXML
    private Text author_res;
    @FXML
    private Text author_len;
    @FXML
    private Text author_des;
    @FXML
    private Text author_mark;

    @FXML
    private AnchorPane details;
    @FXML
    private AnchorPane dop1, dop2, dop3;
    @FXML
    private Text nofind_text;
    @FXML
    private Button det_but;

    @FXML
    private HBox lib_panel;
    @FXML
    private PieChart pch;
    @FXML
    private PieChart diagramm2_chart;

    @FXML
    private HBox admin_panel;
    @FXML
    private ListView<Book> books_list, books_list_1;
    @FXML
    private AnchorPane included_public, included_public_1;
    @FXML
    private AnchorPane details1;
    @FXML
    private AnchorPane lib_public;
    @FXML
    private AnchorPane red_warning;
    private Book for_publication;
    @FXML
    private AnchorPane empty_warning;
    @FXML
    private AnchorPane red_warning1;
    @FXML
    private AnchorPane empty_warning1;
    @FXML
    private TextField field_for_author;
    @FXML
    private AnchorPane details_1;
    @FXML
    private AnchorPane authors_book;

    @FXML
    private Text bookNotSelected;
    @FXML
    private Text lib_bookNotChoosed;
    @FXML
    private AnchorPane del_pane;
    @FXML
    private TextField del_name,del_author,del_red;
    @FXML
    private Text del_suc,nofind_text1;
    @FXML
    private AnchorPane diagramm1_pane;
    @FXML
    private AnchorPane diagramm2_pane;

    public void initialize() {

        DB db = new DB();
        db.connectToDb();
        //admin_panel.setVisible(false);
        //lib_panel.setVisible(false);
        if (LoginController.status.equals("ADMIN")) {
            System.out.println("yes");
            admin_panel.setVisible(true);
            //lib_public.setVisible(false);
            lib_panel.setVisible(false);
        } else if (LoginController.status.equals("LIB")) {
            admin_panel.setVisible(false);
            //lib_public.setVisible(true);
            lib_panel.setVisible(true);
        } else {
            admin_panel.setVisible(false);
            lib_public.setVisible(false);
            lib_panel.setVisible(false);
        }
        diagramm1_pane.setVisible(false);
        diagramm2_pane.setVisible(false);
        authors_book.setVisible(false);
        det_but.setVisible(false);
        nofind_text.setVisible(false);
        //System.out.println(LoginController.status);
        del_pane.setVisible(false);
        red_warning1.setVisible(false);
        empty_warning1.setVisible(false);
        del_suc.setVisible(false);
        nofind_text1.setVisible(false);
        details_1.setVisible(false);
        search_pane.setVisible(false);
        public_pane.setVisible(false);
        left_menu.setVisible(true);
        error.setVisible(false);
        sending.setVisible(false);
        details.setVisible(false);
        tags = db.getTags();

        tags_list.getItems().addAll(tags);
        tags_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tags_list.getItems().add("TEST");
        tags_list.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

                tags = (ArrayList<String>) tags_list.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());

                for (String s : tags) {
                    System.out.println("selected item " + s);
                }

            }

        });
        details1.setVisible(false);
        included_public.setVisible(true);
        books_list.getItems().addAll(db.findRequestBook());
        books_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list.setCellFactory(lv -> new ListCell<Book>() {
            @Override
            public void updateItem(Book artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Название книги: " + artwork.getName() + "\nИмя автора: " + artwork.author);
            }
        });

    }

    public void onClick(ActionEvent e) {
        try {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(1));
            slide.setNode(menuPanel);
            System.out.println(menuPanel.getLayoutX());
            slide.setToX(275);
            slide.play();
            menuPanel.setLayoutX(275);
            menuButton.setVisible(false);
            menuButton1.setVisible(true);
            System.out.println(menuPanel.getLayoutX());
//            slide.setOnFinished(o->{
//                search_pane.setVisible(true);
//            });


        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void secondClick(ActionEvent e) {
        try {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(1));
            slide.setNode(menuPanel);
            System.out.println(menuPanel.getLayoutX());
            slide.setToX(-275);
            slide.play();
            menuPanel.setLayoutX(0);
            menuButton.setVisible(true);
            menuButton1.setVisible(false);

            System.out.println(menuPanel.getLayoutX());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void send(ActionEvent e) {
        if (field1.getText().length() == 0 ||
                field2.getText().length() == 0 ||
                field3.getText().length() == 0 ||
                field4.getText().length() == 0 || tags.size() == 0
        ) {
            error.setVisible(true);
            sending.setVisible(false);
        } else {
            sending.setVisible(true);
            error.setVisible(false);
            DB db = new DB();
            db.connectToDb();

            db.insertBook(field1.getText(), Integer.parseInt(field3.getText()), Integer.parseInt(field2.getText()), tags.toArray(String[]::new),
                    field4.getText(), LoginController.username);


        }
    }

    public void publicBook(ActionEvent e) {
        public_pane.setVisible(true);
        search_pane.setVisible(false);
        exit_search();
        exit_author(e);
    }

    public void chooseBook() {
        public_pane.setVisible(false);
        search_pane.setVisible(true);
        red_warning.setVisible(false);
        empty_warning.setVisible(false);
        nofind_text.setVisible(false);
    }

    public void showDop() {
        dop1.setVisible(true);
        dop2.setVisible(true);
        dop3.setVisible(true);
    }

    public void findBook(ActionEvent e) {


        nofind_text.setVisible(false);

        DB db = new DB();
        db.connectToDb();
        if (!(Pattern.matches("[a-zA-Z]+", find_red.getText()) == false)) {
            red_warning.setVisible(true);
            empty_warning.setVisible(false);
            return;
        } else if (find_author.getText().isEmpty() ||
                find_book.getText().isEmpty() ||
                find_red.getText().isEmpty()) {
            empty_warning.setVisible(true);
            red_warning.setVisible(false);
            return;
        } else {
            red_warning.setVisible(false);
            empty_warning.setVisible(false);
        }

        Book tm = db.findBook(find_author.getText(), find_book.getText(), find_red.getText());
        if (tm != null) {
            new_book_nam.setText(tm.name);
            new_book_autho.setText(tm.author);
            new_book_de.setText(tm.description);
            new_book_le.setText(Integer.toString(tm.length));
            new_book_mar.setText(Integer.toString(tm.marks));
            new_book_re.setText(Integer.toString(tm.release));
            details.setVisible(true);
            det_but.setVisible(true);
            dop1.setVisible(false);
            dop2.setVisible(false);
            dop3.setVisible(false);
        } else {

            nofind_text.setVisible(true);
            details.setVisible(false);
            det_but.setVisible(false);

        }


    }

    public void authorsBook() {

    }

    public void diagramm1() {
        try {
            diagramm1_pane.setVisible(true);
            pch.setVisible(true);
            DB db = new DB();
            db.connectToDb();

            ObservableList<PieChart.Data> tmdt = db.getAuthors();
//            tmdt.add(new PieChart.Data("dwad",500));
//            tmdt.add(new PieChart.Data("dthrjn",900));
//            tmdt.add(new PieChart.Data("cwdwada",300));
            tmdt.forEach(data -> data.nameProperty().bind(Bindings.concat(
                    data.getName(), " books published ", data.pieValueProperty()
            )));
            pch.getData().clear();
            pch.getData().addAll(tmdt);


            pch.setTitle("Relevant authors");
            pch.setLabelLineLength(55);
            pch.setClockwise(true);
            pch.setLabelsVisible(true);
            pch.setStartAngle(180);

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


    }

    public void exit_search() {
        search_pane.setVisible(false);
        details.setVisible(false);
        det_but.setVisible(false);
    }
    public void exit_del() {
        del_pane.setVisible(false);
        del_suc.setVisible(false);
        nofind_text1.setVisible(false);
    }

    public void exit_send() {
        public_pane.setVisible(false);
        sending.setVisible(false);
        error.setVisible(false);
        System.out.println("ex");
    }
    public void exit_diagramm1(){
        diagramm1_pane.setVisible(false);
    }
    public void exit_diagramm2(){
        diagramm2_pane.setVisible(false);
    }
    public void check_detailed_book() {
        try {
            ArrayList<Book> tm_list = new ArrayList<Book>();
            if (!books_list.getSelectionModel().getSelectedItems().isEmpty()) {
                tm_list = (ArrayList<Book>) books_list.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
                details1.setVisible(true);
                lib_bookNotChoosed.setVisible(false);
                new_book_nam1.setText(tm_list.get(0).name);
                new_book_autho1.setText(tm_list.get(0).author);
                new_book_re1.setText(Integer.toString(tm_list.get(0).release));
                new_book_de1.setText(tm_list.get(0).description);
                new_book_le1.setText(Integer.toString(tm_list.get(0).length));
                new_book_mar1.setText(Integer.toString(tm_list.get(0).marks));
            }
            else{
                lib_bookNotChoosed.setVisible(true);
                return;
            }
            if (!tm_list.isEmpty()) {
                System.out.println(tm_list.get(0).author);
                for_publication = tm_list.get(0);
            }
            included_public.setVisible(false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void accept_publication() {
        DB db = new DB();
        db.connectToDb();
        if (!db.checkBook(for_publication)) {
            db.public_book(for_publication);
        }
    }

    public void reject_publication() {

    }

    public void exit_accept() {
        lib_public.setVisible(false);
    }

    public void books_request() {
        lib_public.setVisible(true);

    }

    public void toMenu(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = this.getClass().getResource("/CSS/Welcome.css").toExternalForm();
            scene.getStylesheets().add(css);
            HelloController hc = loader.getController();
            hc.title();
            //stage.setTitle("Exampleprg");
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void authorsBook(ActionEvent e) {
        authors_book.setVisible(true);
        exit_search();
        exit_send();

        //details_1.setVisible(true);
    }

    public void exit_authors_book(ActionEvent e) {

    }

    public void loadAuthorsBooks(ActionEvent e) {
        DB db = new DB();
        db.connectToDb();
        books_list_1.getItems().clear();
        books_list_1.getItems().addAll(db.findBooksByAuthor(field_for_author.getText()));
        books_list_1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list_1.setCellFactory(lv -> new ListCell<Book>() {
            @Override
            public void updateItem(Book artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Название книги: " + artwork.getName() + "\nИмя автора: " + artwork.author);
            }
        });

    }
    public void check_detailed_book_1(ActionEvent e){
        try

        {
            ArrayList<Book> tm_list = new ArrayList<Book>();
            if (!books_list_1.getSelectionModel().getSelectedItems().isEmpty()) {
                tm_list = (ArrayList<Book>) books_list_1.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
                System.out.println("Size ");
                System.out.println(tm_list.size());
                details_1.setVisible(true);
                bookNotSelected.setVisible(false);
                author_name.setText(tm_list.get(0).name);
                author_author.setText(tm_list.get(0).author);
                author_res.setText(Integer.toString(tm_list.get(0).release));
                author_des.setText(tm_list.get(0).description);
                author_len.setText(Integer.toString(tm_list.get(0).length));
                author_mark.setText(Integer.toString(tm_list.get(0).marks));
            }
            else{
                bookNotSelected.setVisible(true);
                return;
            }
            if (!tm_list.isEmpty()) {
                System.out.println(tm_list.get(0).author);
                for_publication = tm_list.get(0);
            }
            included_public_1.setVisible(false);
        }
        catch(Exception ex)

        {
            System.out.println(ex.toString());
        }
    }
    public void againRequests(ActionEvent e){
        DB db = new DB();
        db.connectToDb();
        details1.setVisible(false);
        included_public.setVisible(true);
        books_list.getItems().clear();
        books_list.getItems().addAll(db.findRequestBook());
        books_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list.setCellFactory(lv -> new ListCell<Book>() {
            @Override
            public void updateItem(Book artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Название книги: " + artwork.getName() + "\nИмя автора: " + artwork.author);
            }
        });
    }
    public void exit_author(ActionEvent e){
        authors_book.setVisible(false);
    }
    public void again_author(ActionEvent e){
        details_1.setVisible(false);
        included_public_1.setVisible(true);
    }
    public void showDel(ActionEvent e){
        del_pane.setVisible(true);
    }
    public void deleteBook(ActionEvent e){
        if (!(Pattern.matches("[a-zA-Z]+", del_red.getText()) == false)) {
            red_warning1.setVisible(true);
            empty_warning1.setVisible(false);
            return;
        } else if (del_author.getText().isEmpty() ||
                del_name.getText().isEmpty() ||
                del_red.getText().isEmpty()) {
            empty_warning1.setVisible(true);
            red_warning1.setVisible(false);
            return;
        } else {
            red_warning1.setVisible(false);
            empty_warning1.setVisible(false);
        }

        DB db = new DB();
        db.connectToDb();


        Book tm = db.findBook(del_author.getText(), del_name.getText(), del_red.getText());
        if (tm != null) {
            try{
                db.deleteBook(del_name.getText(),del_author.getText(),del_red.getText());
                del_suc.setVisible(true);
                nofind_text1.setVisible(false);
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }

        } else {

            nofind_text1.setVisible(true);
            del_suc.setVisible(false);
        }
    }
    public void diagramm2(){
        DB db = new DB();
        db.connectToDb();
        diagramm2_pane.setVisible(true);
        ObservableList<PieChart.Data> tmdt = db.getPopularTags();
        tmdt.forEach(data -> data.nameProperty().bind(Bindings.concat(
                data.getName(), " popularity of tags ", data.pieValueProperty()
        )));
        diagramm2_chart.getData().clear();
        diagramm2_chart.getData().addAll(tmdt);


        diagramm2_chart.setTitle("Relevant authors");
        diagramm2_chart.setLabelLineLength(55);
        diagramm2_chart.setClockwise(true);
        diagramm2_chart.setLabelsVisible(true);
        diagramm2_chart.setStartAngle(180);
    }
}

