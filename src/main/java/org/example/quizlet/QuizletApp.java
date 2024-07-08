package org.example.quizlet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizletApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int screenWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
        int screenHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader(QuizletApp.class.getResource("sceneOfCreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth,screenHeight );
        stage.setTitle("Quizlet Quizlet!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        databaseHandler handler =new databaseHandler();
//        try {
//            handler.addCard("akakaak", "akakdwaj", "ododod");
//        } catch (SQLException e) {
//            System.out.printf(e.getMessage());
//        }
        try {
            ResultSet res = handler.getCards();
            System.out.println("Here are the added cards:");
            while (res.next()) {
                System.out.println("-----------------------------");
                System.out.println(res.getString("title"));
                System.out.println(res.getString("frontSide"));
                System.out.println(res.getString("backSide"));
//                System.out.println("-----------------------------");
            }

        }catch(SQLException e) {
            System.out.printf(e.getMessage());
        }
        launch();
    }
}