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
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class WypisanieWizyt implements Initializable {
    @FXML
    public TableColumn peselPacjenta;
    @FXML
    public Button confirmButton;
    @FXML
    public TextField nazwiskoSzukaj;
    @FXML
    public DatePicker dataSzukaj;
    @FXML
    public Button szukajGuzik;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<?, ?> dataKolumna;

    @FXML
    private Button guzikCofacz;

    @FXML
    private TableColumn<?, ?> imieLekarza;

    @FXML
    private TableColumn<?, ?> imiePacjenta;

    @FXML
    private TableColumn<?, ?> nazwiskoLekarza;

    @FXML
    private TableColumn<?, ?> nazwiskoPacjenta;

    @FXML
    private TableView tab;

    @FXML
    private DatePicker zmienDate;
    private Button usun;
    private Button edytujDate;
    private int aktualnie_wybrany;
    ArrayList<WizytyDoWyswietlenia> wizytyDoWyswietlenias;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        confirmButton.setStyle("-fx-background-color: #9E7BB5; -fx-text-fill: #710193;");

        WizytyUtil wizytyUtil = new WizytyUtil();
        wizytyDoWyswietlenias = new ArrayList<WizytyDoWyswietlenia>();
        szukajGuzik.setOnAction(new EventHandler<ActionEvent>() {

            WizytyUtil wizytyUtil = new WizytyUtil();

            @Override
            public void handle(ActionEvent actionEvent) {
                wizytyDoWyswietlenias.clear();
                System.out.println(dataSzukaj.getValue());
                tab.getItems().clear();
                if(dataSzukaj.getValue() == null && nazwiskoSzukaj.getText().isEmpty()){
                    for (Wizyty wizyta:
                         wizytyUtil.szukajPoID(AktualneDane.getInstance().lekarz.getId())) {
                        wizytyDoWyswietlenias.add(new WizytyDoWyswietlenia(wizyta.getIdL().getName(),wizyta.getIdL().getSurname(),wizyta.getIdP().getName(),wizyta.getIdP().getSurname(),wizyta.getDate(),wizyta.getIdP().getPesel()));
                    }
                } else if (dataSzukaj.getValue() == null) {
                    for (Wizyty wizyta:
                            wizytyUtil.szukajPoNazwiskuPacjentaIIdLekarza(nazwiskoSzukaj.getText(),AktualneDane.getInstance().lekarz.getId())) {
                        wizytyDoWyswietlenias.add(new WizytyDoWyswietlenia(wizyta.getIdL().getName(),wizyta.getIdL().getSurname(),wizyta.getIdP().getName(),wizyta.getIdP().getSurname(),wizyta.getDate(),wizyta.getIdP().getPesel()));
                    }
                } else if (nazwiskoSzukaj.getText().isEmpty()){
                    for (Wizyty wizyta:
                            wizytyUtil.szukajPoDacieIIdLekarza(convertToDateViaSqlDate(dataSzukaj.getValue()),AktualneDane.getInstance().lekarz.getId())) {
                        wizytyDoWyswietlenias.add(new WizytyDoWyswietlenia(wizyta.getIdL().getName(),wizyta.getIdL().getSurname(),wizyta.getIdP().getName(),wizyta.getIdP().getSurname(),wizyta.getDate(),wizyta.getIdP().getPesel()));
                    }
                } else {
                    for (Wizyty wizyta:
                            wizytyUtil.szukajPoDacieINazwiskuIIdLekarza(nazwiskoSzukaj.getText(),convertToDateViaSqlDate(dataSzukaj.getValue()),AktualneDane.getInstance().lekarz.getId())) {
                        wizytyDoWyswietlenias.add(new WizytyDoWyswietlenia(wizyta.getIdL().getName(),wizyta.getIdL().getSurname(),wizyta.getIdP().getName(),wizyta.getIdP().getSurname(),wizyta.getDate(),wizyta.getIdP().getPesel()));
                    }
                }
                tab.getItems().addAll(wizytyDoWyswietlenias);
            }
        });


        imieLekarza.setCellValueFactory(new PropertyValueFactory<>("imieLekarza"));
        nazwiskoLekarza.setCellValueFactory(new PropertyValueFactory<>("nazwiskoLekarza"));
        imiePacjenta.setCellValueFactory(new PropertyValueFactory<>("imiePacjenta"));
        nazwiskoPacjenta.setCellValueFactory(new PropertyValueFactory<>("NazwiskoPacjenta"));
        peselPacjenta.setCellValueFactory(new PropertyValueFactory<>("peselPacjenta"));
        dataKolumna.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn odwolania = new TableColumn();

        odwolania.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>> cellFactory
                = //
                new Callback<TableColumn<WizytyDoWyswietlenia, Button>, TableCell<WizytyDoWyswietlenia, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<WizytyDoWyswietlenia, Button> param) {
                        final TableCell<WizytyDoWyswietlenia, Button> cell = new TableCell<WizytyDoWyswietlenia, Button>() {

                            final Button btn = new Button("Odwołaj");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        PacjenciUtil pacjenciUtil = new PacjenciUtil();
                                        WizytyUtil wizytyUtil = new WizytyUtil();
                                        String imie = wizytyDoWyswietlenias.get(getIndex()).getImiePacjenta();
                                        String nazwisko = wizytyDoWyswietlenias.get(getIndex()).getNazwiskoPacjenta();
                                        String pesel = wizytyDoWyswietlenias.get(getIndex()).getPeselPacjenta();
                                        Pacjenci pacjentDoKasacji = pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel);
                                        Wizyty wizytaDoKasacji = wizytyUtil.szukajDoUsun(pacjentDoKasacji.getId(),wizytyDoWyswietlenias.get(getIndex()).getDate());
                                        wizytyUtil.usun(wizytaDoKasacji);
                                        wizytyDoWyswietlenias.remove(getIndex());
                                        tab.getItems().clear();
                                        tab.getItems().addAll(wizytyDoWyswietlenias);


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

                            final Button btn = new Button("Przenieś");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        zmienDate.setVisible(true);
                                        confirmButton.setVisible(true);
                                        zmienDate.setVisible(true);
                                        zmienDate.setValue(convertToLocalDateViaInstant(wizytyDoWyswietlenias.get(getIndex()).getDate()));
                                        aktualnie_wybrany = getIndex();

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


        for (Wizyty wizyta :
             wizytyUtil.szukajPoID(AktualneDane.getInstance().lekarz.getId())) {
            wizytyDoWyswietlenias.add(new WizytyDoWyswietlenia(wizyta.getIdL().getName(),wizyta.getIdL().getSurname(),wizyta.getIdP().getName(),wizyta.getIdP().getSurname(),wizyta.getDate(),wizyta.getIdP().getPesel()));
        }
        tab.getItems().addAll(wizytyDoWyswietlenias);
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
        WizytyUtil wizytyUtil = new WizytyUtil();

        String imie = wizytyDoWyswietlenias.get(aktualnie_wybrany).getImiePacjenta();
        String nazwisko = wizytyDoWyswietlenias.get(aktualnie_wybrany).getNazwiskoPacjenta();
        String pesel = wizytyDoWyswietlenias.get(aktualnie_wybrany).getPeselPacjenta();
        Pacjenci pacjentDoKasacji = pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel);
        Wizyty wizytaDoKasacji = wizytyUtil.szukajDoUsun(pacjentDoKasacji.getId(),wizytyDoWyswietlenias.get(aktualnie_wybrany).getDate());
        LocalDate localDate = zmienDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        wizytaDoKasacji.setDate(Date.from(instant));
        wizytyUtil.zmien(wizytaDoKasacji);
        wizytyDoWyswietlenias.get(aktualnie_wybrany).setDate(Date.from(instant));
        tab.refresh();
    }


}
