package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class ControllerMenu {
    public Button addPerson;
    public Button addBook;
    public Button peopleList;
    public Button bookList;
    public AnchorPane rootPane;


    public void addPerson(ActionEvent actionEvent) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
         rootPane.getChildren().setAll(pane);
    }

    public void addBook(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("addBook.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void peopleList(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("peopleList.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void bookList(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("bookList.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
