package GestioneFamiglie;

import java.awt.Container;
import javax.swing.*;
import java.awt.BorderLayout;

public class RegistraFamiglia extends JFrame {

    String titolo = "Registra Famiglia";
    int width = 800;
	int height = 400;

    private JTextField nome;
	private JTextField cognome;
    private JTextField codice;
    private JTextField data;
    private JTextField indirizzo;
    private JTextField bisogni;

	private JButton conferma;
	private JButton chiudi;
    private JFrame frame;
    private JList<String> lista;

    DefaultListModel<String> modelloLista = new DefaultListModel();

    public RegistraFamiglia() {
        this.setTitle(titolo);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.initItems();
        this.setVisible(true);
    }

    private void initItems() {
        
        //Selezione numero componenti *da sistemare*
        JLabel seleziona = new JLabel("Seleziona il numero di componenti");
        
        String numeroComp[]= {"1", "2", "3", "4", "5", "6", "7", "8","9"};
        lista = new JList<>(numeroComp);

        JScrollPane scrollPane = new JScrollPane(lista);

        conferma = new JButton("Conferma", null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        panel.add(seleziona, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        panel.add(conferma,BorderLayout.SOUTH);

        this.setContentPane(panel);
    }

    



    //Schermata per inserire nome, cognome, codice fiscale, data di nascita, indirizzo di residenza e bisogni speciali con Conferma

    //Verifica dei dati nel DBMS

    //while isRegistered==true -> “Uno o più membri sono già presenti nel sistema. Riprovare!”

    //Invio dati al DBMS con pop-up conferma

    //ritorno a schermata principale

}