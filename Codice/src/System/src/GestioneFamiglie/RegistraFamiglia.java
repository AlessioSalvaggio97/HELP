package GestioneFamiglie;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
    private JList<String> lista;
    private List<MembroFamiglia> membriFamiglia;
    List<String> codici = new ArrayList<>();
    private boolean SQLError = false;


    public RegistraFamiglia() {
        this.nome = new JTextField(20);
        this.cognome = new JTextField(20);
        this.codice = new JTextField(20);
        this.data = new JTextField(20);
        this.indirizzo = new JTextField(20);
        this.bisogni = new JTextField(20);

        membriFamiglia = new ArrayList<>();
        this.setTitle(titolo);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.initItems();
        this.setVisible(true);
    }

    private void initItems() {
        
        //Selezione numero componenti
        JLabel seleziona = new JLabel("Seleziona il numero di componenti");
        
        String numeroComp[]= {"1", "2", "3", "4", "5", "6", "7", "8","9"};
        lista = new JList<>(numeroComp);

        conferma = new JButton("Conferma", null);

        conferma.addActionListener(e -> {
            String numeroSelezionato = lista.getSelectedValue();
            inserisciDati(Integer.parseInt(numeroSelezionato));
        });

        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        panel.add(seleziona, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        panel.add(conferma,BorderLayout.SOUTH);

        this.setContentPane(panel);
    }

    private void inserisciDati(int membro) {

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new BoxLayout(panelPrincipale, BoxLayout.Y_AXIS));

        for (int i = 0; i < membro; i++) {

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            JPanel panelMembro = new JPanel(new GridBagLayout());
            

            JLabel istruzioni = new JLabel("Inserisci i dati del membro n."+(i+1));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            panelMembro.add(istruzioni, constraints);

            //Input nome, cognome, codice fiscale, data di nascita, indirizzo di residenza e bisogni speciali
		    JLabel nomelbl = new JLabel("Nome: ");
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            panelMembro.add(nomelbl, constraints);

		    this.nome = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            panelMembro.add(nome, constraints);

		    JLabel cognomelbl = new JLabel("Cognome: ");
		    constraints.gridx = 0;
            constraints.gridy = 2;
            panelMembro.add(cognomelbl, constraints);

            this.cognome = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panelMembro.add(cognome, constraints);
        
            JLabel codicelbl = new JLabel("Codice fiscale: ");
            constraints.gridx = 0;
            constraints.gridy = 3;
            panelMembro.add(codicelbl, constraints);

            this.codice = new JTextField(20);
		    constraints.gridx = 1;
            constraints.gridy = 3;
            panelMembro.add(codice, constraints);

            JLabel datalbl = new JLabel("Data di nascita: ");
		    constraints.gridx = 0;
            constraints.gridy = 4;
            panelMembro.add(datalbl, constraints);

            this.data = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 4;
            panelMembro.add(data, constraints);

            JLabel indirizzolbl = new JLabel("Indirizzo di residenza: ");
            constraints.gridx = 0;
            constraints.gridy = 5;
            panelMembro.add(indirizzolbl, constraints);
            
            this.indirizzo = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 5;
            panelMembro.add(indirizzo, constraints);

            JLabel bisognilbl = new JLabel("Bisogni speciali: ");
            constraints.gridx = 0;
            constraints.gridy = 6;
            panelMembro.add(bisognilbl, constraints);

            this.bisogni = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 6;
            panelMembro.add(bisogni, constraints);

            

            panelPrincipale.add(panelMembro);
            
        }
        

        JScrollPane scrollPane = new JScrollPane(panelPrincipale);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(conferma, BorderLayout.SOUTH);

        this.setContentPane(panel);
        this.revalidate();
        this.repaint();

        //click su Conferma e verifica dei dati col DBMS
        conferma.addActionListener(e -> {

            
            MembroFamiglia membroFamiglia = new MembroFamiglia(nome.getText(), cognome.getText(), codice.getText(),
                data.getText(), indirizzo.getText(), bisogni.getText());

            membriFamiglia.add(membroFamiglia);
            for (MembroFamiglia membro2 : membriFamiglia) {
                String codice = membro2.getCodice();
                codici.add(codice);
            }
            
            boolean isRegistered = verificaDBMS(codici);
            if (isRegistered) {
                JOptionPane.showMessageDialog(this, "Uno o più membri sono già presenti nel sistema. Riprovare!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            if (SQLError) {
                    JOptionPane.showMessageDialog(this, "Errore di connessione o invio dati!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            if (isRegistered==false && SQLError==false) {
                    inviaDatiAlDBMS(membriFamiglia);
                    if(SQLError==false) {
                        UIManager.put("OptionPane.okButtonText", "Chiudi");
                        JOptionPane.showMessageDialog(this, "Famiglia inserita con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        // SchermataPrincipale();
                    }
                }
        });

    }

    private boolean verificaDBMS(List<String> codici) {
        boolean isRegistered = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Creazione della connessione al DBMS
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "donazioni");

            // Preparazione della query di selezione
            StringBuilder queryBuilder = new StringBuilder("SELECT codice_fiscale FROM dbms.componente WHERE codice_fiscale IN (");
            for (int i = 0; i < codici.size(); i++) {
                queryBuilder.append("?");
                    if (i < codici.size() - 1) {
                        queryBuilder.append(",");
                    }
                }
            queryBuilder.append(")");
            String query = queryBuilder.toString();
            statement = connection.prepareStatement(query);

            // Impostazione dei parametri della query con i codici
            for (int i = 0; i < codici.size(); i++) {
                statement.setString(i + 1, codici.get(i));
            }

            // Esecuzione della query di selezione
            resultSet = statement.executeQuery();

            // Verifica se i codici sono presenti
            if (resultSet.next() || SQLError==true) {
                isRegistered = true;
            }

            // Chiusura delle risorse
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            SQLError= true;
            isRegistered = true;
            // Gestione degli errori di connessione o query
        }

        return isRegistered;
    }   


    private void inviaDatiAlDBMS(List<MembroFamiglia> membriFamiglia) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Creazione della connessione al DBMS
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "donazioni");

            // Preparazione della query di inserimento
            String query = "INSERT INTO dbms.componente (codice_fiscale, nome, cognome, data_nascita, indirizzo, bisogni) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            // Inserimento dei dati di ogni membro nella tabella famiglia
            for (MembroFamiglia membro : membriFamiglia) {
                statement.setString(1, membro.getCodice());
                statement.setString(2, membro.getNome());
                statement.setString(3, membro.getCognome());
                statement.setString(4, membro.getData());
                statement.setString(5, membro.getIndirizzo());
                statement.setString(6, membro.getBisogni());
                statement.executeUpdate();
            }

            // Chiusura delle risorse
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore di connessione o invio dati!", "Errore", JOptionPane.ERROR_MESSAGE);
            SQLError = true;
            // Gestione degli errori di connessione o query
        }
    }


    public class MembroFamiglia {
    private String nome;
    private String cognome;
    private String codice;
    private String data;
    private String indirizzo;
    private String bisogni;

    // Costruttore
    public MembroFamiglia(String nome, String cognome, String codice, String data,
            String indirizzo, String bisogni) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice = codice;
        this.data = data;
        this.indirizzo = indirizzo;
        this.bisogni = bisogni;
    }

    // Metodi get e set

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getBisogni() {
        return bisogni;
    }

    public void setBisogni(String bisogni) {
        this.bisogni = bisogni;
    }
    }

}

