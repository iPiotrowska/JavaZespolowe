package sample;

import javafx.scene.control.TableView;

import polaczenie.KlasaPolaczenie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
