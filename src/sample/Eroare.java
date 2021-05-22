package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Eroare {

    @FXML
    private Button button;

    private Stage errCompStage=new Stage();

    public void launchError() throws Exception
    {

        Parent errCompScene = FXMLLoader.load(getClass().getResource("Eroare.fxml"));
        errCompStage.setTitle("Error");
        errCompStage.setScene(new Scene(errCompScene));
        errCompStage.show();
        errCompStage.setAlwaysOnTop(true);
    }

    public void okButton(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
