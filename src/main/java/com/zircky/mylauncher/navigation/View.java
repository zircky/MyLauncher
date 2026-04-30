package com.zircky.mylauncher.navigation;

import lombok.Getter;

public enum View {
    HOME("/com/zircky/mylauncher/fxml/home.fxml"),
    INSTALLATIONS("/com/zircky/mylauncher/fxml/installations.fxml"),
    SKINS("/com/zircky/mylauncher/fxml/skins.fxml"),
    MODS("/com/zircky/mylauncher/fxml/mods.fxml"),
    RESOURCE_PACKS("/com/zircky/mylauncher/fxml/resource-packs.fxml"),
    SHADERS("/com/zircky/mylauncher/fxml/shaders.fxml"),
    SETTINGS("/com/zircky/mylauncher/fxml/settings.fxml");

    private final @Getter String fxmlPath;

    View (final String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
