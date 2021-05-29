package main;

import gui.LoginProzor;
import sluzba.Sluzba;

public class SluzbaMain {
    public static void main(String[] args) {
        //u sistemu ako vozac kao id vozila ima 0 znaci da nema auto, voznja ako ima vozaca sa id 0 znaci da nije dodeljen vozac.
        Sluzba sluzba = new Sluzba();
        sluzba.ucitajAutomobile();
        sluzba.ucitajDispecere();
        sluzba.ucitajVozace();
        sluzba.ucitajMusterije();
        sluzba.ucitajVoznje();

        LoginProzor loginProzor = new LoginProzor(sluzba);
        loginProzor.setVisible(true); //postavljeni su korIme i lozinka radi brzeg logovanja i testiranja, u loginProzor fajlu izmeniti liniju 48 i 49
    }
}
