package gui.prozoriZaNarucivanje;

import entiteti.Musterije;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import sluzba.Sluzba;
import strukture.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OceniVozacaProzor extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton("OCENI VOZACA");

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;
    private Sluzba taxiSluzba;
    private Musterije musterija;

    public OceniVozacaProzor(Sluzba taxiSluzba, Musterije musterija) {
        this.taxiSluzba = taxiSluzba;
        this.musterija = musterija;
        setTitle("Ocenjivanje vozaca");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
        InitAction();
    }

    private void InitGUI() {
        btnAdd.setBackground(mainToolbar.getBackground());
        btnAdd.setForeground(Color.RED);
        btnAdd.setFont(new Font("Bold",Font.BOLD,13));

        mainToolbar.add(btnAdd);
        add(mainToolbar, BorderLayout.NORTH);

        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
        for(Voznja voznja: taxiSluzba.getVoznje()) {
            if(voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznja.getMusterija().getId() == musterija.getId() && voznja.getOcenaVoznje() == 0) {
                voznje.add(voznja);
            }
        }

        String[] zaglavlja = new String[] {"ID", "Vreme porudzbine", "Adresa polaska", "Adresa destinacije", "Musterija", "Vozac", "Predjeni km", "Trajanje(min)", "Status", "Poruceno", "Izbrisana","Zahtev za novije vozilo", "Pet Friendly","Ocena"};
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
            sadrzaj[i][11] = voznja.isNovijaVozila();
            sadrzaj[i][12] = voznja.isPetFriendly();
            sadrzaj[i][13] = voznja.getOcenaVoznje();
            i++;
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        voznjeTabela = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int j = 0; j<voznjeTabela.getColumnCount();j++) {
            voznjeTabela.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);
        voznjeTabela.setAutoCreateRowSorter(true); // sortiranje tabele

        voznjeTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int k=0; k<zaglavlja.length; k++) {
            voznjeTabela.getColumnModel().getColumn(k).setPreferredWidth(100);
        }


        JScrollPane scrollPane = new JScrollPane(voznjeTabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void InitAction() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = voznjeTabela.getValueAt(red, 0).toString();
                    Voznja izabranaVoznja = taxiSluzba.pronalazenjeVoznje(Integer.parseInt(id));

                    if(izabranaVoznja != null) {
                        OcenjivanjeVozaca ocenjivanjeVozaca = new OcenjivanjeVozaca(taxiSluzba, izabranaVoznja);
                        ocenjivanjeVozaca.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
