package entiteti;

public class Aukcija {

    private Voznja voznja;
    private Vozaci vozac;
    private Double ponudjenoVreme; //u minutama
    private Double ukupnoBodovaVozac;

    public Aukcija(Voznja voznja, Vozaci vozac, Double ponudjenoVreme, Double ukupnoBodovaVozac) {
        this.voznja = voznja;
        this.vozac = vozac;
        this.ponudjenoVreme = ponudjenoVreme;
        this.ukupnoBodovaVozac = ukupnoBodovaVozac;
    }

    public Voznja getVoznja() {
        return voznja;
    }

    public void setVoznja(Voznja voznja) {
        this.voznja = voznja;
    }

    public Vozaci getVozac() {
        return vozac;
    }

    public void setVozac(Vozaci vozac) {
        this.vozac = vozac;
    }

    public Double getPonudjenoVreme() {
        return ponudjenoVreme;
    }

    public void setPonudjenoVreme(Double ponudjenoVreme) {
        this.ponudjenoVreme = ponudjenoVreme;
    }

    public Double getUkupnoBodovaVozac() {
        return ukupnoBodovaVozac;
    }

    public void setUkupnoBodovaVozac(Double ukupnoBodovaVozac) {
        this.ukupnoBodovaVozac = ukupnoBodovaVozac;
    }

    @Override
    public String toString() {
        return "Aukcija{" +
                "voznja=" + voznja +
                ", vozac=" + vozac +
                ", ponudjenoVreme=" + ponudjenoVreme +
                ", ukupnoBodovaVozac=" + ukupnoBodovaVozac +
                '}';
    }
}
