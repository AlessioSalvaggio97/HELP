package GestioneSmistamenti;

import GestioneSmistamenti.GestoreConfermaSmistamento.Smistamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SchermataConfermaSmistamento extends JFrame {
    private JPanel smistamentiPanel;
    private JLabel confermaLabel;
    private GestoreConfermaSmistamento gestore;
    private List<Smistamento> smistamenti;
    private int ID_Spe;

    public SchermataConfermaSmistamento(List<Smistamento> smistamenti, GestoreConfermaSmistamento gestore) {
        this.gestore = gestore;
        this.smistamenti = smistamenti;
        setTitle("Conferma Smistamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        smistamentiPanel = new JPanel();
        smistamentiPanel.setLayout(new BoxLayout(smistamentiPanel, BoxLayout.Y_AXIS));

        for (Smistamento smistamento : smistamenti) {
            JTextArea riepilogoTextArea = new JTextArea();
            riepilogoTextArea.setEditable(false);
            riepilogoTextArea.append("Numero lotto: " + smistamento.getID_Smi() + "\n");
            riepilogoTextArea.append("Data corrente: " + smistamento.getDataCorrente() + "\n");
            riepilogoTextArea.append("Elenco prodotti:\n");
            riepilogoTextArea.append("- " + smistamento.getNomeProdotto() + ", Quantità: " +
                    smistamento.getQuantitaD() + ", Scadenza: " + smistamento.getScadenza() + "\n");
            
            ID_Spe = smistamento.getID_Smi();
            smistamentiPanel.add(riepilogoTextArea);
        }

        JScrollPane scrollPane = new JScrollPane(smistamentiPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton confermaButton = new JButton("Conferma smistamento");
        confermaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestore.confermaSmistamento(ID_Spe);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confermaButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
