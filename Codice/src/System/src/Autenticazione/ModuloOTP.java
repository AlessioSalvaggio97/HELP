package Autenticazione;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloOTP extends JFrame {
    private JTextField txtOTP;
    private JButton btnConferma;

    public ModuloOTP() {
        setTitle("Modulo OTP");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblOTP = new JLabel("Codice OTP:");
        lblOTP.setBounds(10, 20, 80, 20);
        panel.add(lblOTP);

        txtOTP = new JTextField();
        txtOTP.setBounds(100, 20, 150, 20);
        panel.add(txtOTP);

        btnConferma = new JButton("Conferma");
        btnConferma.setBounds(100, 60, 100, 20);
        panel.add(btnConferma);

        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String OTP = txtOTP.getText();
                // Passa il codice all'interfaccia del DBMS
                // Aggiungi qui la logica per passare il codice all'interfaccia del DBMS
            }
        });
    }
}
