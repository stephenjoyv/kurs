package com.kurs.kurs;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserWorkSpaceController {
//    @FXML
//    private AnchorPane sliderMenu;
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

    @FXML AnchorPane balanceWarning,
    balanceSuccess;
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
    private AnchorPane dop1,
            dop2,
            dop3;
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
    private PieChart diagramm3_chart;
    @FXML
    private HBox admin_panel;
    @FXML
    private ListView<Animal> books_list,
            books_list_1;
    @FXML
    private AnchorPane included_public,
            included_public_1;
    @FXML
    private AnchorPane details1;
    @FXML
    private AnchorPane lib_public;
    @FXML
    private AnchorPane red_warning;
    private Animal for_publication;
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
    private TextField del_name,
            del_author,
            del_red;
    @FXML
    private Text del_suc,
            nofind_text1;
    @FXML
    private AnchorPane diagramm1_pane;
    @FXML
    private AnchorPane diagramm2_pane;
    @FXML
    private AnchorPane diagramm3_pane;
    @FXML
    private AnchorPane purchasesPane;
    @FXML
    private Button cartShopping;
    @FXML
    private Text balanceCounter;
    @FXML
    private VBox purchasesBox;
    @FXML
    private Text purchasesCount;

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
        diagramm3_pane.setVisible(false);
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
        purchasesPane.setVisible(false);
        tags = db.getTags();

        tags_list.getItems().addAll(tags);
        tags_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //tags_list.getItems().add("TEST");
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
        books_list.getItems().addAll(db.findRequestAnimal());
        books_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list.setCellFactory(lv -> new ListCell<Animal>() {
            @Override
            public void updateItem(Animal artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Название книги: " + artwork.getName() + "\nИмя автора: " + artwork.getOwner());
            }
        });

        balanceCounter.setText(Integer.toString(db.checkBalance(LoginController.id)));
        purchasesCount.setText(Integer.toString(getPurchasesCount()));
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

    public void showPurchases(ActionEvent e) {
        try {
            DB db = new DB();
            db.connectToDb();
            ArrayList<Animal> listAnimal = db.findAnimalsByUser(LoginController.id);
            purchasesPane.setVisible(true);
            purchasesBox.getChildren().clear();
            for(int i=0;i<listAnimal.size();i++){
                AnchorPane capturePane = new AnchorPane();
                Text text = new Text();
                text.setText(String.format("Имя - %s, Возраст - %d, Дата рождения - %s",
                        listAnimal.get(i).getName(),
                        listAnimal.get(i).getAge(),
                        listAnimal.get(i).getBornDate()
                ));
                text.setFont(Font.font("Arial Black",20));
                TextFlow textFlow = new TextFlow();
                textFlow.getChildren().add(text);
                textFlow.setStyle("-fx-background-color: linear-gradient(to top right, #e1cf9a, #58baff); " +
                        "-fx-background-radius: 20 20 20 20;" +
                        "-fx-text-alignment: center;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 20 20 20 20;" +
                        "-fx-border-color: black");
//                Region region = new Region();
//                region.getStyleClass().add("header");
//                region.setPrefHeight(text.getLayoutBounds().getHeight());
//                region.setPrefWidth(text.getLayoutBounds().getWidth());
//                region.setLayoutY(-1*text.getLayoutBounds().getHeight());
                //capturePane.getChildren().addAll(region,text);
                purchasesBox.setSpacing(5);
                purchasesBox.getChildren().add(textFlow);
            }
            System.out.println("purchases");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public int getPurchasesCount(){
        DB db = new DB();
        db.connectToDb();
        ArrayList<Animal> listAnimal = db.findAnimalsByUser(LoginController.id);
        return listAnimal.size();
    }
    public void closePurchases(ActionEvent e) {
        try {
            purchasesPane.setVisible(false);
            System.out.println("purchases closed");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void increaseBalance(ActionEvent e){
        try {
            DB db = new DB();
            db.connectToDb();
            db.increaseBalance(LoginController.id,100);
            int balance = db.checkBalance(LoginController.id);
            balanceCounter.setText(Integer.toString(balance));
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    public void decreaseBalance(ActionEvent e){
        try {
            DB db = new DB();
            db.connectToDb();
            if(db.decreaseBalance(LoginController.id,100)==-1)
                System.out.println("Нельзя убавить сумму");
            int balance = db.checkBalance(LoginController.id);
            balanceCounter.setText(Integer.toString(balance));
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    public void buyAnimal(ActionEvent e){
        try{
            DB db = new DB();
            db.connectToDb();
            Animal animal = db.findAnimal(new_book_autho.getText(),new_book_nam.getText(),new_book_re.getText());
            if((db.checkBalance(LoginController.id) - animal.getPrice())<0){
                balanceWarning.setVisible(true);
                balanceSuccess.setVisible(false);
                nofind_text.setVisible(false);
            }
            else if(animal==null){
                nofind_text.setVisible(true);
                balanceWarning.setVisible(false);
                balanceSuccess.setVisible(false);

            }
            else{
                db.decreaseBalance(LoginController.id, animal.getPrice());
                db.deleteAnimal(animal.getName(),animal.getOwner(),Integer.toString(animal.getAge()));
                db.animalPurchase(LoginController.id,animal);
                balanceCounter.setText(Integer.toString(db.checkBalance(LoginController.id)));
                purchasesCount.setText(Integer.toString(getPurchasesCount()));
                balanceSuccess.setVisible(true);
                balanceWarning.setVisible(false);
                nofind_text.setVisible(false);
            }
        }
        catch (Exception ex){
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
                field4.getText().length() == 0 || tags.size() == 0 ||
                (Pattern.matches("[a-zA-Z]+", field2.getText()))||
                (Pattern.matches("[a-zA-Z]+", field3.getText()))
        ) {
            error.setVisible(true);
            sending.setVisible(false);
        } else {
            sending.setVisible(true);
            error.setVisible(false);
            DB db = new DB();
            db.connectToDb();
            Animal animalForPublication = new Animal();
            animalForPublication.setName(field1.getText());
            animalForPublication.setOwner(LoginController.username);
            animalForPublication.setPrice(Integer.parseInt(field2.getText()));
            animalForPublication.setAge(Integer.parseInt(field3.getText()));
            java.time.LocalDate bornDate = java.time.LocalDate.now();
            bornDate = bornDate.minusYears(animalForPublication.getAge());
            System.out.println(bornDate.toString());
            animalForPublication.setBornDate(bornDate.toString());
            animalForPublication.setDescription(field4.getText());
            animalForPublication.setTags(tags);
            db.insertAnimal(animalForPublication);
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
        balanceWarning.setVisible(false);
        balanceSuccess.setVisible(false);
    }

    public void showDop() {
        dop1.setVisible(true);
        dop2.setVisible(true);
        dop3.setVisible(true);
    }

    public void findBook(ActionEvent e) {


        nofind_text.setVisible(false);
        balanceWarning.setVisible(false);
        balanceSuccess.setVisible(false);
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
        Animal animal = db.findAnimal(find_author.getText(),find_book.getText(),find_red.getText());
        if (animal != null) {
            new_book_nam.setText(animal.getName());
            new_book_autho.setText(animal.getOwner());
            new_book_de.setText(animal.getDescription());
            new_book_le.setText(Integer.toString(animal.getPrice()));
            new_book_mar.setText(animal.getBornDate().toString());
            new_book_re.setText(Integer.toString(animal.getAge()));
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
                    String.format("%s - владелец %s животных",data.getName(), data.getPieValue())
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
    public void exit_diagramm3(){
        diagramm3_pane.setVisible(false);
    }
    public void check_detailed_animal() {
        try {
            ArrayList<Animal> tm_list = new ArrayList<Animal>();
            if (!books_list.getSelectionModel().getSelectedItems().isEmpty()) {
                tm_list = (ArrayList<Animal>) books_list.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
                details1.setVisible(true);
                lib_bookNotChoosed.setVisible(false);
                new_book_nam1.setText(tm_list.get(0).getName());
                new_book_autho1.setText(tm_list.get(0).getOwner());
                new_book_re1.setText(Integer.toString(tm_list.get(0).getAge()));
                new_book_de1.setText(tm_list.get(0).getDescription());
                new_book_le1.setText(Integer.toString(tm_list.get(0).getPrice()));
                new_book_mar1.setText(tm_list.get(0).getBornDate().toString());
            }
            else{
                lib_bookNotChoosed.setVisible(true);
                return;
            }
            if (!tm_list.isEmpty()) {
                System.out.println(tm_list.get(0).getOwner());
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
        if (!db.checkAnimal(for_publication)) {
            db.publicAnimal(for_publication);
        }
    }

    public void reject_publication() {
        DB db = new DB();
        db.connectToDb();
        db.rejectAnimal(for_publication);
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
        books_list_1.getItems().addAll(db.findAnimalsByAuthor(field_for_author.getText()));
        books_list_1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list_1.setCellFactory(lv -> new ListCell<Animal>() {
            @Override
            public void updateItem(Animal artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Имя животного: " + artwork.getName() + "\nИмя автора: " + artwork.getOwner());
            }
        });

    }
    public void check_detailed_animal_1(ActionEvent e){
        try

        {
            ArrayList<Animal> tm_list = new ArrayList<Animal>();
            if (!books_list_1.getSelectionModel().getSelectedItems().isEmpty()) {
                tm_list = (ArrayList<Animal>) books_list_1.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
                System.out.println("Size ");
                System.out.println(tm_list.size());
                details_1.setVisible(true);
                bookNotSelected.setVisible(false);
                author_name.setText(tm_list.get(0).getName());
                author_author.setText(tm_list.get(0).getOwner());
                author_res.setText(Integer.toString(tm_list.get(0).getAge()));
                author_des.setText(tm_list.get(0).getDescription());
                author_len.setText(Integer.toString(tm_list.get(0).getPrice()));
                author_mark.setText(tm_list.get(0).getBornDate());
            }
            else{
                bookNotSelected.setVisible(true);
                return;
            }
            if (!tm_list.isEmpty()) {
                System.out.println(tm_list.get(0).getOwner());
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
        books_list.getItems().addAll(db.findRequestAnimal());
        books_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_list.setCellFactory(lv -> new ListCell<Animal>() {
            @Override
            public void updateItem(Animal artwork, boolean empty) {
                super.updateItem(artwork, empty);
                setText(empty ? null : "Имя животного: " + artwork.getName() + "\nИмя автора: " + artwork.getOwner());
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
    public void deleteAnimal(ActionEvent e){
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


        Animal tm = db.findAnimal(del_author.getText(), del_name.getText(), del_red.getText());
        if (tm != null) {
            try{
                db.deleteAnimal(del_name.getText(),del_author.getText(),del_red.getText());
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
    public void diagramm3(ActionEvent e){
        DB db = new DB();
        db.connectToDb();
        diagramm3_pane.setVisible(true);
        ObservableList<PieChart.Data> tmdt = db.getPopularOwners();
        tmdt.forEach(data -> data.nameProperty().bind(Bindings.concat(
         String.format("Владелец %s имеет в своём распоряжении %s животных.",data.getName(),data.getPieValue())
        )));
        diagramm3_chart.getData().clear();
        diagramm3_chart.getData().addAll(tmdt);


        diagramm3_chart.setTitle("Relevant authors");
        diagramm3_chart.setLabelLineLength(55);
        diagramm3_chart.setClockwise(true);
        diagramm3_chart.setLabelsVisible(true);
        diagramm3_chart.setStartAngle(180);
    }
}

