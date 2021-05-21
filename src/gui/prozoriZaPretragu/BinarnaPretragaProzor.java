package gui.prozoriZaPretragu;

import entiteti.Vozaci;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


public class BinarnaPretragaProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel();
    private JLabel lblID = new JLabel("Unesi ID za pretragu:");
    private JTextField txtID = new JTextField(20);
    private JButton btnPretrazi = new JButton("Pretrazi");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;

    public BinarnaPretragaProzor(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Pretrazi vozace po ID-u");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        pack();
    }

    private void initGUI(){
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);

        add(lblPozdrav,"span 2");
        add(lblID);
        add(txtID);
        add(new JLabel());
        add(btnPretrazi, "split 2");
        add(btnOtkazi);

        getRootPane().setDefaultButton(btnPretrazi);
    }

    private void initActions(){

        ArrayList vozaci = taxiSluzba.integerListaVozaca();

        btnOtkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinarnaPretragaProzor.this.dispose();
                BinarnaPretragaProzor.this.setVisible(false);
            }
        });

        btnPretrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    int ID = Integer.parseInt(txtID.getText());
                    Vozaci pronadjenID = taxiSluzba.pronalazenjeVozaca(vozaci,ID);
                    if (pronadjenID == null) {
                        JOptionPane.showMessageDialog(null, "Ne postoji vozac sa tim ID-jem!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"Vozac sa tim ID-jem: \n" + "Ime: " + pronadjenID.getIme() + "\nAuto:" + pronadjenID.getAutomobil().getModel() + " " + pronadjenID.getAutomobil().getProizvodjac());
                    }

                    BinarnaPretragaProzor.this.dispose();
                    BinarnaPretragaProzor.this.setVisible(false);
                }
            }
        });
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        try {
            Integer.parseInt(txtID.getText());
        }catch (NumberFormatException e) {
            poruka += "-ID mora biti broj\n";
            ispravno = false;
        }
        if(txtID.getText().trim().equals("")) {
            poruka += "-Morate uneti ID\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
