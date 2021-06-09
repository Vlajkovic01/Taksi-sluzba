package gui.prozoriZaNarucivanje;


import entiteti.*;
import enumeracije.Pol;
import enumeracije.StatusVoznje;
import enumeracije.TipKorisnika;
import enumeracije.TipPorudzbine;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import strukture.ArrayList;
import java.util.Date;

public class NarucivanjeTelefonomProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel();
    private JLabel lblAdresaPolaska = new JLabel(" Adresa polaska:");
    private JTextField txtAdresaPolaska = new JTextField(20);
    private JLabel lblAdresaDestinacije = new JLabel(" Adresa destinacije:");
    private JTextField txtAdresaDestinacije = new JTextField(20);
    private JCheckBox novaVozilaCheckBox = new JCheckBox("Zahtevaj novija vozila ");
    private JButton btnNaruci = new JButton("Naruci");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;
    private Musterije musterija;

    public NarucivanjeTelefonomProzor(Sluzba taxiSluzba, Musterije musterija) {
        this.taxiSluzba = taxiSluzba;
        this.musterija = musterija;
        setTitle("Naruci voznju telefonom");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        pack();
    }

    private void initGUI(){
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);

        novaVozilaCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);

        add(lblPozdrav,"span 2");
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDestinacije);
        add(txtAdresaDestinacije);
        add(novaVozilaCheckBox);
        add(new JLabel());
        add(new JLabel());
        add(btnNaruci, "split 2");
        add(btnOtkazi);

        getRootPane().setDefaultButton(btnNaruci);
    }

    private void initActions(){
        btnOtkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NarucivanjeTelefonomProzor.this.dispose();
                NarucivanjeTelefonomProzor.this.setVisible(false);
            }
        });

        btnNaruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    int id = taxiSluzba.generisanjeIDVoznje();
                    String vremePorudzbine = new SimpleDateFormat("dd-MM-yyyy/HH:mm").format(new Date());
                    String adresaPolaska = txtAdresaPolaska.getText();
                    String adresaDestinacije = txtAdresaDestinacije.getText();
                    Vozaci vozac = new Vozaci("","","","","","",Pol.MUSKO,"",TipKorisnika.VOZAC,false,0,0,0,new Automobil(),new ArrayList<>());
                    double predjeniKm = 0;
                    double trajanjeVoznje = 0;
                    StatusVoznje status = StatusVoznje.KREIRANA;
                    TipPorudzbine tipPorudzbine = TipPorudzbine.TELEFONOM;
                    Voznja voznja = new Voznja(id,vremePorudzbine,adresaPolaska,adresaDestinacije,musterija,vozac,predjeniKm,trajanjeVoznje,status,tipPorudzbine,false);
                    taxiSluzba.dodajVoznju(voznja);
                    JOptionPane.showMessageDialog(null, "Uspesno narucena voznja!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    taxiSluzba.snimiVoznje();
                    NarucivanjeTelefonomProzor.this.dispose();
                    NarucivanjeTelefonomProzor.this.setVisible(false);
                }
            }
        });
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        if(txtAdresaPolaska.getText().trim().equals("")) {
            poruka += "-Morate uneti adresu polaska\n";
            ispravno = false;
        }
        if(txtAdresaDestinacije.getText().trim().equals("")) {
            poruka += "-Morate uneti adresu destinacije\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }

        return ispravno;
    }
}
