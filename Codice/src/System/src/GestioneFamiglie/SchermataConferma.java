package GestioneFamiglie;

import javax.swing.*;

public class SchermataConferma {

    public SchermataConferma(String messaggio) {
        String[] options = {"Chiudi"};
        int choice = JOptionPane.showOptionDialog(null, messaggio, "Schermata Conferma", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.OK_OPTION) {
            // Logica da eseguire dopo la conferma
        }
    }

}
