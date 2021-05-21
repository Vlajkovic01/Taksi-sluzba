package gui.prozoriZaPrikaz;

import entiteti.Voznja;
import gui.prozoriZaDodavanjeIIzmenu.VoznjeDodajIzmeni;
import sluzba.Sluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VoznjePrikaz extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton();
    private JButton btnIzmeni = new JButton();
    private JButton btnObrisi = new JButton();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;
    private Sluzba taxiSluzba;

    public VoznjePrikaz(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Voznje prikaz");
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

        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
        for(Voznja voznja: taxiSluzba.getVoznje()) {
            if(!voznja.isIzbrisana()) {
                voznje.add(voznja);
            }
        }

        String[] zaglavlja = new String[] {"ID", "Vreme porudzbine", "Adresa polaska", "Adresa destinacije", "Musterija", "Vozac", "Predjeni km", "Trajanje(min)", "Status", "Poruceno", "Izbrisana"};
        Object[][] sadrzaj = new Object[voznje.size()][zaglavlja.length];

        int i = 0;
        for (Voznja voznja : voznje) {
            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumIVremePorudzbine();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getMusterija().getId();
            sadrzaj[i][5] = voznja.getVozac().getId();
            sadrzaj[i][6] = voznja.getPredjeniKm();
            sadrzaj[i][7] = voznja.getTrajanjeVoznje();
            sadrzaj[i][8] = voznja.getStatusVoznje();
            sadrzaj[i][9] = voznja.getTipPorudzbine();
            sadrzaj[i][10] = voznja.isIzbrisana();
            i++;
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(voznjeTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void InitAction() {

        ArrayList voznje = taxiSluzba.integerListaVoznji();

        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = voznjeTabela.getValueAt(red, 0).toString();
//                    Voznja voznja = taxiSluzba.pronadjiVoznjuString(id); //stara metoda
                    Voznja voznja = taxiSluzba.pronalazenjeVoznje(voznje,Integer.parseInt(id));
                    int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete voznju?",
                            voznja.getId() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);

                    if(izbor == JOptionPane.YES_OPTION) {
                        taxiSluzba.obrisiVoznju(voznja);
                        tableModel.removeRow(red);
                    }
                }
            }
        });
        btnAdd.addActionListener(new  ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjeDodajIzmeni voznjeDodajIzmeni = new VoznjeDodajIzmeni(taxiSluzba,null);
                voznjeDodajIzmeni.setVisible(true);
            }

        });
        btnIzmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = voznjeTabela.getValueAt(red, 0).toString();
//                    Voznja voznja = taxiSluzba.pronadjiVoznjuString(id); //stara metoda
                    Voznja voznja = taxiSluzba.pronalazenjeVoznje(voznje, Integer.parseInt(id));

                    if(voznja != null) {
                        VoznjeDodajIzmeni voznjeDodajIzmeni = new VoznjeDodajIzmeni(taxiSluzba,voznja);
                        voznjeDodajIzmeni.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
