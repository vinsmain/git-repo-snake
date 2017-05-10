package ru.mgusev.snake;

import javafx.application.Application;

public class MainClass {
    public static void main(String[] args) {
        try {
            Application.launch(FieldFrame.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
