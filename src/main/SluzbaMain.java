package main;

import gui.LoginProzor;
import sluzba.Sluzba;

public class SluzbaMain {
    public static void main(String[] args) {

        /*u sistemu ako vozac kao id vozila ima 0 znaci da nema auto,
        voznja ako ima vozaca sa id 0 znaci da nije dodeljen vozac,
        vozac ako ima prosecnu ocenu 0 znaci da ga jos niko nije ocenio,
        voznja ako ima ocenu 0 znaci da nije ocenjena
         */

        Sluzba sluzba = new Sluzba();
        sluzba.ucitajAutomobile();
        sluzba.ucitajDispecere();
        sluzba.ucitajVozace();
        sluzba.ucitajMusterije();
        sluzba.ucitajVoznje();
        sluzba.ucitajAukcije();

        LoginProzor loginProzor = new LoginProzor(sluzba);
        loginProzor.setVisible(true); //postavljeni su korIme i lozinka radi brzeg logovanja i testiranja, u loginProzor fajlu izmeniti liniju 48 i 49
    }
}
