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
import java.util.ArrayList;

public class SnakeController {

    private FieldFrame mainApp;
    private ImageView imageView;
    private ArrayList<SnakeTail> snakeBodyArray = new ArrayList<SnakeTail>();
    private Double angle = 0.0;
    private int columnIndex = 5;
    private int rowIndex = 5;
    private int columnIndexTail = 5;
    private int rowIndexTail = 4;
    private int columnLastIndex = 5;
    private int rowLastIndex = 3;
    private int columnIndexFruit = 7;
    private int rowIndexFruit = 6;

    @FXML
    private GridPane fieldGP  = new GridPane();

    @FXML
    private Button snakeHead;

    //@FXML
    //private SnakeTail snakeTail;

    @FXML
    private Fruit fruit;

    public SnakeController() {
        snakeBodyArray.add(new SnakeTail(5, 4));
    }

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
                SnakeTail snakeTail = snakeBodyArray.get(0);
                fieldGP.add(snakeTail, snakeTail.getColumnIndex(), snakeTail.getRowIndex());
            }
        });
    }

    public void eatFruit() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                SnakeTail snakeTail = snakeBodyArray.get(snakeBodyArray.size() - 1);
                fieldGP.add(snakeTail, snakeTail.getColumnIndex(), snakeTail.getRowIndex());
            }
        });
    }

    public void initField() {
        for (int i = 0; i < 16; i++) {
            RowConstraints row = new RowConstraints(54);
            ColumnConstraints column = new ColumnConstraints(54);
            fieldGP.getRowConstraints().add(row);
            fieldGP.getColumnConstraints().add(column);
        }
        //fieldGP.setGridLinesVisible(true);
        fieldGP.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
    }

    public void initFruit() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fruit = new Fruit();
                do {
                    columnIndexFruit = (int) (Math.random() * 16);
                    rowIndexFruit = (int) (Math.random() * 16);
                } while (!correctFruitPosition(columnIndexFruit, rowIndexFruit));
                fieldGP.add(fruit, columnIndexFruit, rowIndexFruit);
            }
        });
    }

    public boolean correctFruitPosition(int columnIndexFruit, int rowIndexFruit) {
        if (columnIndex == columnIndexFruit && rowIndex == rowIndexFruit) return false;
        for (SnakeTail snakeTail: snakeBodyArray) {
            if (snakeTail.getColumnIndex() == columnIndexFruit && snakeTail.getRowIndex() == rowIndexFruit) return  false;
        }
        return true;
    }

    public void setMainApp(FieldFrame mainApp) {
        this.mainApp = mainApp;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public void clearField() {
        fieldGP.getChildren().removeAll(snakeHead, snakeBodyArray.get(snakeBodyArray.size() - 1));
        snakeBodyArray.remove(snakeBodyArray.size() - 1);
        snakeBodyArray.add(0, new SnakeTail(columnIndex, rowIndex));
    }

    public void move() throws InterruptedException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clearField();
                rowLastIndex = rowIndexTail;
                columnLastIndex = columnIndexTail;
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
                if (columnIndex == columnIndexFruit && rowIndex == rowIndexFruit) {
                    fieldGP.getChildren().removeAll(fruit);
                    System.out.println(columnLastIndex + " " + rowLastIndex);
                    snakeBodyArray.add(new SnakeTail(columnLastIndex, rowLastIndex));
                    eatFruit();
                    initFruit();
                }
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
