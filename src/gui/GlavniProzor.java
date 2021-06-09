package gui;

import entiteti.Dispeceri;
import entiteti.Korisnici;
import entiteti.Musterije;
import entiteti.Vozaci;
import enumeracije.TipKorisnika;
import gui.aukcija.AukcijaPrikazVoznjiDispeceru;
import gui.aukcija.AukcijaPrikazVoznjiVozacu;
import gui.izvestaj.DnevniIzvestajProzor;
import gui.izvestaj.GodisnjiIzvestajProzor;
import gui.izvestaj.MesecniIzvestajProzor;
import gui.izvestaj.NedeljniIzvestajProzor;
import gui.prozoriZaNarucivanje.DodeljivanjeVoznjeProzor;
import gui.prozoriZaNarucivanje.NarucivanjeTelefonomProzor;
import gui.prozoriZaPretragu.BinarnaPretragaProzor;
import gui.prozoriZaPretragu.KombinovanaPretragaProzor;
import gui.prozoriZaPrikaz.*;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzor extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu meniZaposleni = new JMenu("Zaposleni");
    private JMenu meniVoznje = new JMenu("Voznje");
    private JMenu meniPretraga = new JMenu("Pretraga vozaca");
    private JMenu meniIzvestaj = new JMenu("Izvestaj");
    private JMenu meniNarucivanjeVoznje = new JMenu("Narucivanje voznje");
    private JMenu meniAukcija = new JMenu("Aukcija");
    private JMenuItem aukcijaVozacItem = new JMenuItem("Izaberi voznju");
    private JMenuItem aukcijaDispecerItem = new JMenuItem("Pokreni aukciju");
    private JMenuItem vozaciItem = new JMenuItem("Pregled vozaca");
    private JMenuItem dispeceriItem = new JMenuItem("Pregled dispecera");
    private JMenuItem voznjeItem = new JMenuItem("Pregled voznji");
    private JMenuItem dodeljivanjeVoznjeItem = new JMenuItem("Dodeljivanje voznje");
    private JMenuItem binarnaPretragaItem = new JMenuItem("Binarna pretraga");
    private JMenuItem kombinovanaPretragaItem = new JMenuItem("Kombinovana pretraga");
    private JMenuItem dnevniIzvestajItem = new JMenuItem("Dnevni");
    private JMenuItem nedeljniIzvestajItem = new JMenuItem("Nedeljni");
    private JMenuItem mesecniIzvestajItem = new JMenuItem("Mesecni");
    private JMenuItem godisnjiIzvestajItem = new JMenuItem("Godisnji");
    private JMenuItem narucivanjeTelefonomItem = new JMenuItem("Telefonom");
    private JMenuItem dodeljeneVoznjeItem = new JMenuItem("Dodeljene voznje");
    private JMenuItem zavrsiVoznjeItem = new JMenuItem("Zavrsi voznje");


    private Sluzba taxiSluzba;
    private Korisnici korisnik;
    private TipKorisnika uloga;

    public GlavniProzor(Sluzba taxiSluzba, Korisnici korisnik) {

        this.taxiSluzba = taxiSluzba;
        this.korisnik = korisnik;

        if(korisnik instanceof Musterije) {
            uloga = uloga.MUSTERIJA;
        }
        else if(korisnik instanceof Vozaci) {
            uloga = uloga.VOZAC;
        }
        else {
            uloga = uloga.DISPECER;
        }

        setTitle("Taxi Sluzba(Prijavljeni ste kao "+ uloga +": " + korisnik.getKorisnickoIme() + ")");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
    }

    private void initGUI() {
        if(uloga.equals(TipKorisnika.MUSTERIJA)) {
            setJMenuBar(mainMenu);
            mainMenu.add(meniNarucivanjeVoznje);
            meniNarucivanjeVoznje.add(narucivanjeTelefonomItem);
        }
        else if(uloga.equals(TipKorisnika.VOZAC)) {
            setJMenuBar(mainMenu);
            mainMenu.add(meniVoznje);
            mainMenu.add(meniAukcija);
            meniAukcija.add(aukcijaVozacItem);
            meniVoznje.add(dodeljeneVoznjeItem);
            meniVoznje.add(zavrsiVoznjeItem);
        }
        else{
            setJMenuBar(mainMenu);
            mainMenu.add(meniZaposleni);
            mainMenu.add(meniVoznje);
            mainMenu.add(meniIzvestaj);
            mainMenu.add(meniPretraga);
            mainMenu.add(meniAukcija);
            meniAukcija.add(aukcijaDispecerItem);
            meniZaposleni.add(vozaciItem);
            meniZaposleni.add(dispeceriItem);
            meniVoznje.add(voznjeItem);
            meniVoznje.add(dodeljivanjeVoznjeItem);
            meniIzvestaj.add(dnevniIzvestajItem);
            meniIzvestaj.add(nedeljniIzvestajItem);
            meniIzvestaj.add(mesecniIzvestajItem);
            meniIzvestaj.add(godisnjiIzvestajItem);
            meniPretraga.add(binarnaPretragaItem);
            meniPretraga.add(kombinovanaPretragaItem);
        }
    }

    private void initActions() {
        vozaciItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciPrikaz ap = new VozaciPrikaz(taxiSluzba);
                ap.setVisible(true);
            }
        });

        dispeceriItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DispeceriPrikaz dp = new DispeceriPrikaz(taxiSluzba);
                dp.setVisible(true);
            }
        });

        voznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjePrikaz vp = new VoznjePrikaz(taxiSluzba);
                vp.setVisible(true);
            }
        });

        narucivanjeTelefonomItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NarucivanjeTelefonomProzor nt = new NarucivanjeTelefonomProzor(taxiSluzba, (Musterije) korisnik);
                nt.setVisible(true);
            }
        });

        dodeljivanjeVoznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DodeljivanjeVoznjeProzor dv = new DodeljivanjeVoznjeProzor(taxiSluzba);
                dv.setVisible(true);
            }
        });

        dodeljeneVoznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DodeljeneVoznjePrikaz dv = new DodeljeneVoznjePrikaz(taxiSluzba, (Vozaci) korisnik);
                dv.setVisible(true);
            }
        });

        zavrsiVoznjeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PrihvaceneVoznjePrikaz pv = new PrihvaceneVoznjePrikaz(taxiSluzba, (Vozaci) korisnik);
                pv.setVisible(true);
            }
        });

        binarnaPretragaItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BinarnaPretragaProzor bp = new BinarnaPretragaProzor(taxiSluzba);
                bp.setVisible(true);
            }
        });

        kombinovanaPretragaItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KombinovanaPretragaProzor kp = new KombinovanaPretragaProzor(taxiSluzba);
                kp.setVisible(true);
            }
        });

        dnevniIzvestajItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DnevniIzvestajProzor di = new DnevniIzvestajProzor(taxiSluzba);
                di.setVisible(true);
            }
        });

        godisnjiIzvestajItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GodisnjiIzvestajProzor gi = new GodisnjiIzvestajProzor(taxiSluzba);
                gi.setVisible(true);
            }
        });

        mesecniIzvestajItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MesecniIzvestajProzor mi = new MesecniIzvestajProzor(taxiSluzba);
                mi.setVisible(true);
            }
        });

        nedeljniIzvestajItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NedeljniIzvestajProzor ni = new NedeljniIzvestajProzor(taxiSluzba);
                ni.setVisible(true);
            }
        });

        aukcijaVozacItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AukcijaPrikazVoznjiVozacu av = new AukcijaPrikazVoznjiVozacu(taxiSluzba, (Vozaci) korisnik);
                av.setVisible(true);
            }
        });

        aukcijaDispecerItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AukcijaPrikazVoznjiDispeceru av = new AukcijaPrikazVoznjiDispeceru(taxiSluzba);
                av.setVisible(true);
            }
        });
    }
}
