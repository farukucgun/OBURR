module com.example.fxtester {
    requires javafx.swing;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.jsoup;
    requires java.sql.rowset;

    opens com.example.fxtester to javafx.fxml;
    exports com.example.fxtester;
}