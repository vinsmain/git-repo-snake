package ru.mgusev.snake;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SnakeController {

    private FieldFrame mainApp;
    private int columnIndex = 5;
    private int rowIndex = 5;

    @FXML
    private GridPane fieldGP = new GridPane();

    @FXML
    private Button snakeHead = new Button();

    public void initSnakeHead() {
        snakeHead.setPrefWidth(50);
        snakeHead.setPrefHeight(50);
        fieldGP.add(snakeHead, columnIndex, rowIndex);
    }

    public void setMainApp(FieldFrame mainApp) {
        this.mainApp = mainApp;
    }
}
