package gui.prozoriZaPrikaz;

import entiteti.Vozaci;
import sluzba.Sluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class KombinovanaPretragaPrikaz extends JFrame {

    private DefaultTableModel tableModel;
    private JTable vozaciTabela;
    private Sluzba taxiSluzba;
    private ArrayList<Vozaci> pronadjeniVozaci;

    public KombinovanaPretragaPrikaz(Sluzba taxiSluzba, ArrayList<Vozaci> pronadjeniVozaci) {
        this.taxiSluzba = taxiSluzba;
        this.pronadjeniVozaci = pronadjeniVozaci;
        setTitle("Vozaci prikaz");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
    }

    private void InitGUI() {

        String[] zaglavlja = new String[] {"Korisnicko Ime", "Lozinka", "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Telefon", "Uloga", "Izbrisan", "ID", "Plata", "Clanska karta", "Automobil",};
        Object[][] sadrzaj = new Object[pronadjeniVozaci.size()][zaglavlja.length];

        int i = 0;
        for (Vozaci vozac : pronadjeniVozaci) {
            sadrzaj[i][0] = vozac.getKorisnickoIme();
            sadrzaj[i][1] = vozac.getLozinka();
            sadrzaj[i][2] = vozac.getIme();
            sadrzaj[i][3] = vozac.getPrezime();
            sadrzaj[i][4] = vozac.getJmbg();
            sadrzaj[i][5] = vozac.getAdresa();
            sadrzaj[i][6] = vozac.getPol();
            sadrzaj[i][7] = vozac.getTelefon();
            sadrzaj[i][8] = vozac.getTipKorisnika();
            sadrzaj[i][9] = vozac.isIzbrisan();
            sadrzaj[i][10] = vozac.getId();
            sadrzaj[i][11] = vozac.getPlata();
            sadrzaj[i][12] = vozac.getBrClanskeKarte();
            sadrzaj[i][13] = vozac.getAutomobil().getProizvodjac();
            i++;
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        vozaciTabela = new JTable(tableModel);

        vozaciTabela.setRowSelectionAllowed(true);
        vozaciTabela.setColumnSelectionAllowed(false);
        vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vozaciTabela.setDefaultEditor(Object.class, null);
        vozaciTabela.getTableHeader().setReorderingAllowed(false);
        vozaciTabela.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(vozaciTabela);
        add(scrollPane, BorderLayout.CENTER);
    }
}
