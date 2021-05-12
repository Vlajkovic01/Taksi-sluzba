package gui;

import entiteti.Korisnici;
import entiteti.Musterije;
import entiteti.Vozaci;
import enumeracije.TipKorisnika;
import gui.prozoriZaPrikaz.DispeceriPrikaz;
import gui.prozoriZaPrikaz.VozaciPrikaz;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlavniProzor extends JFrame {

    private JMenuBar mainMenu = new JMenuBar();
    private JMenu prikazMeni = new JMenu("Pregled");
    private JMenuItem vozaciItem = new JMenuItem("Pregled vozaca");
    private JMenuItem dispeceriItem = new JMenuItem("Pregled dispecera");
    private JMenuItem voznjeItem = new JMenuItem("Pregled voznji");

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
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
    }

    private void initGUI() {
        if(uloga.equals(TipKorisnika.MUSTERIJA)) {
            setJMenuBar(mainMenu);
            mainMenu.add(prikazMeni);
        }
        else if(uloga.equals(TipKorisnika.VOZAC)) {
            setJMenuBar(mainMenu);
            mainMenu.add(prikazMeni);
        }
        else{
            setJMenuBar(mainMenu);
            mainMenu.add(prikazMeni);
            prikazMeni.add(vozaciItem);
            prikazMeni.add(dispeceriItem);
            prikazMeni.add(voznjeItem);
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
        //voznjeItem
    }
}
