package main;

import entiteti.*;
import enumeracije.*;
import sluzba.Sluzba;

import java.util.ArrayList;

public class SluzbaMain {
    public static void main(String[] args) {
        Sluzba sluzba = new Sluzba();
        sluzba.ucitajAutomobile();
        sluzba.ucitajDispecere();
        sluzba.ucitajVozace();
        sluzba.ucitajMusterije();
        sluzba.ucitajVoznje();

        System.out.println("PODACI UCITANI IZ DATOTEKA:");
        System.out.println("------------------------------------------------------------------------------------------");
        ispisiSvePodatke(sluzba);
        System.out.println("------------------------------------------------------------------------------------------");

        System.out.println("Dodavanje test podataka, testiranje upisivanja u fajl...");

        Automobil testAutomobil = new Automobil("TestMarka","TestProizvodjac",2021,"TestReg",108, TipVozila.KOMBI,false,false);

        Dispeceri testDispecer = new Dispeceri("test", "test","test","test","test","test", Pol.MUSKO,"test", TipKorisnika.DISPECER,false,5,50000,"123", Odeljenje.PRIJEM_VOZNJI);

        Vozaci testVozac = new Vozaci("test","test","test","test","test","test",Pol.MUSKO,"test",TipKorisnika.VOZAC,false,10,5000,555,testAutomobil, new ArrayList<Voznja>());

        Musterije testMusterija = new Musterije("test", "test","test","test","test","test", Pol.MUSKO,"test", TipKorisnika.MUSTERIJA,false,5,new ArrayList<Voznja>());

        Voznja testVoznja = new Voznja(10,"10-10-2010/22:00","test","test",testMusterija,testVozac,100,30, StatusVoznje.PRIHVACENA);

        System.out.println("Snimanje novih podataka...\n");

        testMusterija.getVoznjeMusterije().add(testVoznja);
        testVozac.getVoznjeVozaca().add(testVoznja);

        sluzba.dodajAutomobil(testAutomobil);
        sluzba.dodajDispecera(testDispecer);
        sluzba.dodajMusteriju(testMusterija);
        sluzba.dodajVozaca(testVozac);
        sluzba.dodajVoznju(testVoznja);

        sluzba.snimiAutomobile();
        sluzba.snimiDispecere();
        sluzba.snimiMusterije();
        sluzba.snimiVozace();
        sluzba.snimiVoznje();

    }

    public static void ispisiSvePodatke(Sluzba sluzba) {
        for(Dispeceri dispecer : sluzba.getDispeceri()) {
            System.out.println(dispecer + "\n");
        }

        for(Vozaci vozac : sluzba.getVozaci()) {
            System.out.println(vozac + "\n");
        }

        for(Musterije musterija : sluzba.getMusterije()) {
            System.out.println(musterija + "\n");
        }

        for(Automobil automobil : sluzba.getAutomobili()) {
            System.out.println(automobil + "\n");
        }
        for(Voznja voznja : sluzba.getVoznje()) {
            System.out.println(voznja + "\n");
        }
    }
}
