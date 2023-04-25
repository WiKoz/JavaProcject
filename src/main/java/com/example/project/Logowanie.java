package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Logowanie {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button cofnij;

    @FXML
    private PasswordField haslo;

    @FXML
    private TextField login;

    @FXML
    private Button zaloguj;
    @FXML
    private Button close;
    @FXML
    private Text bledneDane;

    @FXML
    void Cofanie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Zalogowanie(ActionEvent event) throws IOException {
        bledneDane.setVisible(false);
        LekarzeUtil lekarzeUtil = new LekarzeUtil();
        Lekarze logujacySieLekarz = lekarzeUtil.szukaj1(login.getText());
        PacjenciUtil pacjenciUtil = new PacjenciUtil();
        Pacjenci logujacySiePacjent = pacjenciUtil.szukajPoLoginie(login.getText());

        if(logujacySieLekarz != null)
        {
            if (logujacySieLekarz.getHaslo().equals(haslo.getText())){
                bledneDane.setVisible(false);
                root = FXMLLoader.load(getClass().getResource("FXML/PologowanieL.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                AktualneDane.getInstance().lekarz = logujacySieLekarz;
            }else {
                bledneDane.setVisible(true);
                bledneDane.setFill(Color.RED);
            }
        } else if (logujacySiePacjent != null) {
            if (logujacySiePacjent.getHaslo().equals(haslo.getText())){
                bledneDane.setVisible(false);
                root = FXMLLoader.load(getClass().getResource("FXML/PoLog.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                AktualneDane.getInstance().pacjent = logujacySiePacjent;
            }else {
                bledneDane.setVisible(true);
                bledneDane.setFill(Color.RED);
            }
        } else {
            bledneDane.setVisible(true);
            bledneDane.setFill(Color.RED);
        }
    }
    @FXML
    void Zamknij(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
