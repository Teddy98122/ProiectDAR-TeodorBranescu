package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.krb5.internal.crypto.Aes128;

import java.io.Console;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;


public class Register {


    @FXML
    private Button BackButton;

    @FXML
    private TextField NumeRegister;

    @FXML
    private PasswordField ParolaRegister;

    @FXML
    private PasswordField ParolaConfirmare;


    public void goBack(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("Main");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    private Connection connectDB() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean insertDB(String Nume, String Parola, String salt) {
        String sql = "INSERT INTO User(Name,Password,SALT) VALUES(?,?,?)";

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Nume);
            pstmt.setString(2, Parola);
            pstmt.setString(3, salt);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void register(ActionEvent actionEvent) throws Exception {

        String id_UUID = UUID.randomUUID().toString();

        String Nume = NumeRegister.getText();
        String Parola = ParolaRegister.getText();
        String ParolaCon = ParolaConfirmare.getText();

        String secretKey = Parola+id_UUID;

        String criptare_parola = AES_Crypt.encrypt(Parola,secretKey);
        String criptare_uuid = AES_Crypt.encrypt(id_UUID,Parola);
        if (Parola.equals(ParolaCon)) {
            if (insertDB(Nume, criptare_parola, criptare_uuid) == true) {
                System.out.println("Inserarea a fost efectuata cu succes !");
                Succes suc = new Succes();
                suc.launchSucces();
            } else {
                System.out.println("A aparut o problema la inserarea datelor !");
                Eroare err = new Eroare();
                err.launchError();
            }
        }
        else{
            System.out.println("A aparut o problema cu parola !");
            Eroare err = new Eroare();
            err.launchError();
        }
    }
}
