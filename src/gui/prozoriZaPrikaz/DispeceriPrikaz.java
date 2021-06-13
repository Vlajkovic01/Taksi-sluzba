package gui.prozoriZaPrikaz;

import entiteti.Dispeceri;
import gui.prozoriZaDodavanjeIIzmenu.DispeceriDodajIzmeni;
import sluzba.Sluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import strukture.ArrayList.ArrayList;

public class DispeceriPrikaz extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnIzmeni = new JButton();
    private JButton btnObrisi = new JButton();

    private DefaultTableModel tableModel;
    private JTable dispeceriTabela;
    private Sluzba taxiSluzba;

    public DispeceriPrikaz(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Dispeceri prikaz");
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

        ArrayList<Dispeceri> dispeceri = new ArrayList<Dispeceri>();
        for(Dispeceri dispecer: taxiSluzba.getDispeceri()) {
            if(!dispecer.isIzbrisan()) {
                dispeceri.add(dispecer);
            }
        }

        String[] zaglavlja = new String[] {"Korisnicko Ime", "Lozinka", "Ime", "Prezime", "JMBG", "Adresa", "Pol", "Telefon", "Uloga", "Izbrisan", "ID", "Plata", "Broj Linije", "Odeljenje",};
        Object[][] sadrzaj = new Object[dispeceri.size()][zaglavlja.length];

        int i = 0;
        for (Dispeceri dispecer : dispeceri) {
            sadrzaj[i][0] = dispecer.getKorisnickoIme();
            sadrzaj[i][1] = dispecer.getLozinka();
            sadrzaj[i][2] = dispecer.getIme();
            sadrzaj[i][3] = dispecer.getPrezime();
            sadrzaj[i][4] = dispecer.getJmbg();
            sadrzaj[i][5] = dispecer.getAdresa();
            sadrzaj[i][6] = dispecer.getPol();
            sadrzaj[i][7] = dispecer.getTelefon();
            sadrzaj[i][8] = dispecer.getTipKorisnika();
            sadrzaj[i][9] = dispecer.isIzbrisan();
            sadrzaj[i][10] = dispecer.getId();
            sadrzaj[i][11] = dispecer.getPlata();
            sadrzaj[i][12] = dispecer.getBrLinije();
            sadrzaj[i][13] = dispecer.getOdeljenje();
            i++;
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        dispeceriTabela = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int j = 0; j<dispeceriTabela.getColumnCount();j++) {
            dispeceriTabela.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }

        dispeceriTabela.setRowSelectionAllowed(true);
        dispeceriTabela.setColumnSelectionAllowed(false);
        dispeceriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dispeceriTabela.setDefaultEditor(Object.class, null);
        dispeceriTabela.getTableHeader().setReorderingAllowed(false);
        dispeceriTabela.setAutoCreateRowSorter(true); // sortiranje tabele

        dispeceriTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int k=0; k<zaglavlja.length; k++) {
            dispeceriTabela.getColumnModel().getColumn(k).setPreferredWidth(100);
        }

        JScrollPane scrollPane = new JScrollPane(dispeceriTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void InitAction() {
        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dispeceriTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = dispeceriTabela.getValueAt(red, 10).toString();
                    Dispeceri dispecer = taxiSluzba.pronalazenjeDispecera(Integer.parseInt(id));
                    int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete dispecera?",
                            dispecer.getIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);

                    if(izbor == JOptionPane.YES_OPTION) {
                        taxiSluzba.obrisiDispecera(dispecer);
                        tableModel.removeRow(red);
                    }
                }
            }
        });
        btnAdd.addActionListener(new  ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DispeceriDodajIzmeni dispeceriDodajIzmeni = new DispeceriDodajIzmeni(taxiSluzba,null);
                dispeceriDodajIzmeni.setVisible(true);
            }

        });
        btnIzmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dispeceriTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = dispeceriTabela.getValueAt(red, 10).toString();
                    Dispeceri dispecer = taxiSluzba.pronalazenjeDispecera(Integer.parseInt(id));

                    if(dispecer != null) {
                        DispeceriDodajIzmeni dispeceriDodajIzmeni = new DispeceriDodajIzmeni(taxiSluzba,dispecer);
                        dispeceriDodajIzmeni.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
