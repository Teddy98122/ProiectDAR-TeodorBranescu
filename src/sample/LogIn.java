package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;


import javax.xml.soap.Text;
import java.sql.*;


public class LogIn {
    @FXML
    private Button RegisterButton;

    @FXML
    protected TextField NumeLogIn;

    @FXML
    protected PasswordField ParolaLogIn;

    protected static String nume_ret;

    protected static String passwd_ret;

    protected void name_ret()
    {
        this.nume_ret = NumeLogIn.getText();
    }

    protected void password_ret()
    {
        this.passwd_ret = ParolaLogIn.getText();
    }

    protected Connection connectDB() {
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

    public void registerPage(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage.setTitle("Creare cont nou");
        stage.setScene(new Scene(scene));
        stage.resizableProperty().setValue(Boolean.FALSE);
        //addAbbStage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    public void MainPage(ActionEvent actionEvent) throws Exception {
        name_ret();
        String sql = "SELECT Name, Password FROM User WHERE Name == ?";

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            String name = NumeLogIn.getText();
            String passwd = ParolaLogIn.getText();
            pstmt.setString(1,name);
            //
            ResultSet rs  = pstmt.executeQuery();

            String result_name = rs.getString("Name");
            String result_password = rs.getString("Password");

            if(result_name.equals(name)&&result_password.equals(passwd))
            {
                Stage stage = (Stage) RegisterButton.getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("DSelect.fxml"));
                stage.setTitle("MainSelect");
                stage.setScene(new Scene(scene));
                stage.resizableProperty().setValue(Boolean.FALSE);
                //addAbbStage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Eroare err = new Eroare();
            err.launchError();
        }

    }

    public void CloseButtonLogIn(ActionEvent actionEvent) {
    }
}
