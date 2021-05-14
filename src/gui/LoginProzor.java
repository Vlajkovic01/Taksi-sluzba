package gui;

import entiteti.Dispeceri;
import entiteti.Musterije;
import entiteti.Vozaci;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel("Dobrodosli.Molimo da se prijavite.");
    private JLabel lblKorsicnikoIme = new JLabel("Korisnicko ime");
    private JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lblLozinka = new JLabel("Lozinka");
    private JPasswordField pfLozinka = new JPasswordField(20);
    private JButton btnPrijava = new JButton("Prijava");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;

    public LoginProzor(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Prijava na sistem");
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
        add(lblKorsicnikoIme);
        add(txtKorisnickoIme);
        add(lblLozinka);
        add(pfLozinka);
        add(new JLabel());
        add(btnPrijava, "split 2");
        add(btnOtkazi);

        txtKorisnickoIme.setText("stefo");
        pfLozinka.setText("12345");      // izbrisati ovu liniju i prethodnu, sluzi samo da bi se brze logovali.
        getRootPane().setDefaultButton(btnPrijava);
    }

    private void initActions(){
        btnOtkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginProzor.this.dispose();
                LoginProzor.this.setVisible(false);
            }
        });

        btnPrijava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnikoIme = txtKorisnickoIme.getText().trim();
                String lozinka = new String(pfLozinka.getPassword()).trim();

                if(korisnikoIme.equals("") || lozinka.equals("")) {
                    JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    Dispeceri prijavljenDispecer = taxiSluzba.loginDispecer(korisnikoIme, lozinka);
                    Vozaci prijavljenVozac = taxiSluzba.loginVozac(korisnikoIme, lozinka);
                    Musterije prijavljenMusterija = taxiSluzba.loginMusterija(korisnikoIme, lozinka);
                    if(prijavljenDispecer == null && prijavljenVozac == null && prijavljenMusterija == null) {
                        JOptionPane.showMessageDialog(null, "Pogresni podaci prijave.", "Greska", JOptionPane.WARNING_MESSAGE);
                    }else if(prijavljenMusterija != null){
                        LoginProzor.this.dispose();
                        LoginProzor.this.setVisible(false);
                        GlavniProzor glavniProzor = new GlavniProzor(taxiSluzba, prijavljenMusterija);
                        glavniProzor.setVisible(true);
                    }
                    else if(prijavljenVozac != null){
                        LoginProzor.this.dispose();
                        LoginProzor.this.setVisible(false);
                        GlavniProzor glavniProzor = new GlavniProzor(taxiSluzba, prijavljenVozac);
                        glavniProzor.setVisible(true);
                    }
                    else if(prijavljenDispecer != null){
                        LoginProzor.this.dispose();
                        LoginProzor.this.setVisible(false);
                        GlavniProzor glavniProzor = new GlavniProzor(taxiSluzba, prijavljenDispecer);
                        glavniProzor.setVisible(true);
                    }
                }

            }
        });
    }
}
