package main;

import entiteti.*;
import enumeracije.*;
import gui.LoginProzor;
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

        LoginProzor loginProzor = new LoginProzor(sluzba);
        loginProzor.setVisible(true);


    }
}
