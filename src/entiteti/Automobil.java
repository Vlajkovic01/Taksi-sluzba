package entiteti;

import enumeracije.TipVozila;

import java.util.GregorianCalendar;

public class Automobil {
    protected int idVozila;
    protected String model;
    protected String proizvodjac;
    protected int godProizvodnje;
    protected String regOznaka;
    protected TipVozila tipVozila;
    protected boolean izbrisan;
    protected boolean slobodan;

    public Automobil() {
        this.idVozila = 0;
        this.model = "";
        this.proizvodjac = "";
        this.godProizvodnje = 0;
        this.regOznaka = "";
        this.tipVozila = TipVozila.PUTNICKI;
        this.izbrisan = false;
        this.slobodan = true;
    }

    public Automobil(String model, String proizvodjac, int godProizvodnje, String regOznaka, int idVozila, TipVozila tipVozila, boolean izbrisan, boolean slobodan) {
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godProizvodnje = godProizvodnje;
        this.regOznaka = regOznaka;
        this.idVozila = idVozila;
        this.tipVozila = tipVozila;
        this.izbrisan = izbrisan;
        this.slobodan = slobodan;
    }

    public int getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(int idVozila) {
        this.idVozila = idVozila;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }

    public void setGodProizvodnje(int godProizvodnje) {
        this.godProizvodnje = godProizvodnje;
    }

    public String getRegOznaka() {
        return regOznaka;
    }

    public void setRegOznaka(String regOznaka) {
        this.regOznaka = regOznaka;
    }

    public TipVozila getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(TipVozila tipVozila) {
        this.tipVozila = tipVozila;
    }

    public boolean isIzbrisan() {
        return izbrisan;
    }

    public void setIzbrisan(boolean izbrisan) {
        this.izbrisan = izbrisan;
    }

    public boolean isSlobodan() {
        return slobodan;
    }

    public void setSlobodan(boolean slobodan) {
        this.slobodan = slobodan;
    }

    @Override
    public String toString() {
        return "Automobil{" +
                "idVozila=" + idVozila +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", godProizvodnje=" + godProizvodnje +
                ", regOznaka='" + regOznaka + '\'' +
                ", tipVozila=" + tipVozila +
                ", izbrisan=" + izbrisan +
                ", slobodan=" + slobodan +
                '}';
    }
}
