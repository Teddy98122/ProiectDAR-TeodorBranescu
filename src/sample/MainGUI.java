package sample;

import com.sun.org.apache.bcel.internal.generic.FieldGen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MainGUI extends LogIn {


    @FXML
    private Button BackButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextArea DiaryEntry;

    public void goBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("DSelect.fxml"));
        stage.setTitle("Meniu jurnal");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public boolean insertDB(String Content, String FK_User_Name, String SALT) {
        String sql = "INSERT INTO Diary(Content,FK_User_Name,SALT) VALUES(?,?,?)";

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Content);
            pstmt.setString(2, FK_User_Name);
            pstmt.setString(3, SALT);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addDiary(ActionEvent actionEvent) throws Exception {
        String message = DiaryEntry.getText();
        String passwd = passwd_ret;
        String id_UUID = UUID.randomUUID().toString();
        String message_crypt = AES_Crypt.encrypt(message,passwd+id_UUID);
        String salt_crypt = AES_Crypt.encrypt(id_UUID,passwd);
        if(insertDB(message_crypt,nume_ret,salt_crypt) == true){
            System.out.println("Datele au fost inserate cu succes !");
            Succes suc = new Succes();
            suc.launchSucces();
        }
        else{
            System.out.println("Datele nu au fost inserate cu succes !");
            Eroare err = new Eroare();
            err.launchError();
        }
    }
}
