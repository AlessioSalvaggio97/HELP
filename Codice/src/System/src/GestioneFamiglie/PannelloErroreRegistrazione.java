package GestioneFamiglie;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelloErroreRegistrazione extends JPanel {

    public PannelloErroreRegistrazione() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "Uno o più membri sono già presenti nel sistema. Riprovare!",
                "Errore di Registrazione",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}
