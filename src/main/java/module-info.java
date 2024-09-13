module com.kurs.kurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;
    requires java.management;

    opens com.kurs.kurs to javafx.fxml;
    exports com.kurs.kurs;
}