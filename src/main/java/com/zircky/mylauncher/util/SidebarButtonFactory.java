package com.zircky.mylauncher.util;

import com.zircky.mylauncher.model.SidebarItem;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public final class SidebarButtonFactory {

    private SidebarButtonFactory() {
    }

    public static Button create(final SidebarItem item) {
        final ImageView icon = IconLoader.load(item);
        icon.getStyleClass().add("sidebar-icon");

        final StackPane iconBox = new StackPane(icon);
        iconBox.getStyleClass().add("sidebar-icon-box");
        iconBox.setAlignment(Pos.CENTER);

        final Button button = new Button(item.getTitle());
        button.getStyleClass().add("sidebar-button");
        button.setGraphic(iconBox);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setMaxWidth(Double.MAX_VALUE);

        return button;
    }
}