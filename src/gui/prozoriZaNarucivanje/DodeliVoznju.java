package gui.prozoriZaNarucivanje;

import entiteti.Musterije;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import enumeracije.TipPorudzbine;
import gui.prozoriZaDodavanjeIIzmenu.VoznjeDodajIzmeni;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodeliVoznju extends JFrame {

    private JLabel lblID = new JLabel("ID");
    private JTextField txtID = new JTextField(15);
    private JLabel lblVremePorudzbine = new JLabel("Vreme porudzbine");
    private JTextField txtVremePorudzbine = new JTextField(15);
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(15);
    private JLabel lblAdresaDestinacije = new JLabel("Adresa destinacije");
    private JTextField txtAdresaDestinacije= new JTextField(15);
    private JLabel lblMusterija = new JLabel("Musterija");
    private JComboBox<Integer> cbMusterije = new JComboBox<Integer>();
    private JLabel lblVozac = new JLabel("Vozac");
    private JComboBox<Integer> cbVozaci = new JComboBox<Integer>();
    private JLabel lblStatus = new JLabel("Status voznje");
    private JComboBox<StatusVoznje> cbStatus = new JComboBox<StatusVoznje>();
    private JLabel lblTipPorudzbine = new JLabel("Poruceno");
    private JComboBox<TipPorudzbine> cbTipPorudzbine = new JComboBox<TipPorudzbine>();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Voznja voznja;
    private Sluzba taxiSluzba;

    public DodeliVoznju(Sluzba taxiSluzba, Voznja voznja) {
        this.taxiSluzba = taxiSluzba;
        this.voznja = voznja;
        if(this.voznja != null) {
            setTitle("Dodavanje vozaca voznji - " + this.voznja.getId());
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initGUI();
        initActions();
        pack();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        cbStatus.setModel(new DefaultComboBoxModel<>(StatusVoznje.values()));
        cbStatus.setSelectedIndex(0);
        cbTipPorudzbine.setModel(new DefaultComboBoxModel<>(TipPorudzbine.values()));
        cbTipPorudzbine.setSelectedIndex(0);

        for(Vozaci vozac: taxiSluzba.getVozaci()) {
            if (!vozac.isIzbrisan() && vozac.getId() != 0) {
                cbVozaci.addItem(vozac.getId());
            }
        }

        for(Musterije musterija: taxiSluzba.getMusterije()) {
            cbMusterije.addItem(musterija.getId());
        }

        if(this.voznja != null) {
            popuniPolja();
        }

        add(lblVremePorudzbine);
        add(txtVremePorudzbine);
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDestinacije);
        add(txtAdresaDestinacije);
        add(lblMusterija);
        add(cbMusterije);
        add(lblVozac);
        add(cbVozaci);
        add(lblStatus);
        add(cbStatus);
        add(lblTipPorudzbine);
        add(cbTipPorudzbine);

        txtVremePorudzbine.setEditable(false);
        txtAdresaPolaska.setEditable(false);
        txtAdresaDestinacije.setEditable(false);
        cbMusterije.setEnabled(false);
        cbStatus.setEnabled(false);
        cbTipPorudzbine.setEnabled(false);

        add(new JLabel());
        add(btnOk, "split");
        add(btnCancel);
        txtID.setText(String.valueOf(taxiSluzba.generisanjeIDVoznje()));
    }

    private void initActions() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija()) {
                    int ID = taxiSluzba.generisanjeIDVoznje();
                    String vremePorudzbine = txtVremePorudzbine.getText();
                    String adresaPolaska = txtAdresaPolaska.getText();
                    String adresaDestinacije = txtAdresaDestinacije.getText();
                    Musterije musterija = taxiSluzba.pronadjiMusterijuString(cbMusterije.getSelectedItem().toString());
                    Vozaci vozac = taxiSluzba.pronadjiVozacaString(cbVozaci.getSelectedItem().toString());
                    StatusVoznje status = StatusVoznje.DODELJENA;
                    TipPorudzbine tipPorudzbine = TipPorudzbine.valueOf(cbTipPorudzbine.getSelectedItem().toString());

                    if(voznja != null) {
                        voznja.setDatumIVremePorudzbine(vremePorudzbine);
                        voznja.setAdresaPolaska(adresaPolaska);
                        voznja.setAdresaDestinacije(adresaDestinacije);
                        voznja.setMusterija(musterija);
                        voznja.setVozac(vozac);
                        voznja.setStatusVoznje(status);
                        voznja.setTipPorudzbine(tipPorudzbine);
                        JOptionPane.showMessageDialog(null, "Uspesno dodeljen vozac!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }

                    DodeliVoznju.this.dispose();
                    DodeliVoznju.this.setVisible(false);
                    taxiSluzba.snimiVoznje();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DodeliVoznju.this.dispose();
                DodeliVoznju.this.setVisible(false);
            }
        });
    }

    private void popuniPolja() {
        txtID.setText(String.valueOf(voznja.getId()));
        txtVremePorudzbine.setText(voznja.getDatumIVremePorudzbine());
        txtAdresaPolaska.setText(voznja.getAdresaPolaska());
        txtAdresaDestinacije.setText(voznja.getAdresaDestinacije());
        cbMusterije.setSelectedItem(voznja.getMusterija().getId());
        cbVozaci.setSelectedItem(voznja.getVozac().getId());
        cbStatus.setSelectedItem(voznja.getStatusVoznje());
        cbTipPorudzbine.setSelectedItem(voznja.getTipPorudzbine());
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        try {
            Integer.parseInt(txtID.getText());
        }catch (NumberFormatException e) {
            poruka += "ID mora biti broj\n";
            ispravno = false;
        }
        if(txtID.getText().trim().equals("")) {
            poruka += "Morate uneti ID\n";
            ispravno = false;
        }
        else if(voznja == null) {
            String id = txtID.getText().trim();
            Voznja pronadjena = taxiSluzba.pronadjiVoznjuString(id);
            if(pronadjena != null) {
                poruka += "- Voznja sa unetim ID vec postoji\n";
                ispravno = false;
            }
        }

        if(txtAdresaPolaska.getText().trim().equals("")) {
            poruka += "Morate uneti adresu polaska\n";
            ispravno = false;
        }
        if(txtAdresaDestinacije.getText().trim().equals("")) {
            poruka += "Morate uneti adresu destinacije\n";
            ispravno = false;
        }
        if(txtVremePorudzbine.getText().trim().equals("")) {
            poruka += "Morate uneti vreme porudzbine\n";
            ispravno = false;
        }
        if (cbVozaci.getSelectedItem().toString() == "0") {
            poruka += "Morate izabrati vozaca\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
