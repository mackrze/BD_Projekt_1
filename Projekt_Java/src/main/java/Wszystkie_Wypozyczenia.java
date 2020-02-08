import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Wszystkie_Wypozyczenia {

    private StringProperty klient;
    private StringProperty nr_rejestracyjny;
    private StringProperty marka;
    private StringProperty nazwa;
    private FloatProperty przejechany_dystans;
    private FloatProperty kwota;
    private Date data_start;
    private Date data_oddania;
    private StringProperty status_wypozyczenia;

    public Wszystkie_Wypozyczenia() {
        klient = new SimpleStringProperty();
        nr_rejestracyjny = new SimpleStringProperty();
        marka = new SimpleStringProperty();
        nazwa = new SimpleStringProperty();
        przejechany_dystans = new SimpleFloatProperty();
        kwota = new SimpleFloatProperty();
        data_start = new Date();
        data_oddania = new Date();
        status_wypozyczenia = new SimpleStringProperty();
    }

    public String getKlient() {
        return klient.get();
    }

    public StringProperty klientProperty() {
        return klient;
    }

    public void setKlient(String klient) {
        this.klient.set(klient);
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny.get();
    }

    public StringProperty nr_rejestracyjnyProperty() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny.set(nr_rejestracyjny);
    }

    public String getMarka() {
        return marka.get();
    }

    public StringProperty markaProperty() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka.set(marka);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public float getPrzejechany_dystans() {
        return przejechany_dystans.get();
    }

    public FloatProperty przejechany_dystansProperty() {
        return przejechany_dystans;
    }

    public void setPrzejechany_dystans(float przejechany_dystans) {
        this.przejechany_dystans.set(przejechany_dystans);
    }

    public float getKwota() {
        return kwota.get();
    }

    public FloatProperty kwotaProperty() {
        return kwota;
    }

    public void setKwota(float kwota) {
        this.kwota.set(kwota);
    }

    public Date getData_start() {
        return data_start;
    }

    public void setData_start(Date data_start) {
        this.data_start = data_start;
    }

    public Date getData_oddania() {
        return data_oddania;
    }

    public void setData_oddania(Date data_oddania) {
        this.data_oddania = data_oddania;
    }

    public String getStatus_wypozyczenia() {
        return status_wypozyczenia.get();
    }

    public StringProperty status_wypozyczeniaProperty() {
        return status_wypozyczenia;
    }

    public void setStatus_wypozyczenia(String status_wypozyczenia) {
        this.status_wypozyczenia.set(status_wypozyczenia);
    }
}
