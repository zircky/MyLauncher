package com.mylauncher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LauncherApp extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");

        VBox sidebar = buildSidebar();
        VBox center = buildContent();

        root.setLeft(sidebar);
        root.setCenter(center);

        Scene scene = new Scene(root, 1280, 880);
        scene.getStylesheets().add(getClass().getResource("/com/mylauncher/launcher.css").toExternalForm());

        stage.setTitle("Minecraft Launcher UI (JavaFX)");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(760);
        stage.show();
    }

    private VBox buildSidebar() {
        VBox sidebar = new VBox(8);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPadding(new Insets(24, 14, 16, 14));
        sidebar.setPrefWidth(250);

        Label logo = new Label("MINECRAFT");
        logo.getStyleClass().add("logo");

        VBox menu = new VBox(6,
                menuItem("🏠", "Главная", "Добро пожаловать!", true),
                menuItem("📰", "Новости", "Последние обновления", false),
                menuItem("🧱", "Установки", "Управление версиями", false),
                menuItem("🎭", "Скины", "Ваш стиль в игре", false),
                menuItem("⛏", "Моды", "Управление модами", false),
                menuItem("⚙", "Настройки", "Параметры лаунчера", false)
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox profile = new VBox(2);
        profile.getStyleClass().add("profile");
        Label name = new Label("Игрок");
        name.getStyleClass().add("profile-name");
        Label online = new Label("● Онлайн");
        online.getStyleClass().add("profile-status");
        profile.getChildren().addAll(name, online);

        HBox socials = new HBox(12,
                socialChip("Discord"),
                socialChip("VK"),
                socialChip("Web")
        );
        socials.getStyleClass().add("social-row");

        sidebar.getChildren().addAll(logo, menu, spacer, profile, socials);
        return sidebar;
    }

    private VBox buildContent() {
        VBox content = new VBox(12);
        content.getStyleClass().add("content");
        content.setPadding(new Insets(14));

        StackPane hero = new StackPane();
        hero.getStyleClass().add("hero");
        hero.setMinHeight(390);

        VBox heroText = new VBox(10);
        heroText.setPadding(new Insets(44));
        heroText.setAlignment(Pos.BOTTOM_LEFT);

        Label title = new Label("Добро пожаловать\nв Minecraft!");
        title.getStyleClass().add("hero-title");
        Label subtitle = new Label("Исследуй, строй и выживай в бесконечном\nмире возможностей.");
        subtitle.getStyleClass().add("hero-subtitle");

        Button details = new Button("▶ Подробнее");
        details.getStyleClass().add("secondary-btn");

        heroText.getChildren().addAll(title, subtitle, details);
        hero.getChildren().add(heroText);

        HBox bottomRow = new HBox(12);
        VBox newsPanel = buildNewsPanel();
        VBox versionPanel = buildVersionPanel();
        HBox.setHgrow(newsPanel, Priority.ALWAYS);
        bottomRow.getChildren().addAll(newsPanel, versionPanel);

        content.getChildren().addAll(hero, bottomRow);
        return content;
    }

    private VBox buildNewsPanel() {
        VBox newsPanel = new VBox(10);
        newsPanel.getStyleClass().add("panel");
        newsPanel.setPadding(new Insets(14));

        Label heading = new Label("ПОСЛЕДНИЕ НОВОСТИ");
        heading.getStyleClass().add("panel-heading");

        VBox items = new VBox(8,
                newsItem("Трейлер обновления 1.21", "Смотрите новый трейлер Tricky Trials.", "15 мая 2024"),
                newsItem("Minecraft 1.20.6 — Исправления", "Повышена стабильность и исправлены баги.", "2 мая 2024"),
                newsItem("Релиз Minecraft 1.20.5", "Новая версия уже доступна.", "23 апреля 2024")
        );

        ScrollPane scroll = new ScrollPane(items);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setBackground(Background.EMPTY);
        scroll.setStyle("-fx-background-color: transparent;");

        newsPanel.getChildren().addAll(heading, scroll);
        VBox.setVgrow(scroll, Priority.ALWAYS);
        return newsPanel;
    }

    private VBox buildVersionPanel() {
        VBox versionPanel = new VBox(16);
        versionPanel.getStyleClass().add("panel");
        versionPanel.setPadding(new Insets(16));
        versionPanel.setPrefWidth(330);

        Label versionHeading = new Label("ВАША ВЕРСИЯ");
        versionHeading.getStyleClass().add("panel-heading");

        Label version = new Label("Minecraft 1.20.6\nПоследняя версия");
        version.getStyleClass().add("version-label");

        Button play = new Button("ИГРАТЬ");
        play.getStyleClass().add("play-btn");
        play.setMaxWidth(Double.MAX_VALUE);

        HBox actions = new HBox(8,
                flatButton("🎮 Настройки игры"),
                flatButton("📁 Открыть папку")
        );

        versionPanel.getChildren().addAll(versionHeading, version, play, actions);
        return versionPanel;
    }

    private HBox menuItem(String icon, String title, String subtitle, boolean active) {
        HBox row = new HBox(12);
        row.getStyleClass().add("menu-item");
        if (active) {
            row.getStyleClass().add("active");
        }
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10));

        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("menu-icon");

        VBox textBox = new VBox(2);
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("menu-title");
        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.getStyleClass().add("menu-subtitle");
        textBox.getChildren().addAll(titleLabel, subtitleLabel);

        row.getChildren().addAll(iconLabel, textBox);
        return row;
    }

    private HBox newsItem(String title, String subtitle, String date) {
        HBox row = new HBox(10);
        row.getStyleClass().add("news-item");
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10));

        Region thumbnail = new Region();
        thumbnail.setPrefSize(120, 60);
        thumbnail.getStyleClass().add("thumb");

        VBox text = new VBox(3);
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("news-title");
        Label sub = new Label(subtitle);
        sub.getStyleClass().add("news-subtitle");
        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("news-date");
        text.getChildren().addAll(titleLabel, sub, dateLabel);

        row.getChildren().addAll(thumbnail, text);
        return row;
    }

    private Label socialChip(String value) {
        Label chip = new Label(value);
        chip.getStyleClass().add("social-chip");
        return chip;
    }

    private Button flatButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("flat-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(button, Priority.ALWAYS);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
