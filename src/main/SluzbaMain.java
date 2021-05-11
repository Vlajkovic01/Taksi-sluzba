package main;

import gui.LoginProzor;
import sluzba.Sluzba;

public class SluzbaMain {
    public static void main(String[] args) {
        Sluzba sluzba = new Sluzba();
        sluzba.ucitajAutomobile();
        sluzba.ucitajDispecere();
        sluzba.ucitajVozace();
        sluzba.ucitajMusterije();
        sluzba.ucitajVoznje();

        LoginProzor loginProzor = new LoginProzor(sluzba);
        loginProzor.setVisible(true);
    }
}
