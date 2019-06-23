package sample;

import Models.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import polaczenie.KlasaPolaczenie;

import java.io.IOException;
import java.sql.*;
import java.util.Date;


public class ControllerReturnBook {
    public TableView tab;
    public AnchorPane anc;

    public void initialize() throws SQLException {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        String sql = "Select k.id_ksiazki, k.tytul,k.autor,DATE_FORMAT(k.data_wypozyczenia, '%d-%m-%Y'),CONCAT(o.imie,' ',o.nazwisko) FROM KSIAZKI k LEFT JOIN OSOBY o ON(k.id_osoby=o.id_osoby) " +
                "WHERE data_wypozyczenia IS NOT NULL";
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

    public void returnBook(ActionEvent actionEvent) throws IOException {

        TableView<Book> taview2=tab;
        Object selectedItems2 = taview2.getSelectionModel().getSelectedItem();
        Book selectedBook = (Book) selectedItems2;
        System.out.println(selectedBook.getId());

        int bookId=selectedBook.getId();

        //Połączenie z bazą
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        String sql = "UPDATE KSIAZKI SET data_wypozyczenia=null , id_osoby=null where id_ksiazki="+bookId+"";

        System.out.println(sql);
        try {
            Statement stat = baza.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }

        tab.refresh();

    }

    public void backToMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        anc.getChildren().setAll(pane);
    }


}


