import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WypozyczalniaDAO {
    private String login;
    private DBUtil dbUtil;
    //private TextArea consoleTextArea;

    public WypozyczalniaDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
        //this.consoleTextArea = consoleTextArea;
    }

    public ObservableList<Wypozyczalnia> pokazMozliweWypozyczenia() throws SQLException, ClassNotFoundException {
        String selectStmt = "select * from wypozyczalnia where mozliwosc_wypozyczenia = 1;";
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<Wypozyczalnia> wypozyczalniaObservableList = getWypozyczalniaList(resultSet);
            //consoleTextArea
            return  wypozyczalniaObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }


    }

    public ObservableList<Wypozyczalnia> pokazAuta() throws SQLException, ClassNotFoundException {
        String selectStmt = "select * from wypozyczalnia ;";
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            ObservableList<Wypozyczalnia> wypozyczalniaObservableList = getWypozyczalniaList(resultSet);
            return  wypozyczalniaObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


    private ObservableList<Wypozyczalnia> getWypozyczalniaList(ResultSet rs) throws SQLException {
        ObservableList<Wypozyczalnia> wypozyczalniaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Wypozyczalnia w = new Wypozyczalnia();
            w.setNr_rejestracyjny(rs.getString("nr_rejestracyjny"));
            w.setMarka(rs.getString("marka"));
            w.setNazwa(rs.getString("nazwa"));
            w.setBateria(rs.getFloat("bateria"));
            w.setOdleglosc_na_ladowaniu(rs.getFloat("odleglosc_na_ladowaniu"));
            w.setStan_licznika(rs.getFloat("stan_licznika"));
            w.setData_konca_ubezpieczenia(rs.getDate("data_konca_ubezpieczenia"));
            w.setMozliwosc_wypozyczenia(rs.getInt("mozliwosc_wypozyczenia"));
            wypozyczalniaList.add(w);

        }
        return wypozyczalniaList;
    }
}


