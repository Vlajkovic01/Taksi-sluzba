package gui.prozoriZaDodavanjeIIzmenu;

import entiteti.Dispeceri;
import entiteti.Vozaci;
import enumeracije.Odeljenje;
import enumeracije.Pol;
import enumeracije.TipKorisnika;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispeceriDodajIzmeni extends JFrame {

    private JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime");
    private JTextField txtKorisnickoIme = new JTextField(10);
    private JLabel lblLozinka = new JLabel("Lozinka");
    private JTextField txtLozinka = new JTextField(15);
    private JLabel lblIme = new JLabel("Ime");
    private JTextField txtIme = new JTextField(15);
    private JLabel lblPrezime = new JLabel("Prezime");
    private JTextField txtPrezime= new JTextField(15);
    private JLabel lblJmbg = new JLabel("JMBG");
    private JTextField txtJmbg = new JTextField(13);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(15);
    private JLabel lblPol = new JLabel("Pol");
    private JComboBox<Pol> cbPol = new JComboBox<Pol>();
    private JLabel lblTelefon = new JLabel("Telefon");
    private JTextField txtTelefon = new JTextField(15);
    private JLabel lblUloga = new JLabel("Uloga");
    private JComboBox<TipKorisnika> cbUloga = new JComboBox<TipKorisnika>();
    private JLabel lblID = new JLabel("ID");
    private JTextField txtID = new JTextField(8);
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(8);
    private JLabel lblBrojLinije = new JLabel("Broj Linije");
    private JTextField txtBrojLinije = new JTextField(10);
    private JLabel lblOdeljenje = new JLabel("Odeljenje");
    private JComboBox<Odeljenje> cbOdeljenje = new JComboBox<Odeljenje>();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Dispeceri dispecer;
    private Sluzba taxiSluzba;

    public DispeceriDodajIzmeni(Sluzba taxiSluzba, Dispeceri dispecer) {
        this.taxiSluzba = taxiSluzba;
        this.dispecer = dispecer;
        if(this.dispecer == null) {
            setTitle("Dodavanje dispecera");
        }else {
            setTitle("Izmena podataka - " + this.dispecer.getId());

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
        cbOdeljenje.setModel(new DefaultComboBoxModel<>(Odeljenje.values()));

        if(this.dispecer != null) {
            popuniPolja();
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
        add(lblBrojLinije);
        add(txtBrojLinije);
        add(lblOdeljenje);
        add(cbOdeljenje);

        add(new JLabel());
        add(btnOk, "split");
        add(btnCancel);
        if(dispecer !=null) {
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
                    String brojLinije = txtBrojLinije.getText();
                    Odeljenje odeljenje = Odeljenje.valueOf(cbOdeljenje.getSelectedItem().toString());

                    if(dispecer == null) {
                        Dispeceri dispecer = new Dispeceri(korisnickoIme,lozinka,ime,prezime,jmbg,adresa,pol,telefon,uloga,false,ID,plata,brojLinije,odeljenje);
                        taxiSluzba.dodajDispecera(dispecer);
                        JOptionPane.showMessageDialog(null, "Uspesno kreiran dispecer!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        dispecer.setKorisnickoIme(korisnickoIme);
                        dispecer.setLozinka(lozinka);
                        dispecer.setIme(ime);
                        dispecer.setPrezime(prezime);
                        dispecer.setJmbg(jmbg);
                        dispecer.setAdresa(adresa);
                        dispecer.setPol(pol);
                        dispecer.setTelefon(telefon);
                        dispecer.setTipKorisnika(uloga);
                        dispecer.setId(ID);
                        dispecer.setPlata(plata);
                        dispecer.setBrLinije(brojLinije);
                        dispecer.setOdeljenje(odeljenje);
                        JOptionPane.showMessageDialog(null, "Izmene su sacuvane!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }

                    DispeceriDodajIzmeni.this.dispose();
                    DispeceriDodajIzmeni.this.setVisible(false);
                    taxiSluzba.snimiDispecere();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DispeceriDodajIzmeni.this.dispose();
                DispeceriDodajIzmeni.this.setVisible(false);
            }
        });
    }

    private void popuniPolja() {
        txtKorisnickoIme.setText(dispecer.getKorisnickoIme());
        txtLozinka.setText(dispecer.getLozinka());
        txtIme.setText(dispecer.getIme());
        txtPrezime.setText(dispecer.getPrezime());
        txtJmbg.setText(dispecer.getJmbg());
        txtAdresa.setText(dispecer.getAdresa());
        cbPol.setSelectedItem(dispecer.getPol());
        txtTelefon.setText(dispecer.getTelefon());
        cbUloga.setSelectedItem(dispecer.getTipKorisnika());
        txtID.setText(String.valueOf(dispecer.getId()));
        txtPlata.setText(String.valueOf(dispecer.getPlata()));
        txtBrojLinije.setText(dispecer.getBrLinije());
        cbOdeljenje.setSelectedItem(dispecer.getOdeljenje());
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
        else if(dispecer == null) {
            String id = txtID.getText().trim();
            Vozaci pronadjen = taxiSluzba.pronadjiVozacaString(id);
            if(pronadjen != null) {
                poruka += "- Dispecer sa unetim ID vec postoji\n";
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
