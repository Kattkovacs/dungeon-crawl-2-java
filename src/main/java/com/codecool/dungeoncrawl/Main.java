package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    final int CANVAS_WIDTH = 25;
    final int CANVAS_HEIGHT = 20;
    Canvas canvas = new Canvas(
            CANVAS_WIDTH * Tiles.TILE_WIDTH,
            CANVAS_HEIGHT * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label attackLabel = new Label();
    Label strLabel = new Label();
    Label dexLabel = new Label();
    Label inventoryLabel = new Label();
    Label actionLabel = new Label();
    Label enemyLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0, 1, 1);
        ui.add(healthLabel, 1, 0, 1, 1);

        ui.add(new Label("Attack: "), 0, 1, 1, 1);
        ui.add(attackLabel, 1, 1, 1, 1);

        ui.add(new Label("Strength: "), 0, 2, 1, 1);
        ui.add(strLabel, 1, 2, 1, 1);

        ui.add(new Label("Dexterity: "), 0, 3, 1, 1);
        ui.add(dexLabel, 1, 3, 1, 1);

        ui.add(actionLabel, 0, 4, 2, 1);
        actionLabel.setMinHeight(100);
        actionLabel.setWrapText(true);

        ui.add(enemyLabel, 0, 5, 2, 1);
        enemyLabel.setMinHeight(200);

        ui.add(new Label("Inventory: "), 0, 6, 2, 1);
        ui.add(inventoryLabel, 0, 7, 2, 1);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (map.getPlayer().getHealth() <= 0){
            map.setPlayer(null);
            // TODO
            // pop up - game over - new play
        }
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
            case TAB:
                map.getPlayer().pickUpItem();
                refresh();
                break;
        }
    }

    private void refresh() {
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
}
