package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import polaczenie.KlasaPolaczenie;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public Label info;
    public Button zapisz;
    public TextField nazwisko;
    public TextField imie;
    public TextField telefon;

    public void addPerson(ActionEvent actionEvent) {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();

        String im = imie.getText();
        String nazw = nazwisko.getText();
        String tel = telefon.getText();

        String sql = "INSERT INTO OSOBY VALUES(null,'"+im+"','"+nazw+"'," + tel +")";

        try {
            Statement stat = baza.createStatement();
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }
        info.setText("Dodano: "+im+" "+nazw);
    }
}
