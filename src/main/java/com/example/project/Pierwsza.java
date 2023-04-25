package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Pierwsza {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button zaloguj;
    @FXML
    private Button zarejestruj;
    @FXML
    private Button close;

    @FXML
    void Logowanie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/LogIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Rejestracja(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXML/SignUp.fxml"));
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

}
