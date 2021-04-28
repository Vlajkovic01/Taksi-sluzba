package main;

import entiteti.*;
import sluzba.Sluzba;

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
