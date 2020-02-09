import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Wszystkie_WypozyczeniaDAO {

    private String login;
    private DBUtil dbUtil;

    public Wszystkie_WypozyczeniaDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
    }

    public ObservableList<Wszystkie_Wypozyczenia> pokaz_moje_wypozyczenia() throws SQLException, ClassNotFoundException {
        String selectStmt = "call widok_moje_wypozyczenia ('" + login + "');";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = getWszystkie_wypozyczenia(resultSet);
        return wszystkie_wypozyczeniaObservableList;


    }
    public ObservableList<Wszystkie_Wypozyczenia> pokaz_wszystkie_wypozyczenia() throws SQLException, ClassNotFoundException {
        String selectStmt = "select * from wszystkie_wypozyczenia ;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = getWszystkie_wypozyczenia(resultSet);
        return wszystkie_wypozyczeniaObservableList;


    }

    public ObservableList<Wszystkie_Wypozyczenia> pokaz_wszystkie_wypozyczenia_danego_auta(String rejestracja) throws SQLException, ClassNotFoundException {
        String selectStmt = "select * from wszystkie_wypozyczenia where nr_rejestracyjny = '"+rejestracja+"' ;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = getWszystkie_wypozyczenia(resultSet);
        return wszystkie_wypozyczeniaObservableList;


    }

    public ObservableList<Wszystkie_Wypozyczenia> pokaz_wszystkie_wypozyczenia_zDnia_i_utarg(Date data) throws SQLException, ClassNotFoundException {
        String selectStmt = "select * from wszystkie_wypozyczenia where data_oddania = '"+data+"' ;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Wszystkie_Wypozyczenia> wszystkie_wypozyczeniaObservableList = getWszystkie_wypozyczenia(resultSet);
        return wszystkie_wypozyczeniaObservableList;


    }
    private ObservableList<Wszystkie_Wypozyczenia> getWszystkie_wypozyczenia(ResultSet rs) throws SQLException {
        ObservableList<Wszystkie_Wypozyczenia> wszystkieWypozyczenia = FXCollections.observableArrayList();

        while (rs.next()) {
            Wszystkie_Wypozyczenia w = new Wszystkie_Wypozyczenia();
            w.setKlient(rs.getString("klient"));
            w.setNr_rejestracyjny(rs.getString("nr_rejestracyjny"));
            w.setMarka(rs.getString("marka"));
            w.setNazwa(rs.getString("nazwa"));
            w.setPrzejechany_dystans(rs.getFloat("przejechany_dystans"));
            w.setKwota(rs.getFloat("kwota"));
            w.setData_start(rs.getDate("data_start"));
            w.setData_oddania(rs.getDate("data_oddania"));
            w.setStatus_wypozyczenia(rs.getString("status_wypozyczenia"));
            wszystkieWypozyczenia.add(w);
        }
        return wszystkieWypozyczenia;
    }
}


