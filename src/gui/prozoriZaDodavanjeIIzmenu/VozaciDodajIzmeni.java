package gui.prozoriZaDodavanjeIIzmenu;

import entiteti.Automobil;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.Pol;
import enumeracije.TipKorisnika;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VozaciDodajIzmeni extends JFrame {

    private JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime");
    private JTextField txtKorisnickoIme = new JTextField(15);
    private JLabel lblLozinka = new JLabel("Lozinka");
    private JTextField txtLozinka = new JTextField(15);
    private JLabel lblIme = new JLabel("Ime");
    private JTextField txtIme = new JTextField(15);
    private JLabel lblPrezime = new JLabel("Prezime");
    private JTextField txtPrezime= new JTextField(15);
    private JLabel lblJmbg = new JLabel("JMBG");
    private JTextField txtJmbg = new JTextField(15);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(15);
    private JLabel lblPol = new JLabel("Pol");
    private JComboBox<Pol> cbPol = new JComboBox<Pol>();
    private JLabel lblTelefon = new JLabel("Telefon");
    private JTextField txtTelefon = new JTextField(15);
    private JLabel lblUloga = new JLabel("Uloga");
    private JComboBox<TipKorisnika> cbUloga = new JComboBox<TipKorisnika>();
    private JLabel lblID = new JLabel("ID");
    private JTextField txtID = new JTextField(15);
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(15);
    private JLabel lblClanskaKarta = new JLabel("Clanska Karta");
    private JTextField txtClanskaKarta = new JTextField(15);
    private JLabel lblAutomobil = new JLabel("Automobil");
    private JComboBox<Integer> cbAutomobil = new JComboBox<Integer>();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Vozaci vozac;
    private Sluzba taxiSluzba;

    public VozaciDodajIzmeni(Sluzba taxiSluzba, Vozaci vozac) {
        this.taxiSluzba = taxiSluzba;
        this.vozac = vozac;
        if(this.vozac == null) {
            setTitle("Dodavanje vozaca");
        }else {
            setTitle("Izmena podataka - " + this.vozac.getId());

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
        cbPol.setModel(new DefaultComboBoxModel<>(Pol.values()));
        cbUloga.setModel(new DefaultComboBoxModel<>(TipKorisnika.values()));
        cbUloga.setSelectedIndex(0);

        for(Automobil auto: taxiSluzba.getAutomobili()) {
            if (auto.isSlobodan()) {
                cbAutomobil.addItem(auto.getIdVozila());
            }
        }
        if(this.vozac != null) {
            popuniPolja();
            cbAutomobil.addItem(vozac.getAutomobil().getIdVozila());
            cbAutomobil.setSelectedItem(vozac.getAutomobil().getIdVozila());
        }

        add(lblKorisnickoIme);
        add(txtKorisnickoIme);
        add(lblLozinka);
        add(txtLozinka);
        add(lblIme);
        add(txtIme);
        add(lblPrezime);
        add(txtPrezime);
        add(lblJmbg);
        add(txtJmbg);
        add(lblAdresa);
        add(txtAdresa);
        add(lblPol);
        add(cbPol);
        add(lblTelefon);
        add(txtTelefon);
        add(lblUloga);
        add(cbUloga);
        add(lblID);
        add(txtID);
        add(lblPlata);
        add(txtPlata);
        add(lblClanskaKarta);
        add(txtClanskaKarta);
        add(lblAutomobil);
        add(cbAutomobil);

        add(new JLabel());
        add(btnOk, "split");
        add(btnCancel);
        if(vozac !=null) {
            txtID.setEditable(false);
        }
    }

    private void initActions() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija()) {
                    String korisnickoIme = txtKorisnickoIme.getText();
                    String lozinka = txtLozinka.getText();
                    String ime = txtIme.getText();
                    String prezime = txtPrezime.getText();
                    String jmbg = txtJmbg.getText();
                    String adresa = txtAdresa.getText();
                    Pol pol = Pol.valueOf(cbPol.getSelectedItem().toString());
                    String  telefon = txtTelefon.getText();
                    TipKorisnika uloga = TipKorisnika.valueOf(cbUloga.getSelectedItem().toString());
                    int ID = Integer.parseInt(txtID.getText());
                    double plata  = Double.parseDouble(txtPlata.getText());
                    int brClanskeKarte = Integer.parseInt(txtClanskaKarta.getText());
                    Automobil automobil = taxiSluzba.pronadjiAutomobilString(cbAutomobil.getSelectedItem().toString());

                    if(vozac == null) {
                        Vozaci vozac = new Vozaci(korisnickoIme,lozinka,ime,prezime,jmbg,adresa,pol,telefon,uloga,false,ID,plata,brClanskeKarte,automobil,new ArrayList<Voznja>());
                        taxiSluzba.dodajVozaca(vozac);
                        JOptionPane.showMessageDialog(null, "Uspesno kreiran vozac!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        vozac.setKorisnickoIme(korisnickoIme);
                        vozac.setLozinka(lozinka);
                        vozac.setIme(ime);
                        vozac.setPrezime(prezime);
                        vozac.setJmbg(jmbg);
                        vozac.setAdresa(adresa);
                        vozac.setPol(pol);
                        vozac.setTelefon(telefon);
                        vozac.setTipKorisnika(uloga);
                        vozac.setId(ID);
                        vozac.setPlata(plata);
                        vozac.setBrClanskeKarte(brClanskeKarte);
                        vozac.setAutomobil(automobil);
                        vozac.setVoznjeVozaca(new ArrayList<Voznja>());
                        JOptionPane.showMessageDialog(null, "Izmene su sacuvane!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }

                    VozaciDodajIzmeni.this.dispose();
                    VozaciDodajIzmeni.this.setVisible(false);
                    vozac.getAutomobil().setSlobodan(false);
                    taxiSluzba.snimiAutomobile();
                    taxiSluzba.snimiVozace();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciDodajIzmeni.this.dispose();
                VozaciDodajIzmeni.this.setVisible(false);
            }
        });
    }

    private void popuniPolja() {
        txtKorisnickoIme.setText(vozac.getKorisnickoIme());
        txtLozinka.setText(vozac.getLozinka());
        txtIme.setText(vozac.getIme());
        txtPrezime.setText(vozac.getPrezime());
        txtJmbg.setText(vozac.getJmbg());
        txtAdresa.setText(vozac.getAdresa());
        cbPol.setSelectedItem(vozac.getPol());
        txtTelefon.setText(vozac.getTelefon());
        cbUloga.setSelectedItem(vozac.getTipKorisnika());
        txtID.setText(String.valueOf(vozac.getId()));
        txtPlata.setText(String.valueOf(vozac.getPlata()));
        txtClanskaKarta.setText(String.valueOf(vozac.getBrClanskeKarte()));
        cbAutomobil.setSelectedItem(vozac.getAutomobil().getIdVozila());
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
        else if(vozac == null) {
            String id = txtID.getText().trim();
            Vozaci pronadjen = taxiSluzba.pronadjiVozacaString(id);
            if(pronadjen != null) {
                poruka += "- Vozac sa unetim ID vec postoji\n";
                ispravno = false;
            }
        }

        if(txtIme.getText().trim().equals("")) {
            poruka += "Morate uneti ime\n";
            ispravno = false;
        }
        if(txtPrezime.getText().trim().equals("")) {
            poruka += "Morate uneti prezime\n";
            ispravno = false;
        }
        if(txtJmbg.getText().trim().equals("")) {
            poruka += "Morate uneti jmbg\n";
            ispravno = false;
        }
        if(txtJmbg.getText().trim().length() < 13 || txtJmbg.getText().trim().length() > 13) {
            poruka += "JMBG mora sadrzati 13 cifara\n";
            ispravno = false;
        }
        if(txtAdresa.getText().trim().equals("")) {
            poruka += "Morate uneti adresu\n";
            ispravno = false;
        }
        if(txtTelefon.getText().trim().equals("")) {
            poruka += "Morate uneti broj telefona\n";
            ispravno = false;
        }
        if(txtKorisnickoIme.getText().trim().equals("")) {
            poruka += "Morate uneti korisnicko ime\n";
            ispravno = false;
        }
        try {
            Long.parseLong(txtJmbg.getText());
        }catch (NumberFormatException e) {
            poruka += "- JMBG mora biti broj\n";
            ispravno = false;
        }
        if(txtLozinka.getText().trim().equals("")) {
            poruka += "Morate uneti lozinku\n";
            ispravno = false;
        }
        try {
            Double.parseDouble(txtPlata.getText());
        }catch (NumberFormatException e) {
            poruka += "- Plata mora biti broj\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }
}
