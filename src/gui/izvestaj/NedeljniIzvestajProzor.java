package gui.izvestaj;

import gui.prozoriZaPrikaz.IzvestajPrikaz;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NedeljniIzvestajProzor extends JFrame {

    private JLabel lblPozdrav = new JLabel();
    private JLabel lblDatum = new JLabel("Unesi mesec i godinu:");
    private JFormattedTextField txtDatum = new JFormattedTextField("MM-yyyy");
    private JButton btnPretrazi = new JButton("Pretrazi");
    private JButton btnOtkazi = new JButton("Otkazi");

    private Sluzba taxiSluzba;

    public NedeljniIzvestajProzor(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Nedeljni izvestaj");
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
                NedeljniIzvestajProzor.this.dispose();
                NedeljniIzvestajProzor.this.setVisible(false);
            }
        });

        btnPretrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija()) {
                    String datum = txtDatum.getText();
                    ArrayList pronadjenaVoznja = taxiSluzba.nedeljniIzvestaj(datum);
                    if (pronadjenaVoznja.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ne postoje voznje za tu nedelju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        IzvestajPrikaz di = new IzvestajPrikaz(taxiSluzba, pronadjenaVoznja);
                        di.setVisible(true);
                    }

                    NedeljniIzvestajProzor.this.dispose();
                    NedeljniIzvestajProzor.this.setVisible(false);
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
