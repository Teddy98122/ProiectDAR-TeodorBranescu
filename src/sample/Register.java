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

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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

    public boolean insertDB(String Nume, String Parola) {
        String sql = "INSERT INTO User(Name,Password) VALUES(?,?)";

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Nume);
            pstmt.setString(2, Parola);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void register(ActionEvent actionEvent) {
        String Nume = NumeRegister.getText();
        String Parola = ParolaRegister.getText();
        String ParolaCon = ParolaConfirmare.getText();
        if (Parola.equals(ParolaCon)) {
            if (insertDB(Nume, Parola) == true) {
                System.out.println("Inserarea a fost efectuata cu succes !");
            } else {
                System.out.println("A aparut o problema la inserarea datelor !");
            }
        }
        else{
            System.out.println("A aparut o problema cu parola !");
        }
    }
}
