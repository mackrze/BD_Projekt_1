/**
 * Sample Skeleton for 'KlientWindow.fxml' Controller Class
 */


import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class KlientWindowController {

    public DBUtil dbUtil;
    public String login;
    public WypozyczalniaDAO wypozyczalniaDAO;
    private boolean start = true;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="wypozyczalniaTableView"
    private TableView<Wypozyczalnia> wypozyczalniaTableView; // Value injected by FXMLLoader

    @FXML // fx:id="nrResjestracyjnyCol"
    private TableColumn<Wypozyczalnia, String> nrResjestracyjnyCol; // Value injected by FXMLLoader

    @FXML // fx:id="markaCol"
    private TableColumn<Wypozyczalnia, String> markaCol; // Value injected by FXMLLoader

    @FXML // fx:id="nazwaCol"
    private TableColumn<Wypozyczalnia, String> nazwaCol; // Value injected by FXMLLoader

    @FXML // fx:id="BateriaCol"
    private TableColumn<Wypozyczalnia, Float> BateriaCol; // Value injected by FXMLLoader

    @FXML // fx:id="odlegloscNaPelnymLadowaniuCol"
    private TableColumn<Wypozyczalnia, Float> odlegloscNaPelnymLadowaniuCol; // Value injected by FXMLLoader

    @FXML // fx:id="stanLicznikaCol"
    private TableColumn<Wypozyczalnia, Float> stanLicznikaCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataKoncaUbezpieczeniaCol"
    private TableColumn<Wypozyczalnia, Date> dataKoncaUbezpieczeniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="mozliwosc_wypozyczeniaCol"
    private TableColumn<Wypozyczalnia, Integer> mozliwosc_wypozyczeniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="wypozyczalniaLabel"
    private Label wypozyczalniaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="mojeWypozyczeniaTableView"
    private TableView<?> mojeWypozyczeniaTableView; // Value injected by FXMLLoader

    @FXML // fx:id="nrResjestracyjnyCol1"
    private TableColumn<?, ?> nrResjestracyjnyCol1; // Value injected by FXMLLoader

    @FXML // fx:id="markaCol1"
    private TableColumn<?, ?> markaCol1; // Value injected by FXMLLoader

    @FXML // fx:id="nazwaCol1"
    private TableColumn<?, ?> nazwaCol1; // Value injected by FXMLLoader

    @FXML // fx:id="statusCol"
    private TableColumn<?, ?> statusCol; // Value injected by FXMLLoader

    @FXML // fx:id="mojeWypozyczeniaLabel"
    private Label mojeWypozyczeniaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nrRejestracyjnyTextField"
    private TextField nrRejestracyjnyTextField; // Value injected by FXMLLoader

    @FXML // fx:id="wypozyczBtn"
    private Button wypozyczBtn; // Value injected by FXMLLoader

    @FXML // fx:id="oddajBtn"
    private Button oddajBtn; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    @FXML
    void oddajBtnClicked(ActionEvent event) {

    }

    @FXML
    void wypozyczBtnClicked(ActionEvent event) {
        consoleTextArea.setText("test: " + login);
        wypozyczalniaTableView.getItems().clear();
        try {
            ObservableList<Wypozyczalnia> wypozyczalniaObservableList = wypozyczalniaDAO.pokazMozliweWypozyczenia();
            populateWypozyczalnia(wypozyczalniaObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseEntered(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (start) {
            wypozyczalniaTableView.getItems().clear();

            ObservableList<Wypozyczalnia> wypozyczalniaObservableList = wypozyczalniaDAO.pokazMozliweWypozyczenia();

            System.out.println(wypozyczalniaObservableList.get(0).getNr_rejestracyjny());
            System.out.println(wypozyczalniaObservableList.get(1).getNr_rejestracyjny());
            System.out.println(wypozyczalniaObservableList.get(2).getNr_rejestracyjny());
           // populateWypozyczalnia(wypozyczalniaObservableList);
            wypozyczalniaTableView.getItems().addAll(wypozyczalniaObservableList);
            start = false;

            // mam observable z rekoradmi bo widniejda ale nie wyswietla ich w table view

        }
    }

    private void populateWypozyczalnia(ObservableList<Wypozyczalnia> wypozyczalniaObservableList) {
        wypozyczalniaTableView.setItems(wypozyczalniaObservableList);
    }


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wypozyczalniaTableView != null : "fx:id=\"wypozyczalniaTableView\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nrResjestracyjnyCol != null : "fx:id=\"nrResjestracyjnyCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert markaCol != null : "fx:id=\"markaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nazwaCol != null : "fx:id=\"nazwaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert BateriaCol != null : "fx:id=\"BateriaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert odlegloscNaPelnymLadowaniuCol != null : "fx:id=\"odlegloscNaPelnymLadowaniuCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert stanLicznikaCol != null : "fx:id=\"stanLicznikaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert dataKoncaUbezpieczeniaCol != null : "fx:id=\"dataKoncaUbezpieczeniaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert mozliwosc_wypozyczeniaCol != null : "fx:id=\"mozliwosc_wypozyczeniaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert wypozyczalniaLabel != null : "fx:id=\"wypozyczalniaLabel\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert mojeWypozyczeniaTableView != null : "fx:id=\"mojeWypozyczeniaTableView\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nrResjestracyjnyCol1 != null : "fx:id=\"nrResjestracyjnyCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert markaCol1 != null : "fx:id=\"markaCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nazwaCol1 != null : "fx:id=\"nazwaCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert mojeWypozyczeniaLabel != null : "fx:id=\"mojeWypozyczeniaLabel\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nrRejestracyjnyTextField != null : "fx:id=\"nrRejestracyjnyTextField\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert wypozyczBtn != null : "fx:id=\"wypozyczBtn\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert oddajBtn != null : "fx:id=\"oddajBtn\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'KlientWindow.fxml'.";


    }


}
