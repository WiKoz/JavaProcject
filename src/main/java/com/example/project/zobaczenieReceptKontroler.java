package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class zobaczenieReceptKontroler implements Initializable {
    @FXML
    public TableColumn peselPacjenta;
    @FXML
    public Button confirmButton;
    @FXML
    public TextField nazwiskoSzukaj;
    @FXML
    TextArea tresc;

    @FXML
    public Button szukajGuzik;
    @FXML
    public TableColumn receptaKolumna;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button guzikCofacz;


    @FXML
    private TableColumn<?, ?> imiePacjenta;


    @FXML
    private TableColumn<?, ?> nazwiskoPacjenta;

    @FXML
    private TableView tab;

    private Button usun;
    private Button edytujDate;
    private int aktualnie_wybrany;
    ArrayList<ReceptyDoWyswietlania> recepyArrayList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        confirmButton.setStyle("-fx-background-color: #9E7BB5; -fx-text-fill: #710193;");

        WizytyUtil wizytyUtil = new WizytyUtil();
        recepyArrayList = new ArrayList<ReceptyDoWyswietlania>();
        szukajGuzik.setOnAction(new EventHandler<ActionEvent>() {
            ReceptyUtil receptyUtil = new ReceptyUtil();

            @Override
            public void handle(ActionEvent actionEvent) {
                recepyArrayList.clear();

                tab.getItems().clear();
                if(nazwiskoSzukaj.getText().isEmpty()){
                    for (Recepy recepta:
                            receptyUtil.szukajPoID(AktualneDane.getInstance().lekarz.getId())) {
                        recepyArrayList.add(new ReceptyDoWyswietlania(recepta.getIdP().getName(),recepta.getIdP().getSurname(),recepta.getIdP().getPesel(),recepta.getLek()));
                    }
                } else {
                    for (Recepy recepta:
                            receptyUtil.szukajPoNazwiskuPIIdLekarza(nazwiskoSzukaj.getText(), AktualneDane.getInstance().lekarz.getId())) {
                        recepyArrayList.add(new ReceptyDoWyswietlania(recepta.getIdP().getName(),recepta.getIdP().getSurname(),recepta.getIdP().getPesel(),recepta.getLek()));
                    }
                }
                tab.getItems().addAll(recepyArrayList);
            }
        });
        szukajGuzik.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        szukajGuzik.setStyle("-fx-background-color: #9E7BB5; -fx-text-fill: #710193;");


        imiePacjenta.setCellValueFactory(new PropertyValueFactory<>("imiePacjenta"));
        nazwiskoPacjenta.setCellValueFactory(new PropertyValueFactory<>("NazwiskoPacjenta"));
        peselPacjenta.setCellValueFactory(new PropertyValueFactory<>("peselPacjenta"));
        receptaKolumna.setCellValueFactory(new PropertyValueFactory<>("tresc"));

        TableColumn odwolania = new TableColumn();

        odwolania.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>> cellFactory
                = //
                new Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<WizytyDoWyswietlenia, Button> param) {
                        final TableCell<WizytyDoWyswietlenia, Button> cell = new TableCell<WizytyDoWyswietlenia, Button>() {

                            final Button btn = new Button("Usuń");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        PacjenciUtil pacjenciUtil = new PacjenciUtil();
                                        ReceptyUtil receptyUtil = new ReceptyUtil();
                                        String imie = recepyArrayList.get(getIndex()).getImiePacjenta();
                                        String nazwisko = recepyArrayList.get(getIndex()).getNazwiskoPacjenta();
                                        String pesel = recepyArrayList.get(getIndex()).getPeselPacjenta();
                                        Pacjenci pacjentDoKasacji = pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel);
                                        Recepy receptaDoKasacji = receptyUtil.szukajPoIDPITresi(pacjentDoKasacji.getId(),recepyArrayList.get(getIndex()).getTresc());
                                        receptyUtil.usun(receptaDoKasacji);
                                        recepyArrayList.remove(getIndex());
                                        tab.getItems().clear();
                                        tab.getItems().addAll(recepyArrayList);


                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        odwolania.setCellFactory(cellFactory);

        tab.getColumns().add(odwolania);


        TableColumn przeniesienie = new TableColumn();

        odwolania.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>> cellFactoryy
                = //
                new Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<WizytyDoWyswietlenia, Button> param) {
                        final TableCell<WizytyDoWyswietlenia, Button> cell = new TableCell<WizytyDoWyswietlenia, Button>() {

                            final Button btn = new Button("Zmień");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                        confirmButton.setVisible(true);
                                        tresc.setVisible(true);
                                        aktualnie_wybrany = getIndex();
                                        tresc.setText(recepyArrayList.get(getIndex()).getTresc());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        przeniesienie.setCellFactory(cellFactoryy);

        tab.getColumns().add(przeniesienie);

        ReceptyUtil receptyUtil = new ReceptyUtil();
        for (Recepy recepta:
                receptyUtil.szukajPoID(AktualneDane.getInstance().lekarz.getId())) {
            recepyArrayList.add(new ReceptyDoWyswietlania(recepta.getIdP().getName(),recepta.getIdP().getSurname(),recepta.getIdP().getPesel(),recepta.getLek()));
        }
        tab.getItems().addAll(recepyArrayList);
    }
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    @FXML
    void cofnij(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/PologowanieL.fxml"));
        stage = (Stage) guzikCofacz.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void update(ActionEvent actionEvent) {

        PacjenciUtil pacjenciUtil = new PacjenciUtil();
        ReceptyUtil receptyUtil = new ReceptyUtil();

        String imie = recepyArrayList.get(aktualnie_wybrany).getImiePacjenta();
        String nazwisko = recepyArrayList.get(aktualnie_wybrany).getNazwiskoPacjenta();
        String pesel = recepyArrayList.get(aktualnie_wybrany).getPeselPacjenta();
        String trescRec = tresc.getText();
        Pacjenci pacjentDoKasacji = pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel);
        Recepy recepy = receptyUtil.szukajPoIDPITresi(pacjentDoKasacji.getId(),recepyArrayList.get(aktualnie_wybrany).getTresc());


        recepy.setLek(trescRec);
        receptyUtil.zmien(recepy);
        recepyArrayList.get(aktualnie_wybrany).setTresc(trescRec);
        tab.refresh();
        tresc.clear();
        tresc.setVisible(false);
        confirmButton.setVisible(false);
    }


}
