package gui.prozoriZaDodavanjeIIzmenu;

import entiteti.Musterije;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import enumeracije.TipPorudzbine;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoznjeDodajIzmeni extends JFrame {

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
    private JLabel lblPredjeniKm = new JLabel("Predjeni km");
    private JTextField txtPredjeniKm = new JTextField(15);
    private JLabel lblTrajanjeVoznje = new JLabel("Trajanje voznje");
    private JTextField txtTrajanjeVoznje = new JTextField(15);
    private JLabel lblStatus = new JLabel("Status voznje");
    private JComboBox<StatusVoznje> cbStatus = new JComboBox<StatusVoznje>();
    private JLabel lblTipPorudzbine = new JLabel("Poruceno");
    private JComboBox<TipPorudzbine> cbTipPorudzbine = new JComboBox<TipPorudzbine>();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Voznja voznja;
    private Sluzba taxiSluzba;

    public VoznjeDodajIzmeni(Sluzba taxiSluzba, Voznja voznja) {
        this.taxiSluzba = taxiSluzba;
        this.voznja = voznja;
        if(this.voznja == null) {
            setTitle("Dodavanje voznje");
        }else {
            setTitle("Izmena podataka - " + this.voznja.getId());

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
            if (!vozac.isIzbrisan()) {
                cbVozaci.addItem(vozac.getId());
            }
        }


        for(Musterije musterija: taxiSluzba.getMusterije()) {
            cbMusterije.addItem(musterija.getId());
        }

        if(this.voznja != null) {
            popuniPolja();
            cbVozaci.setSelectedItem(voznja.getVozac().getId());
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
        add(lblPredjeniKm);
        add(txtPredjeniKm);
        add(lblTrajanjeVoznje);
        add(txtTrajanjeVoznje);
        add(lblStatus);
        add(cbStatus);
        add(lblTipPorudzbine);
        add(cbTipPorudzbine);

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
                    Musterije musterija = taxiSluzba.pronadjiMusteriju(Integer.parseInt(cbMusterije.getSelectedItem().toString()));
                    Vozaci vozac = taxiSluzba.pronadjiVozaca(Integer.parseInt(cbVozaci.getSelectedItem().toString()));
//                    Musterije musterija = taxiSluzba.pronalazenjeMusterije(ID); //nova metoda, resiti problem
//                    Vozaci vozac = taxiSluzba.pronalazenjeVozaca(ID); //nova metoda, resiti problem
                    double predjeniKm = Double.parseDouble(txtPredjeniKm.getText());
                    double trajanjeVoznje = Double.parseDouble(txtTrajanjeVoznje.getText());
                    StatusVoznje status = StatusVoznje.valueOf(cbStatus.getSelectedItem().toString());
                    TipPorudzbine tipPorudzbine = TipPorudzbine.valueOf(cbTipPorudzbine.getSelectedItem().toString());

                    if(voznja == null) {
                        Voznja voznja = new Voznja(ID,vremePorudzbine,adresaPolaska,adresaDestinacije,musterija,vozac,predjeniKm,trajanjeVoznje,status,tipPorudzbine,false);
                        taxiSluzba.dodajVoznju(voznja);
                        JOptionPane.showMessageDialog(null, "Uspesno kreirana voznja!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        voznja.setDatumIVremePorudzbine(vremePorudzbine);
                        voznja.setAdresaPolaska(adresaPolaska);
                        voznja.setAdresaDestinacije(adresaDestinacije);
                        voznja.setMusterija(musterija);
                        voznja.setVozac(vozac);
                        voznja.setPredjeniKm(predjeniKm);
                        voznja.setTrajanjeVoznje(trajanjeVoznje);
                        voznja.setStatusVoznje(status);
                        voznja.setTipPorudzbine(tipPorudzbine);

                        JOptionPane.showMessageDialog(null, "Izmene su sacuvane!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }

                    VoznjeDodajIzmeni.this.dispose();
                    VoznjeDodajIzmeni.this.setVisible(false);
                    taxiSluzba.snimiVoznje();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjeDodajIzmeni.this.dispose();
                VoznjeDodajIzmeni.this.setVisible(false);
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
        txtPredjeniKm.setText(String.valueOf(voznja.getPredjeniKm()));
        txtTrajanjeVoznje.setText(String.valueOf(voznja.getTrajanjeVoznje()));
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
            Voznja pronadjena = taxiSluzba.pronadjiVoznju(Integer.parseInt(id));
//            Voznja pronadjena = taxiSluzba.pronalazenjeVoznje(Integer.parseInt(id)); //nova metoda, resiti problem
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

        try {
            Double.parseDouble(txtPredjeniKm.getText());
        }catch (NumberFormatException e) {
            poruka += "- Predjeni km mora biti broj\n";
            ispravno = false;
        }
        try {
            Double.parseDouble(txtTrajanjeVoznje.getText());
        }catch (NumberFormatException e) {
            poruka += "- Trajanje voznje mora biti\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
