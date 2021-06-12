package gui.aukcija;

import entiteti.Aukcija;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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

                    //TODO SVE JE SKALIRANO NA INTERVAL 0-1 RADI LAKSE MANIPULACIJE
                    //broj minuta do dolaska -> sto manje to bolje
                    //broj voznji u prethodnom mesecu -> sto manje to bolje
                    //starost vozila -> sto mladje to bolje
                    //prosecna ocena -> sto veca to bolje
                    //minutaza x6
                    //prosecnaOcena x4
                    //brojVoznji x3
                    //starostVozila x2
                    //max bodova -> 15


                    double ukupnaSuma = 0;
                    double brojVoznjiSuma = 0;
                    LocalDate sadasnjiDatum = LocalDate.now();
                    String prosliMesec = sadasnjiDatum.minusMonths(1).format(DateTimeFormatter.ofPattern("MM-yyyy"));

                    // broj zavrsenih voznji vozaca prethodnog meseca
                    for(Voznja v: taxiSluzba.getVoznje()) {
                        if(!v.isIzbrisana() && v.getVozac().getId() == vozac.getId() && v.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && v.getDatumIVremePorudzbine().substring(3, 10).equals(prosliMesec)) {
                            brojVoznjiSuma++;
                        }
                    }

                    //u slucaju da vozac nema voznje prethodnog meseca
                    if (brojVoznjiSuma == 0) {
                        brojVoznjiSuma = 1;
                    }

                    brojVoznjiSuma = (1 / brojVoznjiSuma) * 3;
                    double vremeDolaskaSuma = (1/minutaza) * 6;
                    int trenutnaGodina = Calendar.getInstance().get(Calendar.YEAR);
                    double starostVozilaSuma = (Double.parseDouble(String.valueOf(vozac.getAutomobil().getGodProizvodnje())) / trenutnaGodina) * 2 ;
                    double prosecnaOcena = (vozac.getProsecnaOcena() / 5) * 4;

                    /* prilikom narucivanja,
                    ako musterija selektuje checkBox za novija vozila
                     */
                    if (voznja.isNovijaVozila()) {
                        ukupnaSuma = brojVoznjiSuma + vremeDolaskaSuma + starostVozilaSuma + prosecnaOcena;
                    }else {
                        ukupnaSuma = brojVoznjiSuma + vremeDolaskaSuma + prosecnaOcena;
                    }


                    Aukcija aukcija = new Aukcija(voznja,vozac,minutaza,ukupnaSuma);
                    taxiSluzba.dodajAukciju(aukcija);
                    JOptionPane.showMessageDialog(null, "Uspesno kreirana ponuda!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

                    UnosMinutazeProzor.this.dispose();
                    UnosMinutazeProzor.this.setVisible(false);
                    taxiSluzba.snimiAukcije();
                    taxiSluzba.snimiVozace();
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
            poruka += "-Minutaza mora biti broj.\n";
            ispravno = false;
        }
        if(txtMinutaza.getText().trim().equals("")) {
            poruka += "-Morate uneti broj minuta.\n";
            ispravno = false;
        }
        if(txtMinutaza.getText().trim().equals("0")) {
            poruka += "-Minimum je 1 minut.\n";
            ispravno = false;
        }
        if(ispravno == false) {
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ispravno;
    }

}
