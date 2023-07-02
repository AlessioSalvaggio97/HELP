package Autenticazione;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelloErroreOTP extends JPanel {

    public PannelloErroreOTP() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "L'OTP inserito non è valido",
                "Errore OTP",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}
