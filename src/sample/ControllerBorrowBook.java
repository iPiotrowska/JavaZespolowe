package sample;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import Models.Book;
import Models.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import polaczenie.KlasaPolaczenie;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerBorrowBook {
    public TableView tab;
    public AnchorPane anc;
    public AnchorPane anc2;
    public TableView tab2;




    public void initialize() throws SQLException {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        String sql = "Select k.id_ksiazki, k.tytul,k.autor FROM KSIAZKI k WHERE k.id_osoby is null"; //,DATE_FORMAT(k.data_wypozyczenia, '%d-%m-%Y'),CONCAT(o.imie,' ',o.nazwisko)
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
            String date=null;
                   // date = rs.getString(4);
            String person =null;
                    // person= rs.getString(5);
            Book book = new Book(id,title,author,date,person);
            tab.getItems().add(book);
        }

        sql = "Select * FROM OSOBY  ";

        try {
            Statement stat = baza.createStatement();
            rs=stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }

        while(rs.next()) {
            int id = rs.getInt(1);
            String imie = rs.getString(2);
            String nazwisko = rs.getString(3);
            int telefon = rs.getInt(4);
            Person person = new Person(id, imie, nazwisko, telefon);
            tab2.getItems().add(person);
        }



    }

    public void borrowBook(ActionEvent actionEvent) throws IOException {

        //Pobierz id ksiązki jako selectedBook.getId()
        TableView<Book> taview2=tab;
        Object selectedItems2 = taview2.getSelectionModel().getSelectedItem();
        Book selectedBook = (Book) selectedItems2;
        System.out.println(selectedBook.getId());

        //Pobierz id osoby jako selectedPerson.getId()
        TableView<Person> taview=tab2;
        Object selectedItems = taview.getSelectionModel().getSelectedItem();
        Person selectedPerson = (Person) selectedItems;
        System.out.println(selectedPerson.getId());

        int personId=selectedPerson.getId();
        int bookId=selectedBook.getId();

        //Połączenie z bazą
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));

        String sql = "UPDATE KSIAZKI SET data_wypozyczenia=NOW() , id_osoby="+personId+" where id_ksiazki="+bookId+"";

        System.out.println(sql);
        try {
            Statement stat = baza.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }

    }



    public void backToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        anc.getChildren().setAll(pane);
    }
}


