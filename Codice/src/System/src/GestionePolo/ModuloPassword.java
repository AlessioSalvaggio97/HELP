package GestionePolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloPassword extends JFrame {
    private JTextField passwordField;

    public ModuloPassword(GestoreRiabilitaPolo grp) {
        setTitle("Modulo Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione dei componenti
        JLabel label = new JLabel("Inserisci la password:");
        passwordField = new JTextField(20);
        JButton confermaButton = new JButton("Conferma");

        // Aggiunta di un ActionListener al pulsante Conferma
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                grp.gestisciPassword(password);
            }
        });

        // Creazione del layout
        setLayout(new FlowLayout());

        // Aggiunta dei componenti al JFrame
        add(label);
        add(passwordField);
        add(confermaButton);

        pack();
        setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
        setVisible(true);
    }
}
