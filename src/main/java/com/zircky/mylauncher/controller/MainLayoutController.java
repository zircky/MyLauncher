package com.zircky.mylauncher.controller;

import com.zircky.mylauncher.model.SidebarItem;
import com.zircky.mylauncher.navigation.NavigationService;
import com.zircky.mylauncher.navigation.View;
import com.zircky.mylauncher.util.SidebarButtonFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

public final class MainLayoutController {

    @FXML
    private HBox titleBar;

    @FXML
    private VBox menuContainer;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button maximizeButton;

    private final Map<SidebarItem, Button> buttons = new EnumMap<>(SidebarItem.class);

    private NavigationService navigationService;

    private SidebarItem activeItem;
    private double dragOffsetX;
    private double dragOffsetY;
    private double windowX;
    private double windowY;

    @FXML
    public void initialize() {
        navigationService = new NavigationService(contentArea);

        createSidebar();
        setActive(SidebarItem.HOME);
        initWindowDragging();
        initTitleBarDoubleClick();
    }

    @FXML
    private void onMinimizeClicked() {
        final Stage stage = getStage();
        stage.setIconified(true);
    }

    @FXML
    private void onMaximizeClicked() {
        final Stage stage = getStage();
        final boolean maximized = !stage.isMaximized();
        stage.setMaximized(maximized);
        updateMaximizeButtonText(maximized);
    }

    @FXML
    private void onCloseClicked() {
        final Stage stage = getStage();
        stage.close();
    }

    private void initWindowDragging() {
        titleBar.setOnMousePressed(this::handleMousePressed);
        titleBar.setOnMouseDragged(this::handleMouseDragged);
    }

    private void initTitleBarDoubleClick() {
        titleBar.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                onMinimizeClicked();
            }
        });
    }

    private void handleMousePressed(final MouseEvent event) {
        final Stage stage = getStage();

        dragOffsetX = event.getScreenX();
        dragOffsetY = event.getScreenY();

        windowX = stage.getX();
        windowY = stage.getY();
    }

    private void handleMouseDragged(final MouseEvent event) {
        final Stage stage = getStage();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
            maximizeButton.setText("□");

            windowX = event.getScreenX() - stage.getWidth() / 2;
            windowY = event.getScreenY() - 18;
        }

        final double deltaX = event.getScreenX() - dragOffsetX;
        final double deltaY = event.getScreenY() - dragOffsetY;

        stage.setX(windowX - deltaX);
        stage.setY(windowY - deltaY);
    }

    private Stage getStage() {
        return (Stage) titleBar.getScene().getWindow();
    }

    private void updateMaximizeButtonText(boolean maximized) {
        maximizeButton.setText(maximized ? "❐" : "□");
    }

    private void createSidebar() {
        for (final SidebarItem item : SidebarItem.values()) {
            final Button button = SidebarButtonFactory.create(item);
            button.setOnAction(event -> setActive(item));

            buttons.put(item, button);
            menuContainer.getChildren().add(button);
        }
    }

    private void setActive(final SidebarItem item) {
        if (item == activeItem) {
            return;
        }

        clearActiveState();

        final Button button = buttons.get(item);
        if (button != null) {
            button.getStyleClass().add("sidebar-button-active");
        }

        activeItem = item;
        navigate(item);
    }

    private void clearActiveState() {
        for (final Button button : buttons.values()) {
            button.getStyleClass().remove("sidebar-button-active");
        }
    }

    private void navigate(final SidebarItem item) {
        final View view = mapToView(item);
        navigationService.navigate(view);
    }

    private View mapToView(SidebarItem item) {
        return switch (item) {
            case HOME -> View.HOME;
            case INSTALLATIONS -> View.INSTALLATIONS;
            case SKINS -> View.SKINS;
            case MODS -> View.MODS;
            case RESOURCE_PACKS -> View.RESOURCE_PACKS;
            case SHADERS -> View.SHADERS;
            case SETTINGS -> View.SETTINGS;
            default -> throw new IllegalStateException("No mapping for " + item);
        };
    }


}