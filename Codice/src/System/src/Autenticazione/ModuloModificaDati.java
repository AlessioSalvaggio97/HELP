package Autenticazione;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloModificaDati extends JFrame {
    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtIndirizzo;
    private JTextField txtRecapito;

    public ModuloModificaDati(String nome, String cognome, String indirizzo, String recapito) {
        setTitle("Modulo Modifica Dati");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 20, 80, 20);
        panel.add(lblNome);

        txtNome = new JTextField(nome);
        txtNome.setBounds(100, 20, 150, 20);
        panel.add(txtNome);

        JLabel lblCognome = new JLabel("Cognome:");
        lblCognome.setBounds(10, 50, 80, 20);
        panel.add(lblCognome);

        txtCognome = new JTextField(cognome);
        txtCognome.setBounds(100, 50, 150, 20);
        panel.add(txtCognome);

        JLabel lblIndirizzo = new JLabel("Indirizzo:");
        lblIndirizzo.setBounds(10, 80, 80, 20);
        panel.add(lblIndirizzo);

        txtIndirizzo = new JTextField(indirizzo);
        txtIndirizzo.setBounds(100, 80, 150, 20);
        panel.add(txtIndirizzo);

        JLabel lblRecapito = new JLabel("Recapito:");
        lblRecapito.setBounds(10, 110, 80, 20);
        panel.add(lblRecapito);

        txtRecapito = new JTextField(recapito);
        txtRecapito.setBounds(100, 110, 150, 20);
        panel.add(txtRecapito);

        JButton btnSalva = new JButton("Salva");
        btnSalva.setBounds(100, 140, 100, 20);
        panel.add(btnSalva);

        btnSalva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ottieni i dati modificati
                String nuovoNome = txtNome.getText();
                String nuovoCognome = txtCognome.getText();
                String nuovoIndirizzo = txtIndirizzo.getText();
                String nuovoRecapito = txtRecapito.getText();

                // Esegui le operazioni di salvataggio nel database
                // Aggiungi qui la logica per salvare i dati nel database

                // Chiudi la finestra di modifica dati
                dispose();
            }
        });
    }

    public void mostraModulo() {
        setVisible(true);
    }
}
