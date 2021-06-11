package gui.aukcija;

import entiteti.Aukcija;
import entiteti.Vozaci;
import entiteti.Voznja;
import enumeracije.StatusVoznje;
import sluzba.Sluzba;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AukcijaPrikazVoznjiVozacu extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton("KONKURISI");

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;
    private Sluzba taxiSluzba;
    private Vozaci vozac;

    public AukcijaPrikazVoznjiVozacu(Sluzba taxiSluzba, Vozaci vozac) {
        this.taxiSluzba = taxiSluzba;
        this.vozac = vozac;
        setTitle("Dodeljivanje voznje");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
        InitAction();
    }

    private void InitGUI() {
        btnAdd.setBackground(mainToolbar.getBackground());
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setFont(new Font("Bold",Font.BOLD,13));

        mainToolbar.add(btnAdd);
        add(mainToolbar, BorderLayout.NORTH);

        ArrayList<Voznja> voznje = new ArrayList<Voznja>();

        //TODO ispraviti, moze bolje

        /* kada vozac konkurise za voznju nakon
        osvezavanja mu se ta voznja ne prikazuje vise
         */

        for (Voznja voznja : taxiSluzba.getVoznje()) {
            if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA) && voznja.getVozac().getId() == 0) {
                voznje.add(voznja);
            }
            for (Aukcija aukcija : taxiSluzba.getAukcije()) {
                if (aukcija.getVoznja().getId() == voznja.getId() && aukcija.getVozac().getId() == vozac.getId()) {
                    voznje.remove(aukcija.getVoznja());
                }
            }
        }
        // nije najbolje resenje, nema sta nisam probao jedino mi ovako radi.


        String[] zaglavlja = new String[] {"ID", "Vreme porudzbine", "Adresa polaska", "Adresa destinacije", "Musterija", "Vozac", "Predjeni km", "Trajanje(min)", "Status", "Poruceno", "Izbrisana","Zahtev za novije vozilo", "Pet Friendly"};
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
                    Voznja voznja = taxiSluzba.pronalazenjeVoznje(Integer.parseInt(id));

                    if(voznja != null) {
                        if (vozac.getAutomobil().getIdVozila() != 0) {
                            UnosMinutazeProzor unosMinutaze = new UnosMinutazeProzor(taxiSluzba, voznja, vozac);
                            unosMinutaze.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nemate dodeljen automobil, ne mozete ucestvovati", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
