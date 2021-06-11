package gui.prozoriZaPretragu;

import entiteti.Vozaci;
import gui.prozoriZaPrikaz.KombinovanaPretragaPrikaz;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import strukture.ArrayList;


public class KombinovanaPretragaProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel();
    private JLabel lblIme = new JLabel("Unesi ime za pretragu:");
    private JTextField txtIme = new JTextField(20);
    private JLabel lblPrezime = new JLabel("Unesi prezime za pretragu:");
    private JTextField txtPrezime = new JTextField(20);
    private JLabel lblPlata = new JLabel("Unesi platu za pretragu:");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblAutomobil = new JLabel("Unesi automobil(proizvodjac):");
    private JTextField txtAutomobil = new JTextField(20);
    private JButton btnPretrazi = new JButton("Pretrazi");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;

    public KombinovanaPretragaProzor(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Pretrazi vozace");
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
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblPlata);
        add(txtPlata);
        add(lblAutomobil);
        add(txtAutomobil);
        add(new JLabel());
        add(btnPretrazi, "split 2");
        add(btnOtkazi);

        getRootPane().setDefaultButton(btnPretrazi);
    }

    private void initActions(){
        btnOtkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KombinovanaPretragaProzor.this.dispose();
                KombinovanaPretragaProzor.this.setVisible(false);
            }
        });

        btnPretrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    String ime = txtIme.getText();
                    String prezime = txtPrezime.getText();
                    String plata = txtPlata.getText();
                    String automobil = txtAutomobil.getText();
                    ArrayList<Vozaci> pronadjenVozac = taxiSluzba.kombinovanaPretragaVozaca(ime, prezime, plata, automobil);
                    if (pronadjenVozac.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ne postoji vozac sa unetim parametrima!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        KombinovanaPretragaPrikaz kp = new KombinovanaPretragaPrikaz(taxiSluzba, pronadjenVozac);
                        kp.setVisible(true);
                    }

                    KombinovanaPretragaProzor.this.dispose();
                    KombinovanaPretragaProzor.this.setVisible(false);
                }
            }
        });
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        if (txtIme.getText().trim().equals("") && txtPrezime.getText().trim().equals("") && txtPlata.getText().trim().equals("") && txtAutomobil.getText().trim().equals("")) {
            poruka += "-Morate uneti bar jedan parametar za pretragu\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
