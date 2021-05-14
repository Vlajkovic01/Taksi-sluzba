package entiteti;

import enumeracije.StatusVoznje;
import enumeracije.TipPorudzbine;

public class Voznja {
    protected int id;
    protected String datumIVremePorudzbine;
    protected String adresaPolaska;
    protected String adresaDestinacije;
    protected Musterije musterija;
    protected Vozaci vozac;
    protected double predjeniKm;
    protected double trajanjeVoznje;
    protected StatusVoznje statusVoznje;
    protected TipPorudzbine tipPorudzbine;
    protected boolean izbrisana;

    public Voznja() {
        this.id = 0;
        this.datumIVremePorudzbine = "";
        this.adresaPolaska = "";
        this.adresaDestinacije = "";
        this.musterija = new Musterije();
        this.vozac = new Vozaci();
        this.predjeniKm = 0;
        this.trajanjeVoznje = 0;
        this.statusVoznje = StatusVoznje.NA_CEKANJU;
        this.tipPorudzbine = TipPorudzbine.TELEFONOM;
        this.izbrisana = false;
    }

    public Voznja(int id, String datumIVremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterije musterija, Vozaci vozac, double predjeniKm, double trajanjeVoznje, StatusVoznje statusVoznje, TipPorudzbine tipPorudzbine, boolean izbrisana) {
        this.id = id;
        this.datumIVremePorudzbine = datumIVremePorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.musterija = musterija;
        this.vozac = vozac;
        this.predjeniKm = predjeniKm;
        this.trajanjeVoznje = trajanjeVoznje;
        this.statusVoznje = statusVoznje;
        this.tipPorudzbine = tipPorudzbine;
        this.izbrisana = izbrisana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatumIVremePorudzbine() {
        return datumIVremePorudzbine;
    }

    public void setDatumIVremePorudzbine(String datumIVremePorudzbine) {
        this.datumIVremePorudzbine = datumIVremePorudzbine;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public Musterije getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterije musterija) {
        this.musterija = musterija;
    }

    public Vozaci getVozac() {
        return vozac;
    }

    public void setVozac(Vozaci vozac) {
        this.vozac = vozac;
    }

    public double getPredjeniKm() {
        return predjeniKm;
    }

    public void setPredjeniKm(double predjeniKm) {
        this.predjeniKm = predjeniKm;
    }

    public double getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public void setTrajanjeVoznje(double trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public StatusVoznje getStatusVoznje() {
        return statusVoznje;
    }

    public TipPorudzbine getTipPorudzbine() {
        return tipPorudzbine;
    }

    public void setTipPorudzbine(TipPorudzbine tipPorudzbine) {
        this.tipPorudzbine = tipPorudzbine;
    }

    public void setStatusVoznje(StatusVoznje statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    public boolean isIzbrisana() {
        return izbrisana;
    }

    public void setIzbrisana(boolean izbrisana) {
        this.izbrisana = izbrisana;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "id=" + id +
                ", datumIVremePorudzbine='" + datumIVremePorudzbine + '\'' +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", musterija=" + musterija.getId() +
                ", vozac=" + vozac.getId() +
                ", predjeniKm=" + predjeniKm +
                ", trajanjeVoznje=" + trajanjeVoznje + "min" +
                ", statusVoznje=" + statusVoznje + ", tipPorudznine=" + tipPorudzbine + ", izbrisana=" + izbrisana +
                '}';
    }
}
