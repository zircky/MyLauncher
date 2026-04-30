module com.zircky.mylauncher {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires static lombok;

    exports com.zircky.mylauncher;
    exports com.zircky.mylauncher.controller;

    opens com.zircky.mylauncher to javafx.fxml;
    opens com.zircky.mylauncher.controller to javafx.fxml;

}