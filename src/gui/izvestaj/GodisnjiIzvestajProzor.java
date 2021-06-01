package gui.izvestaj;

import entiteti.Voznja;
import gui.prozoriZaPrikaz.IzvestajPrikaz;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import strukture.ArrayList;

public class GodisnjiIzvestajProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel();
    private JLabel lblDatum = new JLabel("Unesi godinu:");
    private JFormattedTextField txtDatum = new JFormattedTextField("yyyy");
    private JButton btnPretrazi = new JButton("Pretrazi");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;

    public GodisnjiIzvestajProzor(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Godisnji izvestaj");
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
        add(lblDatum);
        add(txtDatum);
        txtDatum.setColumns(15);
        add(new JLabel());
        add(btnPretrazi, "split 2");
        add(btnOtkazi);
        getRootPane().setDefaultButton(btnPretrazi);
    }

    private void initActions(){
        btnOtkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GodisnjiIzvestajProzor.this.dispose();
                GodisnjiIzvestajProzor.this.setVisible(false);
            }
        });

        btnPretrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    String datum = txtDatum.getText();
                    ArrayList<Voznja> pronadjenaVoznja = taxiSluzba.izvestaj(datum,6,10);
                    if (pronadjenaVoznja.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ne postoje voznje za tu godinu!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        IzvestajPrikaz di = new IzvestajPrikaz(taxiSluzba, pronadjenaVoznja);
                        di.setVisible(true);
                    }

                    GodisnjiIzvestajProzor.this.dispose();
                    GodisnjiIzvestajProzor.this.setVisible(false);
                }
            }
        });
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        if (txtDatum.getText().trim().equals("")) {
            poruka += "Morate uneti datum za pretragu\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
