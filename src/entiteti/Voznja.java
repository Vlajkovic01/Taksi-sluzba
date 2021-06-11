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
    protected boolean novijaVozila;
    protected boolean petFriendly;
    protected int ocenaVoznje;

    public Voznja() {
        this.id = 0;
        this.datumIVremePorudzbine = "";
        this.adresaPolaska = "";
        this.adresaDestinacije = "";
        this.musterija = new Musterije();
        this.vozac = new Vozaci();
        this.predjeniKm = 0;
        this.trajanjeVoznje = 0;
        this.statusVoznje = StatusVoznje.KREIRANA;
        this.tipPorudzbine = TipPorudzbine.TELEFONOM;
        this.izbrisana = false;
        this.novijaVozila = false;
        this.petFriendly = false;
        this.ocenaVoznje = 0;
    }

    public Voznja(int id, String datumIVremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterije musterija, Vozaci vozac, double predjeniKm, double trajanjeVoznje, StatusVoznje statusVoznje, TipPorudzbine tipPorudzbine, boolean izbrisana, boolean novijaVozila, boolean petFriendly, int ocenaVoznje) {
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
        this.novijaVozila = novijaVozila;
        this.petFriendly = petFriendly;
        this.ocenaVoznje = ocenaVoznje;
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

    public boolean isNovijaVozila() {
        return novijaVozila;
    }

    public void setNovijaVozila(boolean novijaVozila) {
        this.novijaVozila = novijaVozila;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public int getOcenaVoznje() {
        return ocenaVoznje;
    }

    public void setOcenaVoznje(int ocenaVoznje) {
        this.ocenaVoznje = ocenaVoznje;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "id=" + id +
                ", datumIVremePorudzbine='" + datumIVremePorudzbine + '\'' +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", musterija=" + musterija +
                ", vozac=" + vozac +
                ", predjeniKm=" + predjeniKm +
                ", trajanjeVoznje=" + trajanjeVoznje +
                ", statusVoznje=" + statusVoznje +
                ", tipPorudzbine=" + tipPorudzbine +
                ", izbrisana=" + izbrisana +
                ", novijaVozila=" + novijaVozila +
                ", petFriendly=" + petFriendly +
                ", ocenaVoznje=" + ocenaVoznje +
                '}';
    }
}
