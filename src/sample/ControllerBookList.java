package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import polaczenie.KlasaPolaczenie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ControllerBookList {
    public TableView tab;
    public AnchorPane anc;

    public void initialize() throws SQLException {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        String sql = "Select k.id_ksiazki, k.tytul,k.autor,DATE_FORMAT(k.data_wypozyczenia, '%d-%m-%Y'),CONCAT(o.imie,' ',o.nazwisko) FROM KSIAZKI k LEFT JOIN OSOBY o ON(k.id_osoby=o.id_osoby)";
        System.out.println(sql);
        try {
            Statement stat = baza.createStatement();
            rs=stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }

        while(rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String author = rs.getString(3);
            String date = rs.getString(4);
            String person = rs.getString(5);
            Book book = new Book(id,title,author,date,person);
            tab.getItems().add(book);
        }

    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        anc.getChildren().setAll(pane);
    }
}
