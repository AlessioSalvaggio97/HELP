package GestioneDonazioni;

import javax.swing.*;

import GestioneDonazioni.GestoreAggiungiProdotto.Prodotto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModuloAggiungiProdotto extends JPanel {
    private List<Prodotto> listaProdottiSelezionabili;
    private GestoreAggiungiProdotto gap;

    public ModuloAggiungiProdotto(List<Prodotto> listaProdottiSelezionabili, GestoreAggiungiProdotto gap) {
        this.listaProdottiSelezionabili = listaProdottiSelezionabili;
        this.gap = gap;
        System.out.println("Modulo eseguito");
        setLayout(new BorderLayout());

        // Creazione della lista dei prodotti selezionabili
        JList<Prodotto> productList = new JList<>(
                listaProdottiSelezionabili.toArray(new Prodotto[0]));
        productList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productList);
        add(scrollPane, BorderLayout.CENTER);

        // Creazione del pulsante "Invio"
        JButton invioButton = new JButton("Invio");
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Azione da eseguire quando viene premuto il pulsante "Invio"
                Prodotto[] selectedProducts = productList.getSelectedValuesList().toArray(new Prodotto[0]);
                //gap. (selectedProducts);
            }
        });
        add(invioButton, BorderLayout.SOUTH);
    }

}
