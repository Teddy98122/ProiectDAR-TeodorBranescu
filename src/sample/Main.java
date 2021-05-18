package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("LogIn page");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createTheTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS User\n" +
                "(\n" +
                "Pk_User_Id INTEGER PRIMARY KEY,\n" +
                "Name TEXT,\n" +
                "Password TEXT\n" +
                ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS Diary\n" +
                "(\n" +
                "Pk_Entry_Id INTEGER PRIMARY KEY,\n" +
                "Content TEXT,\n" +
                "FK_User_ID INTEGER,\n"+
                "FOREIGN KEY(FK_User_Id) REFERENCES User(PK_User_Id)\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.execute(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
        createTheTables();
        launch(args);
    }
}
