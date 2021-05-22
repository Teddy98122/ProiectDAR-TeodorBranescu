package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.DISCARDING;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class DiaryList implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Diary> TableDiary;

    @FXML
    private TableColumn<Diary,String> number;

    @FXML
    private TableColumn<Diary,String> content;

    private ObservableList<Diary> list=FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Diary> test = new ArrayList<Diary>();
        test.add(new Diary("hey","hey"));
        test.add(new Diary("test","test"));
        test.add(new Diary("test","test"));



        ObservableList<Diary> DiaryObs = FXCollections.observableArrayList(test);

        number.setCellValueFactory(new PropertyValueFactory<Diary, String>("number"));
        content.setCellValueFactory(new PropertyValueFactory<Diary, String>("content"));

        //TableDiary.setItems(DiaryObs);
        TableDiary.getItems().setAll(DiaryObs);


        System.out.println(DiaryObs.size());


    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("DSelect.fxml"));
        stage.setTitle("Meniu alegere");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
