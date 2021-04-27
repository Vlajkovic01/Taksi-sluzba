package entiteti;

import enumeracije.Odeljenje;
import enumeracije.Pol;
import enumeracije.TipKorisnika;

public class Dispeceri extends Korisnici {
    private int id;
    private double plata;
    private String brLinije;
    private Odeljenje odeljenje;

    public Dispeceri() {
        this.id = 0;
        this.plata = 0;
        this.brLinije = "";
        this.odeljenje = Odeljenje.PRIJEM_VOZNJI;
    }

    public Dispeceri(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String telefon, TipKorisnika tipKorisnika, boolean izbrisan, int id, double plata, String brLinije, Odeljenje odeljenje) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, telefon, tipKorisnika, izbrisan);
        this.id = id;
        this.plata = plata;
        this.brLinije = brLinije;
        this.odeljenje = odeljenje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String getBrLinije() {
        return brLinije;
    }

    public void setBrLinije(String brLinije) {
        this.brLinije = brLinije;
    }

    public Odeljenje getOdeljenje() {
        return odeljenje;
    }

    public void setOdeljenje(Odeljenje odeljenje) {
        this.odeljenje = odeljenje;
    }

    @Override
    public String toString() {
        return "Dispeceri{" +
                "id=" + id +
                ", plata=" + plata +
                ", brLinije='" + brLinije + '\'' +
                ", odeljenje=" + odeljenje +
                ", korisnickoIme='" + korisnickoIme + '\'' +
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
