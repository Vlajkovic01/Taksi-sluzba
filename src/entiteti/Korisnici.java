package entiteti;

import enumeracije.Pol;
import enumeracije.TipKorisnika;

public abstract class Korisnici {
    protected String korisnickoIme;
    protected String lozinka;
    protected String ime;
    protected String prezime;
    protected String jmbg;
    protected String adresa;
    protected Pol pol;
    protected String telefon;
    protected TipKorisnika tipKorisnika;
    protected boolean izbrisan;


    public Korisnici() {
        this.korisnickoIme = "";
        this.lozinka = "";
        this.ime = "";
        this.prezime = "";
        this.jmbg = "";
        this.adresa = "";
        this.pol = Pol.MUSKO;
        this.telefon = "";
        this.tipKorisnika = TipKorisnika.MUSTERIJA;
        this.izbrisan = false;

    }

    public Korisnici(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String telefon, TipKorisnika tipKorisnika, boolean izbrisan) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.adresa = adresa;
        this.pol = pol;
        this.telefon = telefon;
        this.tipKorisnika = tipKorisnika;
        this.izbrisan = izbrisan;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public boolean isIzbrisan() {
        return izbrisan;
    }

    public void setIzbrisan(boolean izbrisan) {
        this.izbrisan = izbrisan;
    }

    @Override
    public String toString() {
        return "Korisnici{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", adresa='" + adresa + '\'' +
                ", pol=" + pol +
                ", telefon='" + telefon + '\'' +
                ", tipKorisnika=" + tipKorisnika +
                ", izbrisan=" + izbrisan +
                '}';
    }
}
