package entiteti;

import enumeracije.Pol;
import enumeracije.TipKorisnika;

import strukture.ArrayList.ArrayList;

public class Musterije extends Korisnici {
    protected int id;
    protected ArrayList<Voznja> voznjeMusterije;

    public Musterije() {
        super();
        this.id = 0;
        this.voznjeMusterije = new ArrayList<Voznja>();
    }

    public Musterije(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String telefon, TipKorisnika tipKorisnika, boolean izbrisan, int id, ArrayList<Voznja> voznjeMusterije) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, telefon, tipKorisnika, izbrisan);
        this.id = id;
        this.voznjeMusterije = voznjeMusterije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Voznja> getVoznjeMusterije() {
        return voznjeMusterije;
    }

    public void setVoznjeMusterije(ArrayList<Voznja> voznjeMusterije) {
        this.voznjeMusterije = voznjeMusterije;
    }

    @Override
    public String toString() {
        String s = "Musterije{" + "korisnickoIme='" +this.korisnickoIme + '\'' +
                ", lozinka='" + this.lozinka + '\'' +
                ", ime='" + this.ime + '\'' +
                ", prezime='" + this.prezime + '\'' +
                ", jmbg='" + this.jmbg + '\'' +
                ", adresa='" + this.adresa + '\'' +
                ", pol=" + this.pol +
                ", telefon='" + this.telefon + '\'' +
                ", tipKorisnika=" + this.tipKorisnika + '\'' +
                ", izbrisan=" + this.izbrisan + ", id=" + this.id + ", idVoznji=";
        for (Voznja voznja: voznjeMusterije) {
            s += voznja.getId() + ", ";
        }
        return s + "}";
    }
}
