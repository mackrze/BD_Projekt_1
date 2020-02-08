import javafx.beans.property.*;

import java.util.Date;

public class Wypozyczalnia {

    private StringProperty nr_rejestracyjny;
    private StringProperty marka;
    private StringProperty nazwa;
    private FloatProperty bateria;
    private FloatProperty odleglosc_na_ladowaniu;
    private FloatProperty stan_licznika;
    private Date data_konca_ubezpieczenia;
    private FloatProperty mozliwosc_wypozyczenia;

    public Wypozyczalnia() {
       nr_rejestracyjny = new SimpleStringProperty();
      marka = new SimpleStringProperty();
        nazwa = new SimpleStringProperty();
      bateria = new SimpleFloatProperty();
      odleglosc_na_ladowaniu = new SimpleFloatProperty();
     stan_licznika =new SimpleFloatProperty();
        data_konca_ubezpieczenia = new Date();
        mozliwosc_wypozyczenia = new SimpleFloatProperty();
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

    public float getBateria() {
        return bateria.get();
    }

    public FloatProperty bateriaProperty() {
        return bateria;
    }

    public void setBateria(float bateria) {
        this.bateria.set(bateria);
    }

    public float getOdleglosc_na_ladowaniu() {
        return odleglosc_na_ladowaniu.get();
    }

    public FloatProperty odleglosc_na_ladowaniuProperty() {
        return odleglosc_na_ladowaniu;
    }

    public void setOdleglosc_na_ladowaniu(float odleglosc_na_ladowaniu) {
        this.odleglosc_na_ladowaniu.set(odleglosc_na_ladowaniu);
    }

    public float getStan_licznika() {
        return stan_licznika.get();
    }

    public FloatProperty stan_licznikaProperty() {
        return stan_licznika;
    }

    public void setStan_licznika(float stan_licznika) {
        this.stan_licznika.set(stan_licznika);
    }

    public Date getData_konca_ubezpieczenia() {
        return data_konca_ubezpieczenia;
    }

    public void setData_konca_ubezpieczenia(Date data_konca_ubezpieczenia) {
        this.data_konca_ubezpieczenia = data_konca_ubezpieczenia;
    }

    public float getMozliwosc_wypozyczenia() {
        return mozliwosc_wypozyczenia.get();
    }

    public FloatProperty mozliwosc_wypozyczeniaProperty() {
        return mozliwosc_wypozyczenia;
    }

    public void setMozliwosc_wypozyczenia(float mozliwosc_wypozyczenia) {
        this.mozliwosc_wypozyczenia.set(mozliwosc_wypozyczenia);
    }
}
