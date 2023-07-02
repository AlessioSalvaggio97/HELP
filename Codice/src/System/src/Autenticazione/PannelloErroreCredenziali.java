package Autenticazione;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelloErroreCredenziali extends JPanel {

    public PannelloErroreCredenziali() {
        showMessage();
    }

    private void showMessage() {
        JOptionPane.showOptionDialog(
                null,
                "Credenziali inserite non valide",
                "Errore mail o password",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}

}
