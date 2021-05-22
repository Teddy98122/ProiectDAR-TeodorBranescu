package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DSelect extends LogIn {


    @FXML
    private Button ButtonReg;

    @FXML
    private Button showDiary;

    @FXML
    private Button BackButton;


    public void launchReg(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ButtonReg.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Introducere in jurnal");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void launchShow(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) showDiary.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("DiaryList.fxml"));
        stage.setTitle("Lista inregistrari");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void backToRegister(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Inregistrare");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
