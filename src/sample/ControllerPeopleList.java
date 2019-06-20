package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import polaczenie.KlasaPolaczenie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ControllerPeopleList {
    public TableView tab;


    public void initialize() throws SQLException {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();
        ResultSet rs=null;

        String sql = "Select * FROM OSOBY  ";

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
            tab.getItems().add(person);
        }

    }

}
