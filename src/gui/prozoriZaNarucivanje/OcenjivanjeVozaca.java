package gui.prozoriZaNarucivanje;

import entiteti.Voznja;
import enumeracije.StatusVoznje;
import net.miginfocom.swing.MigLayout;
import sluzba.Sluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OcenjivanjeVozaca extends JFrame {

    private JLabel lblOcena = new JLabel("Izaberi ocenu ");
    private JComboBox<Integer> cbOcena = new JComboBox();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Voznja voznja;
    private Sluzba taxiSluzba;

    public OcenjivanjeVozaca(Sluzba taxiSluzba, Voznja voznja) {
        this.taxiSluzba = taxiSluzba;
        this.voznja = voznja;
        if(this.voznja != null) {
            setTitle("Oceni vozaca - " + this.voznja.getVozac().getIme() + " " + this.voznja.getVozac().getPrezime());
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

        for (int i = 1 ; i < 6 ; i ++) {
            cbOcena.addItem(i);
        }

        add(lblOcena);
        add(cbOcena);

        add(new JLabel());
        add(btnOk, "split");
        add(btnCancel);
        getRootPane().setDefaultButton(btnOk);
    }

    private void initActions() {
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int ocena = Integer.parseInt(cbOcena.getSelectedItem().toString());

                voznja.setOcenaVoznje(ocena);

                OcenjivanjeVozaca.this.dispose();
                OcenjivanjeVozaca.this.setVisible(false);
                taxiSluzba.snimiVoznje();


                int brojVoznji = 0;
                double prosecnaOcena = 0;
                for (Voznja v : taxiSluzba.getVoznje()) {
                    if (!v.isIzbrisana() && v.getVozac().getId() == voznja.getVozac().getId() && v.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && v.getOcenaVoznje() != 0) {
                        brojVoznji++;
                        prosecnaOcena += v.getOcenaVoznje();
                    }
                }
                voznja.getVozac().setProsecnaOcena(prosecnaOcena/brojVoznji);
                taxiSluzba.snimiVozace();

                JOptionPane.showMessageDialog(null, "Vozac uspesno ocenjen!\n", "Greska", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                OcenjivanjeVozaca.this.dispose();
                OcenjivanjeVozaca.this.setVisible(false);
            }
        });
    }
}
