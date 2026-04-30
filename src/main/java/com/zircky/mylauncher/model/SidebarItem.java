package com.zircky.mylauncher.model;

import lombok.Getter;

@Getter
public enum SidebarItem {
    HOME("Главная", "/com/zircky/mylauncher/icon/home.png"),
    INSTALLATIONS("Установки", "/com/zircky/mylauncher/icon/installations.png"),
    SKINS("Скины", "/com/zircky/mylauncher/icon/skins.png"),
    MODS("Моды", "/com/zircky/mylauncher/icon/mods.png"),
    RESOURCE_PACKS("Ресурс-паки", "/com/zircky/mylauncher/icon/resource-packs.png"),
    SHADERS("Шейдеры", "/com/zircky/mylauncher/icon/shaders.png"),
    SETTINGS("Настройки", "/com/zircky/mylauncher/icon/settings.png");

    private final String title;
    private final String iconText;
    
    SidebarItem(final String title, final String iconText) {
        this.title = title;
        this.iconText = iconText;
    }
}