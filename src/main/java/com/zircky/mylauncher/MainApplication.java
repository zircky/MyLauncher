package com.zircky.mylauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(
                MainApplication.class.getResource("/com/zircky/mylauncher/fxml/main-layout.fxml")
        );

        final Scene scene = new Scene(loader.load(), 1280, 750);
        scene.getStylesheets().add(
                Objects.requireNonNull(MainApplication.class.getResource("/com/zircky/mylauncher/css/Main.css")).toExternalForm()
        );
        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("MyLauncher");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
