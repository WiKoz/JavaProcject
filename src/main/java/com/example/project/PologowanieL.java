package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PologowanieL {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Text opisWejsciaPierwszego;
    @FXML
    public Text opisWejsciaDrugiego;
    @FXML
    public VBox panelWpisywania;
    @FXML
    public DatePicker datePicker;
    @FXML
    private Button submitButton;

    @FXML
    private TextField wejscieDrugie;

    @FXML
    private TextField wejsciePierwsze;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox doWyswietlania;
    @FXML
    private MenuButton konto;

    @FXML
    private MenuItem kontoZob;

    @FXML
    private ImageView obrazek;

    @FXML
    private Text przywitanie;

    @FXML
    private MenuButton recepty;

    @FXML
    private MenuItem receptyNow;

    @FXML
    private MenuItem receptyZob;

    @FXML
    private Button strG;

    @FXML
    private MenuButton wizyty;

    @FXML
    private MenuItem wizytyUm;

    @FXML
    private MenuItem wizytyZob;

    @FXML
    private MenuItem wyloguj;
    @FXML
    private Button zamknij;
    @FXML
    private Text bledneDane;
    @FXML
    private TextField leki;
    @FXML
    private Text napisLeki;
    @FXML
    private Button zatwierdz;
    @FXML
    private Button usuwanko;

    @FXML
    void Glowna(ActionEvent event) {
        scrollPane.setVisible(false);
        panelWpisywania.setVisible(false);
    }

    @FXML
    void NowaRec(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXML/wypisanieRecept.fxml"));
        stage = (Stage) recepty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void UmowienieWiz(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXML/Pacjenci.fxml"));
        stage = (Stage) recepty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Wylogowanie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/Start.fxml"));
        stage = (Stage) recepty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ZobaczenieWiz(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/zobaczenieRecept.fxml"));
        stage = (Stage) recepty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ZobaczenieWizyt(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXML/wypisanieWizyt.fxml"));
        stage = (Stage) wizyty.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void zamknij(ActionEvent event) {
        Stage stage = (Stage) zamknij.getScene().getWindow();
        stage.close();
    }
}
