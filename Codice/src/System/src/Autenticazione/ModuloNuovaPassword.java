package Autenticazione;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloNuovaPassword extends JFrame {
    private JTextField txtNuovaPassword;
    private JButton btnConferma;
    private String nuovaPassword;

    public ModuloNuovaPassword() {
        setTitle("Modulo Nuova password");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout());

        JLabel lblOTP = new JLabel("Nuova Password:");
        panel.add(lblOTP);

        txtNuovaPassword = new JTextField(15);
        panel.add(txtNuovaPassword);

        btnConferma = new JButton("Conferma");
        panel.add(btnConferma);

        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nuovaPassword = txtNuovaPassword.getText();
                // Passa il codice all'interfaccia del DBMS
                // Aggiungi qui la logica per passare il codice all'interfaccia del DBMS
            }
        });
    }

    public String getPassword(){
        return nuovaPassword;
    }
}
