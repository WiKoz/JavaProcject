package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PologowanieP {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button glowna;

    @FXML
    private MenuButton konto;

    @FXML
    private Button recepty;

    @FXML
    private Button wizyty;

    @FXML
    private MenuItem wyloguj;

    @FXML
    private MenuItem wyswietl;
    @FXML
    private VBox doWyswietlania;

    @FXML
    private ScrollPane scroll;
    @FXML
    private Button doZamkniecia;

    @FXML
    void Recepty(ActionEvent event) {
        scroll.setVisible(true);
        doWyswietlania.getChildren().clear();
        ReceptyUtil receptyUtil = new ReceptyUtil();
        for ( Recepy recepta:
                receptyUtil.szukajPoIDP(AktualneDane.getInstance().pacjent.getId())) {
            Text temp = new Text(" Imie:  " + recepta.getIdP().getName());
            Text temp1 = new Text(" Nazwisko:  " + recepta.getIdP().getSurname());
            Text temp4 = new Text(" Pesel:  " + recepta.getIdP().getPesel());
            Text temp2 = new Text(" Treść recepty:  " + recepta.getLek());
            Text temp3 = new Text("......................................................................................");
            doWyswietlania.getChildren().addAll(temp,temp1,temp4,temp2,temp3);
        }
    }

    @FXML
    void Strona(ActionEvent event) {
        scroll.setVisible(false);
    }

    @FXML
    void Wizyty(ActionEvent event) {
        scroll.setVisible(true);
        doWyswietlania.getChildren().clear();
        WizytyUtil wizytyUtil = new WizytyUtil();
        for ( Wizyty wizyta:
                wizytyUtil.szukajPoIDP(AktualneDane.getInstance().pacjent.getId())) {
            Text temp = new Text("imie:  " + wizyta.getIdL().getName());
            Text temp1 = new Text("nazwisko:  " + wizyta.getIdL().getSurname());
            Text temp2 = new Text("data wizyty:  " + wizyta.getDate().toString());
            Text temp3 = new Text("......................................................................................");
            doWyswietlania.getChildren().addAll(temp,temp1,temp2,temp3);
        }
    }

    @FXML
    void Wyloguj(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/Start.fxml"));
        stage = (Stage) recepty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Wylacz(ActionEvent event) {
        Stage stage = (Stage) doZamkniecia.getScene().getWindow();
        stage.close();
    }

}
