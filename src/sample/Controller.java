package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;


import java.awt.*;


public class Controller {
    @FXML
    private Button RegisterButton;

    public void registerPage(ActionEvent actionEvent) throws Exception {

       // Stage stage = (Stage) RegisterButton.getScene().getWindow();
       // stage.close();
       // Stage s = Main.getPrimaryStage();
       // s.close();
        //Stage stage = (Stage) changeRepoButton.getScene().getWindow();
       // stage.close();
        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage.setTitle("Creare cont nou");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    public void LogInPage(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("MainPage");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void CloseButtonLogIn(ActionEvent actionEvent) {
    }
}
