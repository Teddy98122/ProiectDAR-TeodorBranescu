package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Succes {

    @FXML
    private Button button;

    private Stage sucCompStage=new Stage();

    public void launchSucces() throws Exception
    {

        Parent errCompScene = FXMLLoader.load(getClass().getResource("Succes.fxml"));
        sucCompStage.setTitle("Succes");
        sucCompStage.setScene(new Scene(errCompScene));
        sucCompStage.show();
        sucCompStage.setAlwaysOnTop(true);
    }

    public void okButton(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
