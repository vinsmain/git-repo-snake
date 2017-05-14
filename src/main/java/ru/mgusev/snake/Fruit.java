package ru.mgusev.snake;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Fruit extends Button {

    Image imageDecline = new Image(getClass().getResourceAsStream("/images/apple.png"));
    //imageView = new ImageView(imageDecline);

    public Fruit() {
        setGraphic(new ImageView(imageDecline));
        setPrefWidth(50);
        setPrefHeight(50);
        setPadding(Insets.EMPTY);
        GridPane.setHalignment(this, HPos.CENTER);
        GridPane.setValignment(this, VPos.CENTER);
    }
}
