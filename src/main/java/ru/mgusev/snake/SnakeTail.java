package ru.mgusev.snake;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SnakeTail extends Button {

    private int columnIndex;
    private int rowIndex;

    public SnakeTail(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        Image imageDecline = new Image(getClass().getResourceAsStream("/images/body.png"));
        ImageView imageView = new ImageView(imageDecline);
        setGraphic(imageView);
        setPrefWidth(50);
        setPrefHeight(50);
        setPadding(Insets.EMPTY);
        GridPane.setHalignment(this, HPos.CENTER);
        GridPane.setValignment(this, VPos.CENTER);
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
