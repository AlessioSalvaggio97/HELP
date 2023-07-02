package Autenticazione;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelloErroreRequisiti extends JPanel {

    public PannelloErroreRequisiti() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "La password non rispetta i requisiti",
                "Errore requisiti",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}

