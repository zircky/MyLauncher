package com.zircky.mylauncher.util;

import com.zircky.mylauncher.model.SidebarItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class IconLoader {
    private static final double DEFAULT_SIZE = 22.0;

    private IconLoader() {
    }

    public static ImageView load(final SidebarItem iconPath) {
        return load(iconPath, DEFAULT_SIZE);
    }

    public static ImageView load(final SidebarItem iconPath, final double size) {
        final InputStream inputStream = IconLoader.class.getResourceAsStream(iconPath.getIconText());
        if (inputStream == null) {
            throw new IllegalArgumentException("Icon not found: " + iconPath.getIconText());
        }

        final Image image = new Image(inputStream);
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        return imageView;
    }
}
