package ru.mgusev.snake;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class FieldFrame extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private SnakeController snakeController;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Snake");

        initRootLayout();
        snakeController.initField();
        snakeController.initSnakeHead();
        snakeController.initSnakeTail();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (snakeController.getColumnIndex() > 0 && snakeController.getColumnIndex() < 20 && snakeController.getRowIndex() > 0 && snakeController.getRowIndex() < 20 ){
                        //snakeController.getFieldGP().getChildren().clear();
                        snakeController.move();
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/RootLayout.fxml"));
            rootLayout = loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            snakeController = loader.getController();
            snakeController.setMainApp(this);
            primaryStage.show();

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent key) {
                    String pressedKey = key.getCode().getName();
                    //try {
                        if (pressedKey.equals("Up")) {
                            snakeController.setAngle(0.0);
                            //snakeController.move();
                        } else if (pressedKey.equals("Down")) {
                            snakeController.setAngle(180.0);
                            //snakeController.move();
                        } else if (pressedKey.equals("Right")) {
                            snakeController.setAngle(90.0);
                            //snakeController.move();
                        } else if (pressedKey.equals("Left")) {
                            snakeController.setAngle(270.0);
                            //snakeController.move();
                        }
                    /*} catch (//InterruptedException e) {
                        //e.printStackTrace();
                    }*/
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
