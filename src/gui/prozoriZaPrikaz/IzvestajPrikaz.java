package gui.prozoriZaPrikaz;

import entiteti.Voznja;
import sluzba.Sluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import strukture.ArrayList;

public class IzvestajPrikaz extends JFrame {

    private JTextPane lblIzvestaj = new JTextPane();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;
    private Sluzba taxiSluzba;
    private strukture.ArrayList<Voznja> pronadjenaVoznja;

    public IzvestajPrikaz(Sluzba taxiSluzba, ArrayList<Voznja> pronadjenaVoznja) {
        this.taxiSluzba = taxiSluzba;
        this.pronadjenaVoznja = pronadjenaVoznja;
        setTitle("Voznje prikaz");
        setSize(900,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        InitGUI();
    }

    private void InitGUI() {

        String[] zaglavlja = new String[] {"ID", "Vreme porudzbine", "Adresa polaska", "Adresa destinacije", "Musterija", "Vozac", "Predjeni km", "Trajanje(min)", "Status", "Poruceno", "Izbrisana"};
        Object[][] sadrzaj = new Object[pronadjenaVoznja.size()][zaglavlja.length];

        int i = 0;
        double ukupnoKm = 0;
        double ukupnoMin = 0;
        double cenaStart = 120;
        double cenaKm = 50;
        ArrayList<Integer> brVozaca = new ArrayList<>();

        for (Voznja voznja : pronadjenaVoznja) {
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
            ukupnoKm += voznja.getPredjeniKm();
            ukupnoMin += voznja.getTrajanjeVoznje();

            if (brVozaca.contains(voznja.getVozac().getId())) {
                continue;
            } else {
                brVozaca.add(voznja.getVozac().getId());
            }

        }

        double ukupnaZarada = cenaStart + ukupnoKm*cenaKm;

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
        voznjeTabela.setAutoCreateRowSorter(true);

        voznjeTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for(int k=0; k<zaglavlja.length; k++) {
            voznjeTabela.getColumnModel().getColumn(k).setPreferredWidth(100);
        }

        add(lblIzvestaj, BorderLayout.SOUTH);
        lblIzvestaj.setEditable(false);
        Font font = new Font("Bold",Font.BOLD,12);
        lblIzvestaj.setFont(font);

        lblIzvestaj.setText("Ukupno voznji: " + i + "\nBroj aktivnih vozaca: " + brVozaca.size()  + "\nProsek km: " + ukupnoKm/i +
                "\nProsek min: " + ukupnoMin/i + "\nUkupna zarada: " + ukupnaZarada + " Rsd" +
                "\nProsek zarade po voznji: " + ukupnaZarada/i + " Rsd");

        JScrollPane scrollPane = new JScrollPane(voznjeTabela);
        add(scrollPane, BorderLayout.CENTER);
    }
}
