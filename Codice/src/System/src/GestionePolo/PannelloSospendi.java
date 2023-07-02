package GestionePolo;

import javax.swing.JOptionPane;

public class PannelloSospendi {
    public PannelloSospendi(){
        JOptionPane.showOptionDialog(
                null,
                "Sei sicuro di voler sospendere il polo?",
                "Sospensione polo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new Object[] { "Conferma" },
                "Conferma");
    }
}
