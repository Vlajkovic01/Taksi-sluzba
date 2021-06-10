package gui.aukcija;

import entiteti.Aukcija;
import entiteti.Vozaci;
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

public class AukcijaPrikazVoznjiDispeceru extends JFrame {

    private JToolBar mainToolbar = new JToolBar();
    private JButton btnStartAuction = new JButton();
    private JButton btnEndAuction = new JButton();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;
    private Sluzba taxiSluzba;

    private Voznja voznjaNaAukciji = null;

    public AukcijaPrikazVoznjiDispeceru(Sluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        setTitle("Pokretanje aukcije");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
        initAction();
    }

    private void InitGUI() {
        ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnStartAuction.setIcon(addIcon);

        mainToolbar.add(btnStartAuction);
        add(mainToolbar, BorderLayout.NORTH);

        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
        for(Voznja voznja: taxiSluzba.getVoznje()) {
            if(voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA) && voznja.getVozac().getId() == 0) {
                voznje.add(voznja);
            }
        }

        String[] zaglavlja = new String[] {"ID", "Vreme porudzbine", "Adresa polaska", "Adresa destinacije", "Musterija", "Vozac", "Predjeni km", "Trajanje(min)", "Status", "Poruceno", "Izbrisana" ,"Zahtev za novije vozilo", "Pet Friendly"};
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

    private void initAction() {
        btnStartAuction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if(red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String id = voznjeTabela.getValueAt(red, 0).toString();
                    voznjaNaAukciji = taxiSluzba.pronalazenjeVoznje(Integer.parseInt(id));

                    /* uzima ID selektovane voznje i na osnovu nje
                    pravi listu aukcija tako da ako je lista prazna znaci da
                    niko nije konkurisao za tu voznju
                     */

                    ArrayList<Aukcija> sveAukcije = new ArrayList<Aukcija>();
                    for(Aukcija aukcija: taxiSluzba.getAukcije()) {
                        if (voznjaNaAukciji.getId() == aukcija.getVoznja().getId()) {
                            sveAukcije.add(aukcija);
                        }
                    }

                    /*algoritam za aukciju,
                    prolazi kroz listu svih aukciju i za max
                    postavlja vozaca koji ima najvise bodova
                     */

                    if(voznjaNaAukciji != null) {
                        if (!sveAukcije.isEmpty()) {

                            Aukcija max = sveAukcije.get(0);
                            for(int i=0; i<sveAukcije.size(); i++) {
                                if(max.getUkupnoBodovaVozac() < sveAukcije.get(i).getUkupnoBodovaVozac()) {
                                    max = sveAukcije.get(i);
                                }
                            }

                            Vozaci optimalanVozac = max.getVozac();

                            voznjaNaAukciji.setVozac(optimalanVozac);
                            voznjaNaAukciji.setStatusVoznje(StatusVoznje.DODELJENA);
                            taxiSluzba.snimiVoznje();

                            JOptionPane.showMessageDialog(null, "Uspesno dodeljen vozac pomocu aukcije!\n" + optimalanVozac.getIme() + " " + optimalanVozac.getPrezime() + " - " + optimalanVozac.getId(), "Greska", JOptionPane.ERROR_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Nije moguce pokrenuti aukciju za odabranu voznju!\nNe postoje kandidati.", "Greska", JOptionPane.ERROR_MESSAGE);
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
