package entiteti;

import enumeracije.StatusVoznje;

public class Voznja {
    protected int id;
    protected String datumIVremePorudzbine;
    protected String adresaPolaska;
    protected String adresaDestinacije;
    protected Musterije musterija;
    protected Vozaci vozac;
    protected int predjeniKm;
    protected double trajanjeVoznje;
    protected StatusVoznje statusVoznje;
}
