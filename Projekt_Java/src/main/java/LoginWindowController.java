/**
 * Sample Skeleton for 'LoginWindow.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginWindowController {


    private DBUtil dbUtil;
    private String login;
    private WypozyczalniaDAO wypozyczalniaDAO;
    private Wszystkie_WypozyczeniaDAO wszystkie_wypozyczeniaDAO;



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="loginTextField"
    private TextField loginTextField; // Value injected by FXMLLoader

    @FXML // fx:id="zalogujBtn"
    private Button zalogujBtn; // Value injected by FXMLLoader

    @FXML
    private TextArea connectionTextArea;

    @FXML
    void zalogujBtnClicked(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Kliknieto zaloguj");

        dbUtil = new DBUtil(loginTextField.getText(), passwordTextField.getText(), connectionTextArea);
        dbUtil.dbConnect();
        connectionTextArea.appendText("Zalogowano");
        login = loginTextField.getText();
        wypozyczalniaDAO = new WypozyczalniaDAO(login, dbUtil);
        wszystkie_wypozyczeniaDAO = new Wszystkie_WypozyczeniaDAO(login, dbUtil);

        String logujacy = "admin";

        String selectStmt = "select id_klient from klienci where id_klient = '" + login + "';";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        while (resultSet.next()) {
            logujacy = resultSet.getString("id_klient");
        }
        //System.out.println(logujacy);

        if (!logujacy.equals("admin")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KlientWindow.fxml"));


            Parent root = loader.load();


            //Get controller of modal window
            KlientWindowController klientWindowController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Konto klienta");
            stage.initModality(Modality.WINDOW_MODAL);
            klientWindowController.dbUtil = this.dbUtil;
            klientWindowController.login = this.login;
            klientWindowController.wypozyczalniaDAO = this.wypozyczalniaDAO;
            klientWindowController.wszystkie_wypozyczeniaDAO = this.wszystkie_wypozyczeniaDAO;
            stage.show();
        } else { //osoba zalogowana nie jest klientem a ma dostep do bazy wiec to admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ObslugaWindow.fxml"));
            Parent root = loader.load();

            ObslugaWindowController obslugaWindowController = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Konto obsługi");
            stage.initModality(Modality.WINDOW_MODAL);
            obslugaWindowController.dbUtil = this.dbUtil;
            obslugaWindowController.login = this.login;
            obslugaWindowController.wypozyczalniaDAO = this.wypozyczalniaDAO;
            obslugaWindowController.wszystkie_wypozyczeniaDAO = this.wszystkie_wypozyczeniaDAO;
            stage.show();

        }

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert loginTextField != null : "fx:id=\"loginTextField\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert zalogujBtn != null : "fx:id=\"zalogujBtn\" was not injected: check your FXML file 'LoginWindow.fxml'.";

    }
}
