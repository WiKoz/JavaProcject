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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

public class PacjenciKontroler implements Initializable {

    public Label informacja;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn PESELCol;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn imieCol;

    @FXML
    private TableColumn nazwiskoCol;

    @FXML
    private HBox panelDoPol;
    Button add;

    @FXML
    private TableView tab;
    int wybrany_pacjent = 0;
    ArrayList<PacjentDoTabeli> listaPacjentow;
    TextField imieTextfield;
    TextField nazwiskoTextfield;

    @FXML
    void cofanie(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FXML/PologowanieL.fxml"));
        stage = (Stage) backButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        imieTextfield = new TextField();
        imieTextfield.promptTextProperty().set("Imię");
        informacja = new Label();
        informacja.setVisible(false);

        nazwiskoTextfield = new TextField();
        nazwiskoTextfield.promptTextProperty().set("Nazwisko");
        Button submit = new Button("Szukaj");
        submit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        submit.setStyle("-fx-background-color: #9E7BB5; -fx-text-fill: #710193;");


        DatePicker datePicker = new DatePicker();
        datePicker.promptTextProperty().set("Wybierz datę");
        add = new Button("Dodaj");
        add.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        add.setStyle("-fx-background-color: #9E7BB5;-fx-text-fill: #710193;");

        add.setVisible(false);
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                WizytyUtil wizytyUtil = new WizytyUtil();
                PacjenciUtil pacjenciUtil = new PacjenciUtil();
                if(nazwiskoTextfield.getText().isEmpty() || imieTextfield.getText().isEmpty() || datePicker.getValue() ==null){
                    informacja.setVisible(true);
                    informacja.setText("Proszę uzupełnić wszystkie pola");

                } else if(pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imieTextfield.getText(),nazwiskoTextfield.getText(),listaPacjentow.get(wybrany_pacjent).getPesel()) == null){
                    informacja.setVisible(true);
                    informacja.setText("Taki pacjent nie istnieje");
                }
                else {
                    LocalDate localDate = datePicker.getValue();
                    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                    String imie = listaPacjentow.get(wybrany_pacjent).getName();
                    String nazwisko = listaPacjentow.get(wybrany_pacjent).getSurname();
                    String pesel = listaPacjentow.get(wybrany_pacjent).getPesel();


                    Wizyty wizyta = new Wizyty(null,AktualneDane.getInstance().lekarz, Date.from(instant),pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel));
                    informacja.setVisible(true);
                    if(wizytyUtil.szukajDoUsun(pacjenciUtil.szukajPoImieniuINazwiskuIPeselu(imie,nazwisko,pesel).getId(),Date.from(instant)) == null){
                        wizytyUtil.dodaj(wizyta);
                        informacja.setText("Dodano wizytę");
                        imieTextfield.clear();
                        nazwiskoTextfield.clear();
                        datePicker.setValue(null);
                    }else {
                        informacja.setText("Wizyta już istnieje");
                    }
                }



            }
        });
        panelDoPol.setSpacing(10);
        VBox vBox1 = new VBox();

        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        vBox1.setSpacing(10);
        vBox2.setSpacing(10);
        vBox3.setSpacing(10);
        vBox1.getChildren().addAll(imieTextfield,nazwiskoTextfield);
        vBox2.getChildren().addAll(datePicker,submit);
        vBox3.getChildren().addAll(add,informacja);
        panelDoPol.getChildren().addAll(vBox1,vBox2,vBox3);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PacjenciUtil pacjenciUtil = new PacjenciUtil();
                listaPacjentow.clear();
                if(imieTextfield.getText().isEmpty() && nazwiskoTextfield.getText().isEmpty()){
                    tab.getItems().clear();

                    for (Pacjenci pacejnt:
                            pacjenciUtil.wszyscyPacjenci()) {
                        tab.getItems().add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                        listaPacjentow.add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));

                    }
                }
                else if(nazwiskoTextfield.getText().isEmpty()){
                    tab.getItems().clear();
                    for (Pacjenci pacejnt:
                         pacjenciUtil.szukajPoImieniu(imieTextfield.getText())) {
                        tab.getItems().add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                        listaPacjentow.add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                    }
                } else if (imieTextfield.getText().isEmpty()){
                    tab.getItems().clear();
                    for (Pacjenci pacejnt:
                            pacjenciUtil.szukajPoNazwisku(nazwiskoTextfield.getText())) {
                        tab.getItems().add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                        listaPacjentow.add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                    }
                } else if(!imieTextfield.getText().isEmpty() && !nazwiskoTextfield.getText().isEmpty()){
                    tab.getItems().clear();
                    for (Pacjenci pacejnt:
                            pacjenciUtil.szukajPoImieniuINazwiskuLista(imieTextfield.getText(),nazwiskoTextfield.getText())) {
                        tab.getItems().add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                        listaPacjentow.add(new PacjentDoTabeli(pacejnt.getName(),pacejnt.getSurname(),pacejnt.getPesel()));
                    }
                }
            }
        });

        PacjenciUtil pacjenciUtil = new PacjenciUtil();
        imieCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        PESELCol.setCellValueFactory(new PropertyValueFactory<>("pesel"));

        TableColumn actionCol = new TableColumn();

        actionCol.setCellValueFactory(new PropertyValueFactory<>("guzikWybierania"));

        Callback<TableColumn<PacjentDoTabeli, Button>, TableCell<PacjentDoTabeli, Button>> cellFactory
                = //
                new Callback<TableColumn<PacjentDoTabeli, Button>, TableCell<PacjentDoTabeli, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<PacjentDoTabeli, Button> param) {
                        final TableCell<PacjentDoTabeli, Button> cell = new TableCell<PacjentDoTabeli, Button>() {

                            final Button btn = new Button("Wybierz");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        tab.refresh();
                                        btn.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                                        btn.setStyle("-fx-background-color: #9E7BB5; -fx-text-fill: #710193;");
                                        wybrany_pacjent = getIndex();
                                        System.out.println(wybrany_pacjent);
                                        add.setVisible(true);
                                        imieTextfield.setText(listaPacjentow.get(wybrany_pacjent).getName());
                                        nazwiskoTextfield.setText(listaPacjentow.get(wybrany_pacjent).getSurname());


                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);

        tab.getColumns().add(actionCol);

        listaPacjentow = new ArrayList<>();
        for (Pacjenci pacjent:
             pacjenciUtil.wszyscyPacjenci()) {
            listaPacjentow.add(new PacjentDoTabeli(pacjent.getName(),pacjent.getSurname(),pacjent.getPesel()));

        }

        tab.getItems().addAll(listaPacjentow);

    }
}
