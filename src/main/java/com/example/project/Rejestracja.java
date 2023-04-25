package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Rejestracja {
    private LekarzeUtil lekarzeUtil = new LekarzeUtil();
    private PacjenciUtil pacjenciUtil = new PacjenciUtil();

    private WizytyUtil wizytyUtil = new WizytyUtil();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private RadioButton checkLekarz = new RadioButton();

    @FXML
    private RadioButton checkPacjent = new RadioButton();

    @FXML
    private Button cofnij;

    @FXML
    private PasswordField haslo;

    @FXML
    private TextField imie;

    @FXML
    private TextField login;

    @FXML
    private TextField nazwisko;

    @FXML
    private PasswordField powtorzHaslo;

    @FXML
    private Button close;

    @FXML
    private Button rejestr;
    @FXML
    private Text bledneDane;
    @FXML
    private TextField pesel;
    @FXML
    private TextField weryfikacja;
    private ToggleGroup funkcja = new ToggleGroup();

    @FXML
    public void initialize() {
        checkLekarz.setToggleGroup(funkcja);
        checkPacjent.setToggleGroup(funkcja);
        pesel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    pesel.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void Cofanie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Zamknij(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Rejestrowanie(ActionEvent event) throws IOException {

        PacjenciUtil pacjenciUtil1 = new PacjenciUtil();
        LekarzeUtil lekarzeUtil1 = new LekarzeUtil();
        bledneDane.setText("nieprawidlowe dane");
        bledneDane.setFill(Color.RED);
        weryfikacja.setVisible(false);
        bledneDane.setVisible(false);
        if (checkLekarz.isSelected()){
            if (haslo.getText().equals(powtorzHaslo.getText())){
                if (pesel.getText().length()==11){
                    if(haslo.getText().length() >= 8){
                        if(lekarzeUtil1.szukajPoPeselu(pesel.getText()) == null && pacjenciUtil1.szukajPoPeselu(pesel.getText()) == null){
                            if(pacjenciUtil1.szukajPoLoginie(login.getText()) == null && lekarzeUtil1.szukaj1(login.getText()) == null){
                                weryfikacja.setVisible(true);
                                if(weryfikacja.getText().equals("paracetamol")){
                                    lekarzeUtil.dodaj(new Lekarze(null,imie.getText(),nazwisko.getText(),pesel.getText(),login.getText(),haslo.getText()));
                                    root = FXMLLoader.load(getClass().getResource("FXML/LogIn.fxml"));
                                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                }
                                else{
                                    bledneDane.setText("Proszę wprowadzić poprawny kod");
                                    bledneDane.setVisible(true);
                                }
                            } else {
                                bledneDane.setVisible(true);
                                bledneDane.setText("Login zajęty");
                            }

                        } else {
                            bledneDane.setVisible(true);
                            bledneDane.setText("Pesel zajęty");
                        }

                    } else {
                        bledneDane.setVisible(true);
                        bledneDane.setText("Hasło jest za słabe");
                    }

                }
                else{

                    bledneDane.setVisible(true);
                    bledneDane.setText("Pesel nie ma 11 cyfr");
                }
            }
            else{
                bledneDane.setVisible(true);
                bledneDane.setText("Hasła się różnią");

            }
        }
        if (checkPacjent.isSelected()){
            weryfikacja.setVisible(false);
            if (haslo.getText().equals(powtorzHaslo.getText())){
                if (pesel.getText().length()==11){
                    if(haslo.getText().length() >= 8){
                        if(lekarzeUtil1.szukajPoPeselu(pesel.getText()) == null && pacjenciUtil1.szukajPoPeselu(pesel.getText()) == null){
                            if(pacjenciUtil1.szukajPoLoginie(login.getText()) == null && lekarzeUtil1.szukaj1(login.getText()) == null){
                                pacjenciUtil.dodaj(new Pacjenci(null,imie.getText(),nazwisko.getText(),pesel.getText(),login.getText(),haslo.getText()));
                                root = FXMLLoader.load(getClass().getResource("FXML/LogIn.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } else {
                                bledneDane.setVisible(true);
                                bledneDane.setText("login zajęty");
                            }

                        } else {
                            bledneDane.setVisible(true);
                            bledneDane.setText("pesel zajęty");
                        }

                    } else {
                        bledneDane.setVisible(true);
                        bledneDane.setText("haslo jest za slabe");
                    }

                }
                else{
                    bledneDane.setVisible(true);
                    bledneDane.setText("pesel nie ma 11 cyfr");
                }
            }
            else{
                bledneDane.setVisible(true);
                bledneDane.setText("Hasła się różnią");
            }
        }
    }

}
