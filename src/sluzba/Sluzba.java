package sluzba;

import entiteti.*;
import enumeracije.*;

import java.io.*;
import java.util.ArrayList;

public class Sluzba {
    public int id;
    public String pib;
    public String naziv;
    public String adresa;
    public double cenaStart;
    public double cenaKm;

    private ArrayList<Dispeceri> dispeceri;
    private ArrayList<Vozaci> vozaci;
    private ArrayList<Musterije> musterije;
    private ArrayList<Voznja> voznje;
    private ArrayList<Automobil> automobili;

    public Sluzba() {
        this.id = 0;
        this.pib = "";
        this.naziv = "";
        this.adresa = "";
        this.cenaStart = 0;
        this.cenaKm = 0;

        this.dispeceri = new ArrayList<Dispeceri>();
        this.vozaci = new ArrayList<Vozaci>();
        this.musterije = new ArrayList<Musterije>();
        this.voznje = new ArrayList<Voznja>();
        this.automobili = new ArrayList<Automobil>();
    }

    public Sluzba(int id, String pib, String naziv, String adresa, int cenaStart, int cenaKm) {
        this.id = id;
        this.pib = pib;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStart = cenaStart;
        this.cenaKm = cenaKm;
    }

    public ArrayList<Dispeceri> getDispeceri() {
        return dispeceri;
    }

    public void setDispeceri(ArrayList<Dispeceri> dispeceri) {
        this.dispeceri = dispeceri;
    }

    public ArrayList<Vozaci> getVozaci() {
        return vozaci;
    }

    public void setVozaci(ArrayList<Vozaci> vozaci) {
        this.vozaci = vozaci;
    }

    public ArrayList<Musterije> getMusterije() {
        return musterije;
    }

    public void setMusterije(ArrayList<Musterije> musterije) {
        this.musterije = musterije;
    }

    public ArrayList<Voznja> getVoznje() {
        return voznje;
    }

    public void setVoznje(ArrayList<Voznja> voznje) {
        this.voznje = voznje;
    }

    public ArrayList<Automobil> getAutomobili() {
        return automobili;
    }

    public void setAutomobili(ArrayList<Automobil> automobili) {
        this.automobili = automobili;
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

    //---------------------------Ucitavanje/Upis----------------------------------//

    public void ucitajAutomobile() {
        try {
            File file = new File("fajlovi/automobili.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String model = split[0];
                String proizvodjac = split[1];
                String godProizvodnjeString= split[2];
                int godProizvodnje = Integer.parseInt(godProizvodnjeString);
                String regOznaka = split[3];
                String idVozilaString = split[4];
                int idVozila = Integer.parseInt(idVozilaString);
                TipVozila tipVozila = TipVozila.valueOf(split[5]);
                boolean izbrisan = Boolean.parseBoolean(split[6]);
                boolean slobodan = Boolean.parseBoolean(split[7]);
                Automobil automobil = new Automobil(model,proizvodjac,godProizvodnje,regOznaka,idVozila,tipVozila,izbrisan,slobodan);
                automobili.add(automobil);
                // A6|Audi|2015|NS100NS|100|PUTNICKI|false|false
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja podataka o automobilima");
            e.printStackTrace();
        }
    }

    public void snimiAutomobile() {
        try {
            File file = new File("fajlovi/automobili.txt");
            String content = "";
            for (Automobil automobil : automobili) {
                content += automobil.getModel() + "|" + automobil.getProizvodjac() + "|"
                        + automobil.getGodProizvodnje() + "|" + automobil.getRegOznaka() + "|"
                        + automobil.getIdVozila() + "|" + automobil.getTipVozila() + "|"
                        + automobil.isIzbrisan() + "|" + automobil.isSlobodan() + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja automobila.");
        }
    }

    public void ucitajDispecere() {
        try {
            File file = new File("fajlovi/dispeceri.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String korisnickoIme = split[0];
                String lozinka = split[1];
                String ime = split[2];
                String prezime = split[3];
                String jmbg = split[4];
                String adresa = split[5];
                Pol pol = Pol.valueOf(split[6]);
                String telefon = split[7];
                TipKorisnika tipKorisnika = TipKorisnika.valueOf(split[8]);
                boolean izbrisan = Boolean.parseBoolean(split[9]);
                String idString = split[10];
                int id = Integer.parseInt(idString);
                String plataString = split[11];
                double plata = Double.parseDouble(plataString);
                String brLinije = split[12];
                Odeljenje odeljenje = Odeljenje.valueOf(split[13]);
                Dispeceri dispecer = new Dispeceri(korisnickoIme,lozinka,ime,prezime,jmbg,adresa,pol,telefon,tipKorisnika,izbrisan,id,plata,brLinije,odeljenje);
                dispeceri.add(dispecer);
                // stefo|12345|Stefan|Vlajkovic|0011223344|Balzakova Novi Sad|MUSKO|061555333|DISPECER|false|11|50000|011500|REKLAMACIJE
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja podataka o dispecerima");
            e.printStackTrace();
        }
    }

    public void snimiDispecere() {
        try {
            File file = new File("fajlovi/dispeceri.txt");
            String content = "";
            for (Dispeceri dispecer : dispeceri) {
                content += dispecer.getKorisnickoIme() + "|" + dispecer.getLozinka() + "|"
                        + dispecer.getIme() + "|" + dispecer.getPrezime() + "|"
                        + dispecer.getJmbg() + "|" + dispecer.getAdresa() + "|"
                        + dispecer.getPol() + "|" + dispecer.getTelefon() + "|"
                        + dispecer.getTipKorisnika() + "|" + dispecer.isIzbrisan() + "|"
                        + dispecer.getId() + "|" + dispecer.getPlata() + "|"
                        + dispecer.getBrLinije() + "|" + dispecer.getOdeljenje() + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja dispecera.");
        }
    }

    public void ucitajMusterije() {
        try {
            File file = new File("fajlovi/musterije.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String korisnickoIme = split[0];
                String lozinka = split[1];
                String ime = split[2];
                String prezime = split[3];
                String jmbg = split[4];
                String adresa = split[5];
                Pol pol = Pol.valueOf(split[6]);
                String telefon = split[7];
                TipKorisnika tipKorisnika = TipKorisnika.valueOf(split[8]);
                boolean izbrisan = Boolean.parseBoolean(split[9]);
                String idString = split[10];
                int id = Integer.parseInt(idString);
                ArrayList<Voznja> voznjeMusterije = new ArrayList<Voznja>();
                Musterije musterija = new Musterije(korisnickoIme,lozinka,ime,prezime,jmbg,adresa,pol,telefon,tipKorisnika,izbrisan,id,voznjeMusterije);
                musterije.add(musterija);
                // nidzo|12345|Nikola|Panic|123123123|Nikole Tesle, Beograd|MUSKO|061123123|MUSTERIJA|false|101
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja podataka o musterijama");
            e.printStackTrace();
        }
    }

    public void snimiMusterije() {
        try {
            File file = new File("fajlovi/musterije.txt");
            String content = "";
            for (Musterije musterija : musterije) {
                content += musterija.getKorisnickoIme() + "|" + musterija.getLozinka() + "|"
                        + musterija.getIme() + "|" + musterija.getPrezime() + "|"
                        + musterija.getJmbg() + "|" + musterija.getAdresa() + "|"
                        + musterija.getPol() + "|" + musterija.getTelefon() + "|"
                        + musterija.getTipKorisnika() + "|" + musterija.isIzbrisan() + "|"
                        + musterija.getId() + "|" +  "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja musterije.");
        }
    }

    public void ucitajVozace() {
        try {
            File file = new File("fajlovi/vozaci.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String korisnickoIme = split[0];
                String lozinka = split[1];
                String ime = split[2];
                String prezime = split[3];
                String jmbg = split[4];
                String adresa = split[5];
                Pol pol = Pol.valueOf(split[6]);
                String telefon = split[7];
                TipKorisnika tipKorisnika = TipKorisnika.valueOf(split[8]);
                boolean izbrisan = Boolean.parseBoolean(split[9]);
                String idString = split[10];
                int id = Integer.parseInt(idString);
                String plataString = split[11];
                double plata = Double.parseDouble(plataString);
                String brClanskeKarteString = split[12];
                int brClanskeKarte = Integer.parseInt(brClanskeKarteString);
                String idVozilaString = split[13];
                int idVozila = Integer.parseInt(idVozilaString);
                Automobil automobil = pronadjiAutomobil(idVozila);
                ArrayList<Voznja> voznjeVozaca = new ArrayList<Voznja>();
                Vozaci vozac = new Vozaci(korisnickoIme,lozinka,ime,prezime,jmbg,adresa,pol,telefon,tipKorisnika,izbrisan,id,plata,brClanskeKarte,automobil,voznjeVozaca);
                vozaci.add(vozac);
                // budo|12345|Marko|Budesa|01010101|Svetog Save, Sabac|MUSKO|062323232|VOZAC|false|200|45000|1001|100
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja podataka o vozacima");
            e.printStackTrace();
        }
    }

    public void snimiVozace() {
        try {
            File file = new File("fajlovi/vozaci.txt");
            String content = "";
            for (Vozaci vozac : vozaci) {
                content += vozac.getKorisnickoIme() + "|" + vozac.getLozinka() + "|"
                        + vozac.getIme() + "|" + vozac.getPrezime() + "|"
                        + vozac.getJmbg() + "|" + vozac.getAdresa() + "|"
                        + vozac.getPol() + "|" + vozac.getTelefon() + "|"
                        + vozac.getTipKorisnika() + "|" + vozac.isIzbrisan() + "|"
                        + vozac.getId() + "|" + vozac.getPlata() + "|" + vozac.getBrClanskeKarte() + "|"
                        + vozac.getAutomobil().getIdVozila() + "|" + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja musterije.");
        }
    }

    public void ucitajVoznje() {
        try {
            File file = new File("fajlovi/voznje.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // 01|10-10-2021/22:00|Novi Sad|Beograd|101|200|130|90|ZAVRSENA
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\\|");
                String idString = split[0];
                int id = Integer.parseInt(idString);
                String datumIVremePorudzbine = split[1];
                String adresaPolaska= split[2];
                String adresaDestinacije = split[3];
                String idMusterijeString = split[4];
                int idMusterije = Integer.parseInt(idMusterijeString);
                Musterije musterija = (Musterije) pronadjiMusteriju(idMusterije);
                String idVozacaString = split[5];
                int idVozaca = Integer.parseInt(idVozacaString);
                Vozaci vozac = (Vozaci) pronadjiVozaca(idVozaca);
                String predjeniKmString = split[6];
                double predjeniKm = Double.parseDouble(predjeniKmString);
                String trajanjeVoznjeString = split[7];
                double trajanjeVoznje = Double.parseDouble(trajanjeVoznjeString);
                StatusVoznje statusVoznje = StatusVoznje.valueOf(split[8]);
                Voznja voznja = new Voznja(id,datumIVremePorudzbine,adresaPolaska,adresaDestinacije,musterija,vozac,predjeniKm,trajanjeVoznje,statusVoznje);
                if (vozac != null) {
                    vozac.getVoznjeVozaca().add(voznja);
                }
                if (musterija != null) {
                    musterija.getVoznjeMusterije().add(voznja);
                }
                voznje.add(voznja);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja podataka o voznjama");
            e.printStackTrace();
        }
    }

    public void snimiVoznje() {
        try {
            File file = new File("fajlovi/voznje.txt");
            String content = "";
            for (Voznja voznja : voznje) {
                content += voznja.getId() + "|" + voznja.getDatumIVremePorudzbine() + "|"
                        + voznja.getAdresaPolaska() + "|" + voznja.getAdresaDestinacije() + "|"
                        + voznja.getMusterija().getId() + "|" + voznja.getVozac().getId() + "|"
                        + voznja.getPredjeniKm() + "|" + voznja.getTrajanjeVoznje() + "|"
                        + voznja.getStatusVoznje() + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom snimanja voznje.");
        }
    }

    //-----------------------------Pronalazenje------------------------------------//

    public Automobil pronadjiAutomobil(int idVozila) {
        for (Automobil automobil : automobili) {
            if(automobil.getIdVozila() == idVozila){
                return automobil;
            }
        }
        return null;
    }

    public Automobil pronadjiAutomobilString(String id) {
        for (Automobil auto : automobili) {
            try {
                if(auto.getIdVozila() == Integer.parseInt(id)) {
                    return auto;
                }
            }catch(NumberFormatException e) {

            }
        }
        return null;
    }

    public Musterije pronadjiMusteriju(int id) {
        for (Musterije musterija : musterije) {
            if(musterija.getId() == id ){
                return musterija;
            }
        }
        return null;
    }

    public Vozaci pronadjiVozaca(int id) {
        for (Vozaci vozac : vozaci) {
            if(vozac.getId() == id ){
                return vozac;
            }
        }
        return null;
    }

    public  Vozaci pronadjiVozacaString(String v) {
        for(Vozaci vozac:vozaci) {
            try {
                if(vozac.getId() == Integer.parseInt(v)) {
                    return  vozac;
                }
            }catch(NumberFormatException e) {
            }
        }
        return null;
    }
    public  Dispeceri pronadjiDispeceraString(String d) {
        for(Dispeceri dispecer:dispeceri) {
            try {
                if(dispecer.getId() == Integer.parseInt(d)) {
                    return  dispecer;
                }
            }catch(NumberFormatException e) {
            }
        }
        return null;
    }

    public Voznja pronadjiVoznju(int id) {
        for (Voznja voznja : voznje) {
            if(voznja.getId() == id ){
                return voznja;
            }
        }
        return null;
    }

    //-------------------------------Login--------------------------------------//

    public Vozaci loginVozac(String korisnickoIme, String lozinka) {
        for(Vozaci vozac : vozaci) {
            if(vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
                    vozac.getLozinka().equals(lozinka) && !vozac.isIzbrisan()) {
                return vozac;
            }
        }
        return null;
    }

    public Dispeceri loginDispecer(String korisnickoIme, String lozinka) {
        for(Dispeceri dispecer : dispeceri) {
            if(dispecer.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
                    dispecer.getLozinka().equals(lozinka) && !dispecer.isIzbrisan()) {
                return dispecer;
            }
        }
        return null;
    }

    public Musterije loginMusterija(String korisnickoIme, String lozinka) {
        for(Musterije musterija : musterije) {
            if(musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
                    musterija.getLozinka().equals(lozinka) && !musterija.isIzbrisan()) {
                return musterija;
            }
        }
        return null;
    }

    //-------------------------------Dodavanje-Provera--------------------------------------//

    public void dodajAutomobil(Automobil automobil) {
        boolean postojiAutomobil = false;
        for (Automobil auto: automobili) {
            if (auto.getIdVozila() == automobil.getIdVozila() && !auto.isIzbrisan()) {
                postojiAutomobil = true;
                break;
            }
        }
        if (postojiAutomobil) {
            System.out.println("Postoji automobil sa tim ID-jem.");
        }else {
            this.automobili.add(automobil);
            System.out.println("Automobil uspesno dodat.");
        }
    }

//    public void dodajDispecera(Dispeceri dispecer) {
//        boolean postojiDispecer = false;
//        for (Dispeceri d: dispeceri) {
//            if (d.getId() == dispecer.getId() && !d.isIzbrisan()) {
//                postojiDispecer = true;
//                break;
//            }
//        }
//        if (postojiDispecer) {
//            System.out.println("Postoji dispecer sa tim ID-jem.");
//        }else {
//            this.dispeceri.add(dispecer);
//            System.out.println("Dispecer uspesno dodat.");
//        }
//    }
    public void dodajDispecera(Dispeceri dispecer) {
        this.dispeceri.add(dispecer);
    }

    public void dodajMusteriju(Musterije musterija) {
        boolean postojiMusterija = false;
        for (Musterije m: musterije) {
            if (m.getId() == musterija.getId() && !m.isIzbrisan()) {
                postojiMusterija = true;
                break;
            }
        }
        if (postojiMusterija) {
            System.out.println("Postoji musterija sa tim ID-jem.");
        }else {
            this.musterije.add(musterija);
            System.out.println("Musterija uspesno dodata.");
        }
    }

//    public void dodajVozaca(Vozaci vozac) {
//        boolean postojiVozac = false;
//        for (Vozaci v: vozaci) {
//            if (v.getId() == vozac.getId() && !v.isIzbrisan()) {
//                postojiVozac = true;
//                break;
//            }
//        }
//        if (postojiVozac) {
//            System.out.println("Postoji vozac sa tim ID-jem.");
//        }else {
//            this.vozaci.add(vozac);
//            System.out.println("Vozac uspesno dodat.");
//        }
//    }
    public void dodajVozaca(Vozaci vozac) {
        this.vozaci.add(vozac);
    }

    public void dodajVoznju(Voznja voznja) {
        boolean postojiVoznja = false;
        for (Voznja v: voznje) {
            if (v.getId() == voznja.getId()) {
                postojiVoznja = true;
                break;
            }
        }
        if (postojiVoznja) {
            System.out.println("Postoji voznja sa tim ID-jem.");
        }else {
            this.voznje.add(voznja);
            System.out.println("Voznja uspesno dodata.");
        }
    }
    //----------------------------------Brisanje--------------------------------------//
    public void obrisiAutomobil(Automobil automobil) {
        automobil.setIzbrisan(true);
        snimiAutomobile();
        for(Vozaci vozac: vozaci) {
            if(vozac.getAutomobil().getIdVozila() == automobil.getIdVozila()) {
                vozac.setAutomobil(new Automobil());
                snimiVozace();
            }
        }
    }

    public void obrisiVozaca(Vozaci vozac) {
        vozac.setIzbrisan(true);
        snimiVozace();
        for(Automobil auto: automobili) {
            if(vozac.getAutomobil().getIdVozila() == auto.getIdVozila()) {
                auto.setSlobodan(true);
                snimiAutomobile();
            }
        }
    }

    public void obrisiDispecera(Dispeceri dispecer) {
        dispecer.setIzbrisan(true);
        snimiDispecere();
    }
    //----------------------------------Izmena--------------------------------------//
    public void izmeniVozace(String korisnickoIme,String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String telefon,TipKorisnika tipKorisnika , boolean izbrisan, int id, double plata, int brClanskeKarte, Automobil automobil, ArrayList<Voznja> voznjeVozaca) {
        for(Vozaci vozac: vozaci) {
            if(vozac.getId() == id) {
                vozac.setKorisnickoIme(korisnickoIme);
                vozac.setLozinka(lozinka);
                vozac.setIme(ime);
                vozac.setPrezime(prezime);
                vozac.setJmbg(jmbg);
                vozac.setAdresa(adresa);
                vozac.setPol(pol);
                vozac.setTelefon(telefon);
                vozac.setTipKorisnika(tipKorisnika);
                vozac.setIzbrisan(izbrisan);
                vozac.setId(id);
                vozac.setPlata(plata);
                vozac.setBrClanskeKarte(brClanskeKarte);
                vozac.setAutomobil(automobil);
                vozac.setVoznjeVozaca(voznjeVozaca);
            }
        }
        snimiVozace();
    }
}

