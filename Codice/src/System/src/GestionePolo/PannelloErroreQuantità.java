package GestionePolo;

import javax.swing.JOptionPane;

public class PannelloErroreQuantità {
    public PannelloErroreQuantità() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "Quantità inserita non valida. Riprovare!",
                "Errore di inserimento",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}
