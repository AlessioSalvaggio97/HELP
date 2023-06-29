package Main;

import Autenticazione.Utente;

import javax.swing.*;
import java.awt.*;

public class SchermataPrincipale extends JFrame {

    String titolo = "Pagina Principale";
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    private JPanel headerPnl;
    private JPanel farmaciaPnl;
    private JPanel notifichePnl;
    private JPanel LogoutPanel;
    private JLabel farmaciaLbl;
    private JButton notificheButton;
    private JButton logoutButton;

    public SchermataPrincipale() {

        this.setTitle(titolo);
        this.setSize(width, heigth);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();

        System.out.println("Chiamata schermata principale.");
    }

    public void initItems() {
        headerPnl = new JPanel();
        headerPnl.setLayout(new GridLayout());

        notifichePnl = new JPanel();
        notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        headerPnl.add(notifichePnl);
        notificheButton = new JButton();
        notificheButton.setText("Notifiche");

        cont.add(headerPnl, BorderLayout.NORTH);
    }

}
