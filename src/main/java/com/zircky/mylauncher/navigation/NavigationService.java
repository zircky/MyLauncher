package com.zircky.mylauncher.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public final class NavigationService {
    private final StackPane contentArea;
    private final Map<View, Node> cache = new EnumMap<>(View.class);

    public NavigationService(final StackPane contentArea) {
        this.contentArea = contentArea;
    }

    public void navigate(final View view) {
        final Node node = cache.computeIfAbsent(view, this::load);
        contentArea.getChildren().setAll(node);
    }

    private Node load(final View view) {
        final FXMLLoader loader = new FXMLLoader(
                NavigationService.class.getResource(view.getFxmlPath())
        );

        try {
            return loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load: " + view, e);
        }
    }
}
