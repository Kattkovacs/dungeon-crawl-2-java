package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

import java.sql.SQLException;

public class Main extends Application {
    GameMap map = MapLoader.loadMap(1, null);
    final int CANVAS_WIDTH = 25;
    final int CANVAS_HEIGHT = 20;
    Canvas canvas = new Canvas(
            CANVAS_WIDTH * Tiles.TILE_WIDTH,
            CANVAS_HEIGHT * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label playersInfoSection = new Label("PLAYER'S INFO: ");
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label strLabel = new Label();
    Label dexLabel = new Label();
    Label hintsSection = new Label("HINTS: ");
    Label actionLabel = new Label();
    Label enemyDetectorSection = new Label("ENEMY DETECTOR: ");
    Label enemyLabel = new Label();
    Label inventorySection = new Label("INVENTORY: ");
    Label inventoryLabel = new Label();
    Separator separator1 = new Separator(Orientation.HORIZONTAL);
    Separator separator2 = new Separator(Orientation.HORIZONTAL);
    Separator separator3 = new Separator(Orientation.HORIZONTAL);
    Separator separator4 = new Separator(Orientation.HORIZONTAL);
    private Timeline timeline;
    Stage dialogStage;
    GameDatabaseManager dbManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon = new Image("/dungeonIcon.png");
        primaryStage.getIcons().add(icon);

        setupDbManager();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(playersInfoSection, 0, 0, 1, 1);
        playersInfoSection.setMinHeight(30);
        playersInfoSection.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));

        ui.add(new Label("Health: "), 0, 1, 1, 1);
        ui.add(healthLabel, 1, 1, 1, 1);

        ui.add(new Label("Attack: "), 0, 2, 1, 1);
        ui.add(attackLabel, 1, 2, 1, 1);

        ui.add(new Label("Strength: "), 0, 3, 1, 1);
        ui.add(strLabel, 1, 3, 1, 1);

        ui.add(new Label("Dexterity: "), 0, 4, 1, 1);
        ui.add(dexLabel, 1, 4, 1, 1);

        ui.add(separator1, 0,5, 2,1);

        ui.add(hintsSection, 0, 6, 1, 1);
        hintsSection.setMinHeight(30);
        hintsSection.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 12));

        ui.add(actionLabel, 0, 7, 2, 1);
        actionLabel.setMinHeight(100);
        actionLabel.setWrapText(true);
        actionLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 12));
        actionLabel.setTextFill(Color.PURPLE);

        ui.add(separator2, 0,8, 2,1);

        ui.add(enemyDetectorSection, 0, 9, 1, 1);
        enemyDetectorSection.setMinHeight(30);
        enemyDetectorSection.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));

        ui.add(enemyLabel, 0, 10, 2, 1);
        enemyLabel.setMinHeight(180);
        enemyLabel.setAlignment(Pos.TOP_LEFT);

        ui.add(separator3, 0,11, 2,1);

        ui.add(inventorySection, 0, 12, 2, 1);
        inventorySection.setMinHeight(30);
        inventorySection.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));

        ui.add(inventoryLabel, 0, 13, 2, 1);
        inventoryLabel.setMinHeight(140);
        inventoryLabel.setAlignment(Pos.TOP_LEFT);

        ui.add(separator4, 0,14, 2,1);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    map.moveAI();
                    refresh();
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawler by Adventurers");
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (!map.getPlayer().isDied()) {
            switch (keyEvent.getCode()) {
                case UP:
                    map.getPlayer().move(0, -1);
                    refresh();
                    break;
                case DOWN:
                    map.getPlayer().move(0, 1);
                    refresh();
                    break;
                case LEFT:
                    map.getPlayer().move(-1, 0);
                    refresh();
                    break;
                case RIGHT:
                    map.getPlayer().move(1,0);
                    refresh();
                    break;
                case S:
                    //Player player = map.getPlayer();
                    //dbManager.savePlayer(player);
                    timeline.stop();
                    dbManager.saveGameState(map);
                    timeline.play();
                    break;
                case L:
                    timeline.stop();
                    GameState gameState = dbManager.getGameState();
                    map = MapLoader.loadSavedMap(
                            gameState,
                            dbManager.getMapModel(gameState.getId()),
                            dbManager.getCellModels(gameState.getId()),
                            dbManager.getPlayerModel(gameState.getPlayerId())
                    );
                    timeline.play();
                    break;
                case TAB:
                    map.getPlayer().pickUpItem();
                    map.getPlayer().useItem();
                    refresh();
                    break;
            }
        }
    }


    private void showRetryWindow() {
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Label message = new Label("You have died\nWould you like to try again?");
        message.setFont(new Font(18));
        message.setTextAlignment(TextAlignment.CENTER);
        Button button1 = new Button("YES");
        button1.setFont(new Font(18));
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                map.getPlayer().setDied(false);
                map.getPlayer().resetHealth();
                map = MapLoader.loadMap(map.getPlayer().getMapLevel(), map.getPlayer());
                dialogStage.close();
                dialogStage = null;
            }
        });
        Button button2 = new Button("QUIT");
        button2.setFont(new Font(18));
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        HBox buttons = new HBox(10, button1, button2);
        VBox vbox = new VBox(10, message, buttons);
        vbox.setStyle("-fx-background-color: rgba(30, 30, 30, 0.3)");
        buttons.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setMinWidth(300);
        vbox.setMinHeight(200);
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    private void refresh() {
        if (map.getPlayer().isDied() && dialogStage == null) {
            showRetryWindow();
        }
        if (map.getPlayer().isNextLevel()){
            map.getPlayer().setNextLevel(false);
            map.getPlayer().setMapLevel(map.getPlayer().getMapLevel() + 1);
            map = MapLoader.loadMap(map.getPlayer().getMapLevel(), map.getPlayer());
        }

        int startX = map.getPlayer().getX() - CANVAS_WIDTH / 2;
        if (startX < 0) startX = 0;
        if (startX + CANVAS_WIDTH >= map.getWidth()) startX = map.getWidth() - CANVAS_WIDTH;
        int endX = startX + CANVAS_WIDTH;
        int startY = map.getPlayer().getY() - CANVAS_HEIGHT / 2;
        if (startY < 0) startY = 0;
        if (startY + CANVAS_HEIGHT >= map.getHeight()) startY = map.getHeight() - CANVAS_HEIGHT;
        int endY = startY + CANVAS_HEIGHT;
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int canvasX = 0;
        for (int x = startX; x < endX; x++) {
            int canvasY = 0;
            for (int y = startY; y < endY; y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, canvasX, canvasY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, canvasX, canvasY);
                } else {
                    Tiles.drawTile(context, cell, x, y, canvasX, canvasY);
                }
                canvasY++;
            }
            canvasX++;
        }
        healthLabel.setText("" + map.getPlayer().getHealth() + " / " + map.getPlayer().getBaseHealth());
        attackLabel.setText("" + map.getPlayer().getAttack());
        strLabel.setText("" + map.getPlayer().getStr());
        dexLabel.setText("" + map.getPlayer().getDex());
        inventoryLabel.setText("" + map.getPlayer().getInventoryItem(map.getPlayer().getEquipments()) +
                map.getPlayer().getInventoryItem(map.getPlayer().getUsables()));
        actionLabel.setText("" + map.getPlayer().collectActions());
        enemyLabel.setText("" + map.getPlayer().collectEnemyInfo());
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

}
