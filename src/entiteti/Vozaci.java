package entiteti;

import enumeracije.Pol;
import enumeracije.TipKorisnika;

import java.util.ArrayList;
import java.util.Set;

public class Vozaci extends Korisnici {
    protected int id;
    protected double plata;
    protected int brClanskeKarte;
    protected Automobil automobil;
    protected ArrayList<Voznja> voznjeVozaca;

    public Vozaci() {
        this.id = 0;
        this.plata = 0;
        this.brClanskeKarte = 0;
        this.automobil = new Automobil();
        this.voznjeVozaca = new ArrayList<>(voznjeVozaca);
    }

    public Vozaci(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String telefon, TipKorisnika tipKorisnika, boolean izbrisan, int id, double plata, int brClanskeKarte, Automobil automobil, ArrayList<Voznja> voznjeVozaca) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, telefon, tipKorisnika, izbrisan);
        this.id = id;
        this.plata = plata;
        this.brClanskeKarte = brClanskeKarte;
        this.automobil = automobil;
        this.voznjeVozaca = voznjeVozaca;
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

    public int getBrClanskeKarte() {
        return brClanskeKarte;
    }

    public void setBrClanskeKarte(int brClanskeKarte) {
        this.brClanskeKarte = brClanskeKarte;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public ArrayList<Voznja> getVoznjeVozaca() {
        return voznjeVozaca;
    }

    public void setVoznjeVozaca(ArrayList<Voznja> voznjeVozaca) {
        this.voznjeVozaca = voznjeVozaca;
    }

    @Override
    public String toString() {
        return "Vozaci{" +
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
                ", id=" + id +
                ", plata=" + plata +
                ", brClanskeKarte=" + brClanskeKarte +
                ", automobil=" + automobil +
                ", voznjeVozaca=" + voznjeVozaca +
                '}';
    }
}
