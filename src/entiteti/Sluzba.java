package entiteti;

public class Sluzba {
    public int id;
    public String pib;
    public String naziv;
    public String adresa;
    public double cenaStart;
    public double cenaKm;

    public Sluzba() {
        this.id = 0;
        this.pib = "";
        this.naziv = "";
        this.adresa = "";
        this.cenaStart = 0;
        this.cenaKm = 0;
    }

    public Sluzba(int id, String pib, String naziv, String adresa, int cenaStart, int cenaKm) {
        this.id = id;
        this.pib = pib;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStart = cenaStart;
        this.cenaKm = cenaKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCenaStart() {
        return cenaStart;
    }

    public void setCenaStart(double cenaStart) {
        this.cenaStart = cenaStart;
    }

    public double getCenaKm() {
        return cenaKm;
    }

    public void setCenaKm(double cenaKm) {
        this.cenaKm = cenaKm;
    }

    @Override
    public String toString() {
        return "Sluzba{" +
                "id=" + id +
                ", pib='" + pib + '\'' +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", cenaStart=" + cenaStart +
                ", cenaKm=" + cenaKm +
                '}';
    }
}
