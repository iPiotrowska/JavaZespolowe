package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import polaczenie.KlasaPolaczenie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAddBook {
    public Label info;
    public Button save;
    public TextField title;
    public TextField author;
    public AnchorPane anc;

    public void addBook(ActionEvent actionEvent) {
        KlasaPolaczenie kp = new KlasaPolaczenie();
        Connection baza = kp.dajPolaczenie();

        String tit=title.getText();
        String aut=author.getText();

        String sql = "INSERT INTO KSIAZKI VALUES(null,'"+tit+"','"+aut+"',"+null+","+null+")";

        try {
            Statement stat = baza.createStatement();
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Blad polecenia sql");
        }
        info.setText("Dodano: "+tit+" - "+aut);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        anc.getChildren().setAll(pane);
    }

}
