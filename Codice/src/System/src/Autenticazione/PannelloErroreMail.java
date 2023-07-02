package Autenticazione;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelloErroreMail extends JPanel {

    public PannelloErroreMail() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "La mail inserita non è valida",
                "Errore mail",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}
