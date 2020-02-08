/**
 * Sample Skeleton for 'KlientWindow.fxml' Controller Class
 */


import java.net.URL;
import java.sql.ResultSet;
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
    public Wszystkie_WypozyczeniaDAO wszystkie_wypozyczeniaDAO;
    private boolean start = true;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="wypozyczalniaTableView"
    private TableView wypozyczalniaTableView; // Value injected by FXMLLoader

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
    private TableView mojeWypozyczeniaTableView; // Value injected by FXMLLoader

    @FXML // fx:id="nrResjestracyjnyCol1"
    private TableColumn<Wszystkie_Wypozyczenia, String> nrResjestracyjnyCol1; // Value injected by FXMLLoader

    @FXML // fx:id="markaCol1"
    private TableColumn<Wszystkie_Wypozyczenia, String> markaCol1; // Value injected by FXMLLoader

    @FXML // fx:id="nazwaCol1"
    private TableColumn<Wszystkie_Wypozyczenia, String> nazwaCol1; // Value injected by FXMLLoader

    @FXML // fx:id="przejchanyDystansCol"
    private TableColumn<Wszystkie_Wypozyczenia, Float> przejchanyDystansCol; // Value injected by FXMLLoader

    @FXML // fx:id="kwotaDoZaplatyCol"
    private TableColumn<Wszystkie_Wypozyczenia, Float> kwotaDoZaplatyCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataWypozyczeniaCol"
    private TableColumn<Wszystkie_Wypozyczenia, Date> dataWypozyczeniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="dataOddaniaCol"
    private TableColumn<Wszystkie_Wypozyczenia, Date> dataOddaniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="statusCol"
    private TableColumn<Wszystkie_Wypozyczenia, String> statusCol; // Value injected by FXMLLoader

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
        String rejestracja = nrRejestracyjnyTextField.getText();
        ObservableList<Wszystkie_Wypozyczenia> wszystkieWypozyczenias = mojeWypozyczeniaTableView.getItems();
        for (int i = 0; i < wszystkieWypozyczenias.size(); i++) {
            if (wszystkieWypozyczenias.get(i).getStatus_wypozyczenia().contains("trak") && wszystkieWypozyczenias.get(i).getNr_rejestracyjny().equals(rejestracja)) {
                String selectStmt = "call oddaj ('" + rejestracja + "','" + login + "');";
                consoleTextArea.setText("Auto oddano :) ");
                try {
                    dbUtil.dbExecuteUpdate(selectStmt);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                if (i + 1 == wszystkieWypozyczenias.size())
                    consoleTextArea.setText("Auto o podanym nr rejestracyjnym nie jest aktualnie przez Ciebie wypozyczone ");
            }
        }


        odswiezTabele();
    }

    @FXML
    void wypozyczBtnClicked(ActionEvent event) {

        String rejestracja = nrRejestracyjnyTextField.getText();
        ObservableList<Wypozyczalnia> wypozyczalnias = wypozyczalniaTableView.getItems();
        for (int i = 0; i < wypozyczalnias.size(); i++) {


            if (wypozyczalnias.get(i).getNr_rejestracyjny().equals(rejestracja)) {
                String selectStmt = "call wypozycz ('" + rejestracja + "','" + login + "');";
                consoleTextArea.setText("Auto wypozyczono :) ");
                try {
                    dbUtil.dbExecuteUpdate(selectStmt);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                if (i + 1 == wypozyczalnias.size())
                    consoleTextArea.setText("Auto nie jest mozliwe do wypozyczenia");
            }
        }
        odswiezTabele();

    }

    @FXML
    void onMouseEntered(MouseEvent event) throws SQLException, ClassNotFoundException {
        if (start) {
            odswiezTabele();
        }
        start = false;
    }

    private void populateWypozyczalnia(ObservableList<Wypozyczalnia> wObservableList) {
        wypozyczalniaTableView.setItems(wObservableList);
    }

    private void populateWszystkie_Wypozyczenia(ObservableList<Wszystkie_Wypozyczenia> wObservableList) {
        mojeWypozyczeniaTableView.setItems(wObservableList);
    }

    private void odswiezTabele() {
        ObservableList<Wypozyczalnia> wypozyczalniaObservableList = null;
        try {
            wypozyczalniaObservableList = wypozyczalniaDAO.pokazMozliweWypozyczenia();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateWypozyczalnia(wypozyczalniaObservableList);

        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = null;
        try {
            wszystkie_wypozyczeniaObservableList = wszystkie_wypozyczeniaDAO.pokaz_moje_wypozyczenia();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateWszystkie_Wypozyczenia(wszystkie_wypozyczeniaObservableList);
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
        assert wypozyczalniaLabel != null : "fx:id=\"wypozyczalniaLabel\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert mojeWypozyczeniaTableView != null : "fx:id=\"mojeWypozyczeniaTableView\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nrResjestracyjnyCol1 != null : "fx:id=\"nrResjestracyjnyCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert markaCol1 != null : "fx:id=\"markaCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nazwaCol1 != null : "fx:id=\"nazwaCol1\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert przejchanyDystansCol != null : "fx:id=\"przejchanyDystansCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert kwotaDoZaplatyCol != null : "fx:id=\"kwotaDoZaplatyCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert dataWypozyczeniaCol != null : "fx:id=\"dataWypozyczeniaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert dataOddaniaCol != null : "fx:id=\"dataOddaniaCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert mojeWypozyczeniaLabel != null : "fx:id=\"mojeWypozyczeniaLabel\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert nrRejestracyjnyTextField != null : "fx:id=\"nrRejestracyjnyTextField\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert wypozyczBtn != null : "fx:id=\"wypozyczBtn\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert oddajBtn != null : "fx:id=\"oddajBtn\" was not injected: check your FXML file 'KlientWindow.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'KlientWindow.fxml'.";


    }


}
