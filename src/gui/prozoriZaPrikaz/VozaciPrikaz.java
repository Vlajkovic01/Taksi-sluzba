package gui.prozoriZaPrikaz;

import entiteti.Vozaci;
import gui.prozoriZaDodavanjeIIzmenu.VozaciDodajIzmeni;
import sluzba.Sluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VozaciPrikaz extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnIzmeni = new JButton();
    private JButton btnObrisi = new JButton();

    private DefaultTableModel tableModel;
    private JTable vozaciTabela;
    private Sluzba taxiSluzba;

    public VozaciPrikaz(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Vozaci prikaz");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
        InitAction();
    }

    private void InitGUI() {
        ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnAdd.setIcon(addIcon);
        ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnIzmeni.setIcon(editIcon);
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnObrisi.setIcon(deleteIcon);

        mainToolbar.add(btnAdd);
        mainToolbar.add(btnIzmeni);
        mainToolbar.add(btnObrisi);
        add(mainToolbar, BorderLayout.NORTH);

        ArrayList<Vozaci> vozaci = new ArrayList<Vozaci>();
        for(Vozaci vozac: taxiSluzba.getVozaci()) {
            if(!vozac.isIzbrisan()) {
                vozaci.add(vozac);
            }
        }

        String[] zaglavlja = new String[] {"Korisnicko Ime", "Lozinka", "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Telefon", "Uloga", "Izbrisan", "ID", "Plata", "Clanska karta", "Automobil",};
        Object[][] sadrzaj = new Object[vozaci.size()][zaglavlja.length];

        int i = 0;
        for (Vozaci vozac : vozaci) {
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
            sadrzaj[i][13] = vozac.getAutomobil().getIdVozila();
            i++;
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        vozaciTabela = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int j = 0; j<vozaciTabela.getColumnCount();j++) {
            vozaciTabela.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }

        vozaciTabela.setRowSelectionAllowed(true);
        vozaciTabela.setColumnSelectionAllowed(false);
        vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vozaciTabela.setDefaultEditor(Object.class, null);
        vozaciTabela.getTableHeader().setReorderingAllowed(false);
        vozaciTabela.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(vozaciTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void InitAction() {
        btnObrisi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = vozaciTabela.getValueAt(red, 10).toString();
                    Vozaci vozac = taxiSluzba.pronalazenjeVozaca(Integer.parseInt(id));
                    int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete vozaca?",
                            vozac.getId() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);

                    if(izbor == JOptionPane.YES_OPTION) {
                        taxiSluzba.obrisiVozaca(vozac);
                        tableModel.removeRow(red);
                    }
                }
            }
        });
        btnAdd.addActionListener(new  ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciDodajIzmeni vozaciDodajIzmeni = new VozaciDodajIzmeni(taxiSluzba,null);
                vozaciDodajIzmeni.setVisible(true);
            }

        });
        btnIzmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = vozaciTabela.getValueAt(red, 10).toString();
                    Vozaci vozac = taxiSluzba.pronalazenjeVozaca(Integer.parseInt(id));
                    if(vozac != null) {
                        VozaciDodajIzmeni vozaciDodajIzmeni = new VozaciDodajIzmeni(taxiSluzba,vozac);
                        vozaciDodajIzmeni.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
