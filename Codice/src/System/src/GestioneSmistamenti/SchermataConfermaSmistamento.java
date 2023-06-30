package GestioneSmistamenti;

import GestioneSmistamenti.GestoreConfermaSmistamento.Smistamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchermataConfermaSmistamento extends JFrame {
    private JPanel smistamentiPanel;
    private Map<JButton, Integer> smistamentiMap;
    private JLabel confermaLabel;
    private GestoreConfermaSmistamento gestore;

    public SchermataConfermaSmistamento(List<Smistamento> smistamenti, GestoreConfermaSmistamento gestore) {
        this.gestore = gestore;
        setTitle("Conferma Smistamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        smistamentiPanel = new JPanel(new GridLayout(smistamenti.size(), 2));
        smistamentiMap = new HashMap<>();

        for (Smistamento smistamento : smistamenti) {
            JLabel riepilogoLabel = new JLabel();
            StringBuilder riepilogo = new StringBuilder();
            riepilogo.append("Numero lotto: ").append(smistamento.getID_Smi()).append("<br>");
            riepilogo.append("Data corrente: ").append(smistamento.getDataCorrente()).append("<br>");
            riepilogo.append("Elenco prodotti:<br>");
            riepilogo.append("- ").append(smistamento.getNomeProdotto()).append(", Quantità: ")
                    .append(smistamento.getQuantitaD()).append(", Scadenza: ").append(smistamento.getScadenza())
                    .append("<br>");
            riepilogoLabel.setText("<html>" + riepilogo.toString() + "</html>");
            smistamentiPanel.add(riepilogoLabel);

            JButton confermaButton = new JButton("Conferma smistamento");
            int smistamentoID = smistamento.getID_Smi();
            confermaButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gestore.confermaSmistamento(smistamentoID);
                }
            });
            smistamentiPanel.add(confermaButton);
            smistamentiMap.put(confermaButton, smistamentoID);
        }

        JScrollPane scrollPane = new JScrollPane(smistamentiPanel);
        add(scrollPane, BorderLayout.CENTER);

        confermaLabel = new JLabel();
        add(confermaLabel, BorderLayout.NORTH);

        JButton chiudiButton = new JButton("Chiudi");
        chiudiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestore.chiudiSchermata();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(chiudiButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
