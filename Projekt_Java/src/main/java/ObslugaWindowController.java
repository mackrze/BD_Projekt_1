/**
 * Sample Skeleton for 'ObslugaWindow.fxml' Controller Class
 */

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ObslugaWindowController {

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

    @FXML // fx:id="mozliwoscWypozyczeniaCol"
    private TableColumn<?, ?> mozliwoscWypozyczeniaCol; // Value injected by FXMLLoader

    @FXML // fx:id="mojeWypozyczeniaTableView"
    private TableView mojeWypozyczeniaTableView; // Value injected by FXMLLoader

    @FXML // fx:id="nrResjestracyjnyCol1"
    private TableColumn<Wszystkie_Wypozyczenia, String> nrResjestracyjnyCol1; // Value injected by FXMLLoader

    @FXML // fx:id="klientCol"
    private TableColumn<?, ?> klientCol; // Value injected by FXMLLoader

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

    @FXML // fx:id="nrRejestracyjnyTextField"
    private TextField nrRejestracyjnyTextField; // Value injected by FXMLLoader

    @FXML // fx:id="autaLabel"
    private Label autaLabel; // Value injected by FXMLLoader


    @FXML // fx:id="wszystkieWypozyczeniaLabel"
    private Label wszystkieWypozyczeniaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="naladujBtn"
    private Button naladujBtn; // Value injected by FXMLLoader

    @FXML // fx:id="pokazUtargzDniaBtn"
    private Button pokazUtargzDniaBtn; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="pokazHistorieAutaBtn"
    private Button pokazHistorieAutaBtn; // Value injected by FXMLLoader

    @FXML // fx:id="dataTextField"
    private TextField dataTextField; // Value injected by FXMLLoader

    @FXML // fx:id="datePicker"
    private DatePicker datePicker; // Value injected by FXMLLoader

    @FXML // fx:id="odswiezTabeleBtn"
    private Button odswiezTabeleBtn; // Value injected by FXMLLoader

    @FXML // fx:id="wylogujBtn"
    private Button wylogujBtn; // Value injected by FXMLLoader

    @FXML
    void wylogujClicked(ActionEvent event) {
        Stage stage = (Stage) wylogujBtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    void odswiezTabeleClicked(ActionEvent event) {
        odswiezTabele();
    }

    @FXML
    void naladujBtnClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String rejestracja = nrRejestracyjnyTextField.getText();
        String selectStmt = "call naladuj ('" + rejestracja + "');";
        dbUtil.dbExecuteUpdate(selectStmt);
        ObservableList<Wypozyczalnia> wypozyczalniaObservableList = wypozyczalniaDAO.pokazAuta();
        populateWypozyczalnia(wypozyczalniaObservableList);
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
            wypozyczalniaObservableList = wypozyczalniaDAO.pokazAuta();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateWypozyczalnia(wypozyczalniaObservableList);

        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = null;
        try {
            wszystkie_wypozyczeniaObservableList = wszystkie_wypozyczeniaDAO.pokaz_wszystkie_wypozyczenia();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateWszystkie_Wypozyczenia(wszystkie_wypozyczeniaObservableList);
    }

    @FXML
    void pokazHistorieAutaClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String rejestracja = nrRejestracyjnyTextField.getText();
        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = wszystkie_wypozyczeniaDAO.pokaz_wszystkie_wypozyczenia_danego_auta(rejestracja);
        populateWszystkie_Wypozyczenia(wszystkie_wypozyczeniaObservableList);
    }

    @FXML
    void pokazUtargzDniaBtnClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date dateJava = java.util.Date.from(instant);
        java.sql.Date dataSQL = new java.sql.Date(dateJava.getTime());

        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = wszystkie_wypozyczeniaDAO.pokaz_wszystkie_wypozyczenia_zDnia_i_utarg(dataSQL);
        float utarg = 0;
        for (int i = 0; i < wszystkie_wypozyczeniaObservableList.size(); i++) {
            utarg += wszystkie_wypozyczeniaObservableList.get(i).getKwota();
        }
        utarg = Math.round(utarg * 100) / 100;
        consoleTextArea.setText("Utarg z dnia: " + dataSQL + " wyniosi: " + utarg);
        populateWszystkie_Wypozyczenia(wszystkie_wypozyczeniaObservableList);

    }


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wypozyczalniaTableView != null : "fx:id=\"wypozyczalniaTableView\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert nrResjestracyjnyCol != null : "fx:id=\"nrResjestracyjnyCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert markaCol != null : "fx:id=\"markaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert nazwaCol != null : "fx:id=\"nazwaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert BateriaCol != null : "fx:id=\"BateriaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert odlegloscNaPelnymLadowaniuCol != null : "fx:id=\"odlegloscNaPelnymLadowaniuCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert stanLicznikaCol != null : "fx:id=\"stanLicznikaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert dataKoncaUbezpieczeniaCol != null : "fx:id=\"dataKoncaUbezpieczeniaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert mozliwoscWypozyczeniaCol != null : "fx:id=\"mozliwoscWypozyczeniaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert autaLabel != null : "fx:id=\"autaLabel\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert mojeWypozyczeniaTableView != null : "fx:id=\"mojeWypozyczeniaTableView\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert nrResjestracyjnyCol1 != null : "fx:id=\"nrResjestracyjnyCol1\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert klientCol != null : "fx:id=\"klientCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert markaCol1 != null : "fx:id=\"markaCol1\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert nazwaCol1 != null : "fx:id=\"nazwaCol1\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert przejchanyDystansCol != null : "fx:id=\"przejchanyDystansCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert kwotaDoZaplatyCol != null : "fx:id=\"kwotaDoZaplatyCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert dataWypozyczeniaCol != null : "fx:id=\"dataWypozyczeniaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert dataOddaniaCol != null : "fx:id=\"dataOddaniaCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert wszystkieWypozyczeniaLabel != null : "fx:id=\"wszystkieWypozyczeniaLabel\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert nrRejestracyjnyTextField != null : "fx:id=\"nrRejestracyjnyTextField\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert naladujBtn != null : "fx:id=\"naladujBtn\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert pokazUtargzDniaBtn != null : "fx:id=\"pokazUtargzDniaBtn\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert pokazHistorieAutaBtn != null : "fx:id=\"pokazHistorieAutaBtn\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert odswiezTabeleBtn != null : "fx:id=\"odswiezTabeleBtn\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";
        assert wylogujBtn != null : "fx:id=\"wylogujBtn\" was not injected: check your FXML file 'ObslugaWindow.fxml'.";


    }
}
