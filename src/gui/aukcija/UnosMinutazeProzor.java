package gui.aukcija;

import entiteti.Aukcija;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;
import strukture.ArrayList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnosMinutazeProzor extends JFrame {

    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska:");
    private JTextField txtAdresaPolaska = new JTextField(15);
    private JLabel lblMinutaza = new JLabel("Broj minuuta do dolaska:");
    private JTextField txtMinutaza = new JTextField(15);

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Voznja voznja;
    private Sluzba taxiSluzba;
    private Vozaci vozac;

    public UnosMinutazeProzor(Sluzba taxiSluzba, Voznja voznja, Vozaci vozac) {
        this.taxiSluzba = taxiSluzba;
        this.voznja = voznja;
        this.vozac = vozac;
        if(this.voznja != null) {
            setTitle("Unos minutaze voznji - " + this.voznja.getId());
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

        if(this.voznja != null) {
            popuniPolja();
        }

        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblMinutaza);
        add(txtMinutaza);

        txtAdresaPolaska.setEditable(false);

        add(new JLabel());
        add(btnOk, "split");
        add(btnCancel);
        getRootPane().setDefaultButton(btnOk);
    }

    private void initActions() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija()) {
                    double minutaza = Double.parseDouble(txtMinutaza.getText());

                    ArrayList<Voznja> voznje = new ArrayList<Voznja>();
                    for(Voznja voznja: taxiSluzba.getVoznje()) {
                        if(!voznja.isIzbrisana()) {
                            voznje.add(voznja);
                        }
                    }

                    //broj minuta do dolaska -> sto manje to bolje
                    //minutaza x5
                    //brojVoznji x3
                    //starostVozila / 1000
                    //broj prethodnih voznji -> sto manje to bolje
                    //starost vozila -> sto mladje to bolje

                    double ukupnaSuma = 0;
                    double brojVoznji = 0;

                    // broj zavrsenih voznji vozaca
                    for(Voznja voznja : voznje) {
                        if(voznja.getVozac().getId() == vozac.getId() && voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA))
                            brojVoznji++;
                    }

                    brojVoznji = (1 / brojVoznji) * 3;
                    double ukupnoMinuta = (1/minutaza) * 5;
                    double starostVozila = Double.parseDouble(String.valueOf(vozac.getAutomobil().getGodProizvodnje())) / 1000;

                    /* prilikom narucivanja,
                    ako musterija selektuje checkBox za novija vozila
                     */
                    if (voznja.isNovijaVozila()) {
                        ukupnaSuma = brojVoznji + ukupnoMinuta + starostVozila;
                    }else {
                        ukupnaSuma = brojVoznji + ukupnoMinuta;
                    }


                    Aukcija aukcija = new Aukcija(voznja,vozac,minutaza,ukupnaSuma);
                    taxiSluzba.dodajAukciju(aukcija);
                    JOptionPane.showMessageDialog(null, "Uspesno kreirana ponuda!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

                    UnosMinutazeProzor.this.dispose();
                    UnosMinutazeProzor.this.setVisible(false);
                    taxiSluzba.snimiAukcije();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UnosMinutazeProzor.this.dispose();
                UnosMinutazeProzor.this.setVisible(false);
            }
        });
    }

    private void popuniPolja() {
        txtAdresaPolaska.setText(voznja.getAdresaPolaska());
    }

    private boolean validacija() {
        Boolean ispravno = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        try {
            Integer.parseInt(txtMinutaza.getText());
        }catch (NumberFormatException e) {
            poruka += "-Minutaza mora biti broj\n";
            ispravno = false;
        }
        if(txtMinutaza.getText().trim().equals("")) {
            poruka += "Morate uneti broj minuta\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }

}
