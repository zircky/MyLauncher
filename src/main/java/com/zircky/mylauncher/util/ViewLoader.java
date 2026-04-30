package com.zircky.mylauncher.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public final class ViewLoader {
    private ViewLoader() {}

    public static Node load(final String path) {
        try {
            return FXMLLoader.load(Objects.requireNonNull(ViewLoader.class.getResource(path)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load view: " + path, e);
        }
    }
}
