module com.tsfsclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires okhttp3;
    requires com.google.gson;
    requires java.desktop;

    opens com.tsfsclient to javafx.fxml;
    opens com.tsfsclient.rappers to com.google.gson;
    exports com.tsfsclient;
}