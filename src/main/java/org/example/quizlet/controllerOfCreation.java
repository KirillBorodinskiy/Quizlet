package org.example.quizlet;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class controllerOfCreation {

    @FXML
    private TextField titleCard;

    @FXML
    private TextArea backSideInput;

    @FXML
    private TextArea frontSideInput;

    public void saveCard() throws SQLException, ClassNotFoundException {
        databaseHandler handler = new databaseHandler();
        handler.addCard(titleCard.getText(),frontSideInput.getText(), backSideInput.getText());
    }
}