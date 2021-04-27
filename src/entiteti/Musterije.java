package entiteti;

import enumeracije.Pol;
import enumeracije.TipKorisnika;

import java.util.ArrayList;
import java.util.Set;

public class Musterije extends Korisnici {
    protected int id;
    protected ArrayList<Voznja> voznjeMusterije;

    public Musterije() {
        super();
        this.id = 0;
        this.voznjeMusterije = new ArrayList<>(voznjeMusterije) ;
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
        return "Musterije{" +
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
                ", voznjeMusterije=" + voznjeMusterije +
                '}';
    }
}
