package ru.mgusev.snake;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SnakeController {

    private FieldFrame mainApp;
    private ImageView imageView;
    private Double angle = 0.0;
    private int columnIndex = 5;
    private int rowIndex = 5;
    private int columnIndexTail = 5;
    private int rowIndexTail = 4;

    @FXML
    private GridPane fieldGP  = new GridPane();

    @FXML
    private Button snakeHead;

    @FXML
    private Button snakeTail;

    public void initSnakeHead() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                snakeHead = new Button();
                Image imageDecline = new Image(getClass().getResourceAsStream("/images/head.png"));
                imageView = new ImageView(imageDecline);
                imageView.setRotate(angle);
                snakeHead.setGraphic(imageView);
                snakeHead.setPrefWidth(50);
                snakeHead.setPrefHeight(50);
                snakeHead.setPadding(Insets.EMPTY);
                fieldGP.add(snakeHead, columnIndex, rowIndex);
                GridPane.setHalignment(snakeHead, HPos.CENTER);
                GridPane.setValignment(snakeHead, VPos.CENTER);
            }
        });
    }

    public void initSnakeTail() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                snakeTail = new Button();
                Image imageDecline = new Image(getClass().getResourceAsStream("/images/body.png"));
                ImageView imageView = new ImageView(imageDecline);
                snakeTail.setGraphic(imageView);
                snakeTail.setPrefWidth(50);
                snakeTail.setPrefHeight(50);
                snakeTail.setPadding(Insets.EMPTY);
                fieldGP.add(snakeTail, columnIndexTail, rowIndexTail);
                GridPane.setHalignment(snakeTail, HPos.CENTER);
                GridPane.setValignment(snakeTail, VPos.CENTER);
            }
        });
    }

    public void initField() {
        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints(54);
            ColumnConstraints column = new ColumnConstraints(54);
            fieldGP.getRowConstraints().add(row);
            fieldGP.getColumnConstraints().add(column);
        }
        fieldGP.setGridLinesVisible(true);
        fieldGP.setStyle("-fx-background-color: greenyellow; -fx-grid-lines-visible: true");
    }

    public void setMainApp(FieldFrame mainApp) {
        this.mainApp = mainApp;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public void move() throws InterruptedException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fieldGP.getChildren().clear();
                rowIndexTail = rowIndex;
                columnIndexTail = columnIndex;
                if (angle == 0.0) {
                    rowIndex--;
                } else if (angle == 180) {
                    rowIndex++;
                } else if (angle == 90) {
                    columnIndex++;
                } else if (angle == 270) {
                    columnIndex--;
                }
                initSnakeHead();
                initSnakeTail();
            }
        });
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public GridPane getFieldGP() {
        return fieldGP;
    }
}
