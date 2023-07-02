package GestionePolo;

import javax.swing.*;

public class PannelloRiabilita {

    public PannelloRiabilita(String messaggio, GestoreRiabilitaPolo grp) {
        String[] options = {"Conferma"};
        int choice = JOptionPane.showOptionDialog(null, messaggio, "Schermata Conferma", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.OK_OPTION) {
            System.out.println("Cliccato conferma");
            grp.creaModulo();
        }
    }

}
